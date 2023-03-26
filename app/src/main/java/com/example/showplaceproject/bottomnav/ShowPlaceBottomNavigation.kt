package com.example.showplaceproject.bottomnav

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.example.showplaceproject.R
import com.example.showplaceproject.SelectedScreen
import com.example.showplaceproject.ui.theme.AudioIconColor
import com.example.showplaceproject.ui.theme.AudioIconColorSecondary


@Composable
fun ShowPlaceBottomNavigation(selectedScreen: SelectedScreen) {
    when (selectedScreen) {
        SelectedScreen.MAIN -> {
            ConstraintLayout(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                val bottomCenterImage = createRef()
                val backgroundImage = createRef()
                Image(
                    painter = painterResource(id = R.drawable.ic_bottom_background),
                    contentDescription = "background",
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(backgroundImage) {
                            bottom.linkTo(parent.bottom)
                        }
                        .offset(y = (8).dp)
                )

                Image(
                    painter = painterResource(id = R.drawable.ic_audio),
                    contentDescription = "Bottom Left Image",
                    modifier = Modifier.constrainAs(createRef()) {

                        end.linkTo(bottomCenterImage.start)
                        start.linkTo(parent.start)
                        top.linkTo(backgroundImage.top)
                        bottom.linkTo(backgroundImage.bottom)
                    }
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_circle),
                    contentDescription = "Bottom Center Image",
                    modifier = Modifier
                        .constrainAs(bottomCenterImage) {
                            end.linkTo(parent.end)
                            start.linkTo(parent.start)
                            bottom.linkTo(backgroundImage.bottom)
                        }
                        .padding(bottom = 30.dp)
                )
                Image(
                    painter = painterResource(id = R.drawable.ic_media),
                    contentDescription = "Bottom Right Image",
                    modifier = Modifier.constrainAs(createRef()) {
                        end.linkTo(parent.end)
                        start.linkTo(bottomCenterImage.end)
                        top.linkTo(backgroundImage.top)
                        bottom.linkTo(backgroundImage.bottom)
                    }
                )
            }
        }
        SelectedScreen.AUDIO -> {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
//                TopShadow()
                Card(
                    elevation = 40.dp,
                    shape = RoundedCornerShape(topStart = 32.dp, topEnd = 32.dp),
                    modifier = Modifier
                        .fillMaxWidth()
                        .shadow(40.dp, clip = true)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .align(CenterHorizontally)
                            .padding(vertical = 30.dp, horizontal = 70.dp)
                            .shadow(40.dp, clip = true),
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_audio),
                            contentDescription = "Icon 1",
                            tint = AudioIconColor,
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_camera),
                            contentDescription = "Icon 2",
                            modifier = Modifier.weight(1f),
                            tint = AudioIconColorSecondary,
                        )
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_media),
                            contentDescription = "Icon 3",
                            tint = AudioIconColorSecondary,
                        )
                    }
                }
            }

        }
        SelectedScreen.MAP -> TODO()
        SelectedScreen.MEDIA -> TODO()
    }

}
