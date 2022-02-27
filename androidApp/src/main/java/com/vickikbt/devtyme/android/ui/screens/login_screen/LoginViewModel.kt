package com.vickikbt.devtyme.android.ui.screens.login_screen

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.devtyme.data.repository.AuthRepository
import kotlinx.coroutines.launch

class LoginViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    fun fetchUserToken(code: String) {
        viewModelScope.launch {
            try {
                val response = authRepository.fetchUserToken(code)
                Log.e("VickiKbt", "Response: $response")
            } catch (e: Exception) {
                Log.e("VickiKbt", "Error: ${e.localizedMessage}")
            }
        }
    }
}
