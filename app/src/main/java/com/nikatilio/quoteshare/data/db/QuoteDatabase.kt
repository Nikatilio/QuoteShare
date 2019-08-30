package com.nikatilio.quoteshare.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.nikatilio.quoteshare.data.model.Quote
import androidx.room.Room
import android.icu.lang.UCharacter.GraphemeClusterBreak.V
import com.nikatilio.quoteshare.utils.AppConstants.Companion.DATABASE_NAME


@Database(entities = [Quote::class], version = 1)
abstract class QuoteDatabase: RoomDatabase() {
    abstract fun quoteDao(): QuoteDao

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