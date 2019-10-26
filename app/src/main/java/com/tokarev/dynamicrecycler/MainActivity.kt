package com.tokarev.dynamicrecycler

import android.os.Bundle
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.VERTICAL

private const val ITEMS_AMOUNT = 10

class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private val dynamicListAdapter: DynamicListAdapter = DynamicListAdapter()
    private val items: MutableList<String> = mutableListOf()
    private var itemsCounter: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val recyclerView: RecyclerView = findViewById(R.id.activity_main_list)
        val addItemsAbove: Button = findViewById(R.id.activity_main_add_items_above_button)
        val addItemsBelow: Button = findViewById(R.id.activity_main_add_items_below_button)
        val removeItemsAbove: Button = findViewById(R.id.activity_main_remove_items_above_button)
        val removeItemsBelow: Button = findViewById(R.id.activity_main_remove_items_below_button)

        recyclerView.layoutManager = LinearLayoutManager(this, VERTICAL, false)
        recyclerView.adapter = dynamicListAdapter

        addItemsAbove.setOnClickListener { addItemsAbove() }
        addItemsBelow.setOnClickListener { addItemsBelow() }

        removeItemsAbove.setOnClickListener { removeItemsAbove() }
        removeItemsBelow.setOnClickListener { removeItemsBelow() }
    }

    private fun addItemsAbove() {
        val newItems: List<String> = mutableListOf<String>().apply {
            repeat(ITEMS_AMOUNT) {
                itemsCounter++
                add(itemsCounter.toString())
            }
        }

        items.addAll(0, newItems)
        dynamicListAdapter.addItemsAbove(newItems)
    }

    private fun addItemsBelow() {
        val newItems: List<String> = mutableListOf<String>().apply {
            repeat(ITEMS_AMOUNT) {
                itemsCounter++
                add(itemsCounter.toString())
            }
        }

        items.addAll(newItems)
        dynamicListAdapter.addItemsBelow(newItems)
    }

    private fun removeItemsAbove() {
        if (items.size < ITEMS_AMOUNT) return

        items.subList(0, ITEMS_AMOUNT).clear()
        dynamicListAdapter.removeItemsAbove(ITEMS_AMOUNT)
    }

    private fun removeItemsBelow() {
        if (items.size < ITEMS_AMOUNT) return

        items.subList(items.size - ITEMS_AMOUNT, items.size).clear()
        dynamicListAdapter.removeItemsBelow(ITEMS_AMOUNT)
    }
}
