package com.fransbudikashira.suitmediatest.ui.screen.Third

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.paging.ExperimentalPagingApi
import com.fransbudikashira.suitmediatest.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@ExperimentalPagingApi
@HiltViewModel
class ThirdViewModel @Inject constructor(
    userRepository: UserRepository
): ViewModel() {
    val getUsers = userRepository.getUsers()
}