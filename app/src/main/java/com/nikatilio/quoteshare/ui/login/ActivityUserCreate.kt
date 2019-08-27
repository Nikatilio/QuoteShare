package com.nikatilio.quoteshare.ui.login

import android.accounts.AccountManager
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.nikatilio.quoteshare.BuildConfig
import com.nikatilio.quoteshare.R
import com.nikatilio.quoteshare.utils.ServiceAuthenticator
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.HttpURLConnection
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ActivityUserCreate: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_create)

        createAccount()
    }

    fun createAccount() {
        val username = BuildConfig.TEST_LOGIN
        val password = BuildConfig.TEST_PASSWORD
        val appToken = BuildConfig.FAVQS_API_KEY

//        val accountType = intent.getStringExtra(AccountManager.KEY_ACCOUNT_TYPE)
        val accountType = "com.nikatilio.quoteshare"

        // Validate input info

        val authToken = "" // Create Account

        ////////////////

        Thread {
            try {
                val url = URL("https://favqs.com/api/users")
//                val url = URL("https://favqs.com/api/qotd")
                val client = url.openConnection() as HttpsURLConnection
                client.requestMethod = "POST"
                client.addRequestProperty("Content-Type", "application/json")
                client.addRequestProperty("Authorization", "Token token=$appToken")

                val userJson = JSONObject()
                userJson.put("login", username)
//                userJson.put("email", email)
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

                if (client.responseCode == 200) {
                    val br = BufferedReader(InputStreamReader(client.inputStream))
                    var output = br.readLine()
                    while (output != null) {
                        println(output)
                        output = br.readLine()
                    }
                }


            } catch (e: Exception) {

            }
            ////////////////

            val authTokenType = "StandardUserAccessToken"

            if (authToken.isEmpty()) {
                // Account can't be registered
            }

            val bundle = Bundle()
            bundle.putString(AccountManager.KEY_ACCOUNT_NAME, username)
            bundle.putString(AccountManager.KEY_ACCOUNT_TYPE, accountType)
            bundle.putString(AccountManager.KEY_AUTHTOKEN, authToken)
            bundle.putString(ServiceAuthenticator.PASSWORD, password)
            bundle.putString(ServiceAuthenticator.TOKEN_TYPE, authTokenType)

            val resultIntent = Intent()
            resultIntent.putExtras(bundle)

            setResult(RESULT_OK, resultIntent)
            finish()

        }.start()



    }
}