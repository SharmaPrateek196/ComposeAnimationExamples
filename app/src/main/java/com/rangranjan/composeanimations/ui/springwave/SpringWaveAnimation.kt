package com.rangranjan.composeanimations.ui

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.*
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import com.rangranjan.composeanimations.ui.springwave.Oval3D

@Composable
fun SpringWaveAnimation() {

    val smallestWidth = 27f
    val smallestHeight = 9f

    BoxWithConstraints(
        modifier = Modifier.background(Color.Black).fillMaxSize()
    ) {

        Oval3D(givenSurfaceWidth = smallestWidth, givenSurfaceHeight = smallestHeight, modifier = Modifier.absoluteOffset(this.maxWidth/2, 600.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*2, givenSurfaceHeight = smallestHeight*2.1f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(13).dp, (597.5).dp))
        Oval3D(givenSurfaceWidth = smallestWidth*3, givenSurfaceHeight = smallestHeight*3.2f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(26.5).dp, (595).dp))
        Oval3D(givenSurfaceWidth = smallestWidth*4, givenSurfaceHeight = smallestHeight*4.4f, modifier = Modifier.absoluteOffset(this.maxWidth/2-40.dp, (592.5).dp))
        Oval3D(givenSurfaceWidth = smallestWidth*5, givenSurfaceHeight = smallestHeight*5.6f, modifier = Modifier.absoluteOffset(this.maxWidth/2-53.dp, (589.75).dp))
        Oval3D(givenSurfaceWidth = smallestWidth*6, givenSurfaceHeight = smallestHeight*6.9f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(67).dp, (587).dp))
        Oval3D(givenSurfaceWidth = smallestWidth*7, givenSurfaceHeight = smallestHeight*8.2f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(80).dp, (584).dp))

    }

}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SpringWaveAnimPrev() {
    SpringWaveAnimation()
}