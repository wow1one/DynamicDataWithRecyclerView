package com.tokarev.dynamicrecycler

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class ItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    private val text: TextView = itemView.findViewById(R.id.item_element_text)

    fun bindItem(item: String) {
        text.text = item
    }
}