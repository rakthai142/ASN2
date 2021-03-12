package bec.c231371.u8_apis.products._models

import bec.c231371.poss.sales._models.Product
import bec.c231371.u8_apis.courses._models.ProductDao
import bec.c231371.u8_apis.courses._models.ProductWebService
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

@ViewModelScoped
class ProductRepository @Inject constructor(
    private val service: ProductWebService,
    private val dao: ProductDao
) {

    suspend fun refresh() = withContext(Dispatchers.IO) {
        // Call the JSON API (get new data)
        val response = service.getProducts()

        // Save the new data in local db
        dao.replaceProducts(response.payload)
    }

    fun getProducts(): Flow<List<Product>> = dao.getProducts()

    fun getProductByCode(code: String): Flow<Product> = dao.getProductByBarcode(code)

    suspend fun updateProduct(
        oldBarcode: String,
        newBarcode: String,
        name: String,
        priceSatang: Int,
        category: String
    ) = withContext(Dispatchers.IO){
        dao.replaceProduct(oldBarcode,Product(newBarcode,name,priceSatang,category))
    }
}