package com.example.showplaceproject.presentation.media.videos

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.example.showplaceproject.presentation.media.FullVideoPlayer
import com.example.showplaceproject.presentation.theme.Typography

@Composable
fun VideosScreen(videos: List<String>) {
    Box(modifier = Modifier.fillMaxSize()) {

        Column {
            Text(
                text = "Видеогалерея",
                style = Typography.h1,
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 33.dp)
                    .padding(horizontal = 24.dp)
            )


            LazyColumn(
                modifier = Modifier.padding(start = 16.dp),
            ) {
                items(videos.size) { index ->
                    FullVideoPlayer(
                        videos[index],
                        Modifier
                            .fillMaxWidth()
                            .padding(bottom = 32.dp, end = 16.dp)
                            .aspectRatio(1.6f)
                            .height(193.dp)
                            .clip(RoundedCornerShape(12.dp))
                    )
                }
            }
        }

    }
}