package com.park.quest.rawdata

/**
 * Created by Nirbhay Pherwani on 7/24/2023.
 */
data class RawPark (val name: String, val pageId: Int, val aboutUrl: String)

data class RawStamp(val name: String)

data class RawParkStamp(val parks: List<RawPark>, val stamps: List<RawStamp>)