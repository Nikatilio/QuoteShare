package com.nikatilio.quoteshare.ui.quotesmain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikatilio.quoteshare.data.model.Quote
import com.nikatilio.quoteshare.data.QuoteRepository
import com.nikatilio.quoteshare.data.model.Typeahead
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.coroutines.CoroutineContext

class QuotesListViewModel @Inject constructor(private val repository: QuoteRepository): ViewModel() {

    private val parentJob = Job()

    private val coroutineContext: CoroutineContext
        get() = parentJob + Dispatchers.Default

    private val scope = CoroutineScope(coroutineContext)

    private val quotes: MutableLiveData<List<Quote>> by lazy {
        MutableLiveData<List<Quote>>().also {
            loadQuotes()
        }
    }

    private val typeahead: MutableLiveData<Typeahead> by lazy {
        MutableLiveData<Typeahead>().also {
            loadTypeahead()
        }
    }

    fun getQuotes(): LiveData<List<Quote>> {
        return quotes
    }

    fun getTypeahead(): LiveData<Typeahead> {
        return typeahead
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

    private fun loadTypeahead() {
        scope.launch {
            val typeahead = repository.getTypeaheadItems()
            println(typeahead)
        }
    }
}