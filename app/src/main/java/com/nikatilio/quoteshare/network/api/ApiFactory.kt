package com.nikatilio.quoteshare.network.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.nikatilio.quoteshare.utils.AppConstants
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

/*
 * ApiFactory sets http communication, creates retrofit objects, and initialized API interface
 */
object ApiFactory {

    private val logInterceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    // Create Auth Interceptor to add app authorization token to every request
    private val authInterceptor = Interceptor { chain ->
        var appToken = AppConstants.API_ACCESS_KEY

        val newRequest = chain.request()
            .newBuilder()
            .header("Authorization", "Token token=$appToken")
            .build()

        chain.proceed(newRequest)
    }

    // OkHttpClient for building request url
    private val httpClient = OkHttpClient().newBuilder()
        .addInterceptor(authInterceptor)
        .addInterceptor(logInterceptor)
        .build()

    private fun retrofit() : Retrofit = Retrofit.Builder()
        .client(httpClient)
        .baseUrl(AppConstants.API_BASE_URL)
        .addConverterFactory(MoshiConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    val quotesApi : QuotesAPI = retrofit().create(QuotesAPI::class.java)
}