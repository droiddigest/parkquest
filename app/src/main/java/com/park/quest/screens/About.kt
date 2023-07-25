package com.park.quest.screens

import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.park.quest.components.openUrl

/**
 * Created by Nirbhay Pherwani on 7/23/2023.
 */

@Composable
fun About() {
    val items = mutableListOf<AboutItem>()

    val textUnicode = "›"

    items.add(AboutItem("Nirbhay Pherwani $textUnicode", "https://linkedin.com/in/nirbhaypherwani"))
    items.add(AboutItem("Raghul Krishnan $textUnicode", "https://linkedin.com/in/raghul-krishnan"))
    items.add(AboutItem("Medium Publication $textUnicode", "https://medium.com/droid-digest"))
    items.add(
        AboutItem(
            "Github Repository $textUnicode",
            "https://github.com/droiddigest/parkquest"
        )
    )

    AboutList(items)
}

@Composable
fun AboutList(items: List<AboutItem>) {
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .fillMaxHeight()
            .wrapContentWidth()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            text = "About Park Quest",
            color = Color.White,
            fontWeight = FontWeight.Black,
            fontSize = 26.sp,
            textAlign = TextAlign.Center
        )

        Text(
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            text = "Here, you can find the source code for the app and supporting medium articles for explanations. " +
                    "If you would like to collaborate with us, feel free to contact us on LinkedIn!",
            color = Color.White,
            fontWeight = FontWeight.Normal,
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(50.dp))

        Divider(
            thickness = 0.5.dp,
            color = Color.LightGray,
            modifier = Modifier.width(180.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        for (item in items) {
            Column(
                modifier = Modifier
                    .padding(10.dp)
                    .wrapContentSize()
                    .clickable { openUrl(context, item.link) },
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    modifier = Modifier.fillMaxWidth(),
                    text = item.text,
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    textAlign = TextAlign.Center
                )

                Spacer(modifier = Modifier.height(26.dp))

                Divider(
                    thickness = 0.5.dp,
                    color = Color.LightGray,
                    modifier = Modifier.width(180.dp)
                )

                Spacer(modifier = Modifier.height(3.dp))

            }
        }

    }
}

data class AboutItem(var text: String, var link: String)