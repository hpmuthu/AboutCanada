package com.example.aboutcanada.pojo

import com.google.gson.annotations.SerializedName

data class CountryInfo (

    @SerializedName("title") val title : String,
    @SerializedName("rows") val rows : List<CountryInfoItem>?
)