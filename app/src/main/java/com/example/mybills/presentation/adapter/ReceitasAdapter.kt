package com.example.mybills.presentation.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mybills.databinding.ItemReceitasCardBinding
import com.example.mybills.domain.Receita

class ReceitasAdapter: ListAdapter<Receita, ReceitasAdapter.viewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemReceitasCardBinding.inflate(inflater, parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(getItem(position))
    }




    inner class viewHolder(
        private val binding: ItemReceitasCardBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(item : Receita){
            binding.txtValue.text = item.valor.toString()
            binding.txtData.text = item.data

        }
    }


    class DiffCallback: DiffUtil.ItemCallback<Receita>(){
        override fun areItemsTheSame(oldItem: Receita, newItem: Receita) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Receita, newItem: Receita) = oldItem.id == newItem.id


    }

}