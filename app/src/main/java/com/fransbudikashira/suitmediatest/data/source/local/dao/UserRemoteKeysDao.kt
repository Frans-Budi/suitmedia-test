package com.fransbudikashira.suitmediatest.data.source.local.dao

import androidx.room.*
import com.fransbudikashira.suitmediatest.data.source.local.entity.UserRemoteKeys

@Dao
interface UserRemoteKeysDao {

    @Query("SELECT * FROM user_remote_keys_table WHERE id =:id")
    fun getRemoteKeys(id: String): UserRemoteKeys

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<UserRemoteKeys>)

    @Query("DELETE FROM user_remote_keys_table")
    suspend fun deleteAllRemoteKeys()
}