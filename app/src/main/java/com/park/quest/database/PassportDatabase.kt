package com.park.quest.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */
@Database(
    entities = [
        Park::class,
        VisitStamp::class
    ],
    version = 1
)


abstract class PassportDatabase : RoomDatabase() {

    abstract val passportDao: PassportDao

    companion object {
        const val DATABASE_NAME = "park_quest_db"
    }
}
