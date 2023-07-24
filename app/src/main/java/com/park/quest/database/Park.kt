package com.park.quest.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */
@Entity(tableName = "park")
data class Park(
    @PrimaryKey
    val pageId: Int,
    val name: String,
    val aboutUrl: String,
    val stampId: Int = -1,
    val time: Long = -1,
)

@Entity(tableName = "stamp")
data class Stamp(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String
)


