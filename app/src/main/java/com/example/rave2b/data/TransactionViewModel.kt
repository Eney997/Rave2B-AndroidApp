package com.example.rave2b.data

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlin.Exception

class TransactionViewModel:ViewModel()
{
    private val _transactions = MutableStateFlow<List<TransactionDto>>(emptyList())
    val transactions: StateFlow<List<TransactionDto>> = _transactions

    fun fetchTransactions(username:String){
        viewModelScope.launch {
            try{
                val response = RetrofitClient.apiService.getTransactionsByUsername(username)
                if(response.isSuccessful){
                    _transactions.value = response.body() ?: emptyList()
                }
            }catch (e: Exception) {
            e.printStackTrace()
            }
        }
    }
}