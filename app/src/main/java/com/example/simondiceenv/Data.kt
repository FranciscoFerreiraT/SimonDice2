package com.example.simondiceenv



import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.graphics.Color

object Data {
    var round = mutableStateOf(0);
    var secuence = mutableListOf<Int>();
    var secuenceUser = mutableListOf<Int>();
    var record = mutableStateOf(0);
    var state = State.START;
    var colors = listOf(
        Colors.ROJO.color,
        Colors.AZUL.color,
        Colors.AMARILLO.color,
        Colors.VERDE.color
    )

    var numColors = Colors.values()


    var colorPath: Color = Color.White


    enum class State {
        START, // Estado inicial del juego
        SEQUENCE, // Mostrando la secuencia de colores
        WAITING, // Esperando la interacción del usuario
        CHECKING, // Comprobando la secuencia del usuario
        FINISHED // Juego finalizado
    }



    enum class Colors(val color: MutableState<Color>,  val colorName: String) {
        ROJO(mutableStateOf(Color.Red), "ROJO"),
        AZUL(color = mutableStateOf(Color.Blue), "AZUL"),
        AMARILLO(color = mutableStateOf(Color.Yellow), "AMARILLO"),
        VERDE(color = mutableStateOf(Color.Green), "VERDE"),

    }


}