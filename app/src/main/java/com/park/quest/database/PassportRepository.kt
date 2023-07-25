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
class PassportRepository @Inject constructor(
    val appContext: Context,
    private val passportDatabase: PassportDatabase
) {

    fun addPark(park: Park) {
        passportDatabase.passportDao.addPark(park)
    }

    fun getParks(): Flow<List<Park>> {
        return passportDatabase.passportDao.getParks()
    }

    fun getParksVisitedCounter(): Flow<Int>{
        return passportDatabase.passportDao.getParksVisitedCounter()
    }

    fun addParks(parks: List<Park>) {
        passportDatabase.passportDao.addParks(parks)
    }

    fun addVisitStamp(visitStamp: VisitStamp) {
        passportDatabase.passportDao.addVisitStamp(visitStamp)
    }

    fun getVisitStamps(parkId: Int): Flow<List<VisitStamp>> {
        return passportDatabase.passportDao.getVisitStamps(parkId)
    }
}