package com.mpd.pmdm.dicerollerconstraintlayout.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface LanzamientoDao {

    @Query("SELECT * FROM lanzamiento")
    fun getAllLanzamientos(): LiveData<List<Lanzamiento>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(lanzamiento: Lanzamiento)

    @Query("DELETE FROM lanzamiento")
    suspend fun clearAll()

}