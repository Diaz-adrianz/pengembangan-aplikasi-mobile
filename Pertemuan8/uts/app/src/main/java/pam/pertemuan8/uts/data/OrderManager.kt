package pam.pertemuan8.uts.data

object OrderManager {

    var lastOrder: Order? = null
        private set

    fun placeOrder(recipient: String, address: String, landmark: String): Order {
        val order = Order(
            id = "KN-" + (1000..9999).random(),
            items = CartManager.items,
            total = CartManager.totalPrice,
            recipient = recipient,
            address = address,
            landmark = landmark
        )
        lastOrder = order
        CartManager.clear()
        return order
    }
}