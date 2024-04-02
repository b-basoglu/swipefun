package com.bbasoglu.swipefun.matchmaker.feed.composeui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.bbasoglu.swipefun.matchmaker.feed.composeui.FeedScreenComposeFragmentViewModel
import com.bbasoglu.swipefun.matchmaker.feed.composeui.screen.FeedComposeScreen

@Composable
fun NavGraph(fragmentViewModel: FeedScreenComposeFragmentViewModel) {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = FeedScreen.FeedComposeScreen.route,
    ) {

        composable(route = FeedScreen.FeedComposeScreen.route) {
            FeedComposeScreen(
                fragmentViewModel = fragmentViewModel,
                navController = navController
            )
        }
    }
}