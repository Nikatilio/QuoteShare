package com.nikatilio.quoteshare.data

import com.nikatilio.quoteshare.data.db.QuoteDatabase
import com.nikatilio.quoteshare.data.model.Quote
import com.nikatilio.quoteshare.data.model.QuotesList
import com.nikatilio.quoteshare.data.model.Typeahead
import com.nikatilio.quoteshare.data.model.TypeaheadItem
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

    suspend fun getTypeaheadItems(): List<TypeaheadItem> {

        var list = db.typeaheadItemDao().getAll()
        if (list.isEmpty()) {
            list = mutableListOf()

            val typeaheadResponse = safeApiCall(
                call = { api.getTypeahead().await() },
                errorMessage = "Error fetching typeahead"
            )

            typeaheadResponse?.let { typeahead ->
                typeahead.authors.forEach {
                    it.type = "author"
                }
                list.addAll(typeahead.authors)

                typeahead.tags.forEach {
                    it.type = "tag"
                }
                list.addAll(typeahead.tags)

                typeahead.users.forEach {
                    it.type = "user"
                }
                list.addAll(typeahead.users)
            }

            db.typeaheadItemDao().addAll(list)

        }

        return list
    }


}