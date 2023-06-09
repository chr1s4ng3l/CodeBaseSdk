package com.tamayo.code_base_sdk.data.model


import com.google.gson.annotations.SerializedName

data class Icon(
    @SerializedName("Height")
    val height: String? = null,
    @SerializedName("URL")
    val uRL: String? = null,
    @SerializedName("Width")
    val width: String? = null
)