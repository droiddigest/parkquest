package com.park.quest.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.park.quest.routes.AppRoutes
import com.park.quest.screens.About
import com.park.quest.screens.Home
import com.park.quest.screens.Search
import com.park.quest.screens.Passport
import com.park.quest.viewmodels.ProvideAppViewModel

/**
 * Created by Nirbhay Pherwani on 7/23/2023.
 */


@Composable
fun App(){
    val navController = rememberNavController()

    ProvideAppViewModel(navController) {
        NavHost(navController = navController, startDestination = AppRoutes.HOME.name) {
            composable(AppRoutes.HOME.name) {
                Home()
            }

            composable(AppRoutes.ABOUT.name) {
                About()
            }

            composable(AppRoutes.SEARCH.name) {
                Search()
            }

            composable("${AppRoutes.STAMPS.name}/{horizontalPageIndex}/{verticalPageIndex}") { backStackEntry ->
                val verticalPageIndex = backStackEntry.arguments?.getString("verticalPageIndex")?.toInt()
                val horizontalPageIndex = backStackEntry.arguments?.getString("horizontalPageIndex")?.toInt()


                if(verticalPageIndex != null && horizontalPageIndex != null) {
                    Passport(horizontalPageIndex, verticalPageIndex)
                }
                else if(verticalPageIndex == null && horizontalPageIndex != null) {
                    Passport(horizontalPageIndex)
                } else {
                    Passport()
                }
            }
        }
    }

}