package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.Lanzamiento

import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentItemBinding

class LanzamientosRecyclerViewAdapter(
    private var lanzamientosList: List<Lanzamiento> = emptyList()
) : RecyclerView.Adapter<LanzamientosRecyclerViewAdapter.ViewHolder>()  {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        return ViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = lanzamientosList[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = lanzamientosList.size

    inner class ViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lanzamiento: Lanzamiento){
            binding.id.text = lanzamiento.id.toString()
            binding.fecha.text = lanzamiento.fecha.toString()
            binding.dado1.text = lanzamiento.dado1.toString()
            binding.dado2.text = lanzamiento.dado2.toString()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun updateList(newModulesList: List<Lanzamiento>){
        lanzamientosList = newModulesList
        notifyDataSetChanged()
    }

}