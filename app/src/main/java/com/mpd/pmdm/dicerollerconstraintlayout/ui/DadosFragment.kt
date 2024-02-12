package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.activityViewModels
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentDadosBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModelFactory

class DadosFragment : Fragment() {
    private var _binding: FragmentDadosBinding? = null
    private val binding get() = _binding!!

    //Referenciamos las instancias de los ViewModel con el delegado que los crea en la Actividad
    private val twoDices: TwoDicesViewModel by activityViewModels {
        TwoDicesViewModelFactory(6)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentDadosBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        twoDices.caraDado1.observe(viewLifecycleOwner){
            caraActual ->
            rollDice(caraActual, binding.ivDice1)
        }

        twoDices.caraDado2.observe(viewLifecycleOwner){
                caraActual ->
            rollDice(caraActual, binding.ivDice2)
        }
    }


    private fun rollDice(currentSide: Int, imageViewDice: ImageView) {
        val imgDiceResource = when (currentSide) {
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