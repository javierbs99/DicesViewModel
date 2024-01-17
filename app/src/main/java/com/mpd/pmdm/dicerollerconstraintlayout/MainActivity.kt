package com.mpd.pmdm.dicerollerconstraintlayout

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val twoDicesVM: TwoDicesViewModel by viewModels<TwoDicesViewModel>{
        TwoDicesViewModelFactory(6)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnRoll.setOnClickListener {
            twoDicesVM.rollDices()
        }

        twoDicesVM.caraDado1.observe(this){
            rollDice(it, binding.ivDice1)
        }

        twoDicesVM.caraDado2.observe(this){
            rollDice(it, binding.ivDice2)
        }

    }


    /**
     * Función que crea un dado, lo tira, y muestra su valor en la IU
     */
    private fun rollDice(currentSide: Int, imageViewDice: ImageView) {
        val imgDiceResource = when(currentSide){
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            6 -> R.drawable.dice_6
            //Esto no se debería dar, pero me obliga al usar when como expresión
            else -> R.drawable.dice_6
        }

        imageViewDice.setImageResource(imgDiceResource)
        //Le damos una descripción a la imagen para aportar accesibilidad
        imageViewDice.contentDescription = currentSide.toString()
    }
}


