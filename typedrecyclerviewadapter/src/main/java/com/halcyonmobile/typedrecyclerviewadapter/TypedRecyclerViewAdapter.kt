package com.halcyonmobile.typedrecyclerviewadapter

import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

/**
 * Base implementation for the typed factory based [RecyclerView.Adapter]. It supports multiple item types by leveraging the handling of an item type to item
 * factories ([TypedAdapterBaseFactory] implementations).
 *
 * The adapter accepts a list of [RecyclerItem]s and requires for every [RecyclerItem] to have a valid [TypedAdapterBaseFactory] implementation, which can
 * handle the submitted [RecyclerItem]s, otherwise, a [FactoryNotFoundException] will be thrown.
 *
 * In order to support a new type to this Adapter, 2 components are required. Firstly a model class is required which implements the [RecyclerItem]. For this
 * item, a [TypedAdapterBaseFactory] implementation is required which needs to be bound to the [RecyclerItem] through [TypedAdapterBaseFactory.canHandle]
 * method. Finally, the factory implementation needs to be registered to the adapter (can be done through [addCellFactories]). This type connects the
 * model with the factory which will handle it.
 */
abstract class TypedRecyclerViewAdapter<RecyclerItemType : RecyclerItem>(diffCallback: DiffUtil.ItemCallback<RecyclerItemType>) :
    ListAdapter<RecyclerItemType, TypedAdapterBaseFactory.BindingViewHolder<ViewDataBinding>>(diffCallback) {

    private val itemFactoryList: MutableList<TypedAdapterBaseFactory<RecyclerItemType, ViewDataBinding>> = mutableListOf()

    // region Adapter methods
    @LayoutRes
    @CallSuper
    override fun getItemViewType(position: Int): Int {
        val item = super.getItem(position)

        return (itemFactoryList.firstOrNull { it.canHandle(item) } ?: throw FactoryNotFoundException()).getLayoutId()
    }

    @CallSuper
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TypedAdapterBaseFactory.BindingViewHolder<ViewDataBinding> {
        val factory = itemFactoryList.firstOrNull { it.getLayoutId() == viewType } ?: throw FactoryNotFoundException()
        return factory.createViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: TypedAdapterBaseFactory.BindingViewHolder<ViewDataBinding>, position: Int) =
        bind(holder, position, null)

    override fun onBindViewHolder(holder: TypedAdapterBaseFactory.BindingViewHolder<ViewDataBinding>, position: Int, payloads: MutableList<Any>) =
        bind(holder, position, payloads)

    private fun bind(holder: TypedAdapterBaseFactory.BindingViewHolder<ViewDataBinding>, position: Int, payloads: MutableList<Any>?) {

        // when a VH is rebound to the same item, we don't have to call the setters
        if (payloads == null || payloads.isEmpty() || payloads.any { it !== DB_PAYLOAD }) {
            val item = getItem(position)
            if (item != null) {
                val factory = itemFactoryList.firstOrNull { it.getLayoutId() == holder.itemViewType }
                    ?: throw FactoryNotFoundException("Not supported View Holder: $holder")

                factory.bind(item, holder, position, payloads.orEmpty())
            }
        }
        holder.binding.executePendingBindings()
    }

    public override fun getItem(position: Int): RecyclerItemType? = super.getItem(position)
    // endregion Adapter methods

    /**
     * Registers [TypedAdapterBaseFactory] to be used to handle [RecyclerItem] elements passed to this RecyclerView Adapter.
     *
     * @param itemFactories A set of [TypedAdapterBaseFactory] used to render the data list.
     */
    fun <Factory : TypedAdapterBaseFactory<out RecyclerItemType, out ViewDataBinding>> addCellFactories(vararg itemFactories: Factory) {
        itemFactories.forEach { itemFactory ->
            @Suppress("UNCHECKED_CAST")
            itemFactoryList.add(itemFactory as TypedAdapterBaseFactory<RecyclerItemType, ViewDataBinding>)
        }
    }

    companion object {
        private val DB_PAYLOAD = Any()
    }
}