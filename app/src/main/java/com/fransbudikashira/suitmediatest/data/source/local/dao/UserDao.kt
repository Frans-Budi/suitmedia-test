package com.fransbudikashira.suitmediatest.data.source.local.dao

import androidx.paging.PagingSource
import androidx.room.*
import com.fransbudikashira.suitmediatest.data.source.local.entity.UserEntity

@Dao
interface UserDao {

    @Query("SELECT * FROM user_table")
    fun getUsers(): PagingSource<Int, UserEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addUsers(users: List<UserEntity>)

    @Query("DELETE FROM user_table")
    suspend fun deleteUsers()
}