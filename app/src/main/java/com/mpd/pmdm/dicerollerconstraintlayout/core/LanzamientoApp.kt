package com.mpd.pmdm.dicerollerconstraintlayout.core

import android.app.Application
import com.mpd.pmdm.dicerollerconstraintlayout.data.AppRepository
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.LanzamientoDatabase

class LanzamientoApp: Application() {
    private val lanzamientoDatabse: LanzamientoDatabase by lazy { LanzamientoDatabase.getDataBase(this) }
    val appRepository: AppRepository by lazy { AppRepository((lanzamientoDatabse.lanzamientoDao())) }
}