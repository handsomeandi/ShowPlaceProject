package com.example.showplaceproject.presentation.media.text

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.showplaceproject.domain.TextModel
import com.example.showplaceproject.presentation.theme.AudioTextColor
import com.example.showplaceproject.presentation.theme.Typography

@Composable
fun TextScreen(texts: List<TextModel>) {
    Column {
        Text(
            text = "Изучайте подробности!",
            style = Typography.h1,
            modifier = Modifier
                .padding(top = 60.dp, bottom = 33.dp)
                .padding(horizontal = 24.dp)
        )
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(horizontal = 24.dp)
        ) {
            items(texts.size ) { index ->
                TextElement(texts[index])

            }
        }
    }
}

@Composable
fun TextElement(text: TextModel) {
    Column(modifier = Modifier.padding(bottom = 21.dp)) {
        Text(
            text = text.name,
            style = Typography.body1,
            color = AudioTextColor,
            modifier = Modifier.padding(bottom = 13.dp)
        )
        Text(
            text = text.content,
            style = Typography.body2,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 13.dp)
        )
    }
}