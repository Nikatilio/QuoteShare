package com.nikatilio.quoteshare.network.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nikatilio.quoteshare.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ApiFactory {

    val interceptor = HttpLoggingInterceptor()

    // Create Auth Interceptor to add api_key query in front of all requests
    private val authInterceptor = Interceptor { chain ->
        var appToken = AppConstants.favqsApiKey

        val newRequest = chain.request()
            .newBuilder()
            .header("Authorization", "Token token=$appToken")
            .build()

        interceptor.level = HttpLoggingInterceptor.Level.BODY

        chain.proceed(newRequest)
    }


    // OkHttpClient for building request url
    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(interceptor)
        .build()

    fun retrofit() : Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl("https://favqs.com/")
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val quotesApi : QuotesAPI = retrofit().create(QuotesAPI::class.java)
}