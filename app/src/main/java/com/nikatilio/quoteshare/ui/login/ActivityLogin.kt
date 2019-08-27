package com.nikatilio.quoteshare.ui.login

import android.accounts.*
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import android.os.Message
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nikatilio.quoteshare.BuildConfig
import com.nikatilio.quoteshare.R
import com.nikatilio.quoteshare.utils.ServiceAuthenticator
import kotlinx.android.synthetic.main.activity_login.*
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ActivityLogin: AccountAuthenticatorActivity() {

    private lateinit var accountManager: AccountManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        accountManager = AccountManager.get(this)

        loginButton.setOnClickListener { onLogin() }
        registerButton.setOnClickListener { onCreateAccount() }
    }

    fun onCreateAccount() {
//        val intent = Intent(this, ActivityUserCreate::class.java)
//        intent.putExtras(getIntent().extras)
//        startActivityForResult(intent, REGISTER_CALL)
    }

    fun onLogin() {
//        val username = loginNameEditText.text.toString()
//        val password  = loginPassEditText.text.toString()

        val username = BuildConfig.TEST_LOGIN
        val password = BuildConfig.TEST_PASSWORD

        var accountType = intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE)
        if (accountType.isNullOrEmpty()) {
            accountType = getString(R.string.account_type)
        }

        val account: Account

        val accounts = accountManager.getAccountsByType(accountType)
        if (accounts.isNullOrEmpty()) { // Account not found. Create a new account

            Account(username, accountType).also { acc ->
                accountManager.addAccountExplicitly(acc, password, null)
                account = acc
            }

        } else {
            account = accounts[0]
        }

        createSession {

            val authTokenType = "StandardUserAccessToken"

            accountManager.setAuthToken(account, authTokenType, it)

            // Do I need to check if there's more then one account with give accountType?
            val options = Bundle()
            accountManager.getAuthToken(
                account,
                authTokenType,
                options,
                this,
                OnTokenAcquired(),
                Handler(OnError())
            )
        }
    }

    fun createSession(result: (authToken: String) -> Unit) {
        val username = BuildConfig.TEST_LOGIN
        val password = BuildConfig.TEST_PASSWORD
        val appToken = BuildConfig.FAVQS_API_KEY

        Thread {
            try {
                val url = URL("https://favqs.com/api/session")
//                val url = URL("https://favqs.com/api/qotd")
                val client = url.openConnection() as HttpsURLConnection
                client.requestMethod = "POST"
                client.addRequestProperty("Content-Type", "application/json")
                client.addRequestProperty("Authorization", "Token token=$appToken")

                val userJson = JSONObject()
                userJson.put("login", username)
                userJson.put("password", password)

                val jsonParam = JSONObject()
                jsonParam.put("user", userJson)

                println(jsonParam.toString())

                val os = DataOutputStream(client.outputStream)
                os.writeBytes(jsonParam.toString())

                os.flush()
                os.close()

                println("Response code: " + client.responseCode)
                println("Message: " + client.responseMessage)

                var result = ""
                if (client.responseCode == 200) {
                    val br = BufferedReader(InputStreamReader(client.inputStream))
                    var output = br.readLine()
                    while (output != null) {
                        result = output
                        println(output)
                        output = br.readLine()
                    }
                }

                val json = JSONObject(result)

                val authToken: String = json.get("User-Token") as String

                result(authToken)

            } catch (e: Exception) {
                println(e)
            }

        }.start()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        /*if (resultCode == Activity.RESULT_OK && requestCode == REGISTER_CALL) {
            Toast.makeText(this, "SUCCESSFUL REGISTRATION", Toast.LENGTH_LONG).show()
            println("SUCCESSFUL REGISTRATION")
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }*/
    }

    class OnTokenAcquired: AccountManagerCallback<Bundle> {
        override fun run(future: AccountManagerFuture<Bundle>?) {
            val bundle = future?.result
            val token = bundle?.getString(AccountManager.KEY_AUTHTOKEN)
            println("Token: $token")
            val loginIntent = bundle?.get(AccountManager.KEY_INTENT)
            if (loginIntent != null) {
                println("Repeat login")
            }
        }
    }

    class OnError: Handler.Callback {
        override fun handleMessage(msg: Message?): Boolean {
            println(msg.toString())
            TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
        }
    }
}