package bec.c231371.poss.sales

import androidx.lifecycle.*
import bec.c231371.poss._services.navigation.NavigationDispatcher
import bec.c231371.poss.sales._models.Product
import bec.c231371.poss.sales._models.ProductWithQuantity
import bec.c231371.u8_apis.products._models.ProductRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.concurrent.atomic.AtomicInteger
import javax.inject.Inject

@HiltViewModel
class SaleViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val navigationDispatcher: NavigationDispatcher,
    private val repository: ProductRepository
) : ViewModel() {

    init {
        viewModelScope.launch {
            repository.refresh()
            repository.getProducts()
                .map { list -> list.associateBy { it.barcode } }
                .collect {
                    barcodeProductMap = it
                    pushUpdate()
                }
        }
    }

    private var barcodeProductMap = mapOf<String, Product>()
    private val barcodeQuantityMap = mutableMapOf<String, AtomicInteger>()

    private val _basket = MutableLiveData<List<ProductWithQuantity>>()
    val basket: LiveData<List<ProductWithQuantity>> = _basket

    private fun pushUpdate() {
        _basket.value = barcodeQuantityMap.map { (barcode, quantity) ->
            barcodeProductMap[barcode]
                ?.let { product -> ProductWithQuantity(product, quantity.get()) }
                ?: ProductWithQuantity(barcode, quantity.get())
        }
    }

    fun incProduct(barcode: String) {
        barcodeQuantityMap[barcode]?.incrementAndGet()
            ?: run { barcodeQuantityMap[barcode] = AtomicInteger(1) }
        pushUpdate()
    }

    fun decProduct(barcode: String) {
        barcodeQuantityMap[barcode]?.decrementAndGet()
            ?.also { if (it == 0) barcodeQuantityMap.remove(barcode) }
        pushUpdate()
    }

    fun updateProduct(
        oldBarcode: String,
        newBarcode: String,
        name: String,
        priceSatang: Int,
        category: String
    ) {
        // Update barcode in basket
        val quantity = barcodeQuantityMap[oldBarcode]?.get() ?: 1
        barcodeQuantityMap.remove(oldBarcode)
        barcodeQuantityMap[newBarcode] = AtomicInteger(quantity)

        // Update product data
        viewModelScope.launch {
            repository.updateProduct(oldBarcode, newBarcode, name, priceSatang, category)
        }
    }

}