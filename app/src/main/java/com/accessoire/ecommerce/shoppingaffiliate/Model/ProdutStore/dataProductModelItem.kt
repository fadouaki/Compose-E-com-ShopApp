package com.accessoire.ecommerce.shoppingaffiliate.Model.ProdutStore

data class dataProductModelItem(
    val category: String,
    val description: String,
    val link : String,
    val id: Int,
    val image: List<String>,
    val price: Double,
    val rating: Rating,
    val title: String
)
