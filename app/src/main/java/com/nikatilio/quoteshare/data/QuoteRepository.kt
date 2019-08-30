package com.nikatilio.quoteshare.data

import android.app.Application
import com.nikatilio.quoteshare.data.db.QuoteDatabase
import com.nikatilio.quoteshare.data.model.Quote
import com.nikatilio.quoteshare.data.model.QuotesList
import com.nikatilio.quoteshare.network.api.QuotesAPI
import com.nikatilio.quoteshare.network.api.Session
import com.nikatilio.quoteshare.network.api.UserCredentials

class QuoteRepository constructor(app: Application, private val api: QuotesAPI): BaseRepository() {

    private var db: QuoteDatabase = QuoteDatabase.getDatabase(app)

    suspend fun createSession(credentials: UserCredentials): Session? {

        val sessionResponse = safeApiCall(
            call = { api.createSession(credentials).await() },
            errorMessage = "Error creating sessions"
        )

        return sessionResponse

    }

    suspend fun getQuoteOfTheDay(): Quote? {

        val quoteResponse = safeApiCall(
            call = { api.quoteOfTheDay().await() },
            errorMessage = "Error fetching quote of the day"
        )

        // TODO Do I need to check if quoteResponse is correct?
        return quoteResponse?.quote
    }

    suspend fun getQuotes(): QuotesList {

        val quotesResponse = safeApiCall(
            call = { api.quotesList().await() },
            errorMessage = "Error fetching quotes list"
        )

        // TODO Check if getQuotes result is correct
        return quotesResponse!!
    }


}