package com.zurichat.app.ui.newchannel

import com.zurichat.app.data.remoteSource.UsersService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitChannelClient {
    private const val BASE_URL = "https://api.zuri.chat/"
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private val client = OkHttpClient.Builder()
        .connectTimeout(2, TimeUnit.MINUTES)
        .writeTimeout(2, TimeUnit.MINUTES) // write timeout
        .readTimeout(2, TimeUnit.MINUTES)
        .addInterceptor(interceptor)
        .build()

    fun getRetrofitService(): UsersService {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(UsersService::class.java)
    }

}