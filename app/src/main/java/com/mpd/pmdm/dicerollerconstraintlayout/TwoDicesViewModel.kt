package com.mpd.pmdm.dicerollerconstraintlayout

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class TwoDicesViewModel(numSides: Int): ViewModel() {
    private val dice1 = Dice(numSides)
    private val dice2 = Dice(numSides)

    //Qué va a ser lo mutable y observable en nuestra aplicación?
    //La cara de cada uno de los dados

    //Si fuesen las dos instancias de Dice, sólo se notificaría al modificar
    //la instancia!
    private val _caraDado1 = MutableLiveData<Int>(dice1.roll())
    val caraDado1: LiveData<Int> = _caraDado1

    private val _caraDado2 = MutableLiveData<Int>(dice2.roll())
    val caraDado2: LiveData<Int> = _caraDado2

    init{
        Log.d("TwoDicesViewModel", "Creado ViewModel con $numSides caras")
    }

    fun rollDices(){
        _caraDado1.value = dice1.roll()
        _caraDado2.value = dice2.roll()
    }


}
//Clase que implementa ViewModelProvider.Factory para permitirnos
//crear instancias de ViewModel con parámetros iniciales
class TwoDicesViewModelFactory(val numCaras: Int): ViewModelProvider.Factory{


    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(TwoDicesViewModel::class.java))
            return TwoDicesViewModel(numCaras) as T
        throw IllegalArgumentException("Invalid class for factory: ${modelClass.name}")
    }
}

