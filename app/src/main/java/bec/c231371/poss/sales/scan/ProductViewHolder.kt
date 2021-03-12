package bec.c231371.poss.sales.scan

import androidx.recyclerview.widget.RecyclerView
import bec.c231371.poss.databinding.ItemProductQuantityBinding
import bec.c231371.poss.sales._models.ProductWithQuantity

class ProductViewHolder(
    private val binding: ItemProductQuantityBinding,
    private val itemClickListener: (pq: ProductWithQuantity) -> Unit
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(pq: ProductWithQuantity) = with(binding) {
        pqName.text = pq.name
        pqPrice.text = (pq.priceSatang / 100.0).toString()
        pqQuantity.text = pq.quantity.toString()
        pqTotal.text = ((pq.priceSatang * pq.quantity) / 100.0).toString()

        root.setOnClickListener { itemClickListener(pq) }
    }

}