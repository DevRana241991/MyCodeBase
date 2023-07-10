package com.example.fitpeotasksample.ui.home.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.fitpeotasksample.data.model.GetServerData
import com.example.fitpeotasksample.data.network.ApiRepository
import com.example.fitpeotasksample.ui.home.model.UIState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@ExperimentalCoroutinesApi
@HiltViewModel
class HomeViewModel @Inject constructor(private val apiRepository: ApiRepository) : ViewModel() {

    private val _userFlow = MutableStateFlow<UIState<ArrayList<GetServerData>>>(UIState.Loading())
    val userFlow: StateFlow<UIState<ArrayList<GetServerData>>> = _userFlow

    fun loadUserData() {
        viewModelScope.launch {
            apiRepository.getUser()
                .catch { exception ->
                    _userFlow.value = UIState.Error(exception.message)
                }
                .collect {
                    _userFlow.value = UIState.Success(it)
                }
        }
    }
}