package com.halcyonmobile.typedrecyclerviewadapter

import androidx.recyclerview.widget.DiffUtil

/**
 * Simple implementation for [TypedAdapterBaseFactory]. Nothing extra supported by this, only the basic list handling.
 *
 * Provides a [DiffUtil.ItemCallback] for a list of [RecyclerItem]. For requirements by this DiffUtil implementation, see [RecyclerItemDiffUtilCallback].
 */
class RecyclerAdapter(diffCallback: DiffUtil.ItemCallback<RecyclerItem> = RecyclerItemDiffUtilCallback()) : TypedRecyclerViewAdapter<RecyclerItem>(diffCallback)