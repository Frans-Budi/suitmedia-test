package com.fransbudikashira.suitmediatest.data.source.paging

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.fransbudikashira.suitmediatest.data.source.local.UserDatabase
import com.fransbudikashira.suitmediatest.data.source.local.entity.UserEntity
import com.fransbudikashira.suitmediatest.data.source.local.entity.UserRemoteKeys
import com.fransbudikashira.suitmediatest.data.source.network.ApiService
import com.fransbudikashira.suitmediatest.util.Constants.ITEMS_PER_PAGE
import com.fransbudikashira.suitmediatest.util.DataMapper

@OptIn(ExperimentalPagingApi::class)
class UserRemoteMediator (
    private val apiService: ApiService,
    private val userDatabase: UserDatabase
): RemoteMediator<Int, UserEntity>() {

    private val userDao = userDatabase.userDao()
    private val userRemoteKeysDao = userDatabase.userRemoteKeysDao()

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, UserEntity>
    ): MediatorResult {
        return try {
            Log.d("HALO", "load: ${loadType}")
            val currentPage = when(loadType) {
                LoadType.REFRESH -> {
                    Log.d("HALO", "Refresh")
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    Log.d("HALO", "remoteKeys: ${remoteKeys}")
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    Log.d("HALO", "PREPEND")
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    Log.d("HALO", "APPEND")
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }

            val response = apiService.getUsers(page = currentPage, perPage = ITEMS_PER_PAGE)
            Log.d("HALO", "load: ${response}")
            val endOfPaginationReached = response.data.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1
            Log.d("HALO", "nextPage: ${nextPage} prevPage: ${prevPage}")

            userDatabase.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    userDao.deleteUsers()
                    userRemoteKeysDao.deleteAllRemoteKeys()
                }
                val keys = response.data.map { key ->
                    UserRemoteKeys(
                        id = key.id.toString(),
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }
                userRemoteKeysDao.addAllRemoteKeys(remoteKeys = keys)
                userDao.addUsers(users = DataMapper.mapUserResponseToUserEntities(response.data))
            }
            MediatorResult.Success(endOfPaginationReached = endOfPaginationReached)
        } catch (e: Exception) {
            return MediatorResult.Error(e)
        }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, UserEntity>
    ): UserRemoteKeys? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.userId?.let { userId ->
                userRemoteKeysDao.getRemoteKeys(id = "$userId")
            }
        }
    }

    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, UserEntity>
    ): UserRemoteKeys? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { user ->
                userRemoteKeysDao.getRemoteKeys(id = user.userId.toString())
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, UserEntity>
    ): UserRemoteKeys? {
        return state.pages.lastOrNull() { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { user ->
                userRemoteKeysDao.getRemoteKeys(id = user.userId.toString())
            }
    }
}