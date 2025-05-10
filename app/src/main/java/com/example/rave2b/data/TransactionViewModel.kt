package com.example.rave2b.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.Exception

class TransactionViewModel:ViewModel()
{
    private val _transactions = MutableStateFlow<List<TransactionDto>>(emptyList())
    val transactions: StateFlow<List<TransactionDto>> = _transactions
    //for loading error
    private val _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading
    //for internet error
    private val _hasInternetError = MutableStateFlow(false)
    val hasInternetError: StateFlow<Boolean> = _hasInternetError
    //no transactions
    private val _emptyMessage = MutableStateFlow<String?>(null)
    val emptyMessage: StateFlow<String?> = _emptyMessage

    fun fetchTransactions(username:String){
        viewModelScope.launch {
            _isLoading.value = true
            _hasInternetError.value = false
            _emptyMessage.value = null
            try{
                val response = RetrofitClient.apiService.getTransactionsByUsername(username)
                if(response.isSuccessful){
                    val result = response.body() ?: emptyList()
                    _transactions.value = result

                    if(result.isEmpty()){
                        _emptyMessage.value = "No tickets ordered yet"
                    }
                }else {
                    _emptyMessage.value = "No tickets ordered yet"
                }
            }catch (e: Exception) {
                e.printStackTrace()
                _hasInternetError.value = true
            }finally {
                _isLoading.value = false
            }
        }
    }
}