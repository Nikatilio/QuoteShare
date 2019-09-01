package com.nikatilio.quoteshare.data

import com.nikatilio.quoteshare.data.db.QuoteDatabase
import com.nikatilio.quoteshare.data.model.Quote
import com.nikatilio.quoteshare.data.model.QuotesList
import com.nikatilio.quoteshare.data.model.Typeahead
import com.nikatilio.quoteshare.network.api.QuotesAPI
import com.nikatilio.quoteshare.network.api.Session
import com.nikatilio.quoteshare.network.api.UserCredentials

class QuoteRepository constructor(private val api: QuotesAPI, private val db: QuoteDatabase): BaseRepository() {

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

    suspend fun getTypeahead(): Typeahead? {

        val typeaheadResponse = safeApiCall(
            call = { api.getTypeahead().await() },
            errorMessage = "Error fetching typeahead"
        )



        return typeaheadResponse
    }


}