package com.ifkusyoba.tunetrades.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifkusyoba.tunetrades.data.Repository
import com.ifkusyoba.tunetrades.data.model.OrderMusic
import com.ifkusyoba.tunetrades.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<OrderMusic>>> =
        MutableStateFlow(UiState.Loading)
    val uiState: MutableStateFlow<UiState<List<OrderMusic>>>
        get() = _uiState

    fun getAllItem() {
        viewModelScope.launch {
            repository.getAllItem()
                .catch { errorMsg ->
                    _uiState.value = UiState.Error(errorMsg.message.toString())
                }
                .collect { orderRewards ->
                    _uiState.value = UiState.Success(orderRewards)
                }
        }
    }

    fun clearCart(){
        viewModelScope.launch {
            repository.clearCart()
        }
    }
}