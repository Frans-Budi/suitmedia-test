package com.fransbudikashira.suitmediatest.data.source.local.entity

import android.os.Parcelable
import androidx.room.*
import com.fransbudikashira.suitmediatest.util.Constants.USER_TABLE
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = USER_TABLE)
data class UserEntity(
    @PrimaryKey(autoGenerate = false)
    @ColumnInfo("userId")
    val userId: Int,

    @ColumnInfo("imageUrl")
    val imageUrl: String,

    @ColumnInfo("email")
    val email: String,

    @ColumnInfo("first_name")
    val first_name: String,

    @ColumnInfo("last_name")
    val last_name: String,
): Parcelable
