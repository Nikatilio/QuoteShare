package com.nikatilio.quoteshare.utils

import com.nikatilio.quoteshare.BuildConfig

class AppConstants {
    companion object {
        val API_ACCESS_KEY = BuildConfig.FAVQS_API_KEY
        val API_BASE_URL = "https://favqs.com/"
        val DATABASE_NAME = "quote_database"
    }
}