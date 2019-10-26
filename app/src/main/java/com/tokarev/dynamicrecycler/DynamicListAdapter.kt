package com.tokarev.dynamicrecycler

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DynamicListAdapter : RecyclerView.Adapter<ItemViewHolder>() {

    private val currentItems: MutableList<String> = mutableListOf()

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemViewHolder = ItemViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_element, parent, false)
    )

    override fun getItemCount(): Int = currentItems.size

    override fun onBindViewHolder(
        holder: ItemViewHolder,
        position: Int
    ) {
        val item: String = currentItems[position]

        holder.bindItem(item)
    }

    fun addItemsAbove(items: List<String>) {
        currentItems.addAll(0, items)
        notifyItemRangeInserted(0, items.size)
    }

    fun addItemsBelow(items: List<String>) {
        currentItems.addAll(items)
        notifyItemRangeInserted(currentItems.size, items.size)
    }

    fun removeItemsAbove(amount: Int) {
        // https://docs.oracle.com/javase/1.5.0/docs/api/java/util/List.html#subList%28int,%20int%29
        currentItems.subList(0, amount).clear()
        notifyItemRangeRemoved(0, amount)
    }

    fun removeItemsBelow(amount: Int) {
        val startPosition: Int = currentItems.size - amount
        val lastPosition: Int = currentItems.size

        // https://docs.oracle.com/javase/1.5.0/docs/api/java/util/List.html#subList%28int,%20int%29
        currentItems.subList(startPosition, lastPosition).clear()
        notifyItemRangeRemoved(startPosition, amount)
    }
}