package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDateTime

@Entity(tableName = "lanzamiento")
data class Lanzamiento (

    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val fecha: String,
    val dado1: Byte,
    val dado2: Byte

)