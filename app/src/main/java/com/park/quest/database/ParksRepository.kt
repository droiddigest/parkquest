package com.park.quest.database

import android.content.Context
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */
class ParksRepository @Inject constructor(val appContext: Context, private val parksDatabase: ParksDatabase) {

    fun addParks(parks: List<Park>){
        parksDatabase.parkDao.addParks(parks)
    }

    fun addStamps(stamps: List<Stamp>) {
        parksDatabase.parkDao.addStamps(stamps)
    }

    fun getStamp(stampId: Int): Flow<Stamp> {
        return parksDatabase.parkDao.getStamp(stampId)
    }


}