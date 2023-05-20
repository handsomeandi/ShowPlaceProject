package com.example.showplaceproject.presentation.media

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.media3.common.MediaItem
import androidx.media3.datasource.DataSource
import androidx.media3.datasource.DefaultHttpDataSource
import androidx.media3.datasource.HttpDataSource
import androidx.media3.exoplayer.ExoPlayer
import androidx.media3.exoplayer.source.DefaultMediaSourceFactory
import androidx.media3.ui.PlayerView
import androidx.navigation.NavHostController
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import com.example.showplaceproject.R
import com.example.showplaceproject.presentation.SelectedScreen
import com.example.showplaceproject.presentation.bottomnav.ShowPlaceBottomNavigation
import com.example.showplaceproject.presentation.navigation.NavigationItem
import com.example.showplaceproject.presentation.theme.Typography
import com.google.accompanist.pager.ExperimentalPagerApi
import com.google.accompanist.pager.HorizontalPager
import com.google.accompanist.pager.rememberPagerState
import com.google.gson.Gson


@Composable
fun MediaScreen(navHostController: NavHostController) {

    val viewModel: MediaScreenViewModel = hiltViewModel()
    val media = viewModel.media.observeAsState()

    LaunchedEffect(Unit) {
        viewModel.init()
    }
    Column {
        val scrollState = rememberScrollState()
        Column(
            modifier = Modifier
                .verticalScroll(scrollState)
                .weight(1f)
        ) {
            Text(
                text = "Изучайте все \nматериалы!",
                style = Typography.h1,
                modifier = Modifier
                    .padding(top = 60.dp, bottom = 2.dp)
                    .padding(horizontal = 24.dp)
            )
            Title("Фотографии") {
                val gson = Gson()
                val photos = gson.toJson(media.value?.photos)
                navHostController.navigate("${NavigationItem.Photo.route}?photos=$photos")
            }
            media.value?.photos?.map {
                it.file
            }?.let { ViewPagerWithPhotos(it) }
            Title("Видео") {
                val gson = Gson()
                val videos = gson.toJson(media.value?.video)
                navHostController.navigate("${NavigationItem.Video.route}?videos=$videos")
            }
            media.value?.video?.map {
                it.file
            }?.let { ViewPagerWithVideos(it) }
            Title("Файлы") {
                val gson = Gson()
                val texts = gson.toJson(media.value?.text)
                navHostController.navigate("${NavigationItem.Text.route}?texts=$texts")
            }
        }
        ShowPlaceBottomNavigation(SelectedScreen.MEDIA, navHostController)
    }
}

@Composable
private fun Title(title: String, clickListener: () -> Unit) {
    Row(
        horizontalArrangement = Arrangement.SpaceBetween,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 16.dp)
            .padding(top = 22.dp, bottom = 33.dp)
    ) {
        Text(
            text = title,
            style = Typography.h1,
            modifier = Modifier
        )

        Row(modifier = Modifier.clickable {
            clickListener()
        }) {

            Text(
                text = "Все",
                style = Typography.h1,
            )
            Image(
                painter = painterResource(R.drawable.ic_arrow_right),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 9.dp)
                    .align(CenterVertically)
            )
        }

    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ViewPagerWithVideos(videoUrls: List<String?>) {
    Scaffold(
        modifier = Modifier
            .height(193.dp)
    ) {
        Column(Modifier) {
            val pagerState = rememberPagerState()

            // Display 10 items
            HorizontalPager(
                verticalAlignment = Alignment.Top,
                count = videoUrls.size,
                state = pagerState,
                // Add 32.dp horizontal padding to 'center' the pages
                contentPadding = PaddingValues(start = 16.dp, end = 80.dp),
                // Add some horizontal spacing between items
                itemSpacing = 0.dp,
                modifier = Modifier
                    .fillMaxWidth()
            ) { page ->
                VideoPlayer(
                    Uri.parse(videoUrls[page]),
                    Modifier
                        .width(299.dp)
                        .aspectRatio(1.6f)
                        .height(193.dp)
                        .clip(RoundedCornerShape(12.dp))
                ) {}
            }

        }
    }
}

@Composable
fun VideoPlayer(uri: Uri, modifier: Modifier, onClick: () -> Unit) {
    var isPlaying by remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .background(Color.Black)
    ) {
        AndroidView(
            factory = { context ->
                VideoView(context).apply {
                    setVideoURI(uri)
                }
            },
            update = { view ->
                if (isPlaying) {
                    view.start()
                } else {
                    view.pause()
                }
            },
            modifier = modifier
        )

        // Add a button to play/pause the video
        Image(painter = painterResource(id = if (isPlaying) R.drawable.ic_pause_btn else R.drawable.ic_play_btn),
            contentDescription = "play button",
            modifier = Modifier
                .align(Alignment.Center)
                .clickable {
                    onClick()
                    isPlaying = !isPlaying
                })
    }

}


@Composable
@androidx.annotation.OptIn(androidx.media3.common.util.UnstableApi::class)
fun FullVideoPlayer(uri: String?, modifier: Modifier) {
    val httpDataSourceFactory: HttpDataSource.Factory =
        DefaultHttpDataSource.Factory().setAllowCrossProtocolRedirects(false)
    val dataSourceFactory: DataSource.Factory = DataSource.Factory {
        val dataSource = httpDataSourceFactory.createDataSource()
        dataSource.setRequestProperty(
            "cookie", "cookieValue"
        )
        dataSource.setRequestProperty("Range", "1-10000")
        dataSource
    }

    val mContext = LocalContext.current
    // Initializing ExoPLayer
    val mExoPlayer = remember(mContext) {
        ExoPlayer.Builder(mContext)
            .setMediaSourceFactory(DefaultMediaSourceFactory(dataSourceFactory)).build().apply {

                val mediaItem = MediaItem.Builder()
                    .setUri(Uri.parse(uri))
                    .build()
                setMediaItem(mediaItem)
                playWhenReady = false
                prepare()

            }

    }

    DisposableEffect(
        // Implementing ExoPlayer
        AndroidView(
            factory = { context ->
                PlayerView(context).apply {
                    player = mExoPlayer
                }
            },
            modifier = modifier
        )
    ) {
        onDispose {
            mExoPlayer.release()
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@OptIn(ExperimentalPagerApi::class)
@Composable
private fun ViewPagerWithPhotos(photoUrls: List<String>) {
    Scaffold(
        modifier = Modifier
            .height(193.dp)
    ) {
        Column(Modifier) {
            val pagerState = rememberPagerState()

            // Display 10 items
            HorizontalPager(
                count = photoUrls.size,
                state = pagerState,
                // Add 32.dp horizontal padding to 'center' the pages
                contentPadding = PaddingValues(start = 16.dp, end = 80.dp),
                // Add some horizontal spacing between items
                itemSpacing = 0.dp,
                modifier = Modifier
                    .fillMaxWidth()
            ) { page ->
                PagerSampleItem(
                    modifier = Modifier
                        .aspectRatio(1.6f),
                    photoUrls[page]
                )
            }

        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
internal fun PagerSampleItem(
    modifier: Modifier = Modifier,
    url: String
) {
    Box(modifier) {
        // Our page content, displaying a random image
        Image(
            painter = rememberImagePainter(
                data = url,
            ),
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .width(299.dp)
                .height(193.dp)
                .clip(RoundedCornerShape(12.dp))
        )
    }
}
