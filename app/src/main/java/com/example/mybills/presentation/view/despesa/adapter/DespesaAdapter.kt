package com.example.mybills.presentation.view.despesa.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mybills.databinding.ItemDespesaCardBinding
import com.example.mybills.domain.Despesa
import java.text.NumberFormat


class DespesaAdapter(
    val onClickDelete: (id: Int) -> Any,
    val onclickUpdadte: (despesa: Despesa) -> Any
) : ListAdapter<Despesa, DespesaAdapter.viewHolder>(DiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemDespesaCardBinding.inflate(inflater, parent, false)

        return viewHolder(binding)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.bind(getItem(position))
    }


    inner class viewHolder(
        private val binding: ItemDespesaCardBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Despesa) {
            binding.txtValue.text = NumberFormat.getCurrencyInstance().format((item.valor / 100))
            binding.txtData.text = item.data
            binding.deleteBtn.setOnClickListener {
                onClickDelete(item.id)
                notifyDataSetChanged()
            }

            binding.updateBtn.setOnClickListener {
                onclickUpdadte(item)
            }

        }
    }


    class DiffCallback : DiffUtil.ItemCallback<Despesa>() {
        override fun areItemsTheSame(oldItem: Despesa, newItem: Despesa) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Despesa, newItem: Despesa) =
            oldItem.id == newItem.id

    }


}