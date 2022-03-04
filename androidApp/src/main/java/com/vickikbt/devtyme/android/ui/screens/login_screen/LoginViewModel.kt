package com.vickikbt.devtyme.android.ui.screens.login_screen

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.devtyme.domain.models.AccessToken
import com.vickikbt.devtyme.domain.repositories.AuthRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class LoginViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _accessToken = MutableLiveData<AccessToken?>()
    val accessToken: LiveData<AccessToken?> get() = _accessToken

    private val _isLoading: MutableState<Boolean> = mutableStateOf(false)
    val isLoading: State<Boolean> get() = _isLoading

    fun fetchUserToken(code: String) {
        Napier.e("Fetching user token")
        viewModelScope.launch {
            try {
                Napier.e("Fetching user token-Success")
                authRepository.fetchUserToken(code)
            } catch (e: Exception) {
                Napier.e("Error: ${e.localizedMessage}")
            }
        }
    }

    fun getUserToken() {
        Napier.e("Getting user token")
        _isLoading.value = true
        viewModelScope.launch {
            _accessToken.value = authRepository.getUserToken().first()
        }
        _isLoading.value = false
    }
}
