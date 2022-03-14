package com.rangranjan.composeanimations.ui.alarm

import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.keyframes
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rangranjan.composeanimations.R
import com.rangranjan.composeanimations.ui.alarm.AnimationState.Idle
import com.rangranjan.composeanimations.ui.alarm.AnimationState.Running
import kotlinx.coroutines.launch

@Composable
fun AlarmAnimation() {

    var animationState by remember {
        mutableStateOf(Idle)
    }
    
    val watchFaceRotationAnimation = remember {
        Animatable(0f)
    }

    LaunchedEffect(key1 = animationState) {
        watchFaceRotationAnimation.animateTo(
            targetValue = 0f,
            animationSpec = watchRotationAnimSpec
        )
    }

    BoxWithConstraints(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.alarm),
            contentDescription = "Alarm watch",
            modifier = Modifier
                .size(120.dp)
                .align(Alignment.Center)
                .rotate(watchFaceRotationAnimation.value)
                .clickable {
                    animationState = if(animationState==Idle) Running else Idle
                }
        )
        
    }
}

val watchRotationAnimSpec = keyframes<Float> {
    durationMillis = 1800
    -45f at 200
    45f at 500
    -30f at 900
    30f at 1200
    -15f at 1400
    15f at 1600
    -5f at 1700
    5f at 1750
    0f at 1800
}

enum class AnimationState {
    Idle, Running
}

@Preview
@Composable
fun AlarmAnimationPrev() {
    AlarmAnimation()
}