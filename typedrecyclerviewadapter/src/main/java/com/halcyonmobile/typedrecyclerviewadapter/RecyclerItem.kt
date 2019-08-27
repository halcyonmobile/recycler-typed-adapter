package com.halcyonmobile.typedrecyclerviewadapter

/**
 * Interface to implement by every item submitted to any of the [TypedRecyclerViewAdapter] implementations.
 */
interface RecyclerItem {

    /**
     * Unique value which identifies the given item.
     */
    val id: String

    override fun equals(other: Any?): Boolean
}