package ui

import androidx.compose.animation.*
import androidx.compose.animation.core.tween
import androidx.navigation.NavBackStackEntry


val enterT: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
    slideIntoContainer(
        AnimatedContentTransitionScope.SlideDirection.Left,
        animationSpec = tween(700)
    )
}

val exitT: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
    fadeOut(
        tween(2000)
    )
}

val popExitT: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> ExitTransition) = {
    slideOutOfContainer(
        AnimatedContentTransitionScope.SlideDirection.Right,
        animationSpec = tween(700)
    )
}

val popEnterT: (AnimatedContentTransitionScope<NavBackStackEntry>.() -> EnterTransition) = {
    fadeIn()
}