package com.fransbudikashira.suitmediatest.ui.screen.Third

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import coil.annotation.ExperimentalCoilApi
import com.fransbudikashira.suitmediatest.data.source.local.entity.UserEntity
import com.fransbudikashira.suitmediatest.ui.component.UserItem
import com.fransbudikashira.suitmediatest.ui.theme.SuitmediaTestTheme

@ExperimentalCoilApi
@ExperimentalPagingApi
@Composable
fun ThirdScreen(
    modifier: Modifier = Modifier,
    viewModel: ThirdViewModel = hiltViewModel()
) {
    val getUsers = viewModel.getUsers.collectAsLazyPagingItems()

    ThirdContent(
        users = getUsers,
        onClick = {},
        modifier = modifier
    )
}

@ExperimentalCoilApi
@Composable
fun ThirdContent(
    users: LazyPagingItems<UserEntity>,
    onClick: (UserEntity) -> Unit,
    modifier: Modifier
) {
    Log.d("Error", users.loadState.toString())

    LazyColumn(
        modifier.fillMaxSize()
    ) {
//        items(
//            count = users.itemCount,
//            key = {
//                users.itemKey { it }
//            },
//            itemContent = {
//                val data = users[it]!!
//                UserItem(
//                    imageUrl = data.imageUrl,
//                    email = data.email,
//                    firstName = data.first_name,
//                    lastName = data.last_name,
//                    modifier = Modifier.clickable {
//                        onClick(data)
//                    },
//                )
//            }
//        )
        items(users.itemCount) {
            val data = users[it]!!
            UserItem(
                imageUrl = data.imageUrl,
                email = data.email,
                firstName = data.first_name,
                lastName = data.last_name,
                modifier = Modifier.clickable {
                    onClick(data)
                },
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ThirdScreenPreview() {
    SuitmediaTestTheme {
//        ThirdContent(
//            users = listOf(),
//            onClick = {},
//            modifier = Modifier
//        )
    }
}