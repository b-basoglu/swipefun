package com.bbasoglu.swipefun.matchmaker.feed.composeui.screen.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.bbasoglu.swipefun.matchmaker.feed.composeui.model.FeedData
import com.bbasoglu.swipefun.matchmaker.feed.composeui.util.ColorTransparent
import com.bbasoglu.swipefun.matchmaker.feed.composeui.util.ColorTransparent70

@Composable
fun FeedItemCard(
    modifier: Modifier,
    feedData: FeedData,
) {
    Card(modifier) {
        Box {
            Image(contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxSize(),
                painter = rememberAsyncImagePainter(feedData.image),
                contentDescription = null)
            val brush = Brush.verticalGradient(listOf(ColorTransparent, ColorTransparent70))
            Column(
                modifier = Modifier.align(Alignment.BottomStart).fillMaxWidth()
                    .background(brush = brush)
            ) {
                Text(
                    text = feedData.status ?: "",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 16.dp)
                )
                Text(
                    text = feedData.name ?: "",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 26.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(vertical = 2.dp, horizontal = 16.dp)

                )
                Text(
                    text = feedData.location?.name ?: "",
                    color = MaterialTheme.colorScheme.onPrimary,
                    fontSize = 22.sp,
                    fontWeight = FontWeight.Medium,
                    modifier = Modifier.padding(PaddingValues(start = 16.dp,top = 2.dp,bottom= 16.dp, end =16.dp))
                )
            }
        }
    }
}