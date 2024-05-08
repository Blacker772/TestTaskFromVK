package com.example.testtaskfromvk.data.productmodel

import com.google.gson.annotations.SerializedName
import javax.inject.Inject

data class Product @Inject constructor(
    val id: Int?,
    val title:String?,
    val description: String?,
    val price: String?,
    val rating: String?,
    val stock: String?,
    val brand: String?,
    val category: String?,
    @SerializedName("thumbnail")
    val image: String?
)