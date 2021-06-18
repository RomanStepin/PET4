package com.example.pet4.data.models

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

/**
 * Город с именем, id и координатами.
 */
@Entity
class Place {
    @PrimaryKey(autoGenerate = true)
    var primKey: Long = 0
    var id: Long = 0
    var name: String = ""
    @Embedded
    var coord: Coord = Coord()

}