package com.park.quest.animations

import androidx.compose.animation.*
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.TweenSpec
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset

/**
 * Created by Nirbhay Pherwani on 7/25/2023.
 */
@Composable
fun SlideAnimation(visible: Boolean, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = slideIn(
            initialOffset = { IntOffset(0, 0) },
            animationSpec = tween(durationMillis = 500)
        ),
        exit = slideOut(
            targetOffset = {  fullWidth -> IntOffset(fullWidth.width , 0) }
        )
    ) {
        content()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun SmallScaleAnimation(visible: Boolean, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(
            initialScale = 0F,
            animationSpec = tween(durationMillis = 350)
        ),
        exit = scaleOut(
            targetScale = 1F,
            animationSpec = tween(durationMillis = 350)
        )
    ) {
        content()
    }
}

@OptIn(ExperimentalAnimationApi::class)
@Composable
fun ScaleAnimation(visible: Boolean, content: @Composable () -> Unit) {
    AnimatedVisibility(
        visible = visible,
        enter = scaleIn(
            initialScale = 4F,
            animationSpec = tween(durationMillis = 350)
        ),
        exit = scaleOut(
            targetScale = 1F,
            animationSpec = tween(durationMillis = 350)
        )
    ) {
        content()
    }
}