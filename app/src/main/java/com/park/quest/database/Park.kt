package com.park.quest.database

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */
@Entity(tableName = "park",  primaryKeys = ["pageId", "position"])
data class Park(
    val pageId: Int,
    val name: String,
    val aboutUrl: String,
    val stampId: Int,
    var position: Int = -1,
    var rotation: Float = -1F,
    var time: Long = -1,
)

@Entity(tableName = "stamp")
data class Stamp(
    @PrimaryKey
    val id: Int,
    val name: String
)


