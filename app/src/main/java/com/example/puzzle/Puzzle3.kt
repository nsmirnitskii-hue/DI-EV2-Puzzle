package com.example.puzzle

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp


data class  PieceShape( var top: Boolean,var right: Boolean,var botom: Boolean, var left: Boolean )

// Mapa de piezas para tablero 2x2
val pieceMap: List<PieceShape> = listOf(
    // Fila 0, Columna 0
    PieceShape(top = false, right = true, botom = true, left = false),
    // Fila 0, Columna 1
    PieceShape(top = false, right = false, botom = true, left = true),
    // Fila 1, Columna 0
    PieceShape(top = true, right = true, botom = false, left = false),
    // Fila 1, Columna 1
    PieceShape(top = true, right = false, botom = false, left = true)
)

@Composable
fun puzzle3(n: Int ){
    var OffsetX by remember{ mutableStateOf(0.dp) }
    var OffsetY by remember{ mutableStateOf(0.dp) }
    val density = LocalDensity.current
    val color = colorPiezas(n)

    val shape = pieceMap[n]
    divujarBox(modifier = Modifier.size(140.dp)
            .padding(4.dp)
            .offset(x = OffsetX, y = OffsetY)
            .background(colorPiezas(n))
            .pointerInput(Unit) {
                detectDragGestures { change, dragAmount ->
                    change.consume()
                    OffsetX += with(density) { dragAmount.x.toDp() }
                    OffsetY += with(density) { dragAmount.y.toDp() }
                }
            },
        color = colorPiezas(n),
        top = shape.top ,
        right = shape.right,
        botom = shape.botom,
        left = shape.left,
    )
}
