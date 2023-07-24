package com.park.quest.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.navigation.NavController
import com.park.quest.viewmodels.ParksViewModel

/**
 * Created by Nirbhay Pherwani on 7/23/2023.
 */

@Composable
fun Stamps(horizontalPageIndex: Int = 0, verticalPageIndex: Int = 0){
    val parksViewModel: ParksViewModel = hiltViewModel()

}