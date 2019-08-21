package com.nikatilio.quoteshare.ui.quotesmain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikatilio.quoteshare.data.Quote
import com.nikatilio.quoteshare.data.QuoteRepository
import com.nikatilio.quoteshare.network.api.ApiFactory
import com.nikatilio.quoteshare.network.api.User
import com.nikatilio.quoteshare.network.api.UserCredentials
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlin.coroutines.CoroutineContext

class QuotesListViewModel: ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val repository: QuoteRepository = QuoteRepository(ApiFactory.quotesApi)

    private val quotes: MutableLiveData<List<Quote>> by lazy {
        MutableLiveData<List<Quote>>().also {
            loadQuotes()
        }
    }

    fun getQuotes(): LiveData<List<Quote>> {
        return quotes
    }

    /*
    * Call QuotesApi in async mode and update quotes list
    * */
    private fun loadQuotes() {
        scope.launch {
            val quotesList = repository.getQuotes()
            quotes.postValue(quotesList.quotes)
        }
    }
}