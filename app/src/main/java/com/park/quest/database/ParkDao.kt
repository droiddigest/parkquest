package com.park.quest.database

import androidx.room.*
import kotlinx.coroutines.flow.Flow

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 * Email - npherwani@reachmobi.com
 */

@Dao
interface ParkDao {
    @Insert
    fun addPark(park: Park)

    @Delete
    fun removePark(park: Park)

    @Query("SELECT * FROM park ORDER BY pageId")
    fun getParks(): Flow<List<Park>>

    @Query("SELECT * FROM park where pageId = :page")
    fun getPark(page: Int): Flow<Park>

    @Transaction
    fun addParks(parks: List<Park>) {
        parks.forEach {
            addPark(it)
        }
    }

    @Transaction
    fun addAndRemoveParks(park: Park) {
        addPark(park)
        removePark(park = park)
    }


    @Insert
    fun addStamp(stamp: Stamp)

    @Query("SELECT * FROM stamp where id = :id")
    fun getStamp(id: Int): Flow<Stamp>

    @Transaction
    fun addStamps(stamps: List<Stamp>) {
        stamps.forEach {
            addStamp(it)
        }
    }


}