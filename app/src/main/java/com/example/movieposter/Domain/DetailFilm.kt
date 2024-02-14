package com.example.movieposter.Domain

import com.google.gson.annotations.SerializedName


data class DetailFilm(
    @SerializedName("data") val data: List<Data> = arrayListOf(),
    @SerializedName("metadata") val metadata: Metadata? = Metadata(),
)