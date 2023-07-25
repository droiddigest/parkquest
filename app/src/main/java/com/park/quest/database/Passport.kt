package com.park.quest.database

import androidx.room.*

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 * Considering 1 Park has only 1 Stamp
 */
@Entity(tableName = "park")
data class Park(
    @PrimaryKey
    val id: Int,
    val name: String,
    val link: String,
    val stamp: String,
)

@Entity(tableName = "visit_stamp",  primaryKeys = ["parkId", "stampPosition"], foreignKeys = [ForeignKey(
    entity = Park::class,
    childColumns = ["parkId"],
    parentColumns = ["id"],
    onDelete = ForeignKey.CASCADE
)])
data class VisitStamp(
    val parkId: Int,
    var stampPosition: Int,
    var stampRotation: Float,
    var visitTime: Long,
    @Embedded(prefix = "visit_stamp_")
    val park: Park
)


