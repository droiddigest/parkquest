package com.park.quest.viewmodels

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.park.quest.R
import com.park.quest.database.Park
import com.park.quest.database.ParkStamp
import com.park.quest.database.ParksRepository
import com.park.quest.database.Stamp
import com.park.quest.rawdata.RawPark
import com.park.quest.rawdata.RawParkStamp
import com.park.quest.rawdata.RawStamp
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.internal.immutableListOf
import javax.inject.Inject

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */

@HiltViewModel
class ParksViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val parksRepository: ParksRepository
) : ViewModel() {
    private val _parksViewState: MutableStateFlow<List<Park>> =
        MutableStateFlow(emptyList())
    val parksViewState: StateFlow<List<Park>> = _parksViewState.asStateFlow()


    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                populateRawData(context)
            }
        }
    }

    private suspend fun populateRawData(context: Context) {
        parksRepository.getParks().collect {
            if (it.isEmpty()) {
                addRawDataToDatabase(context)
            } else {
                _parksViewState.value = it
            }
        }
    }

    private fun addRawDataToDatabase(context: Context) {
        val gson = Gson()
        val rawParkStamp: RawParkStamp =
            gson.fromJson(Utility.readRawResource(context, R.raw.parks), RawParkStamp::class.java)
        populateStamps(rawParkStamp.stamps)
        populateParks(rawParkStamp.parks)
    }

    private fun populateStamps(rawStamps: List<RawStamp>) {
        val stamps = mutableListOf<Stamp>()
        rawStamps.forEach {
            val stamp = Stamp(name = it.name, id = it.stampId)
            stamps.add(stamp)
        }
        parksRepository.addStamps(stamps)
    }

    private fun populateParks(rawParks: List<RawPark>) {
        val parks = mutableListOf<Park>()
        rawParks.forEach {
            val park = Park(
                pageId = it.pageId,
                name = it.name,
                aboutUrl = it.aboutUrl,
                stampId = it.pageId
            )
            parks.add(park)
        }
        parksRepository.addParks(parks)
    }

    fun addParkStamp(park: Park, position: Int, rotation: Float) {
        park.time = System.currentTimeMillis()
        park.position = position
        park.rotation = rotation

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                parksRepository.addPark(park)
            }
        }
    }


    fun getParkStamps(parkId: Int) = flow {
        parksRepository.getParkStamps(parkId).collect {
            emit(it)
        }
    }
}

