package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.activityViewModels
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.mpd.pmdm.dicerollerconstraintlayout.core.LanzamientoApp
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.TwoDicesViewModelFactory
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentBotonBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.LanzamientosViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.LanzamientosViewModelFactory
import java.time.LocalDateTime


class BotonFragment : Fragment() {
    private var _binding: FragmentBotonBinding? = null
    private val binding get() = _binding!!

    private val dices: TwoDicesViewModel by activityViewModels {
        TwoDicesViewModelFactory(6)
    }
    private val lanzamientosViewModel: LanzamientosViewModel by activityViewModels{
        LanzamientosViewModelFactory((activity?.application as LanzamientoApp).appRepository)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBotonBinding.inflate(inflater, container, false)
        return binding.root
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRoll.setOnClickListener {
            dices.rollDices()
            crearHistorico()
        }

        binding.btnClearList.setOnClickListener {
            //Creamos un cuadro de diálogo para confirmar la operación
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Confirmación")
                .setMessage("Deseas borrar todos los lanzamientos?\n" +
                        "    La operación es irreversible")
                .setNegativeButton("Cancelar"){
                        dialog,_ -> dialog.cancel()
                }
                .setPositiveButton("Confirmar"){
                        _,_ -> lanzamientosViewModel.clearAll()
                }
                .setCancelable(false)
                .show()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun crearHistorico(){
        val fecha = LocalDateTime.now().toString()
        lanzamientosViewModel.insert(fecha, dices.caraDado1.value?.toByte() ?: 0, dices.caraDado2.value?.toByte() ?: 0)
    }
}