package com.fransbudikashira.suitmediatest.data.repository

import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.fransbudikashira.suitmediatest.data.source.local.UserDatabase
import com.fransbudikashira.suitmediatest.data.source.local.entity.UserEntity
import com.fransbudikashira.suitmediatest.data.source.network.ApiService
import com.fransbudikashira.suitmediatest.data.source.paging.UserRemoteMediator
import com.fransbudikashira.suitmediatest.util.Constants.ITEMS_PER_PAGE
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

@ExperimentalPagingApi
class UserRepository @Inject constructor(
    private val apiService: ApiService,
    private val userDatabase: UserDatabase
) {

    fun getUsers(): Flow<PagingData<UserEntity>> {
        val pagingSourceFactory = { userDatabase.userDao().getUsers() }
        return Pager(
            config = PagingConfig(pageSize = ITEMS_PER_PAGE),
            remoteMediator = UserRemoteMediator(
                apiService = apiService,
                userDatabase = userDatabase
            ),
            pagingSourceFactory = pagingSourceFactory
        ).flow
    }
}