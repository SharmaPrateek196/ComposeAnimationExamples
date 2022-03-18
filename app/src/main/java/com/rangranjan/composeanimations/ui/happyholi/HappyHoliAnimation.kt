package com.rangranjan.composeanimations.ui.happyholi

import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.rangranjan.composeanimations.R
import com.rangranjan.composeanimations.ui.AnimationState

@Composable
fun HappyHoliAnimation() {

    val BALLOON_SIZE_TWEEN_DURATION = 3000

    val infiniteTransition = rememberInfiniteTransition()
    val shiveringOffset by infiniteTransition.animateFloat(
        initialValue = -2f,
        targetValue = 2f,
        animationSpec = infiniteRepeatable(
            animation = tween(50, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )

    val animatedBalloonSize = remember {
        Animatable(5f)
    }

    var sizeState by remember {
        mutableStateOf(AnimationState.Start)
    }

    LaunchedEffect(key1 = sizeState) {
        animatedBalloonSize.animateTo(
            targetValue = if(sizeState==AnimationState.Start) 150f else 5f,
            animationSpec = tween(BALLOON_SIZE_TWEEN_DURATION)
        )
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(300.dp).background(Color.Yellow),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier.absoluteOffset(shiveringOffset.dp).background(Color.Cyan).animateContentSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.balloon),
                    contentDescription = "balloon",
                    modifier = Modifier
                        .size(animatedBalloonSize.value.dp)
                )
            }
        }

        Button(onClick = {
            sizeState = if(sizeState == AnimationState.Start) AnimationState.End else AnimationState.Start
        },
            Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Click")
        }


    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ThisPreview() {
    HappyHoliAnimation()
}