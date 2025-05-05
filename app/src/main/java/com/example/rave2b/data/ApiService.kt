package com.example.rave2b.data

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.POST
import retrofit2.http.Path

interface ApiService {
    //for registration to create account
    @POST("api/registration")
    suspend fun createUser(@Body registration:RegistrationDto):Response<Void>
    //for log in
    @POST("api/registration/login")
    suspend fun loginUser(@Body loginDto: LoginDto): Response<Void>
    //for delete account
    @DELETE("api/registration/{username}")
    suspend fun deleteUser(@Path("username") username: String): Response<Void>

}