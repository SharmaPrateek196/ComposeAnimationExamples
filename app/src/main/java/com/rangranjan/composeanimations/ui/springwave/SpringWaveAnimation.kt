package com.rangranjan.composeanimations.ui

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
import androidx.compose.ui.unit.dp
import com.rangranjan.composeanimations.ui.AnimationState.Start
import com.rangranjan.composeanimations.ui.springwave.Oval3D
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@Composable
fun SpringWaveAnimation() {
    val smallestWidth = 27f
    val smallestHeight = 9f

    var currentAnimationState by remember { mutableStateOf(Start)}

    val circle1offsetState = remember {
        Animatable(600f)
    }

    val circle2offsetState = remember {
        Animatable(597.5f)
    }

    val circle3offsetState = remember {
        Animatable(595f)
    }

    val circle4offsetState = remember {
        Animatable(592.5f)
    }

    val circle5offsetState = remember {
        Animatable(589.75f)
    }

    val circle6offsetState = remember {
        Animatable(587f)
    }

    val circle7offsetState = remember {
        Animatable(584f)
    }

    val coroutineScope = rememberCoroutineScope()

    BoxWithConstraints(
        modifier = Modifier
            .background(Color.Black)
            .fillMaxSize()
    ) {
        Oval3D(givenSurfaceWidth = smallestWidth, givenSurfaceHeight = smallestHeight, modifier = Modifier.absoluteOffset(this.maxWidth/2, circle1offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*2, givenSurfaceHeight = smallestHeight*2.1f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(13).dp, circle2offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*3, givenSurfaceHeight = smallestHeight*3.2f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(26.5).dp, circle3offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*4, givenSurfaceHeight = smallestHeight*4.4f, modifier = Modifier.absoluteOffset(this.maxWidth/2-40.dp, circle4offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*5, givenSurfaceHeight = smallestHeight*5.6f, modifier = Modifier.absoluteOffset(this.maxWidth/2-53.dp, circle5offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*6, givenSurfaceHeight = smallestHeight*6.9f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(67).dp, circle6offsetState.value.dp))
        Oval3D(givenSurfaceWidth = smallestWidth*7, givenSurfaceHeight = smallestHeight*8.2f, modifier = Modifier.absoluteOffset(this.maxWidth/2-(80).dp, circle7offsetState.value.dp))

        Button(
            modifier = Modifier
                .align(Alignment.BottomCenter)
                .wrapContentSize(align = Alignment.Center),
            onClick = {

                coroutineScope.launch {

                    /*
                    * Need to launch these coroutines in different different
                    * scopes because we want that they all run simultaneously
                    * with there own delays from the global starting time.
                    *
                    * If we would have used these all animateTo() methods in
                    * a single coroutine scope then they would run sequentially
                    * one after another; means that the second ring would start moving
                    * only after the damping effect of the first ring completes.
                    * */

                    launch {
                        circle1offsetState.animateTo(300f, spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
                    }
                    launch {
                        delay(40)
                        circle2offsetState.animateTo(297.5f, spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
                    }
                    launch {
                        delay(90)
                        circle3offsetState.animateTo(295f, spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
                    }
                    launch {
                        delay(130)
                        circle4offsetState.animateTo(292.5f, spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
                    }
                    launch {
                        delay(170)
                        circle5offsetState.animateTo(289.75f, spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
                    }
                    launch {
                        delay(210)
                        circle6offsetState.animateTo(287f, spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
                    }
                    launch {
                        delay(250)
                        circle7offsetState.animateTo(284f, spring(Spring.DampingRatioHighBouncy, Spring.StiffnessVeryLow))
                    }
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