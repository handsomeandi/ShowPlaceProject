package com.example.showplaceproject.presentation.audio

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.showplaceproject.R
import com.example.showplaceproject.core.formatTime
import com.example.showplaceproject.domain.AudioModel
import com.example.showplaceproject.presentation.AudioWaveform
import com.example.showplaceproject.presentation.SelectedScreen
import com.example.showplaceproject.presentation.bottomnav.ShowPlaceBottomNavigation
import com.example.showplaceproject.presentation.theme.*

@Composable
fun AudioScreen(navHostController: NavHostController) {
    val viewModel: AudioScreenViewModel = hiltViewModel()
    val audios by viewModel.audio.observeAsState()
    LaunchedEffect(Unit) {
        viewModel.init()
    }
    Column {
        Text(
            text = "Прослушивайте \nаудио!",
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
            items(audios?.size ?: 0) { index ->
                audios?.get(index)?.let {
                    AudioElement(it)
                }

            }
        }
        ShowPlaceBottomNavigation(SelectedScreen.AUDIO, navHostController)
    }

}


//TODO: make fast-forwarding
@Composable
fun AudioElement(audio: AudioModel) {
    val context = LocalContext.current
    val audioPlayer = remember { AudioPlayer(context, audio.file) }
    var progress by remember { mutableStateOf(0f) }
    val duration by audioPlayer.duration.observeAsState()
    var isPlaying by remember { mutableStateOf(false) }
    isPlaying = audioPlayer.isPlaying()

    Column(modifier = Modifier.padding(bottom = 21.dp)) {
        Text(
            text = audio.name ?: "",
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
            Row {
                IconButton(
                    onClick = {
                        if (isPlaying) {
                            audioPlayer.pauseAudio()
                        } else {
                            if (progress > 0) audioPlayer.resumeAudio() else audioPlayer.playAudio(
                                audio.file ?: "",
                            ) { current, audioDuration ->
                                progress = current.toFloat() / audioDuration.toFloat()
                            }
                        }
                        isPlaying = audioPlayer.isPlaying()
                    },
                    modifier = Modifier.align(CenterVertically)
                ) {
                    Icon(
                        imageVector = if (isPlaying) ImageVector.vectorResource(id = R.drawable.ic_pause) else ImageVector.vectorResource(
                            id = R.drawable.ic_resume
                        ),
                        tint = if (isPlaying) AudioIconColorSecondary else AudioTextColor,
                        contentDescription = if (audioPlayer.isPlaying()) "Pause" else "Play"
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
            Text(
                text = formatTime(duration ?: 0),
                style = Typography.body2,
                modifier = Modifier.align(Alignment.End)
            )
        }
        DisposableEffect(Unit) {
            onDispose {
                audioPlayer.release()
            }
        }
    }
}