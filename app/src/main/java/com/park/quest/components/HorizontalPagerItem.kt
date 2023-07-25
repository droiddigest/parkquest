package com.park.quest.components

import android.content.Context
import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.park.quest.animations.ScaleAnimation
import com.park.quest.database.Park
import com.park.quest.database.ParkStamp
import com.park.quest.viewmodels.ParksViewModel
import kotlinx.coroutines.delay
import kotlin.random.Random


/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */

@Composable
fun HorizontalPagerItem(
    park: Park,
    modifier: Modifier = Modifier,
    padding: Dp = 20.dp,
    backgroundColor: Color = Color.White,
    textColor: Color = Color.DarkGray,
    textSize: TextUnit = 24.sp,
    aboutTextSize: TextUnit = 16.sp,
    shape: Shape = RoundedCornerShape(10),
    pageIndex: Int
) {
    val configuration = LocalConfiguration.current
    val screenWidth = configuration.screenWidthDp.dp.value.toInt()

    val parksViewModel: ParksViewModel = hiltViewModel()

    var parkStamps by remember {
        mutableStateOf(emptyList<ParkStamp>())
    }

    LaunchedEffect(Unit) {
        parksViewModel.getParkStamps(park.pageId).collect { value ->
            parkStamps = value.filter { it.park.time != -1L }
        }
    }

    val context = LocalContext.current

    Card(
        modifier = modifier.padding(padding),
        shape = shape,
    ) {
        Box(modifier = Modifier.fillMaxSize()) {
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.FillBounds,
                model = com.park.quest.R.drawable.paper,
                contentDescription = "Polaroid",
            )

            Column(
                modifier = modifier
                    .fillMaxSize()
                    .padding(16.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                for (i in 0..3 step (2)) {
                    PageRow(i, park, parkStamps)
                }

                Spacer(modifier = Modifier.height(16.dp))

                Text(
                    text = park.name,
                    color = textColor,
                    fontSize = textSize,
                    fontWeight = FontWeight.Bold
                )
                Button(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = { openUrl(context = context, park.aboutUrl) },
                    colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                    border = BorderStroke(1.dp, com.park.quest.Color.DarkGrey)
                ) {
                    Text(text = "Park Website", color = textColor)
                }
            }
        }
    }
}

private fun openUrl(context: Context, url: String){
    val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
    context.startActivity(browserIntent)
}

@Composable
private fun PageRow(index: Int, park: Park, parkStamps: List<ParkStamp>) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(300.dp),
    ) {
        for (i in 0..1) {
            val rowItem = getParkStamp(parkStamps, index + i)
            PageStamp(
                modifier = Modifier
                    .fillMaxSize()
                    .weight(1F),
                parkStamp = rowItem,
                park = park,
                position = index + i
            )
        }
    }
}

private fun getParkStamp(parkStamps: List<ParkStamp>?, position: Int): ParkStamp? {
    return parkStamps?.firstOrNull { it.park.position == position }
}

@Composable
fun PageStamp(modifier: Modifier, park: Park, parkStamp: ParkStamp?, position: Int) {
    val parksViewModel: ParksViewModel = hiltViewModel()

    val addStamp: () -> Unit = {
        val rotation = ((Random.nextFloat() * 2) - 1) * 35
        parksViewModel.addParkStamp(park, position, rotation)
    }

    val visibility = remember { mutableStateOf(false) }

    Box(
        modifier = modifier
            .border(BorderStroke(2.dp, com.park.quest.Color.DarkGrey))
            .clickable { addStamp.invoke() }
            .rotate(parkStamp?.park?.rotation ?: 50F)
    ) {
        parkStamp?.let {
            val stampUrl =
                "${HorizontalPagerItemUtility.STAMP_URL}/${parkStamp.stamp.name}.png"

            LaunchedEffect(parkStamp.park.pageId.toString() + ":" + parkStamp.park.pageId) {
                // Animate the item when it changes or first appears
                visibility.value = false
                delay(200) // Delay for visibility change to take effect
                visibility.value = true
            }

            Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally, modifier = Modifier.fillMaxSize()) {
                ScaleAnimation(visible = visibility.value) {
                    AsyncImage(
                        modifier = Modifier
                            .size(145.dp)
                            .border(
                                BorderStroke(8.dp, com.park.quest.Color.Orange),
                                RoundedCornerShape(topStartPercent = 50, topEndPercent = 50)
                            )
                            .padding(10.dp)
                            .clip(RoundedCornerShape(topStartPercent = 25, topEndPercent = 25)),
                        contentScale = ContentScale.Crop,
                        model = stampUrl,
                        contentDescription = "Stamp",
                    )
                }
            }
        }
    }
}

object HorizontalPagerItemUtility {
    const val STAMP_URL = "https://ik.imagekit.io/droiddigest/parkquest"
}