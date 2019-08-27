package com.halcyonmobile.recyclertypedadapter.items.user

import com.halcyonmobile.recyclertypedadapter.data.User
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem

data class UserRecyclerItem(val user: User) : RecyclerItem {

    override val id: String
        get() = user.id
}