package com.example.rave2b.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    //for registration to create account
    @POST("api/registration")
    suspend fun createUser(@Body registration:RegistrationDto):Response<Void>
    //for log in
    @POST("api/registration/login")
    suspend fun loginUser(@Body loginDto: LoginDto): Response<Void>
}