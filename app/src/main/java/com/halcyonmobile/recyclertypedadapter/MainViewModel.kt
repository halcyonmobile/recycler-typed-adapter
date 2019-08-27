package com.halcyonmobile.recyclertypedadapter

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.halcyonmobile.recyclertypedadapter.data.MockDataProvider
import com.halcyonmobile.recyclertypedadapter.items.header.HeaderRecyclerItem
import com.halcyonmobile.recyclertypedadapter.items.user.UserRecyclerItem
import com.halcyonmobile.recyclertypedadapter.data.GuestListType
import com.halcyonmobile.typedrecyclerviewadapter.RecyclerItem

class MainViewModel: ViewModel() {

    private val _guestList = MutableLiveData<List<RecyclerItem>>()
    val guestList: LiveData<List<RecyclerItem>> get() = _guestList

    init {
        determineData()
    }

    private fun determineData() {

        val going = MockDataProvider.generateUsers(0, 10).map(::UserRecyclerItem)
        val maybe = MockDataProvider.generateUsers(10, 13).map(::UserRecyclerItem)
        val invited = MockDataProvider.generateUsers(15, 40).map(::UserRecyclerItem)

        _guestList.value = mutableListOf<RecyclerItem>().apply {
            add(HeaderRecyclerItem(GuestListType.GOING.name))
            addAll(going)
            add(HeaderRecyclerItem(GuestListType.MAYBE.name))
            addAll(maybe)
            add(HeaderRecyclerItem(GuestListType.INVITED.name))
            addAll(invited)
        }
    }
}