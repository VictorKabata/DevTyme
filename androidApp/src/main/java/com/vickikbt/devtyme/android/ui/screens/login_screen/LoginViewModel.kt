package com.vickikbt.devtyme.android.ui.screens.login_screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.devtyme.data.repository.AuthRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.launch

class LoginViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    fun fetchUserToken(code: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.fetchUserToken(code)
            } catch (e: Exception) {
                Napier.e("Error: ${e.localizedMessage}")
            }
        }
    }
}
