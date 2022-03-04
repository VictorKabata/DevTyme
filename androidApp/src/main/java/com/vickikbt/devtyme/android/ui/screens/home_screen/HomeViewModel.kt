package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.devtyme.data.network.models.CurrentUserDto
import com.vickikbt.devtyme.domain.repositories.AuthRepository
import io.github.aakira.napier.Napier
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _currentUser = MutableLiveData<CurrentUserDto?>()
    val currentUser: LiveData<CurrentUserDto?> get() = _currentUser

    init {
        getCurrentUserProfile()
    }

    fun getCurrentUserProfile() {
        viewModelScope.launch {
            try {
                val response = authRepository.getUserProfile()
                response.collectLatest {
                    Napier.e("Response: $it")
                    _currentUser.value = it
                }
            } catch (e: Exception) {
                Napier.e("Error: ${e.localizedMessage}")
            }
        }
    }
}
