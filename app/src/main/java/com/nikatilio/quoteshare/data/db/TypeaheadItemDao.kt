package com.nikatilio.quoteshare.data.db

import androidx.room.*
import com.nikatilio.quoteshare.data.model.Typeahead
import com.nikatilio.quoteshare.data.model.TypeaheadItem

@Dao
interface TypeaheadItemDao {

    @Query("SELECT * FROM typeahead_item")
    fun getAll(): List<TypeaheadItem>

    @Insert
    fun addAll(itemList: List<TypeaheadItem>)


    /*@Query("SELECT * FROM typeahead LIMIT 1")
    fun get(): Typeahead

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(typeahead: Typeahead): Long

    @Update
    fun update(typeahead: Typeahead)

    fun set(typeahead: Typeahead) {
        if (insert(typeahead) < 0) update(typeahead)
    }*/

}