package com.fransbudikashira.suitmediatest.util

import com.fransbudikashira.suitmediatest.data.model.User
import com.fransbudikashira.suitmediatest.data.source.local.entity.UserEntity
import com.fransbudikashira.suitmediatest.data.source.network.UserResponseItem

object DataMapper {

    fun mapUserResponseToUserEntities(input: List<UserResponseItem>): List<UserEntity> {
        val userList = ArrayList<UserEntity>()
        input.map {
            val user = UserEntity(
                userId = it.id,
                email = it.email,
                first_name = it.firstName,
                last_name = it.lastName,
                imageUrl = it.avatar
            )
            userList.add(user)
        }
        return userList
    }

    fun mapUserEntityToUser(input: List<UserEntity>): List<User> {
        return input.map {
            User(
                id = it.userId,
                email = it.email,
                first_name = it.first_name,
                last_name = it.last_name,
                avatar = it.imageUrl
            )
        }
    }
}