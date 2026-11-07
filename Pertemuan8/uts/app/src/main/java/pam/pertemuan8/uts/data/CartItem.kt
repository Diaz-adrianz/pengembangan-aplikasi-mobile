package pam.pertemuan8.uts.data

data class CartItem(val product: Product, var qty: Int) {
    val subtotal: Int get() = product.price * qty
}