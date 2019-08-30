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
    @PrimaryKey(autoGenerate = true) val uid: Int,
    @ColumnInfo(name = "quote_id") val id: Int,
    @ColumnInfo(name = "dialogue") val dialogue: Boolean,
    @ColumnInfo(name = "private") val private: Boolean,
    @ColumnInfo(name = "tags") val tags: List<String>,
    @ColumnInfo(name = "url") val url: String,
    @ColumnInfo(name = "favorites_count") val favorites_count: Int,
    @ColumnInfo(name = "upvotes_count") val upvotes_count: Int,
    @ColumnInfo(name = "downvotes_count") val downvotes_count: Int,
    @ColumnInfo(name = "author") val author: String,
    @ColumnInfo(name = "author_permalink") val author_permalink: String,
    @ColumnInfo(name = "body") val body: String
)