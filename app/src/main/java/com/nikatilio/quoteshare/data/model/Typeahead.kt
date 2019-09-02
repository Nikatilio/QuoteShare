package com.nikatilio.quoteshare.data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

data class Typeahead(
    var authors: List<TypeaheadItem>,
    var tags: List<TypeaheadItem>,
    var users: List<TypeaheadItem>
)

@Entity(tableName = "typeahead_item")
data class TypeaheadItem(
    @PrimaryKey(autoGenerate = true) val uid: Int,
    var type: String,
    val count: Int,
    val permalink: String,
    val name: String
)