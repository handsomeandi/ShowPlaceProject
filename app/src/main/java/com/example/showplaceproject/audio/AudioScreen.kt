package com.example.showplaceproject.audio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.example.showplaceproject.R
import com.example.showplaceproject.SelectedScreen
import com.example.showplaceproject.bottomnav.ShowPlaceBottomNavigation
import com.example.showplaceproject.core.AudioWaveform
import com.example.showplaceproject.ui.theme.*
import kotlinx.coroutines.delay

@Composable
fun AudioScreen() {
    Column(
    ) {
        Text(
            text = "Прослушивайте \nаудио!",
            style = Typography.h1,
            modifier = Modifier.padding(top = 60.dp, bottom = 33.dp)
                .padding(horizontal = 24.dp)
        )
        LazyColumn(modifier = Modifier.fillMaxWidth().weight(1f)
            .padding(horizontal = 24.dp)) {
            items(5) { index ->
                AudioElement("Первые обитатели")
            }
        }
        ShowPlaceBottomNavigation(SelectedScreen.AUDIO)
    }

}


//TODO: make audio element class containing name, duration and audio itself
@Composable
fun AudioElement(title: String) {
    var isPlaying by remember { mutableStateOf(false) }
    var progress by remember { mutableStateOf(0f) }

    LaunchedEffect(isPlaying) {
        if (isPlaying) {
            while (progress < 1f) {
                progress += 0.01f
                delay(100)
            }
            isPlaying = false
        } else {
            progress = 0f
        }
    }

    Column(modifier = Modifier.padding(bottom = 21.dp)) {
        Text(
            text = title,
            style = Typography.body1,
            color = AudioTextColor,
            modifier = Modifier.padding(bottom = 13.dp)
        )
        Column(
            Modifier
                .clip(RoundedCornerShape(14.dp))
                .background(color = AudioBackground)
                .padding(horizontal = 31.dp)
                .padding(top = 20.dp, bottom = 4.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Row() {
                IconButton(
                    onClick = { isPlaying = !isPlaying },
                    modifier = Modifier.align(CenterVertically)
                ) {
                    Icon(
                        imageVector = if (isPlaying) ImageVector.vectorResource(id = R.drawable.ic_pause) else ImageVector.vectorResource(
                            id = R.drawable.ic_resume
                        ),
                        tint = if (isPlaying) AudioIconColorSecondary else AudioTextColor,
                        contentDescription = if (isPlaying) "Pause" else "Play"
                    )
                }
                AudioWaveform(
                    amplitudes = listOf(
                        45,
                        40,
                        35,
                        40,
                        30,
                        45,
                        30,
                        45,
                        39,
                        27,
                        35,
                        30,
                        45,
                        40,
                        35,
                        40,
                        30,
                        45,
                        30,
                        45,
                        39,
                        27,
                        35,
                        30
                    ),
                    progress = progress,
                    progressBrush = SolidColor(AudioTrackColor),
                    waveformBrush = SolidColor(AudioTrackBackground),
                    onProgressChange = { },
                    modifier = Modifier
                        .padding(horizontal = 28.dp)
                        .align(CenterVertically)
                )
//                LinearProgressIndicator(
//                    color = AudioIconColor,
//                    backgroundColor = AudioTrackBackground,
//                    progress = progress, modifier = Modifier
//                        .padding(start = 28.dp)
//                        .align(CenterVertically)
//                )
            }
            Text(text = "1:21", style = Typography.body2, modifier = Modifier.align(Alignment.End))
        }

    }
}