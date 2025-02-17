package com.accessoire.ecommerce.shoppingaffiliate.RetrofitData

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.accessoire.ecommerce.shoppingaffiliate.Model.Category.CategoryModelItem
import com.accessoire.ecommerce.shoppingaffiliate.Model.OfferModel.OfferModelItem
import com.accessoire.ecommerce.shoppingaffiliate.Model.ProdutStore.dataProductModelItem
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StoreViewModel : ViewModel() {
    private val _products = MutableStateFlow<List<dataProductModelItem>>(emptyList())
    private val _categories = MutableStateFlow<List<CategoryModelItem>>(emptyList())
    private val _offers = MutableStateFlow<List<OfferModelItem>>(emptyList())
    val products: StateFlow<List<dataProductModelItem>> = _products
    val categories: StateFlow<List<CategoryModelItem>> = _categories
    val offers: StateFlow<List<OfferModelItem>> = _offers

    private val api_products = Retrofit.Builder()
        .baseUrl("https://www.dropbox.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StoreApi::class.java)
    private val api_categories = Retrofit.Builder()
        .baseUrl("https://www.dropbox.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StoreApi::class.java)
    private val api_offers = Retrofit.Builder()
        .baseUrl("https://www.dropbox.com")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(StoreApi::class.java)

    init {
        fetchProducts()
        fetchCategories()
        fetchOffers()
    }

    private fun fetchProducts() {
        viewModelScope.launch {
            try {
                val fetchedProducts = api_products.getProducts()
                _products.value = fetchedProducts
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private fun fetchCategories() {
        viewModelScope.launch {
            try {
                val fetchedCategories = api_categories.getCategories()
                _categories.value = fetchedCategories
            } catch (e: Exception) {
                // Handle error
            }
        }
    }

    private fun fetchOffers() {
        viewModelScope.launch {
            try {
                val fetchedOffers = api_offers.getOffers()
                _offers.value = fetchedOffers
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}