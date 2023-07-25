package com.park.quest.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */

@Dao
interface PassportDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addPark(park: Park)

    @Delete
    fun removePark(park: Park)

    @Query("SELECT * FROM park ORDER BY id")
    fun getParks(): Flow<List<Park>>

    @Transaction
    fun addParks(parks: List<Park>) {
        parks.forEach {
            addPark(it)
        }
    }

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addVisitStamp(stamp: VisitStamp)

    @Query("SELECT * FROM visit_stamp where parkId = :parkId")
    fun getVisitStamps(parkId: Int): Flow<List<VisitStamp>>

    @Query("SELECT COUNT(DISTINCT parkId) FROM visit_stamp")
    fun getParksVisitedCounter(): Flow<Int>

}