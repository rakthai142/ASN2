package bec.c231371.poss.sales._models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Product(
    @PrimaryKey var barcode: String = "",
    var name: String = "",
    var priceSatang: Int = 0,
    var category: String = ""
)
