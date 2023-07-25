package com.park.quest.components

import android.util.Log
import android.util.Size
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.park.quest.database.Park
import com.park.quest.screens.StampsUtility
import kotlin.math.abs
import kotlin.random.Random

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */

@Composable
fun VerticalPagerItem(park: Park) {

    val backGroundSize = remember { mutableStateOf(IntSize(0, 0)) }

    val background = @Composable {
        AsyncImage(
            modifier = Modifier
                .fillMaxSize()
                .padding(horizontal = 30.dp, vertical = 100.dp)
                .onGloballyPositioned { coordinates ->
                    backGroundSize.value = coordinates.size
                },
            contentScale = ContentScale.FillBounds,
            model = com.park.quest.R.drawable.polaroid,
            contentDescription = "Polaroid",
        )
    }

    Box(
        modifier = Modifier
    ) {
        background()
        UnsplashRandomPhoto(backGroundSize.value, park.name)
    }
}

@Composable
fun UnsplashRandomPhoto(backGroundSize: IntSize, parkName: String) {
    val x = backGroundSize.width.minus(60)
    val y = backGroundSize.height.minus(140)
    x.let {
        val bgX = abs(x.toFloat() - (x * 0.12F)).toInt()
        y.let {
            val extra = y * 0.2F
            val bgY = abs(y.toFloat() - (extra)).toInt()
            val imageUrl = "${StampsUtility.PHOTOS_ENDPOINT}/${bgX + Random.nextInt(99)}x$bgY?${parkName} national park"
            Log.d("Image url", imageUrl)
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(horizontal = 45.dp, vertical = 100.dp)
                    .offset(y = (extra * -0.15F).dp),
                model = imageUrl,
                contentDescription = "Unsplash"
            )
        }
    }
}
