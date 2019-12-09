package com.halcyonmobile.typedrecyclerviewadapter

import androidx.recyclerview.widget.DiffUtil

/**
 * Default [DiffUtil.ItemCallback] which works for every [RecyclerItem] implementation.
 *
 * Note that this requires the [RecyclerItem.id] to be unique in the whole set. If two [RecyclerItem] implementation can have the same id, than
 * [areItemsTheSame] will not work correctly.
 */
open class RecyclerItemDiffUtilCallback : DiffUtil.ItemCallback<RecyclerItem>() {

    override fun areItemsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem) = oldItem.id == newItem.id

    override fun areContentsTheSame(oldItem: RecyclerItem, newItem: RecyclerItem) = oldItem == newItem
}