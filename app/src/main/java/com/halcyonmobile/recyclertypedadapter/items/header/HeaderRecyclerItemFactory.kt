package com.halcyonmobile.recyclertypedadapter.items.header

import com.halcyonmobile.recyclertypedadapter.HeaderItemBinding
import com.halcyonmobile.recyclertypedadapter.R
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem
import com.halcyonmobile.typedrecyclerviewadapter.TypedAdapterBaseFactory

class HeaderRecyclerItemFactory : TypedAdapterBaseFactory<HeaderRecyclerItem, HeaderItemBinding>() {

    override fun getLayoutId(): Int = R.layout.item_header

    override fun canHandle(item: RecyclerItem): Boolean = item is HeaderRecyclerItem

    override fun bind(model: HeaderRecyclerItem, holder: BindingViewHolder<HeaderItemBinding>, position: Int, payloads: List<Any>) {
        holder.binding.title.text = model.title
    }
}