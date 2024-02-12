package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.core.LanzamientoApp
import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentLanzamientosListBinding
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.LanzamientosViewModel
import com.mpd.pmdm.dicerollerconstraintlayout.ui.viewmodel.LanzamientosViewModelFactory

class LanzamientosListFragment : Fragment() {
    private var _binding: FragmentLanzamientosListBinding? = null
    private val binding get() = _binding!!

    private val viewModel: LanzamientosViewModel by activityViewModels {
        LanzamientosViewModelFactory(
            (activity?.application as LanzamientoApp).appRepository
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLanzamientosListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.list.layoutManager = LinearLayoutManager(context)
        val adapter = LanzamientosRecyclerViewAdapter()
        binding.list.adapter = adapter

        viewModel.allLanzamientos.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}