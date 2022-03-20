package com.vickikbt.devtyme.android.ui.screens.home_screen

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.vickikbt.devtyme.data.network.models.CurrentUserDto
import com.vickikbt.devtyme.domain.models.Summaries
import com.vickikbt.devtyme.domain.repositories.AuthRepository
import com.vickikbt.devtyme.domain.repositories.DateTimeRepository
import com.vickikbt.devtyme.domain.repositories.SummariesRepository
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    private val authRepository: AuthRepository,
    private val dateTimeRepository: DateTimeRepository,
    private val summariesRepository: SummariesRepository
) : ViewModel() {

    private val _currentUser = MutableLiveData<CurrentUserDto?>()
    val currentUser: LiveData<CurrentUserDto?> get() = _currentUser

    private val _greetingMessage = MutableLiveData<String>()
    val greetingMessage: LiveData<String> get() = _greetingMessage

    private val _currentDate = MutableLiveData<String>()
    val currentDate: LiveData<String> get() = _currentDate

    private val _daysOfWeek = MutableLiveData<List<String>>()
    val daysOfWeek: LiveData<List<String>> get() = _daysOfWeek

    private val _summaries = MutableLiveData<Summaries>()
    val summaries: LiveData<Summaries> get() = _summaries

    private val _dailyGoal = MutableLiveData<Long?>()
    val dailyGoal: LiveData<Long?> get() = _dailyGoal

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> get() = _isLoading

    private val _errorMessage = MutableLiveData<String>()
    val errorMessage: LiveData<String> get() = _errorMessage

    init {
        getCurrentDate()
    }

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

    fun getTimeOfDay() {
        viewModelScope.launch {
            try {
                dateTimeRepository.getTimeOfDay().collectLatest {
                    _greetingMessage.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }

    private fun getCurrentDate() {
        viewModelScope.launch {
            try {
                dateTimeRepository.getCurrentDate().collectLatest {
                    _currentDate.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }

    fun getDaysOfWeek() {
        viewModelScope.launch {
            try {
                dateTimeRepository.getDaysOfWeek().collectLatest {
                    _daysOfWeek.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }

    fun getSummaries() {
        viewModelScope.launch {
            try {
                summariesRepository.fetchSummaries(start = currentDate.value, range = "Today")
                    .collectLatest {
                        _summaries.value = it
                    }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }

    fun saveDailyGoal(hours: Int) {
        viewModelScope.launch { summariesRepository.saveDailyGoal(hours = hours.toLong()) }
    }

    fun getDailyGoal() {
        viewModelScope.launch {
            try {
                summariesRepository.getDailyGoal().collectLatest {
                    _dailyGoal.value = it
                }
            } catch (e: Exception) {
                _errorMessage.value = e.localizedMessage
            }
        }
    }
}
