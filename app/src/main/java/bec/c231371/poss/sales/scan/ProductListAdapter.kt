package bec.c231371.poss.sales.scan

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import bec.c231371.poss.databinding.ItemProductQuantityBinding
import bec.c231371.poss.sales._models.ProductWithQuantity

class ProductListAdapter(
    private var data: List<ProductWithQuantity>,
    private val itemClickListener: (pq: ProductWithQuantity) -> Unit
) : RecyclerView.Adapter<ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemProductQuantityBinding.inflate(inflater, parent, false)
        return ProductViewHolder(binding, itemClickListener)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) =
        holder.bind(data[position])

    override fun getItemCount(): Int = data.size

    fun update(data: List<ProductWithQuantity>) {
        this.data = data
        notifyDataSetChanged()
    }

}