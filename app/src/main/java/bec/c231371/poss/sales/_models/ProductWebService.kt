package bec.c231371.u8_apis.courses._models

import bec.c231371.poss.sales._models.Product
import bec.c231371.u8_apis._services.api.WebServiceResultList
import retrofit2.http.GET

interface ProductWebService {

    @GET("products")
    suspend fun getProducts(): WebServiceResultList<Product>

}
