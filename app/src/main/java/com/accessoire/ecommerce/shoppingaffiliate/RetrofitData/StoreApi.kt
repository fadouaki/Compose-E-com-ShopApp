package com.accessoire.ecommerce.shoppingaffiliate.RetrofitData

import com.accessoire.ecommerce.shoppingaffiliate.Model.Category.CategoryModelItem
import com.accessoire.ecommerce.shoppingaffiliate.Model.OfferModel.OfferModelItem
import com.accessoire.ecommerce.shoppingaffiliate.Model.ProdutStore.dataProductModelItem
import retrofit2.http.GET


interface StoreApi {
    @GET("scl/fi/5bl2v9vttj84or2u05z44/StoreContent.json?rlkey=jzsw82kpriuoqw356kxi822ds&st=skabv0ks&dl=1")
    suspend fun getProducts(): List<dataProductModelItem>
    @GET("scl/fi/v0z8tolns2vmq2nuisy9o/categories.json?rlkey=mvnikbzjljqkcw0dyip0wf0rg&st=uu54t0rh&dl=1")
    suspend fun getCategories(): List<CategoryModelItem>
    @GET("scl/fi/qkgfiplfdwbdpj73yi2pr/slides.json?rlkey=8a0dv64qs5eazhgp4c4hfi4jc&st=icv5h8hi&dl=1")
    suspend fun getOffers(): List<OfferModelItem>
}
