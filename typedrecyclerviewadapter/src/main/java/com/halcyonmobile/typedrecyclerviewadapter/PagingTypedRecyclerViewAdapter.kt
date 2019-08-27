package com.halcyonmobile.typedrecyclerviewadapter

import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil

/**
 * Extension for [TypedRecyclerViewAdapter] which supports paginated lists by showing a loading indicator at the end of the list while the determination of the
 * additional data is in progress.
 *
 * The loading item by default is a circular progress loading indicator which uses the ?attr/colorPrimary color. It can be customized by overriding the
 * [onCreateLoadingMoreViewHolder] and [onBindLoadingMoreViewHolder] methods. The visibility of the loading indicator can be handled by [onLoadingMore] and
 * [onLoadingMoreDone] methods.
 */
abstract class PagingTypedRecyclerViewAdapter<UiModel : RecyclerItem>(diffCallback: DiffUtil.ItemCallback<UiModel>) :
    TypedRecyclerViewAdapter<UiModel>(diffCallback) {

    private var isInLoadingState = false

    final override fun getItemCount(): Int = super.getItemCount() + if (isInLoadingState) 1 else 0

    final override fun getItemViewType(position: Int): Int = when (position) {
        super.getItemCount() -> LOADING_ITEM_ID
        else -> super.getItemViewType(position)
    }

    final override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypedAdapterBaseFactory.BindingViewHolder<ViewDataBinding> =
        when (viewType) {
            LOADING_ITEM_ID -> onCreateLoadingMoreViewHolder(parent)
            else -> super.onCreateViewHolder(parent, viewType)
        }

    override fun onBindViewHolder(holder: TypedAdapterBaseFactory.BindingViewHolder<ViewDataBinding>, position: Int) {
        when (position) {
            super.getItemCount() -> onBindLoadingMoreViewHolder(holder, position)
            else -> super.onBindViewHolder(holder, position)
        }
    }

    override fun onBindViewHolder(holder: TypedAdapterBaseFactory.BindingViewHolder<ViewDataBinding>, position: Int, payloads: MutableList<Any>) {
        if (position == super.getItemCount()) {
            onBindViewHolder(holder, position)
        } else {
            super.onBindViewHolder(holder, position, payloads)
        }
    }

    open fun onCreateLoadingMoreViewHolder(parent: ViewGroup) =
        TypedAdapterBaseFactory.BindingViewHolder.create<LoadingItemBinding>(parent, R.layout.item_loading)

    open fun onBindLoadingMoreViewHolder(holder: TypedAdapterBaseFactory.BindingViewHolder<ViewDataBinding>, position: Int) = Unit

    fun onLoadingMore() {
        if (!isInLoadingState) {
            isInLoadingState = true
            notifyItemInserted(super.getItemCount())
        }
    }

    fun onLoadingMoreDone() {
        if (isInLoadingState) {
            isInLoadingState = false
            notifyItemRemoved(super.getItemCount())
        }
    }

    companion object {
        private const val LOADING_ITEM_ID = -1
    }
}