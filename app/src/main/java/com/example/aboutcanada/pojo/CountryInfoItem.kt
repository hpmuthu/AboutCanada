package com.example.aboutcanada.pojo

import com.google.gson.annotations.SerializedName

data class CountryInfoItem (

    @SerializedName("title") val title : String?,
    @SerializedName("description") val description : String?,
    @SerializedName("imageHref") val imageHref : String?
)