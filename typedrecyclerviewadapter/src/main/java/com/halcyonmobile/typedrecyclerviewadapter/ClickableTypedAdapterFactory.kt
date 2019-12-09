package com.halcyonmobile.typedrecyclerviewadapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

/**
 * An abstract implementation of [TypedAdapterBaseFactory] which supports [android.view.View.OnClickListener] by default.
 */
abstract class ClickableTypedAdapterFactory<UiModel : RecyclerItem, ViewBinding : ViewDataBinding>(
    private val onClickListener: (position: Int) -> Unit
) : TypedAdapterBaseFactory<UiModel, ViewBinding>() {

    override fun createViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder<ViewBinding> =
        super.createViewHolder(parent, viewType).apply {
            binding.root.setOnClickListener {
                if (adapterPosition != RecyclerView.NO_POSITION) {
                    onClickListener(adapterPosition)
                }
            }
        }
}
