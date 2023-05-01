package com.tamayo.code_base_sdk.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.tamayo.code_base_sdk.databinding.CharacterItemBinding
import com.tamayo.code_base_sdk.domain.DomainCharacter


/**
 * Adapter class for the app's RecyclerView that shows a list of DomainCharacter items.
 * @property itemSet The list of DomainCharacter items to display.
 * @property onItemClick A lambda function that is triggered when an item is clicked.
 */
class AppAdapter(
    private var itemSet: MutableList<DomainCharacter> = mutableListOf(),
    private val onItemClick: (DomainCharacter) -> Unit
) : RecyclerView.Adapter<ItemViewHolder>() {

    private var searchList: MutableList<DomainCharacter> = mutableListOf()

    /**
     * Updates the list of items displayed in the RecyclerView and notifies the adapter of the change.
     * @param newItems The new list of DomainCharacter items to display.
     */
    @SuppressLint("NotifyDataSetChanged")
    fun updateItems(newItems: List<DomainCharacter>) {
        if (itemSet != newItems) {
            itemSet.clear()
            itemSet.addAll(newItems)
            notifyDataSetChanged()
            searchList.clear()
            searchList.addAll(newItems)

        }
    }

    /**
     * Filters the [itemSet] based on the provided [query] and sets the filtered list as the [searchList].
     * If the [query] is empty, the [itemSet] is used as the [searchList].
     * Finally, notifies the adapter of the data set change to update the displayed data.
     * @param query the search query string
     */
    @SuppressLint("NotifyDataSetChanged")
    fun performSearch(query: String) {
        val filterCharacters = if (query.isBlank()) {
            searchList.toMutableList()
        } else {
            searchList.filter { it.name.contains(query, ignoreCase = true) }.toMutableList()
        }
        itemSet.clear()
        itemSet.addAll(filterCharacters)
        notifyDataSetChanged()
    }

    /**
     * Creates a new ViewHolder object for each item in the list of DomainCharacter items.
     * @param parent The ViewGroup that contains the item.
     * @param viewType The type of view for the item.
     * @return A new ViewHolder object for the item.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
        ItemViewHolder(
            CharacterItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    /**
     * Returns the number of items in the list of DomainCharacter items.
     * @return The number of items in the list.
     */
    override fun getItemCount(): Int = itemSet.size

    /**
     * Binds a DomainCharacter item to a ViewHolder object and sets up the click listener.
     * @param holder The ViewHolder object to bind the item to.
     * @param position The position of the item in the list.
     */
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) =
        holder.bind(itemSet[position], onItemClick)

}


/**
 * ViewHolder class for a single DomainCharacter item in the app's RecyclerView.
 * @property binding The view binding object for the item.
 */
class ItemViewHolder(
    private val binding: CharacterItemBinding
) : ViewHolder(binding.root) {

    /**
     * Binds a DomainCharacter item to the view holder.
     * @param item The DomainCharacter item to bind.
     * @param onItemClick A lambda function that is triggered when the item is clicked.
     */
    fun bind(item: DomainCharacter, onItemClick: (DomainCharacter) -> Unit) {

        binding.tvName.text = item.name

        itemView.setOnClickListener{
            onItemClick(item)
        }

    }

}