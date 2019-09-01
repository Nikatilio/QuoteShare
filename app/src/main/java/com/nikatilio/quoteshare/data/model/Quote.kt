package com.nikatilio.quoteshare.data.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

data class QuoteInfo(
    val qotd_date: String,
    val quote: Quote
)

data class QuotesList(
    val page: Int,
    val last_page: Boolean,
    val quotes: List<Quote>
)

@Entity
data class Quote(
    @PrimaryKey(autoGenerate = true) var uid: Int = 0,
    @ColumnInfo(name = "quote_id") var id: Int = 0,
    @ColumnInfo(name = "dialogue") var dialogue: Boolean = false,
    @ColumnInfo(name = "private") var private: Boolean = false,
    @ColumnInfo(name = "tags") var tags: List<String> = arrayListOf(),
    @ColumnInfo(name = "url") var url: String = "",
    @ColumnInfo(name = "favorites_count") var favorites_count: Int = 0,
    @ColumnInfo(name = "upvotes_count") var upvotes_count: Int = 0,
    @ColumnInfo(name = "downvotes_count") var downvotes_count: Int = 0,
    @ColumnInfo(name = "author") var author: String = "",
    @ColumnInfo(name = "author_permalink") var author_permalink: String = "",
    @ColumnInfo(name = "body") var body: String = ""
)