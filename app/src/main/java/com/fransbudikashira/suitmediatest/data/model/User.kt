package com.fransbudikashira.suitmediatest.data.model

import com.fransbudikashira.suitmediatest.data.source.network.UserResponseItem

data class User(
    val id: Int,
    val email: String,
    val first_name: String,
    val last_name: String,
    val avatar: String
)

fun UserResponseItem.toUser() = User(
    id = this.id,
    email = this.email,
    first_name = this.firstName,
    last_name = this.lastName,
    avatar = this.avatar
)