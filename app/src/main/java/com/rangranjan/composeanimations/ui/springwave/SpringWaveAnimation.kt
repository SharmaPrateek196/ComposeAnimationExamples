package com.rangranjan.composeanimations.ui

import android.util.Log
import androidx.compose.animation.core.*
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.*
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rangranjan.composeanimations.ui.AnimationState.End
import com.rangranjan.composeanimations.ui.AnimationState.Start
import com.rangranjan.composeanimations.ui.springwave.Oval3D

@Composable
fun SpringWaveAnimation() {
    val smallestWidth = 27f
    val smallestHeight = 9f

    var currentAnimationState by remember { mutableStateOf(Start)}

    val circle1offsetState = animateDpAsState(
        targetValue = if(currentAnimationState == Start) 600.dp else 300.dp,
        spring(
            dampingRatio = Spring.DampingRatioHighBouncy,
            visibilityThreshold = 170.dp,
            stiffness = 7f
        )
        //animationSpec = tween<Dp>(1000, 0, LinearEasing)
    )

    val circle2offsetState = animateDpAsState(
        targetValue = if(currentAnimationState == Start) 597.5.dp else (297.5).dp,
        spring(dampingRatio = Spring.DampingRatioHighBouncy,
            visibilityThreshold = 150.dp,
            stiffness = 7f),
        //animationSpec = tween<Dp>(1000, 50, LinearEasing)
    )

    val circle3offsetState = animateDpAsState(
        targetValue = if(currentAnimationState == Start) 595.dp else 295.dp,
        spring(dampingRatio = Spring.DampingRatioHighBouncy,
            visibilityThreshold = 130.dp,
            stiffness = 7f)
        //animationSpec = tween<Dp>(1000, 100, LinearEasing)
    )

    val circle4offsetState = animateDpAsState(
        targetValue = if(currentAnimationState == Start) 592.5.dp else (292.5).dp,
        spring(dampingRatio = Spring.DampingRatioHighBouncy,
            visibilityThreshold = 11.dp,
            stiffness = 7f)
        //animationSpec = tween<Dp>(1000, 150, LinearEasing)
    )

    val circle5offsetState = animateDpAsState(
        targetValue = if(currentAnimationState == Start) 589.75.dp else (289.75).dp,
        spring(dampingRatio = Spring.DampingRatioHighBouncy,
            visibilityThreshold = 9.dp,
            stiffness = 7f)
        //animationSpec = tween<Dp>(1000, 200, LinearEasing)
    )

    val circle6offsetState = animateDpAsState(
        targetValue = if(currentAnimationState == Start) 587.dp else 287.dp,
        spring(dampingRatio = Spring.DampingRatioHighBouncy,
            visibilityThreshold = 7.dp,
            stiffness = 7f)
        //animationSpec = tween<Dp>(1000, 250, LinearEasing)
    )

    val circle7offsetState = animateDpAsState(
        targetValue = if(currentAnimationState == Start) 584.dp else 284.dp,
        spring(dampingRatio = Spring.DampingRatioHighBouncy,
            visibilityThreshold = 5.dp,
            stiffness = 7f)
        //animationSpec = tween<Dp>(1000, 300, LinearEasing)
    )

    BoxWithConstraints(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Oval3D(givenSurfaceWidth = smallestWidth, givenSurfaceHeight = smallestHeight, modifier = Modifier.absoluteOffset(this.maxWidth/2, circle1offsetState.value))
        Oval3D(givenSurfaceWidth = smallestWidth*2, givenSurfaceHeight = smallestHeight*2.1f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(13).dp, circle2offsetState.value))
        Oval3D(givenSurfaceWidth = smallestWidth*3, givenSurfaceHeight = smallestHeight*3.2f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(26.5).dp, circle3offsetState.value))
        Oval3D(givenSurfaceWidth = smallestWidth*4, givenSurfaceHeight = smallestHeight*4.4f, modifier = Modifier.absoluteOffset(this.maxWidth/2-40.dp, circle4offsetState.value))
        Oval3D(givenSurfaceWidth = smallestWidth*5, givenSurfaceHeight = smallestHeight*5.6f, modifier = Modifier.absoluteOffset(this.maxWidth/2-53.dp, circle5offsetState.value))
        Oval3D(givenSurfaceWidth = smallestWidth*6, givenSurfaceHeight = smallestHeight*6.9f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(67).dp, circle6offsetState.value))
        Oval3D(givenSurfaceWidth = smallestWidth*7, givenSurfaceHeight = smallestHeight*8.2f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(80).dp, circle7offsetState.value))


        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentSize(align = Alignment.Center),
            onClick = {
                if(currentAnimationState == Start) {
                    currentAnimationState = End
                }
                else {
                    currentAnimationState = Start
                }
            }) {
            Text(text = "Trigger the Magic")
        }
    }
}

enum class AnimationState {
    Start, End
}


@Preview(showBackground = true, showSystemUi = true)
@Composable
fun SpringWaveAnimPrev() {
    SpringWaveAnimation()
}