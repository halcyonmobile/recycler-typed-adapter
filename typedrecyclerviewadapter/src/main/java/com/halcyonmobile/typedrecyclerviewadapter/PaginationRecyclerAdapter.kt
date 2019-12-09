package com.halcyonmobile.typedrecyclerviewadapter

import androidx.recyclerview.widget.DiffUtil

/**
 * Simple implementation for [PagingTypedRecyclerViewAdapter]. Nothing extra supported by this, only the basic list handling and pagination with loading more.
 *
 * Provides a [DiffUtil.ItemCallback] for a list of [RecyclerItem]. For requirements by this DiffUtil implementation, see [RecyclerItemDiffUtilCallback].
 */
class PaginationRecyclerAdapter(diffUtilCallback: DiffUtil.ItemCallback<RecyclerItem> = RecyclerItemDiffUtilCallback()) :
    PagingTypedRecyclerViewAdapter<RecyclerItem>(diffUtilCallback)