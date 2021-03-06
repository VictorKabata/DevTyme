package com.vickikbt.devtyme.android.ui.screens.login_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.devtyme.domain.repositories.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    private val _errorMessage: MutableState<String> = mutableStateOf("Unknown error")
    val errorMessage: State<String> get() = _errorMessage

    fun fetchUserToken(code: String) {
        _isLoading.value = true
        viewModelScope.launch {
            try {
                authRepository.fetchUserToken(code)
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
        _isLoading.value = false
    }
}
