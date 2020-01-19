package com.example.aboutcanada.webService

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object ApiClient {

    //LIVE
    private val URL = "https://dl.dropboxusercontent.com/"

    private var retrofit: Retrofit? = null


    val client: Retrofit
        get() {

            if (retrofit == null) {
                val okHttpClient = OkHttpClient()
                    .newBuilder()
                    .build()

                retrofit = Retrofit.Builder()
                    .baseUrl(URL)
                    .client(okHttpClient)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
            }
            return this.retrofit!!
        }

    fun getService(): ApiService {
        return client.create(ApiService::class.java)
    }

}
