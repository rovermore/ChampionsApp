package com.example.championsapp.model

import com.google.gson.annotations.SerializedName

data class Champion (
    @SerializedName("iconURL")
    var image: String?
)