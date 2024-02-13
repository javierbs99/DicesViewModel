package com.mpd.pmdm.dicerollerconstraintlayout.data

import com.mpd.pmdm.dicerollerconstraintlayout.data.database.Lanzamiento
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.LanzamientoDao

class AppRepository(private val lanzamientoDao: LanzamientoDao) {

    val allLanzamientos = lanzamientoDao.getAllLanzamientos()


    suspend fun insert(lanzamiento: Lanzamiento) {
        lanzamientoDao.insert(lanzamiento)
    }


    suspend fun clearAll() {
        lanzamientoDao.clearAll()
    }

}