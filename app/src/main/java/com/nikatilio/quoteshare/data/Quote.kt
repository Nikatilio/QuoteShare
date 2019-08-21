package com.nikatilio.quoteshare.data

data class QuoteInfo(
    val qotd_date: String,
    val quote: Quote
)

data class QuotesList(
    val page: Int,
    val last_page: Boolean,
    val quotes: List<Quote>
)

data class Quote(
    val id: Int,
    val dialogue: Boolean,
    val private: Boolean,
    val tags: List<String>,
    val url: String,
    val favorites_count: Int,
    val upvotes_count: Int,
    val downvotes_count: Int,
    val author: String,
    val author_permalink: String,
    val body: String
)