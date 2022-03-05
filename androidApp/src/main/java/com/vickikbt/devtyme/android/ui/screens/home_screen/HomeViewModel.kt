package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.devtyme.data.network.models.CurrentUserDto
import com.vickikbt.devtyme.domain.repositories.AuthRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel constructor(private val authRepository: AuthRepository) : ViewModel() {

    private val _currentUser = MutableLiveData<CurrentUserDto?>()
    val currentUser: LiveData<CurrentUserDto?> get() = _currentUser

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    fun getCurrentUserProfile() {
        viewModelScope.launch {
            try {
                val response = authRepository.getUserProfile()
                response.collectLatest {
                    _currentUser.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }
}
