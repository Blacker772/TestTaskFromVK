package com.example.testtaskfromvk.data.productmodel

import javax.inject.Inject

data class AllProducts @Inject constructor(
    val products: List<Product>
)

