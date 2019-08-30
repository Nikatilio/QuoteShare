package com.nikatilio.quoteshare.network.api

import com.nikatilio.quoteshare.data.model.QuoteInfo
import com.nikatilio.quoteshare.data.model.QuotesList
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface QuotesAPI {

    @POST("/api/session")
    fun createSession(@Body credentials: UserCredentials): Deferred<Response<Session>>

    @GET("/api/qotd")
    fun quoteOfTheDay(): Deferred<Response<QuoteInfo>>

    @GET("/api/quotes")
    fun quotesList(): Deferred<Response<QuotesList>>

    @GET("/api/quotes/{id}")
    fun getQuote(@Path("id") id:Int): Deferred<Response<QuoteInfo>>

}