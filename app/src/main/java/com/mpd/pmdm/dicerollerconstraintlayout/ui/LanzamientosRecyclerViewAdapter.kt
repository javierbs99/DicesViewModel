package com.mpd.pmdm.dicerollerconstraintlayout.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.mpd.pmdm.dicerollerconstraintlayout.data.database.Lanzamiento

import com.mpd.pmdm.dicerollerconstraintlayout.databinding.FragmentItemBinding

class LanzamientosRecyclerViewAdapter() :
    ListAdapter<Lanzamiento, LanzamientosRecyclerViewAdapter.LanzamientoViewHolder>(DiffUtilItemCallback){

    companion object{
        val DiffUtilItemCallback = object: DiffUtil.ItemCallback<Lanzamiento>(){
            override fun areItemsTheSame(oldItem: Lanzamiento, newItem: Lanzamiento): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Lanzamiento, newItem: Lanzamiento): Boolean {
                return oldItem == newItem
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LanzamientoViewHolder {

        return LanzamientoViewHolder(
            FragmentItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }


    override fun onBindViewHolder(holder: LanzamientoViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }


    inner class LanzamientoViewHolder(val binding: FragmentItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(lanzamiento: Lanzamiento){
            binding.id.text = lanzamiento.id.toString()
            binding.fecha.text = lanzamiento.fecha.toString()
            binding.dado1.text = lanzamiento.dado1.toString()
            binding.dado2.text = lanzamiento.dado2.toString()
        }
    }

    /*@SuppressLint("NotifyDataSetChanged")
    fun updateList(newModulesList: List<Lanzamiento>){
        lanzamientosList = newModulesList
        notifyDataSetChanged()
    }*/

}