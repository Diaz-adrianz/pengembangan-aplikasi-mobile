package pam.pertemuan8.uts.data


object CartManager {

    private val map = linkedMapOf<Int, CartItem>()

    val items: List<CartItem> get() = map.values.map { it.copy() }
    val totalQty: Int get() = map.values.sumOf { it.qty }
    val totalPrice: Int get() = map.values.sumOf { it.subtotal }
    val isEmpty: Boolean get() = map.isEmpty()

    fun qtyOf(productId: Int): Int = map[productId]?.qty ?: 0

    fun add(product: Product) {
        val existing = map[product.id]
        if (existing == null) map[product.id] = CartItem(product, 1) else existing.qty++
    }

    fun decrease(product: Product) {
        val existing = map[product.id] ?: return
        if (existing.qty <= 1) map.remove(product.id) else existing.qty--
    }

    fun clear() = map.clear()
}