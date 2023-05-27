package com.ifkusyoba.tunetrades.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ifkusyoba.tunetrades.data.Repository
import com.ifkusyoba.tunetrades.data.model.Music
import com.ifkusyoba.tunetrades.data.model.OrderMusic
import com.ifkusyoba.tunetrades.ui.state.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailViewModel(private val repository: Repository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<OrderMusic>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<OrderMusic>> get() = _uiState

    // *Get Item by Id
    fun getItemById(itemId: Long) {
        _uiState.value = UiState.Loading
        _uiState.value = UiState.Success(repository.getItemOrderById(itemId))
    }

    // *Cart
    fun addToCart(item: Music, count: Int) {
        viewModelScope.launch {
            repository.updateOrderedMusic(item.id, count)
        }
    }
}