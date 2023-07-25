package com.park.quest.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.park.quest.Color
import com.park.quest.components.Avatar
import com.park.quest.components.NavItem
import com.park.quest.routes.AppRoutes
import com.park.quest.screens.Utility.NAV_ITEM_HEIGHT
import com.park.quest.viewmodels.AppViewModel
import com.park.quest.viewmodels.LocalAppViewModel
import com.park.quest.viewmodels.ParksViewModel

/**
 * Created by Nirbhay Pherwani on 7/23/2023.
 */

@Composable
fun Home() {
    val navController: NavHostController? = LocalAppViewModel.current.appViewModel?.navController

    val parkViewModel: ParksViewModel = hiltViewModel()

    val state by parkViewModel.parksViewState.collectAsState()

    val parksVisitedCounter by remember(state) {
        derivedStateOf { state.count { it.time > 0 } }
    }

    val gradientColors = listOf(Color.Yellow, Color.Orange)
    val gradientBrush = Brush.horizontalGradient(gradientColors)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(gradientBrush)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Avatar()

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "Kavya Malik", color = androidx.compose.ui.graphics.Color.White, fontSize = 22.sp, fontWeight = FontWeight.Bold)

        Spacer(modifier = Modifier.height(8.dp))

        Text(text = "$parksVisitedCounter Parks Visited", color = androidx.compose.ui.graphics.Color.White, fontSize = 18.sp, fontWeight = FontWeight.SemiBold)

        Spacer(modifier = Modifier.height(24.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
            ,
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
                onClick = { navController?.navigate(AppRoutes.SEARCH.name) }
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
                onClick = { navController?.navigate(AppRoutes.ABOUT.name) }
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
                onClick = { navController?.navigate("${AppRoutes.STAMPS.name}/0/0") }
            )
        }
    }
}

object Utility {
    val NAV_ITEM_HEIGHT = 200.dp
}