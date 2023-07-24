package com.park.quest.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.park.quest.Color
import com.park.quest.components.NavItem
import com.park.quest.routes.AppRoutes
import com.park.quest.screens.Utility.NAV_ITEM_HEIGHT

/**
 * Created by Nirbhay Pherwani on 7/23/2023.
 */

@Composable
fun Home(navController: NavController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            NavItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .height(NAV_ITEM_HEIGHT),
                backgroundColor = Color.Orange,
                text = "Search\nParks",
                textColor = Color.DarkGrey,
                shape = RoundedCornerShape(0, 25, 25, 25),
                buttonText = "Discover",
                buttonTextColor = Color.DarkGrey,
                onClick = { navController.navigate(AppRoutes.SEARCH.name) }
            )

            NavItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1F)
                    .height(NAV_ITEM_HEIGHT),
                backgroundColor = Color.Yellow,
                text = "About\nApp",
                textColor = Color.DarkGrey,
                shape = RoundedCornerShape(25),
                buttonTextColor = Color.DarkGrey,
                onClick = { navController.navigate(AppRoutes.ABOUT.name) }
            )
        }
        Row {
            NavItem(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(175.dp),
                backgroundColor = Color.Green,
                text = "My Passport Stamps",
                textColor = Color.DarkGrey,
                shape = RoundedCornerShape(topStartPercent = 25, bottomEndPercent = 25),
                buttonTextColor = Color.DarkGrey,
                onClick = { navController.navigate("${AppRoutes.STAMPS.name}/0/0") }
            )
        }
    }
}

object Utility {
    val NAV_ITEM_HEIGHT = 200.dp
}