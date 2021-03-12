package bec.c231371.u8_apis.courses._models

import androidx.room.*
import bec.c231371.poss.sales._models.Product
import kotlinx.coroutines.flow.Flow

@Dao
abstract class ProductDao {

    @Query("SELECT * FROM Product ORDER BY barcode")
    abstract fun getProducts(): Flow<List<Product>>

    @Query("SELECT * FROM Product WHERE barcode == :barcode")
    abstract fun getProductByBarcode(barcode: String): Flow<Product>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    protected abstract fun insertProducts(vararg products: Product)

    @Query("DELETE FROM Product WHERE barcode == :barcode")
    protected abstract fun deleteProduct(barcode: String)

    @Query("DELETE FROM Product")
    protected abstract fun deleteProducts()

    @Transaction
    open fun replaceProducts(products: List<Product>) {
        deleteProducts()
        insertProducts(*products.toSet().toTypedArray())
    }

    @Transaction
    open fun replaceProduct(barcode: String, product: Product) {
        deleteProduct(barcode)
        insertProducts(product)
    }

}
