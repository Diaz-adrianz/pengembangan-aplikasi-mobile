package pam.pertemuan8.uts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pam.pertemuan8.uts.R
import pam.pertemuan8.uts.data.CartItem
import pam.pertemuan8.uts.databinding.ItemOrderBinding
import pam.pertemuan8.uts.util.toRupiah

class OrderItemAdapter(private val items: List<CartItem>) :
    RecyclerView.Adapter<OrderItemAdapter.VH>() {

    inner class VH(val b: ItemOrderBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemOrderBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        val ctx = holder.itemView.context
        holder.b.tvName.text = item.product.name
        holder.b.tvQtyPrice.text =
            ctx.getString(R.string.item_qty_price, item.qty, item.product.price.toRupiah())
        holder.b.tvSubtotal.text = item.subtotal.toRupiah()
    }
}