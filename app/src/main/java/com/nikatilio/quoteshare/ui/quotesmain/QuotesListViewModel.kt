package com.nikatilio.quoteshare.ui.quotesmain

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.nikatilio.quoteshare.data.Quote

class QuotesListViewModel: ViewModel() {

    private val quotes: MutableLiveData<List<Quote>> by lazy {
        MutableLiveData<List<Quote>>().also {
            loadQuotes()
        }
    }

    fun getQuotes(): LiveData<List<Quote>> {
        return quotes
    }

    private fun loadQuotes() {
        println("Loading users...")
        Thread {
            val list = ArrayList<Quote>()
            list.add(Quote(0, "Why do we never have time to do it right, but always have time to do it over?"))
            list.add(Quote(1, "Good judgment comes from experience, and experience comes from bad judgment"))
            quotes.postValue(list)
        }.start()
    }
}