package com.example.mybills.presentation.view.receitas.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.mybills.databinding.ItemReceitasCardBinding
import com.example.mybills.domain.Receita
import java.text.NumberFormat

class ReceitasAdapter(val onClickDelete:(id:Int) -> Any,
                      val onclickUpdadte:(receita : Receita) -> Any ): ListAdapter<Receita, ReceitasAdapter.viewHolder>(DiffCallback()) {

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
            binding.txtValue.text = NumberFormat.getCurrencyInstance().format((item.valor/100))
            binding.txtData.text = item.data
            binding.deleteBtn.setOnClickListener {
                onClickDelete(item.id)

            }
            binding.updateBtn.setOnClickListener {
                onclickUpdadte(item)
            }
        }
    }


    class DiffCallback: DiffUtil.ItemCallback<Receita>(){
        override fun areItemsTheSame(oldItem: Receita, newItem: Receita) = oldItem == newItem

        override fun areContentsTheSame(oldItem: Receita, newItem: Receita) = oldItem.id == newItem.id


    }

}