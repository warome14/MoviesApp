package com.example.moviesapp.api

import com.example.moviesapp.model.Boss
import com.example.moviesapp.model.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET


private val retrofit = Retrofit.Builder().baseUrl("https://eldenring.fanapis.com/api/")
    .addConverterFactory(GsonConverterFactory.create())
    .build()

val api = retrofit.create(Api::class.java)

interface Api{
    @GET("bosses")
    suspend fun getBosses():Response<Boss>

}