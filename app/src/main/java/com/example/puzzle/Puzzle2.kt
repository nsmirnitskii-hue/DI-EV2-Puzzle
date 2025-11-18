package com.example.puzzle

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun Puzzle2(n: Int ){
    var OffsetX by remember{ mutableStateOf(0.dp) }
    var OffsetY by remember{ mutableStateOf(0.dp) }
    val density = LocalDensity.current
    divujarBox(
        modifier = Modifier.size(140.dp)
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
        top = n==0 ,
        right = n==1,
        botom = n==2,
        left = n==3,
    )
}
@Composable
fun divujarBox(modifier: Modifier, color: Color, top: Boolean, right: Boolean, botom: Boolean, left: Boolean){
    Canvas(modifier) {
        val w = size.width
        val h = size.height
        val knobSize = w * 0.2f //tamaño de la cabeza del puzzle (20% de ancho)
        val path = Path().apply { //Patch() para dibujar el borde
            moveTo(0f, 0f)
            // borde superior
            if (top){
                lineTo(w * 0.4f, 0f)
                cubicTo(
                    w * 0.45f, -knobSize,
                    w * 0.55f, -knobSize,
                    w * 0.6f, 0f
                )
                lineTo(w, 0f)
            }else{
                lineTo(w, 0f)
            }
            // borde derecho
            if (right) {
                lineTo(w, h * 0.4f)
                cubicTo(
                    w + knobSize, h * 0.45f,
                    w + knobSize, h * 0.55f,
                    w, h * 0.6f
                )
                lineTo(w, h)
            } else {
                lineTo(w, h)
            }

            // borde inferior
            if (botom) {
                lineTo(w * 0.6f, h)
                cubicTo(
                    w * 0.55f, h + knobSize,
                    w * 0.45f, h + knobSize,
                    w * 0.4f, h
                )
                lineTo(0f, h)
            } else {
                lineTo(0f, h)
            }

            // borde izquierdo
            if (left) {
                lineTo(0f, h * 0.6f)
                cubicTo(
                    -knobSize, h * 0.55f,
                    -knobSize, h * 0.45f,
                    0f, h * 0.4f
                )
                lineTo(0f, 0f)
            } else {
                lineTo(0f, 0f)
            }

            close()
        }
        drawPath(path, color)
    }
}
