package com.fransbudikashira.suitmediatest.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.fransbudikashira.suitmediatest.util.Constants.USER_REMOTE_KEYS_TABLE

@Entity(tableName = USER_REMOTE_KEYS_TABLE)
data class UserRemoteKeys(
    @PrimaryKey(autoGenerate = false)
    val id: String,
    val prevPage: Int?,
    val nextPage: Int?
)
