package com.example.mybills.presentation.view.despesa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mybills.databinding.ItemReceitasCardBinding
import com.example.mybills.domain.Despesa
import com.example.mybills.ultis.FormatesTypes


class DespesaAdapter: ListAdapter<Despesa, DespesaAdapter.viewHolder>(DiffCallback()) {

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
        fun bind(item : Despesa){
            binding.txtValue.text = FormatesTypes.formatMoney(item.valor)
            binding.txtData.text = item.data

        }
    }


    class DiffCallback: DiffUtil.ItemCallback<Despesa>(){
        override fun areItemsTheSame(oldItem: Despesa, newItem: Despesa) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Despesa, newItem: Despesa) = oldItem.id == newItem.id

    }


}