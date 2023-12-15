# Descripción General
Este código implementa unA VERSION DE JUEGO DE Simon Dice En el juego, se presenta una secuencia de colores y el jugador debe repetir la secuencia correctamente. El código está estructurado en funciones Compose que definen la interfaz de usuario y las interacciones del juego.

# Estructura del Código
El código está dividido en varias funciones Compose, cada una encargada de renderizar una parte específica de la interfaz de usuario o de manejar una acción del juego.

# Greeting
La función Greeting define la estructura principal de la interfaz de usuario. Contiene componentes como la Ronda, el Record, la Botonera y los botones de inicio y envío.

# Record y Ronda
Las funciones Record y Ronda son responsables de mostrar el número de récord y el número de ronda actual, respectivamente. Estos números se obtienen de un objeto Data que almacena el estado del juego.

    @Composable
    fun  Record(){
    Text(
        text = "RECORD: ${Data.record.value} ", // Mostrar el número de ronda
        color = Color.Black,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    )
    }
    @Composable
    fun Ronda() {
    Text(
        text = "RONDA: ${Data.round.value} ", // Mostrar el número de ronda
        color = Color.Black,
        fontWeight = FontWeight.ExtraBold,
        fontSize = 20.sp
    )

  }



# Botonera
La función Botonera renderiza una disposición de botones de colores, utilizando la clase Button de Compose. Los colores se dividen en dos filas y se manejan mediante la función Boton.

    @Composable
    fun Botonera(vModel: VModel) {
    val colorsInTwoRows = Data.Colors.values().toList().chunked(2)

    Column(
        modifier = Modifier.padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        colorsInTwoRows.forEach { rowColors ->
            Row {
                rowColors.forEach { color ->
                    Spacer(modifier = Modifier.width(8.dp))
                    Boton(color = color.color, miModel = vModel, name = color.colorName)
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
        }
    }
}

#Boton
La función Boton representa un botón de color. Al hacer clic en un botón, se verifica el estado del juego y se ejecutan acciones según el estado actual, como guardar la selección del usuario y cambiar el color del botón.

    @Composable
    fun Boton(color: MutableState<Color>, miModel: VModel, name: String) {

    Button(
        onClick = {
            //Recogemos el color que hemos pulsado
            // miModel.aumentarSecuenciaUsuario(Data.colors.indexOf(color))
            if (Data.state == Data.State.WAITING && miModel.buttonsEnabled) {
                miModel.guardarSecuenciaUsuario(Data.colors.indexOf(color))
                miModel.cambiaColorBotonAlPulsar(color)
            }
        },
        modifier = Modifier
            .padding(10.dp)
            .size(150.dp)
            .padding(8.dp), // Añadir padding para que se vean mejor los bordes redondeados
        shape = androidx.compose.foundation.shape.RoundedCornerShape(20.dp), // Agregar bordes redondeados

        colors = ButtonDefaults.buttonColors(color.value)
    ) {
        Text(
            text = name,
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
      }
    }

# StartButton y Enviar
Las funciones StartButton y Enviar representan botones para iniciar el juego y enviar la secuencia del jugador, respectivamente. Estos botones también verifican el estado actual del juego y ejecutan acciones correspondientes.

    @Composable
    fun StartButton(miModel: VModel) {
    //Declaramos un Boton
    Button(
        onClick = {
            miModel.startGame()
            miModel.changeState()
            if (Data.state == Data.State.SEQUENCE ){
                miModel.generarSecuencia()
                miModel.changeState()
            }else{
                miModel.startGame()
            }

        },
        modifier = Modifier
        // Aumentar ligeramente el tamaño del botón
    ) {
        Text(
            text = "START",
            color = Color.Black,
            fontWeight = FontWeight.ExtraBold,
            fontSize = 20.sp
        )
    }
}

# Modelo y Lógica de Juego
El código utiliza un modelo (VModel) para manejar la lógica del juego, incluyendo la generación de secuencias, el seguimiento de la secuencia del jugador y el cambio de estados del juego.

# Consideraciones Importantes
Se utiliza el patrón de arquitectura de Compose para estructurar la interfaz de usuario.
La lógica del juego está separada en un modelo (VModel) para mantener un código más limpio y organizado.
El código hace uso de las funcionalidades de Compose para diseñar la interfaz de usuario de manera declarativa y reactiva.
