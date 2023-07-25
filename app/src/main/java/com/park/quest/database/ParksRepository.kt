package com.park.quest.database

import android.content.Context
import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.forEach
import okhttp3.internal.immutableListOf
import javax.inject.Inject

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */
class ParksRepository @Inject constructor(val appContext: Context, private val parksDatabase: ParksDatabase) {

    fun addPark(park: Park) {
        parksDatabase.parkDao.addPark(park)
    }

    fun getParks(): Flow<List<Park>> {
        return parksDatabase.parkDao.getParks()
    }

    fun addParks(parks: List<Park>){
        parksDatabase.parkDao.addParks(parks)
    }

    fun addStamps(stamps: List<Stamp>) {
        parksDatabase.parkDao.addStamps(stamps)
    }

    suspend fun getParkStamps(parkId: Int): Flow<List<ParkStamp>> = flow {
        val parks = parksDatabase.parkDao.getParks(parkId)
        val stamp = parksDatabase.parkDao.getStamp(parkId)
        val parkStamps = mutableListOf<ParkStamp>()
        stamp.collect { s ->
            parks.collect {
                it.forEach { p ->
                    parkStamps.add(ParkStamp(p, s))
                }
                emit(parkStamps)
            }
        }
    }
}

data class ParkStamp(var park: Park, var stamp: Stamp)
