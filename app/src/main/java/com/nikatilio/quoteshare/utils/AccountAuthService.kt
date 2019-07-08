package com.nikatilio.quoteshare.utils

import android.app.Service
import android.content.Intent
import android.os.IBinder

class AccountAuthService: Service() {

    override fun onBind(intent: Intent?): IBinder? {
        val authenticator = ServiceAuthenticator(this)
        return authenticator.iBinder
    }
}