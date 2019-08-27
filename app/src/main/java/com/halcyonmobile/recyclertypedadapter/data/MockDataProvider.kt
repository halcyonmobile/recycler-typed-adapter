package com.halcyonmobile.recyclertypedadapter.data

object MockDataProvider {

    fun generateUsers(startPos: Int, endPos: Int) = (startPos until endPos).map {
        User(id = "user_$it", name = "User $it")
    }
}