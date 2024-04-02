package com.bbasoglu.swipefun.matchmaker.feed.composeui.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.bbasoglu.swipefun.matchmaker.feed.composeui.FeedScreenComposeFragmentViewModel
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.ui.geometry.Offset
import androidx.constraintlayout.compose.ChainStyle
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import com.bbasoglu.swipefun.matchmaker.feed.composeui.screen.ui.CircleButton
import com.bbasoglu.swipefun.matchmaker.feed.composeui.screen.ui.FeedItemCard
import com.bbasoglu.swipefun.matchmaker.feed.composeui.screen.ui.swipecard.Direction
import com.bbasoglu.swipefun.matchmaker.feed.composeui.screen.ui.swipecard.rememberSwipeAbleCardState
import com.bbasoglu.swipefun.matchmaker.feed.composeui.screen.ui.swipecard.swipeAbleCard
import kotlinx.coroutines.launch

@Composable
fun FeedComposeScreen(
    fragmentViewModel: FeedScreenComposeFragmentViewModel,
    navController: NavController,
    viewModel: FeedScreenComposeViewModel = hiltViewModel()
) {
    val moviePagingItems by viewModel.feedState.collectAsState()
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        ConstraintLayout(
            modifier = Modifier.fillMaxSize()
        ) {
            val (images, buttons,loading) = createRefs()
            createVerticalChain(images,buttons, chainStyle = ChainStyle.Packed)
            CircularProgressIndicator(
                modifier = Modifier.constrainAs(loading) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
            )
            val states = moviePagingItems.reversed()
                .map { it to rememberSwipeAbleCardState() }

            val scope = rememberCoroutineScope()
            Box(
                Modifier
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .constrainAs(images) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(buttons.top)
                        height = Dimension.fillToConstraints
                    }
            ) {
                states.forEachIndexed { index, (matchProfile, state) ->
                    matchProfile?.let {feedData ->
                        if (state.swipedDirection == null) {
                            FeedItemCard(
                                modifier = Modifier
                                    .fillMaxSize()
                                    .swipeAbleCard(
                                        state = state,
                                        blockedDirections = listOf(Direction.Down, Direction.Up),
                                        onSwiped = {

                                        },
                                        onSwipeCancel = {

                                        }
                                    ),
                                feedData = feedData
                            )
                        }
                        LaunchedEffect(matchProfile, state.swipedDirection) {
                            if (state.swipedDirection == Direction.Right) {
                                viewModel.insertRickMortyLike(matchProfile)
                                if (index == 0){
                                    viewModel.getCharacters()
                                }
                            }else if (state.swipedDirection == Direction.Left){
                                if (index ==0){
                                    viewModel.getCharacters()
                                }
                            }

                        }
                    }
                }
            }
            Row(
                modifier = Modifier
                    .padding(vertical = 10.dp)
                    .constrainAs(buttons) {
                        top.linkTo(images.bottom)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        bottom.linkTo(parent.bottom)
                    },
                horizontalArrangement = Arrangement.Center
            ) {
                CircleButton(
                    onClick = {
                        scope.launch {
                            val last = states.reversed()
                                .firstOrNull {
                                    it.second.offset.value == Offset(0f, 0f)
                                }?.second
                            last?.swipe(Direction.Left)
                        }
                    },
                    icon = Icons.Rounded.Close
                )
                Spacer(modifier = Modifier.width(16.dp))
                CircleButton(
                    onClick = {
                        scope.launch {
                            val last = states.reversed()
                                .firstOrNull {
                                    it.second.offset.value == Offset(0f, 0f)
                                }?.second

                            last?.swipe(Direction.Right)
                        }
                    },
                    icon = Icons.Rounded.Favorite
                )
            }
        }
    }
}



