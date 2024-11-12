package com.example.ppapb12.modal

import com.google.gson.annotations.SerializedName


data class Api(
    @SerializedName("tanggal")
    val tanggal : String,
    @SerializedName("keterangan")
    val keterangan : String,
    @SerializedName("is_cuti")
    val cuti : Boolean
)
