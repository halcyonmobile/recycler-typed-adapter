package com.halcyonmobile.typedrecyclerviewadapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.halcyonmobile.typedrecyclerviewadapter.TypedAdapterBaseFactory.BindingViewHolder.Companion.create

/**
 * Base Factory for items in [TypedRecyclerViewAdapter].
 *
 * Every implementation has to provide a [RecyclerItem], [ViewDataBinding] implementation.
 */
abstract class TypedAdapterBaseFactory<UiModel : RecyclerItem, ViewBinding : ViewDataBinding> {

    /**
     * Provides the layout resource which will be used to create the [RecyclerView.ViewHolder].
     */
    @LayoutRes
    abstract fun getLayoutId(): Int

    /**
     * Determines if the current factory can handle the given [item].
     *
     * @param item UiModel for which the compatibility should be handled.
     *
     * @return true if the [item] can be handled by this factory, true otherwise.
     */
    abstract fun canHandle(item: RecyclerItem): Boolean

    /**
     * Binds the [model] to the item view.
     *
     * Every Factory has to take care how to bind a [RecyclerItem] Ui model implementation to the ViewHolder.
     */
    abstract fun bind(model: UiModel, holder: BindingViewHolder<ViewBinding>, position: Int, payloads: List<Any>)

    @CallSuper
    open fun createViewHolder(parent: ViewGroup, @LayoutRes viewType: Int): BindingViewHolder<ViewBinding> = create(parent, viewType)

    /**
     * A [RecyclerView.ViewHolder] implementation based on DataBinding.
     */
    class BindingViewHolder<out ViewBinding : ViewDataBinding> private constructor(val binding: ViewBinding) : RecyclerView.ViewHolder(binding.root) {

        companion object {

            /**
             * Instantiates a [BindingViewHolder] defined with [ViewBinding] binding implementation.
             *
             * @param parent [ViewGroup] to which the item will belong.
             * @param layoutId The layout to inflate.
             */
            fun <ViewBinding : ViewDataBinding> create(parent: ViewGroup, @LayoutRes layoutId: Int): BindingViewHolder<ViewBinding> {
                val binding: ViewBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.context), layoutId, parent, false)
                return BindingViewHolder(binding)
            }
        }
    }
}