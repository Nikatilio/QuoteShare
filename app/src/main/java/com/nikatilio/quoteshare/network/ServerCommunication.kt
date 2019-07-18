package com.nikatilio.quoteshare.network

import org.json.JSONObject
import java.io.BufferedReader
import java.io.DataOutputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class ServerCommunication {

    companion object {
        var authToken: String? = null
    }

    fun userSignIn(username: String, password: String, authTokenType: String?): String {
        return ""
    }

    fun checkToken(): Boolean {
        return true
    }

    fun quoteOfTheDay(): String {

        val appToken = "e8a87992e9aed0055542bd2fe4c129e9"

        Thread {
            try {
                val url = URL("https://favqs.com/api/quotes")
//                val url = URL("https://favqs.com/api/qotd")
                val client = url.openConnection() as HttpsURLConnection
                client.requestMethod = "GET"
                client.addRequestProperty("Content-Type", "application/json")
                client.addRequestProperty("Authorization", "Token token=$appToken")
                client.addRequestProperty("User-Token", authToken)

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
                println(json.toString())

            } catch (e: Exception) {
                println(e)
            }

        }.start()

        return ""
    }
}