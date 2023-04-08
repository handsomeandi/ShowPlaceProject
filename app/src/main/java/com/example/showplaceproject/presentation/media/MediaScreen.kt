package com.example.showplaceproject.presentation.media

import android.annotation.SuppressLint
import android.net.Uri
import android.widget.VideoView
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
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
    val photoUrls = listOf(
        "https://sun9-67.userapi.com/impg/B4LVU2JfWJK9bKXrHXiIrabjAjCYt5v0iv9JXA/4hnQR0FdSOg.jpg?size=1439x2160&quality=95&sign=ba9c48676e3e007b12ebb73f9972979a&type=album",
        "https://sun6-22.userapi.com/impg/GfG1MleN1rG7QJFsLiN87jCkafGjbTQ5bGFc-w/CuTZWy2NfiE.jpg?size=2560x2560&quality=95&sign=00f59e4134e97de60e776fec4b09971a&type=album",
        "https://sun9-29.userapi.com/impg/c858520/v858520570/d392f/C1kM5naR-h0.jpg?size=1104x1104&quality=96&sign=f7ab099258ed3dbbe815afd7aa3f5c86&type=album",
        "https://sun9-19.userapi.com/impg/tmZlwuDZAuSPZ6cddEvVmXUZtklqv03V1kHj3Q/SrfR2cighmU.jpg?size=900x1600&quality=96&sign=e4137baf608f943c8369acae5bbf1dfb&type=album",
        "https://sun9-42.userapi.com/impg/igDKlf7VmLQn6mnWC-ExC6dUiKCqWNeuryNd8A/lmHpw0v0Pj0.jpg?size=1220x2160&quality=95&sign=28e392fde40e1df8067b452c7bb27d71&type=album",
    )

    val videoUrls = listOf(
        "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4",
        "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4",
        "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4",
        "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4",
        "https://videocdn.bodybuilding.com/video/mp4/62000/62792m.mp4",
    )

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
                val photos = gson.toJson(photoUrls)
                navHostController.navigate("${NavigationItem.Photo.route}?photos=$photos")
            }
            ViewPagerWithPhotos(photoUrls)
            Title("Видео") {
                val gson = Gson()
                val videos = gson.toJson(videoUrls)
                navHostController.navigate("${NavigationItem.Video.route}?videos=$videos")
            }
            ViewPagerWithVideos(videoUrls)
            Title("Файлы") {
                navHostController.navigate(NavigationItem.Text.route)
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
private fun ViewPagerWithVideos(videoUrls: List<String>) {
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
fun FullVideoPlayer(uri: String, modifier: Modifier) {
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
