package com.nikatilio.quoteshare.ui.login

import android.accounts.AccountAuthenticatorActivity
import android.accounts.AccountManager
import android.app.Activity
import android.content.Intent
import android.os.Bundle
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

        val accountType = intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE)

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
}