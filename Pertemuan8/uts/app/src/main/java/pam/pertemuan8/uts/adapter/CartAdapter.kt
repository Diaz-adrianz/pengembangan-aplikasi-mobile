package pam.pertemuan8.uts.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import pam.pertemuan8.uts.data.CartItem
import pam.pertemuan8.uts.data.CartManager
import pam.pertemuan8.uts.databinding.ItemCartBinding
import pam.pertemuan8.uts.util.toRupiah

class CartAdapter(
    private var items: List<CartItem>,
    private val onChanged: () -> Unit
) : RecyclerView.Adapter<CartAdapter.VH>() {

    inner class VH(val b: ItemCartBinding) : RecyclerView.ViewHolder(b.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemCartBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val item = items[position]
        val b = holder.b

        b.imgProduct.setImageResource(item.product.imageRes)
        b.tvName.text = item.product.name
        b.tvPrice.text = item.subtotal.toRupiah()
        b.tvQty.text = item.qty.toString()

        b.btnPlus.setOnClickListener {
            CartManager.add(item.product)
            refresh()
        }
        b.btnMinus.setOnClickListener {
            CartManager.decrease(item.product)
            refresh()
        }
    }

    private fun refresh() {
        items = CartManager.items
        notifyDataSetChanged()
        onChanged()
    }
}