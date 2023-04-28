package com.tamayo.code_base_sdk.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tamayo.code_base_sdk.databinding.CharacterItemBinding
import com.tamayo.code_base_sdk.domain.DomainCharacter


class AppAdapter(
    private val itemSet: MutableList<DomainCharacter> = mutableListOf(),
    private val onItemClick: (DomainCharacter) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {

    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<DomainCharacter>) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)
            notifyDataSetChanged()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    override fun getItemCount(): Int = itemSet.size

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)

}

class ItemViewHolder(
    private val binding: CharacterItemBinding
) : ViewHolder(binding.root) {

    fun bind(item: DomainCharacter, onItemClick: (DomainCharacter) -> Unit) {


    }

}