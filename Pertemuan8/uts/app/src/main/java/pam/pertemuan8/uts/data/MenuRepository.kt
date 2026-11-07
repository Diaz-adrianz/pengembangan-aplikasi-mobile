package pam.pertemuan8.uts.data

import pam.pertemuan8.uts.R


object MenuRepository {

    val products: List<Product> = listOf(
        Product(
            id = 1,
            name = "Kopi Susu Gula Aren",
            description = "Espresso, susu segar, dan gula aren yang legit",
            price = 22000,
            imageRes = R.drawable.img_kopi_susu,
            category = Category.DRINK
        ),
        Product(
            id = 2,
            name = "Donat Cokelat",
            description = "Donat empuk dengan glaze cokelat",
            price = 12000,
            imageRes = R.drawable.img_donat,
            category = Category.FOOD
        ),
        Product(
            id = 3,
            name = "Sourdough Loaf",
            description = "Roti fermentasi alami dengan kulit renyah",
            price = 35000,
            imageRes = R.drawable.img_sourdough,
            category = Category.FOOD
        ),
        Product(
            id = 4,
            name = "Cappuccino",
            description = "Espresso dengan foam susu yang lembut",
            price = 25000,
            imageRes = R.drawable.img_cappucino,
            category = Category.DRINK
        ),
        Product(
            id = 5,
            name = "Matcha Latte",
            description = "Matcha Jepang dengan susu segar",
            price = 27000,
            imageRes = R.drawable.img_matcha_latte,
            category = Category.DRINK
        ),
        Product(
            id = 6,
            name = "Banana Cake",
            description = "Bolu lembut berbahan dasar pisang",
            price = 27000,
            imageRes = R.drawable.img_banana_cake,
            category = Category.FOOD
        ),
        Product(
            id = 7,
            name = "Croissant",
            description = "Plain croissant",
            price = 27000,
            imageRes = R.drawable.img_croissant,
            category = Category.FOOD
        ),
        Product(
            id = 8,
            name = "Mont Blanc",
            description = "Cold brew dengan krim lembut dan irisan jeruk",
            price = 27000,
            imageRes = R.drawable.img_mont_blanc,
            category = Category.DRINK
        )
    )
}