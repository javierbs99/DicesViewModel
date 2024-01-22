package com.mpd.pmdm.dicerollerconstraintlayout

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentBotonBinding
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentDadosBinding


class BotonFragment : Fragment() {
    private var _binding: FragmentBotonBinding? = null
    private val binding get() = _binding!!

    private val dices: TwoDicesViewModel by activityViewModels {
        TwoDicesViewModelFactory(6)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentBotonBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnRoll.setOnClickListener {
            dices.rollDices()
        }
    }
}