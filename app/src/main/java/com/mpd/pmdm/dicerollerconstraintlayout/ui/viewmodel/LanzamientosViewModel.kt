package com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mpd.pmdm.dicerollerconstraintlayout.data.AppRepository
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.Lanzamiento
import kotlinx.coroutines.launch
import java.time.LocalDateTime

class LanzamientosViewModel(private val appRepository: AppRepository): ViewModel() {
    val allLanzamientos: LiveData<List<Lanzamiento>> = appRepository.allLanzamientos

    fun insert(fecha: String, dado1: Byte, dado2: Byte){
        viewModelScope.launch {
            val lanzamiento = Lanzamiento(fecha = fecha, dado1 = dado1, dado2 = dado2)
            appRepository.insert(lanzamiento)
        }
    }

    fun clearAll(){
        viewModelScope.launch {
            appRepository.clearAll()
        }
    }
}

class LanzamientosViewModelFactory(private val repository: AppRepository): ViewModelProvider.Factory{
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(LanzamientosViewModel::class.java))
            return LanzamientosViewModel(repository) as T
        throw IllegalArgumentException("Error al instanciar el ViewModel")
    }
}