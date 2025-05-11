package com.example.rave2b.dtos

data class RegistrationDto(
    val username: String,
    val password: String,
    val gmail: String,
    val country: String,
    val fName: String,
    val lName: String,
    val idNumber: String
)