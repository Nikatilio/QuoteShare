package com.nikatilio.quoteshare.data.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import com.nikatilio.quoteshare.data.model.Quote

@Dao
interface QuoteDao {

    @Query("SELECT * FROM quote LIMIT 1")
    fun getAll(): List<Quote>

    @Query("SELECT * FROM quote WHERE uid IN (:quoteIds)")
    fun getByIds(quoteIds: IntArray): List<Quote>

    @Insert
    fun insertAll(vararg quotes: Quote)

    @Delete
    fun delete(quote: Quote)
}