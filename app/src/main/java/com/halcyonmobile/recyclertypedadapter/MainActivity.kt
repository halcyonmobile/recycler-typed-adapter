package com.halcyonmobile.recyclertypedadapter

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.halcyonmobile.recyclertypedadapter.items.header.HeaderRecyclerItemFactory
import com.halcyonmobile.recyclertypedadapter.items.user.UserRecyclerItem
import com.halcyonmobile.recyclertypedadapter.items.user.UserRecyclerItemFactory
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerAdapter

class MainActivity : AppCompatActivity() {

    private lateinit var binding: MainActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        val viewModel = ViewModelProviders.of(this).get(MainViewModel::class.java)

        val adapter = RecyclerAdapter()

        adapter.addCellFactories(
            UserRecyclerItemFactory(clickListener = { position ->
                (adapter.getItem(position) as? UserRecyclerItem)?.let {
                    Toast.makeText(this, "User item with name ${it.user.name} clicked", Toast.LENGTH_SHORT).show()
                }
            }),
            HeaderRecyclerItemFactory()
        )

        binding.recycler.adapter = adapter


        viewModel.guestList.observe(this, Observer(adapter::submitList))
    }
}