package com.halcyonmobile.typedrecyclerviewadapter

/**
 * Exception thrown whenever [com.halcyonmobile.typedrecyclerviewadapter.TypedRecyclerViewAdapter] can't find a valid factory for a given
 * [com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem].
 */
class FactoryNotFoundException @JvmOverloads constructor(
    message: String = "Factory not found for the given type",
    cause: Throwable? = null
) : IllegalStateException(message, cause)