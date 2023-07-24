package com.park.quest.viewmodels

import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import com.park.quest.database.ParksRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 * Email - npherwani@reachmobi.com
 */

@HiltViewModel
class ParksViewModel @Inject constructor(val parksRepository: ParksRepository) : ViewModel() {

}