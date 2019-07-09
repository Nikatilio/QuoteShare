package com.nikatilio.quoteshare.ui.login

import android.accounts.AccountAuthenticatorActivity
import android.accounts.AccountManager
import android.accounts.AccountManagerCallback
import android.accounts.AccountManagerFuture
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.widget.Toast
import com.nikatilio.quoteshare.R
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_user_create.*

class ActivityLogin: AccountAuthenticatorActivity() {

    private val REGISTER_CALL = 1

    private lateinit var accountManager: AccountManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        accountManager = AccountManager.get(this)

        loginButton.setOnClickListener { onLogin() }
        registerButton.setOnClickListener { onCreateAccount() }
    }

    fun onCreateAccount() {
        val intent = Intent(this, ActivityUserCreate::class.java)
//        intent.putExtras(getIntent().extras)
        startActivityForResult(intent, REGISTER_CALL)
    }

    fun onLogin() {
        val username = loginNameEditText.text
        val password  = loginPassEditText.text

        var accountType = intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE)
        if (accountType.isNullOrEmpty()) {
            accountType = getString(R.string.account_type)
        }

        val allAccounts = accountManager.accounts

        val accounts = accountManager.getAccountsByType(accountType)
        if (accounts.isNullOrEmpty()) { // Account not found. Create a new account



        } else {
            // Do I need to check if there's more then one account with give accountType?
            val options = Bundle()
            accountManager.getAuthToken(
                accounts[0],
                accountType,
                options,
                this,
                onTokenAcquired(),
                Handler(OnError())
                )
        }

        // Async operation
        println(accountType)


    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == REGISTER_CALL) {
            Toast.makeText(this, "SUCCESSFUL REGISTRATION", Toast.LENGTH_LONG).show()
            println("SUCCESSFUL REGISTRATION")
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }

    class onTokenAcquired: AccountManagerCallback<Bundle> {
        override fun run(future: AccountManagerFuture<Bundle>?) {
            val bundle = future?.result
            val token = bundle?.getString(AccountManager.KEY_AUTHTOKEN)
            println("Token: $token")
        }
    }

    class OnError: Handler.Callback {
        override fun handleMessage(msg: Message?): Boolean {
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}