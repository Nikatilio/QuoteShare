package com.nikatilio.quoteshare.network.api

data class Session(
    val userToken: String,
    val login: String,
    val email: String
)