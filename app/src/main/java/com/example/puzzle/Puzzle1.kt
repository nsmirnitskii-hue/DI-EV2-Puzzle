package com.example.puzzle

import android.util.Log
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp

@Composable
fun puzzle1(){
    LazyColumn(modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally) {
        item {
        for (i in 1..2) {
            Row() {
                for (j in 1..2) {
                    Box(
                        modifier = Modifier.size(140.dp)
                            .padding(4.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .background(Color.LightGray)
                    )
                }
            }
        }
            Spacer(modifier = Modifier.height(40.dp))
            listOf(0,1,2,3).shuffled().forEach(){el->//para barajear las piezas
                    Row() {
                        //Piezas(it)
                        Log.d("PuzleN",el.toString())
                        Piezas2(el)
                    }
            }

        }

        }
    }


@Composable
fun Piezas(n: Int ){
  var OffsetX by remember{ mutableStateOf(0.dp) }
  var OffsetY by remember{ mutableStateOf(0.dp) }
    val density = LocalDensity.current
    Box(modifier = Modifier.size(140.dp)
        .padding(4.dp)
        .offset(x=OffsetX, y=OffsetY)
        .clip(RoundedCornerShape(8.dp))
        .background(colorPiezas(n))
        .pointerInput(Unit){
            detectDragGestures { change, dragAmount ->
                change.consume()
                OffsetX += with(density){dragAmount.x.toDp()}
                OffsetY += with(density){dragAmount.y.toDp()}
            }
        })
}

fun colorPiezas(n: Int): Color {
    if (n == 0) {
        return Color.Black
    } else if (n == 1) {
        return Color.Yellow
    } else if (n == 2) {
        return Color.Blue
    } else if (n == 3) {
        return Color.Red
    }
    return Color.Transparent
}


