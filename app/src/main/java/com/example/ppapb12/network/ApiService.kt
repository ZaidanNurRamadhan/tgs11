package com.example.ppapb12.network

import com.example.ppapb12.modal.Api
import retrofit2.Call
import retrofit2.http.GET

interface ApiService {
    @GET("api")
    fun getAllAuthors(): Call<List<Api>>
}