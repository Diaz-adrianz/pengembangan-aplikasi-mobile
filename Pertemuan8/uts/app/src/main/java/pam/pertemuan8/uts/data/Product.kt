package pam.pertemuan8.uts.data

import androidx.annotation.DrawableRes

enum class Category { DRINK, FOOD }

data class Product(
    val id: Int,
    val name: String,
    val description: String,
    val price: Int,
    @DrawableRes val imageRes: Int,
    val category: Category
)