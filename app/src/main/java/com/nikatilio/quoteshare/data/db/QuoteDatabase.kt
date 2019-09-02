package com.nikatilio.quoteshare.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikatilio.quoteshare.data.model.Quote
import androidx.room.Room
import androidx.room.TypeConverters
import com.nikatilio.quoteshare.data.model.Typeahead
import com.nikatilio.quoteshare.data.model.TypeaheadItem
import com.nikatilio.quoteshare.utils.AppConstants.Companion.DATABASE_NAME


@Database(entities = [Quote::class, TypeaheadItem::class], version = 1)
@TypeConverters(Converters::class)
abstract class QuoteDatabase: RoomDatabase() {

    abstract fun quoteDao(): QuoteDao
    abstract fun typeaheadItemDao(): TypeaheadItemDao

    companion object {
        @Volatile
        private var instance: QuoteDatabase? = null

        fun getDatabase(context: Context): QuoteDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): QuoteDatabase {
            return Room.databaseBuilder(
                context.applicationContext,
                QuoteDatabase::class.java, DATABASE_NAME
            ).build()
        }
    }
}