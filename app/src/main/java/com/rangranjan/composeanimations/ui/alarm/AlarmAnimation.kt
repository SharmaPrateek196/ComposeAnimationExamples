package com.rangranjan.composeanimations.ui.alarm

import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rangranjan.composeanimations.R
import com.rangranjan.composeanimations.ui.alarm.AnimationState.Idle
import com.rangranjan.composeanimations.ui.alarm.AnimationState.Running
import kotlinx.coroutines.launch

@Composable
fun AlarmAnimation() {

    var noiseAnimationState by remember {
        mutableStateOf(Idle)
    }

    val leftNoiseOffsetState by animateOffsetAsState(
        targetValue = if(noiseAnimationState == Running) Offset(-54f, -45f) else Offset(-34f, -25f),
        animationSpec = tween(1200, easing = FastOutSlowInEasing),
    ) {
        noiseAnimationState = Idle
    }

    val rightNoiseOffsetState by animateOffsetAsState(
        targetValue = if(noiseAnimationState == Running) Offset(120f, -43f) else Offset(100f, -23f),
        animationSpec = tween(1200, easing = FastOutSlowInEasing),
    ) {
        noiseAnimationState = Idle
    }
    
    val watchFaceRotationAnimation = remember {
        Animatable(0f)
    }

    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BoxWithConstraints {
            Image(
                painter = painterResource(id = R.drawable.alarm),
                contentDescription = "Alarm watch",
                modifier = Modifier
                    .size(120.dp)
                    .align(Alignment.Center)
                    .rotate(watchFaceRotationAnimation.value)
            )


            Image(
                painter = painterResource(id = R.drawable.alarm_noise_left),
                contentDescription = "noise",
                modifier = Modifier
                    .size(50.dp)
                    .offset(leftNoiseOffsetState.x.dp, leftNoiseOffsetState.y.dp)
            )

            Image(
                painter = painterResource(id = R.drawable.alarm_noise_right),
                contentDescription = "noise",
                modifier = Modifier
                    .size(50.dp)
                    .offset(rightNoiseOffsetState.x.dp, rightNoiseOffsetState.y.dp)
            )
        }

        Box(modifier = Modifier.height(140.dp))

        Button(
            onClick = {
                noiseAnimationState = if (noiseAnimationState == Idle) Running else Idle

                coroutineScope.launch {
                    watchFaceRotationAnimation.animateTo(
                        targetValue = 0f,
                        animationSpec = watchRotationAnimSpec,
                        initialVelocity = .5f
                    )
                }
            }
        ) {
            Text(text = "Trigger Animation")
        }
    }
}

val watchRotationAnimSpec = keyframes<Float> {
    durationMillis = 1800
    -45f at 200 with LinearEasing
    45f at 500 with LinearEasing
    -30f at 950 with LinearEasing
    30f at 1200 with LinearEasing
    -15f at 1450 with LinearEasing
    15f at 1600 with LinearEasing
    -5f at 1700 with LinearEasing
    5f at 1750 with LinearEasing
    0f at 1800 with LinearEasing
}

enum class AnimationState {
    Idle, Running
}

@Preview(showSystemUi = true)
@Composable
fun AlarmAnimationPrev() {
    AlarmAnimation()
}