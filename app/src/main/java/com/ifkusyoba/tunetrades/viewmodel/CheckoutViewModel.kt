package com.ifkusyoba.tunetrades.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifkusyoba.tunetrades.data.Repository
import com.ifkusyoba.tunetrades.ui.state.UiState
import com.ifkusyoba.tunetrades.ui.views.checkout.CheckoutState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class CheckoutViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<CheckoutState>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<CheckoutState>> get() = _uiState

    // * Get Ordered Music
    fun getOrderedMusic(){
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            repository.getOrderedMusic()
                .collect{item ->
                    val totalPrice = item.sumOf { it.music.price * it.count }
                    _uiState.value = UiState.Success(CheckoutState(item, totalPrice))
                }
        }
    }

    // * Update Ordered Music
    fun updateOrderedMusic(id: Long, count: Int){
        viewModelScope.launch {
            repository.updateOrderedMusic(id, count)
                .collect { isUpdated ->
                    if (isUpdated){
                        getOrderedMusic()
                    }
                }
        }
    }
}