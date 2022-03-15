package com.rangranjan.composeanimations

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.HorizontalPagerIndicator
import com.google.accompanist.pager.rememberPagerState
import com.rangranjan.composeanimations.ui.SpringWaveAnimation
import com.rangranjan.composeanimations.ui.alarm.AlarmAnimation
import com.rangranjan.composeanimations.ui.theme.ComposeAnimationsTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalPagerApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ComposeAnimationsTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    val pagerState = rememberPagerState()
                    Column(modifier = Modifier.fillMaxSize()) {
                        HorizontalPager(
                            modifier = Modifier
                                .weight(1f)
                                .fillMaxWidth(),
                            count = 2, state = pagerState,
                        ) { page ->
                            // Our page content
                            when (page) {
                                0 -> {
                                    SpringWaveAnimation()
                                }
                                1 -> {
                                    AlarmAnimation()
                                }
                            }
                        }
                        HorizontalPagerIndicator(
                            pagerState = pagerState,
                            modifier = Modifier
                                .align(Alignment.CenterHorizontally)
                                .padding(16.dp),
                        )
                    }
                }
            }
        }
    }
}