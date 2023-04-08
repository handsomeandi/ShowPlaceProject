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
import com.example.showplaceproject.presentation.theme.AudioTextColor
import com.example.showplaceproject.presentation.theme.Typography

@Composable
fun TextScreen(navHostController: NavHostController) {
    val texts = listOf(
        "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin malesuada dolor nisi, lobortis volutpat sapien porttitor condimentum. Nullam pretium nisi ac lorem faucibus pretium. In posuere eu ligula id ultrices. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Suspendisse potenti. Nulla luctus, lacus ac venenatis scelerisque, sem erat scelerisque neque, non consequat justo urna ut eros. In ut quam tempus dolor feugiat sollicitudin vel vitae ex. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Fusce ac malesuada magna, ac ornare dolor. Phasellus tempus erat eget turpis bibendum accumsan. Quisque pellentesque eros a euismod scelerisque. Cras arcu sem, ornare in sodales ac, ultricies ac erat. Curabitur quam quam, porttitor a ligula sit amet, rhoncus euismod ligula. In libero orci, dignissim eget dignissim vitae, sodales et ligula. Maecenas congue tortor ac ante finibus, et hendrerit purus pellentesque. Phasellus molestie eros a arcu ultricies, eu pellentesque lacus imperdiet.",
        "Ut quis efficitur erat. Duis rutrum dui turpis, vitae finibus augue consequat sed. Aenean finibus dolor sit amet sapien consequat dignissim. Aliquam risus nunc, luctus in nunc et, bibendum imperdiet purus. Morbi turpis nibh, gravida nec leo eu, lobortis dapibus felis. Maecenas sed posuere sapien, id lobortis tellus. Etiam et mattis ex. Nulla lectus arcu, vehicula vel mollis ac, elementum non nibh.\n" +
                "\n" +
                "Aenean tincidunt dolor quis ipsum dignissim, in finibus leo accumsan. Pellentesque elementum eget nisi quis cursus. Vestibulum ante ipsum primis in faucibus orci luctus et ultrices posuere cubilia curae; Vivamus mattis nec mauris rutrum imperdiet. Integer a ligula lacinia, faucibus purus id, pulvinar orci. Donec eros ligula, semper quis est quis, tincidunt feugiat felis. Curabitur ipsum ante, elementum eget pharetra vitae, lacinia in ipsum. Phasellus congue facilisis sem id dignissim. Donec sed libero non mi lacinia sodales. Duis egestas, nisi a accumsan ultrices, purus nunc pharetra nisi, eget pharetra quam lectus a erat. Nullam tortor nunc, sagittis nec nunc at, egestas imperdiet neque. Aenean tincidunt dolor a massa facilisis, quis semper nibh volutpat. Sed ac erat quis est vestibulum efficitur sed nec nisi.",
        "Donec feugiat elit eu elit suscipit, quis ornare ante vehicula. Nunc ut massa ex. Maecenas ut turpis consectetur est porttitor suscipit. Aliquam eleifend ligula ex, a pulvinar ante luctus in. Duis consequat, nunc quis tempor hendrerit, orci lectus cursus purus, quis varius dolor metus ac odio. Aliquam erat volutpat. Ut quis nibh ac turpis pulvinar iaculis sit amet quis libero. Pellentesque egestas fermentum massa, eget tristique magna iaculis quis. Sed eros urna, ornare vel mauris vel, cursus porta erat. Fusce finibus, ligula eget eleifend laoreet, est leo maximus est, et molestie dui ante in urna. Suspendisse at nisl semper, iaculis turpis vitae, facilisis nulla. Maecenas faucibus faucibus tincidunt. Nullam commodo tempor nulla eget aliquet. Nam dapibus magna nisi, quis tincidunt mauris posuere in. Donec congue elit sit amet nisl tincidunt maximus.",
        "Donec ut neque id ligula volutpat bibendum. Ut eleifend magna sed magna tincidunt aliquet. Praesent congue lacinia feugiat. Curabitur id rutrum justo, vel ullamcorper justo. Donec finibus arcu justo, a fringilla justo sollicitudin nec. Maecenas semper odio neque, pulvinar finibus dolor euismod ut. Integer varius orci ac lectus elementum, at aliquam purus tempus. Curabitur a nulla sollicitudin, fringilla risus sit amet, vehicula risus. Aenean id tortor enim. Curabitur facilisis consectetur lorem, sit amet porta quam lacinia sit amet. Ut id elit ante. Phasellus imperdiet commodo quam, nec volutpat odio fringilla eu.",
    )
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
fun TextElement(text: String) {
    Column(modifier = Modifier.padding(bottom = 21.dp)) {
        Text(
            text = text.take(35),
            style = Typography.body1,
            color = AudioTextColor,
            modifier = Modifier.padding(bottom = 13.dp)
        )
        Text(
            text = text,
            style = Typography.body2,
            color = Color.Black,
            modifier = Modifier.padding(bottom = 13.dp)
        )
    }
}