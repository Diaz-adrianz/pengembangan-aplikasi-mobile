package pam.pertemuan8.uts.data

data class Order(
    val id: String,
    val items: List<CartItem>,
    val total: Int,
    val recipient: String,
    val address: String,
    val landmark: String
)