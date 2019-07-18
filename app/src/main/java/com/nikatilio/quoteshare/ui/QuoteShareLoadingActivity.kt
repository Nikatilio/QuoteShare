package com.nikatilio.quoteshare.ui

import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.os.PersistableBundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nikatilio.quoteshare.R
import com.nikatilio.quoteshare.network.ServerCommunication
import com.nikatilio.quoteshare.ui.login.ActivityLogin

class QuoteShareLoadingActivity: AppCompatActivity() {

    private val REGISTER_CALL = 1
    private val LOGIN_CALL = 2

    private lateinit var accountManager: AccountManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        accountManager = AccountManager.get(this)

        login()
    }

    fun login() {
        val accountType = getString(R.string.account_type)
        val accounts = accountManager.getAccountsByType(accountType)

        if (accounts.isEmpty()) {
            val intent = Intent(this, ActivityLogin::class.java)
            startActivity(intent)
            return
        }

        val authTokenType = "StandardUserAccessToken"

        val options = Bundle()
        accountManager.getAuthToken(
            accounts[0],
            authTokenType,
            options,
            this,
            OnTokenAcquired(),
            Handler(OnError())
        )
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REGISTER_CALL) {
            Toast.makeText(this, "SUCCESSFUL REGISTRATION", Toast.LENGTH_LONG).show()
            println("SUCCESSFUL REGISTRATION")
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    class OnTokenAcquired: AccountManagerCallback<Bundle> {
        override fun run(future: AccountManagerFuture<Bundle>?) {
            val bundle = future?.result

            val loginIntent = bundle?.get(AccountManager.KEY_INTENT)
            if (loginIntent == null) {
                val token = bundle?.getString(AccountManager.KEY_AUTHTOKEN)
                ServerCommunication.authToken = token
                val serverCommunication = ServerCommunication()
                serverCommunication.quoteOfTheDay()
                println("Token: $token")
            } else {
            }
        }
    }

    class OnError: Handler.Callback {
        override fun handleMessage(msg: Message?): Boolean {
            println(msg.toString())
            return true
        }
    }
}