package com.example.aboutcanada.webService

import com.example.aboutcanada.pojo.CountryInfo
import retrofit2.Call
import retrofit2.http.*

interface ApiService {

    @GET("s/2iodh4vg0eortkl/facts.json")
    fun getCountryInfo(@Header("Content-Type") contentType: String): Call<CountryInfo>

}
