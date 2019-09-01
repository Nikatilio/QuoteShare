package com.nikatilio.quoteshare.dagger

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nikatilio.quoteshare.ui.quotesmain.QuotesListViewModel
import com.nikatilio.quoteshare.ui.quotesmain.QuotesListViewModelFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    abstract fun viewModelFactory(viewModelFactory: QuotesListViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(QuotesListViewModel::class)
    abstract fun bindQuotesListViewModel(viewModel: QuotesListViewModel): ViewModel
}