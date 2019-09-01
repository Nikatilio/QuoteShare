package com.nikatilio.quoteshare.ui.quotesmain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class QuotesListViewModelFactory @Inject constructor(
    private val viewModelProvider: Provider<QuotesListViewModel>): ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(QuotesListViewModel::class.java)) {
            return viewModelProvider.get() as T
        }
        throw IllegalArgumentException("Unsupported ViewModel class ${modelClass.canonicalName}")
    }
}