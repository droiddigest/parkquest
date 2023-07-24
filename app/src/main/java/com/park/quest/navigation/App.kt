package com.park.quest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.park.quest.screens.About
import com.park.quest.screens.Home
import com.park.quest.screens.Search
import com.park.quest.screens.Stamps
import com.park.quest.routes.AppRoutes

/**
 * Created by Nirbhay Pherwani on 7/23/2023.
 */


@Composable
fun App(){
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = AppRoutes.HOME.name) {
        composable(AppRoutes.HOME.name) {
            Home(navController = navController)
        }

        composable(AppRoutes.ABOUT.name) {
            About(navController = navController)
        }

        composable(AppRoutes.SEARCH.name) {
            Search(navController = navController)
        }


        composable("${AppRoutes.STAMPS.name}/{horizontalPageIndex}/{verticalPageIndex}") { backStackEntry ->
            val verticalPageIndex = backStackEntry.arguments?.getInt("verticalPageIndex")
            val horizontalPageIndex = backStackEntry.arguments?.getInt("horizontalPageIndex")

            if(verticalPageIndex == null && horizontalPageIndex != null) {
                Stamps(navController, horizontalPageIndex)
            } else {
                Stamps(navController)
            }
        }
    }
}