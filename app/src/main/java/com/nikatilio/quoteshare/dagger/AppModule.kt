package com.nikatilio.quoteshare.dagger

import android.content.Context
import com.nikatilio.quoteshare.data.QuoteRepository
import com.nikatilio.quoteshare.data.db.QuoteDatabase
import com.nikatilio.quoteshare.network.api.ApiFactory
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun provideQuoteDatabase(applicationContext: Context): QuoteDatabase {
        return QuoteDatabase.getDatabase(applicationContext)
    }

    @Provides
    @Singleton
    fun provideQuoteRepository(quoteDatabase: QuoteDatabase): QuoteRepository {
        return QuoteRepository(ApiFactory.quotesApi, quoteDatabase)
    }
}