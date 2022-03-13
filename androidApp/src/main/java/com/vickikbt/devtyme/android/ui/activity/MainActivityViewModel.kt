package com.vickikbt.devtyme.android.ui.activity

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.devtyme.domain.models.AccessToken
import com.vickikbt.devtyme.domain.repositories.AuthRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class MainActivityViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _accessToken: MutableState<AccessToken?> = mutableStateOf(null)
    val accessToken: State<AccessToken?> get() = _accessToken

    init {
        getUserToken()
    }

    private fun getUserToken() {
        viewModelScope.launch {
            try {
                val response = authRepository.getUserToken()
                response.collectLatest {
                    _accessToken.value = it
                }
            } catch (e: Exception) {
                Napier.e("Error getting token: ${e.localizedMessage}")
            }
        }
    }
}
