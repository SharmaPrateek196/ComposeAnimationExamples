package com.rangranjan.composeanimations.ui.happyholi

import androidx.annotation.DrawableRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.*
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.rangranjan.composeanimations.R
import kotlinx.coroutines.launch

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
        Animatable(15f)
    }

    val animatedHoliWishSize = remember {
        Animatable(0f)
    }

    val animatedWaterWorksSize = remember {
        Animatable(0f)
    }

    var sizeState by remember {
        mutableStateOf(AnimationState.Start)
    }

    LaunchedEffect(key1 = sizeState) {
        launch {
            animatedBalloonSize.animateTo(
                targetValue = if(sizeState==AnimationState.Start) 150f else 5f,
                animationSpec = if (sizeState==AnimationState.Start) {
                    tween(BALLOON_SIZE_TWEEN_DURATION)
                } else {
                    snap()
                }
            )
        }

        launch {
            animatedHoliWishSize.animateTo(
                targetValue = if (sizeState==AnimationState.Start) 1f else 0f,
                animationSpec = if (sizeState==AnimationState.Start) {
                    tween(800, BALLOON_SIZE_TWEEN_DURATION-1400, LinearOutSlowInEasing)
                } else {
                    snap()
                }
            )
        }

        launch {
            animatedWaterWorksSize.animateTo(
                targetValue = if (sizeState==AnimationState.Start) 120f else 0f,
                animationSpec = if (sizeState==AnimationState.Start) {
                    tween(800, BALLOON_SIZE_TWEEN_DURATION-700, LinearOutSlowInEasing)
                } else {
                    snap()
                }
            )
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Box(
            modifier = Modifier
                .align(Alignment.Center)
                .size(300.dp),
            contentAlignment = Alignment.Center
        ) {
            Box(
                modifier = Modifier
                    .animateContentSize()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.balloon),
                    contentDescription = "balloon",
                    modifier = Modifier
                        .size(animatedBalloonSize.value.dp)
                        .padding(8.dp)
                        .absoluteOffset(shiveringOffset.dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.holi_wish),
                    contentDescription = "balloon",
                    modifier = Modifier
                        .padding(8.dp)
                        .fillMaxSize(animatedHoliWishSize.value)
                        .align(Alignment.Center)
                )
            }

            WaterWork(R.drawable.water3, animatedWaterWorksSize, -100.dp, -250.dp, Modifier.align(Alignment.Center))
            WaterWork(R.drawable.water4, animatedWaterWorksSize, 100.dp, -300.dp, Modifier.align(Alignment.Center))
            WaterWork(R.drawable.water1, animatedWaterWorksSize, 80.dp, -130.dp, Modifier.align(Alignment.Center))
            WaterWork(R.drawable.water4, animatedWaterWorksSize, -80.dp, 200.dp, Modifier.align(Alignment.Center))
            WaterWork(R.drawable.water3, animatedWaterWorksSize, 80.dp, 130.dp, Modifier.align(Alignment.Center))
            WaterWork(R.drawable.water1, animatedWaterWorksSize, 100.dp, 270.dp, Modifier.align(Alignment.Center))
        }

        Button(onClick = {
            sizeState = if(sizeState == AnimationState.Start) AnimationState.End else AnimationState.Start
        },
            Modifier.align(Alignment.BottomCenter)
        ) {
            Text(text = "Toggle Animation")
        }
    }
}

@Composable
fun WaterWork(
    @DrawableRes image: Int,
    animatedWaterWorksSize: Animatable<Float, AnimationVector1D>,
    x: Dp, y: Dp,
    modifier: Modifier
) {
    Image(
        painter = painterResource(id = image),
        contentDescription = "balloon",
        modifier = modifier
            .size(animatedWaterWorksSize.value.dp)
            .absoluteOffset(x, y)
    )
}

enum class AnimationState {
    Start, End
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun ThisPreview() {
    HappyHoliAnimation()
}