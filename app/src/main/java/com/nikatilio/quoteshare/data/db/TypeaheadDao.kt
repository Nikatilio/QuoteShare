package com.nikatilio.quoteshare.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.nikatilio.quoteshare.data.model.Typeahead

@Dao
interface TypeaheadDao {

    @Query("SELECT * FROM typeahead")
    fun get()

    @Insert
    fun add(typeahead: Typeahead)

}