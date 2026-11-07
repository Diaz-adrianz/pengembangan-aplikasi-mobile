package pam.pertemuan8.uts.adapter


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import pam.pertemuan8.uts.data.CartManager
import pam.pertemuan8.uts.data.Product
import pam.pertemuan8.uts.databinding.ItemProductBinding
import pam.pertemuan8.uts.util.toRupiah

class ProductAdapter(
    private var data: List<Product>,
    private val onCartChanged: () -> Unit
) : RecyclerView.Adapter<ProductAdapter.VH>() {

    inner class VH(val b: ItemProductBinding) : RecyclerView.ViewHolder(b.root)

    fun submit(list: List<Product>) {
        data = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        VH(ItemProductBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: VH, position: Int) {
        val p = data[position]
        val b = holder.b

        b.imgProduct.setImageResource(p.imageRes)
        b.tvName.text = p.name
        b.tvDesc.text = p.description
        b.tvPrice.text = p.price.toRupiah()

        val qty = CartManager.qtyOf(p.id)
        b.btnAdd.isVisible = qty == 0
        b.layoutStepper.isVisible = qty > 0
        b.tvQty.text = qty.toString()

        fun change(action: () -> Unit) {
            action()
            val pos = holder.bindingAdapterPosition
            if (pos != RecyclerView.NO_POSITION) notifyItemChanged(pos)
            onCartChanged()
        }

        b.btnAdd.setOnClickListener { change { CartManager.add(p) } }
        b.btnPlus.setOnClickListener { change { CartManager.add(p) } }
        b.btnMinus.setOnClickListener { change { CartManager.decrease(p) } }
    }
}