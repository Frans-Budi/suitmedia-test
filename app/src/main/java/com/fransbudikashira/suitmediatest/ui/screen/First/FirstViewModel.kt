package com.fransbudikashira.suitmediatest.ui.screen.First

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class FirstViewModel: ViewModel() {

    private val _name = mutableStateOf("")
    val name: State<String> get() = _name

    private val _palindrome = mutableStateOf("")
    val palindrome: State<String> get() = _palindrome

    private val _showDialog = mutableStateOf(false)
    val showDialog: State<Boolean> get() = _showDialog

    private val _isPalindrome = mutableStateOf(false)
    val isPalindrome: State<Boolean> get() = _isPalindrome

    fun onNameChange(inputName: String) {
        _name.value = inputName
    }

    fun onPalindromeChange(inputPalindrome: String) {
        _palindrome.value = inputPalindrome
    }

    fun checkPalindrome(input: String) {
        viewModelScope.launch {
            val normalizedInput = input.filter { it.isLetterOrDigit() }.lowercase()
            _isPalindrome.value = normalizedInput == normalizedInput.reversed()
            _showDialog.value = true
        }
    }

    fun dismissDialog() {
        _showDialog.value = false
    }
}