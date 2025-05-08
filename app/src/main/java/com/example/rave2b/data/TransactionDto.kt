package com.example.rave2b.data

data class TransactionDto(
    val userNm: String,
    val djOne: String,
    val djTwo: String,
    val djThree: String,
    val djFour: String,
    val eventDateSave: String,
    val cardHolderName: String,
    val cardHolderLastName: String,
    val cardNumber: String,
    val cardCvv: String,
    val cardExpireDate: String
)