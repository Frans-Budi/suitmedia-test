package com.fransbudikashira.suitmediatest.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.fransbudikashira.suitmediatest.data.source.local.dao.UserDao
import com.fransbudikashira.suitmediatest.data.source.local.dao.UserRemoteKeysDao
import com.fransbudikashira.suitmediatest.data.source.local.entity.UserEntity
import com.fransbudikashira.suitmediatest.data.source.local.entity.UserRemoteKeys

@Database(entities = [UserEntity::class, UserRemoteKeys::class], version = 1)
abstract class UserDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
    abstract fun userRemoteKeysDao(): UserRemoteKeysDao
}