package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Divider
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.mpd.pmdm.dicerollerconstraintlayout.R
import com.mpd.pmdm.dicerollerconstraintlayout.core.LanzamientoApp
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.Lanzamiento
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

    @OptIn(ExperimentalFoundationApi::class)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.composeView.setContent {
            val listaLanzamientos = viewModel.allLanzamientos.observeAsState(emptyList())
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(BorderStroke(1.dp, Color.Blue))
                    .padding(10.dp)
            ) {
                stickyHeader {
                    Cabecera()
                }
                items(listaLanzamientos.value.size){
                    RegistroLanzamiento(listaLanzamientos.value, it)
                }
            }
        }
    }

    @Composable
    private fun Cabecera() {
        Surface(Modifier.height(30.dp)){
            Column{
                Row(){
                    Text(text = "Id", Modifier.weight(1.0f))
                    Text(text = "Fecha y hora", Modifier.weight(3.0f))
                    Text(text = "Dado 1", Modifier.weight(1.0f))
                    Text(text = "Dado 2", Modifier.weight(1.0f))
                }
                Divider()
            }
        }
    }

    @Composable
    private fun RegistroLanzamiento(moduleList: List<Lanzamiento>, pos: Int) {
        val bg = if(pos % 2 == 0) Color.LightGray else Color.Transparent
        val module = moduleList[pos]
        Row(Modifier.background(bg)){
            Text(text = module.id.toString(), Modifier.weight(1.0f))
            Text(text = module.fecha, Modifier.weight(3.0f))
            Text(text = module.dado1.toString(), Modifier.weight(1.0f))
            Text(text = module.dado2.toString(), Modifier.weight(1.0f))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}