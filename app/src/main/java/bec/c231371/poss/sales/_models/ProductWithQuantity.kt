package bec.c231371.poss.sales._models

data class ProductWithQuantity(
    val barcode: String,
    val name: String,
    val priceSatang: Int,
    val category: String,
    val quantity: Int
) {
    constructor(product: Product, quantity: Int)
            : this(product.barcode, product.name, product.priceSatang,
        product.category, quantity)

    constructor(barcode: String, quantity: Int)
            : this(barcode, "Unknown\n[${barcode}]", 0, "unknown", quantity)
}
