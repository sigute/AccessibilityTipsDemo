package com.github.sigute.accessibilitydemo.recyclerview

import android.os.Handler
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.github.sigute.accessibilitydemo.R

class ItemsAdapter(
        private val findViewHolderAtPosition: (Int) -> ItemViewHolder? = { null }
) : RecyclerView.Adapter<ItemViewHolder>() {

    private var items = mutableListOf<Item>()
    private var deleteMode = false
    private var modifyFocus = false

    fun setItems(items: List<Item>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    fun setDeleteMode(deleteMode: Boolean, modifyFocus: Boolean) {
        this.deleteMode = deleteMode
        this.modifyFocus = modifyFocus
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder =
            ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.view_holder_item, parent, false))

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        holder.setItem(items[position], deleteMode) { itemDeleted(it) }
    }

    private fun itemDeleted(item: Item) {
        val position = items.indexOf(item)
        items.removeAt(position)
        items.add(position, Item(item.name, deleted = true))

        if (modifyFocus) {
            // delay is done first, to allow recycler view time to refresh view holders
            // after the delay we are getting the view holder at the right position
            // if view holder is determined before delay, it might be recycled and wrong view holder will get focus
            Handler().postDelayed({
                //in production this could use more error checking!
                val viewHolder = findViewHolderAtPosition(position)
                viewHolder?.let {
                    with(it.itemView) {
                        isFocusableInTouchMode = true
                        clearFocus()
                        requestFocus()
                        isFocusableInTouchMode = false
                    }
                }
            }, 50)
        }
    }

    override fun getItemCount(): Int = items.size
}