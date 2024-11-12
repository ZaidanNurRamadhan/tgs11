package com.example.ppapb12.network

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {
    fun getInstance() : ApiService {
        val mHttpInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

        val mOkHttpClient = OkHttpClient.Builder().addInterceptor(mHttpInterceptor).build()

        val builder = Retrofit.Builder().baseUrl("https://dayoffapi.vercel.app/")
            .addConverterFactory(GsonConverterFactory.create()).client(mOkHttpClient).build()

        return builder.create(ApiService::class.java)
    }
}