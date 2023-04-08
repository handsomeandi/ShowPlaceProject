package com.example.showplaceproject.presentation.media.photo

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberImagePainter
import com.example.showplaceproject.presentation.theme.Typography

@Composable
fun PhotosScreen(photos: List<String>) {
    var selectedPhoto: String? by remember { mutableStateOf(null) }
    Box(modifier = Modifier.fillMaxSize()) {

        Column {
            Text(
                text = "Фотогалерея",
                style = Typography.h1,
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 33.dp)
                    .padding(horizontal = 24.dp)
            )


            LazyVerticalGrid(
                modifier = Modifier.padding(start = 16.dp),
                columns = GridCells.Fixed(3)
            ) {
                items(photos.size) { index ->
                    Image(
                        painter = rememberImagePainter(
                            data = photos[index],
                        ),
                        contentScale = ContentScale.Crop,
                        contentDescription = null,
                        modifier = Modifier
                            .height(107.dp)
                            .width(107.dp)
                            .padding(end = 22.dp, bottom = 22.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .clickable {
                                selectedPhoto = photos[index]
                            }
                    )
                }
            }
        }
        if (selectedPhoto != null) {
            Dialog(
                onDismissRequest = { selectedPhoto = null },
                properties = DialogProperties(usePlatformDefaultWidth = false)
            ) {
                Image(
                    painter = rememberImagePainter(
                        data = selectedPhoto,
                    ),
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxSize(0.9f)
                )
            }
        }
    }
}