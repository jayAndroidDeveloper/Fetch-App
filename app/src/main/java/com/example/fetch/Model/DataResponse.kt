package com.example.fetch.Model

import com.google.gson.annotations.SerializedName

data class DataResponse(
    @SerializedName("title") val title: String,
    @SerializedName("description") val description: String,
    @SerializedName("urlToImage") val urlToImage: String,
    @SerializedName("earning") val earning: String,
)