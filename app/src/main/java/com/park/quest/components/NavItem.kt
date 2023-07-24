package com.park.quest.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Created by Nirbhay Pherwani on 7/23/2023.
 */

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavItem(
    modifier: Modifier = Modifier,
    padding: Dp = 20.dp,
    backgroundColor: Color = Color.White,
    text: String = "Placeholder",
    textColor: Color = Color.DarkGray,
    textSize: TextUnit = 20.sp,
    shape: Shape = RoundedCornerShape(20),
    buttonText: String = "View",
    buttonTextColor: Color = Color.White,
    onClick: () -> Unit
) {
    Card(
        modifier = modifier.padding(padding),
        colors = CardDefaults.cardColors(
            containerColor = backgroundColor,
        ),
        shape = shape,
        onClick = { onClick() }
    ) {
        Column(
            modifier = modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
        ) {
            Text(text = text, color = textColor, fontSize = textSize, fontWeight = FontWeight.Bold)
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                modifier = Modifier.fillMaxWidth(),
                onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(containerColor = Color.Transparent),
                border = BorderStroke(1.dp, com.park.quest.Color.DarkGrey)
            ) {
                Text(text = buttonText, color = buttonTextColor)
            }
        }
    }

}