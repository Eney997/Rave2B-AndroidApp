package com.example.rave2b.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.rave2b.data.RetrofitClient
import com.example.rave2b.dtos.TicketDto
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicketViewModel : ViewModel()
{
    private val _tickets = MutableStateFlow<List<TicketDto>>(emptyList())
    val tickets: StateFlow<List<TicketDto>> = _tickets
    //for loading error
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    //for internet error
    private val _hasInternetError = MutableStateFlow(false)
    val hasInternetError: StateFlow<Boolean> = _hasInternetError

    fun fetchTickets() {
        viewModelScope.launch {
            _isLoading.value = true
            _hasInternetError.value = false
            try {
                val response = RetrofitClient.apiService.getAllTickets()
                if (response.isSuccessful) {
                    delay(500)
                    _tickets.value = response.body() ?: emptyList()
                }else {
                    _hasInternetError.value = true
                }
            } catch (e: Exception) {
                e.printStackTrace()
                _hasInternetError.value = true
            }finally {
                _isLoading.value = false
            }
        }
    }
}