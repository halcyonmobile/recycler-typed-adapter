package com.halcyonmobile.recyclertypedadapter.items.header

import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem

data class HeaderRecyclerItem(val title: String) : RecyclerItem {

    override val id: String
        get() = title
}