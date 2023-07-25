package com.park.quest.viewmodels

import android.content.Context
import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.gson.Gson
import com.park.quest.R
import com.park.quest.database.Park
import com.park.quest.database.PassportRepository
import com.park.quest.database.VisitStamp
import com.park.quest.rawdata.RawParkStamp
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */

@HiltViewModel
class PassportViewModel @Inject constructor(
    @ApplicationContext context: Context,
    private val passportRepository: PassportRepository
) : ViewModel() {
    private val _parksViewState: MutableStateFlow<List<Park>> =
        MutableStateFlow(emptyList())
    val parksViewState: StateFlow<List<Park>> = _parksViewState.asStateFlow()

    private val _parksVisitedState: MutableStateFlow<Int> =
        MutableStateFlow(0)
    val parksVisitedState: StateFlow<Int> = _parksVisitedState.asStateFlow()

    init {
        getParksVisitedCounter()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                populateRawData(context)
            }
        }
    }

    private suspend fun populateRawData(context: Context) {
        passportRepository.getParks().collect {
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
        populateParks(rawParkStamp)
    }

    private fun populateParks(rawParkStamp: RawParkStamp) {
        val parks = mutableListOf<Park>()
        val rawParks = rawParkStamp.parks

        rawParks.forEach {
            val stamp =
                rawParkStamp.stamps.firstOrNull() { stamp -> it.pageId == stamp.stampId }?.name
            stamp?.let { _ ->
                val park = Park(
                    id = it.pageId,
                    name = it.name,
                    link = it.aboutUrl,
                    stamp = stamp
                )
                parks.add(park)
            }
        }
        passportRepository.addParks(parks)
    }

    fun addVisitStamp(park: Park, position: Int, rotation: Float) {
        val visitStamp = VisitStamp(
            parkId = park.id,
            stampRotation = rotation,
            stampPosition = position,
            visitTime = System.currentTimeMillis(),
            park = park
        )

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                passportRepository.addVisitStamp(visitStamp)
            }
        }
    }

    private fun getParksVisitedCounter() {
        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                passportRepository.getParksVisitedCounter().collect {
                    _parksVisitedState.value = it
                }
            }
        }
    }

    fun getVisitStamps(parkId: Int) = flow {
        passportRepository.getVisitStamps(parkId).collect {
            emit(it)
        }
    }
}

