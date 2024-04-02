package com.bbasoglu.swipefun.matchmaker.feed.composeui.navigation

sealed class FeedScreen(val route: String) {
    object FeedComposeScreen : FeedScreen(ConstantAppScreenName.FEED_SCREEN)
}


object ConstantAppScreenName {
    const val FEED_SCREEN = "feed_screen"
}