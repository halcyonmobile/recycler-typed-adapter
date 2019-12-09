package com.halcyonmobile.recyclertypedadapter.items.user

import com.halcyonmobile.recyclertypedadapter.R
import com.halcyonmobile.recyclertypedadapter.UserItemBinding
import com.halcyonmobile.typedrecyclerviewadapter.ClickableTypedAdapterFactory
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem

class UserRecyclerItemFactory(clickListener: (Int) -> Unit) :
    ClickableTypedAdapterFactory<UserRecyclerItem, UserItemBinding>(clickListener) {

    override fun getLayoutId(): Int = R.layout.item_user

    override fun canHandle(item: RecyclerItem): Boolean = item is UserRecyclerItem

    override fun bind(model: UserRecyclerItem, holder: BindingViewHolder<UserItemBinding>, position: Int, payloads: List<Any>) {
        holder.binding.user = model.user
    }
}