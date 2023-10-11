package id.ac.del.pam.w06s03

data class Menu(
    val Image: Int,
    val title: String,
    val price: String
)

val Menus = listOf(
    Menu(R.drawable.menu1, "Tiramisu Coffeee Milk", "Rp 28.000"),
    Menu(R.drawable.menu2, "Pumpkin Spice Latte", "Rp 18.000"),
    Menu(R.drawable.menu3, "Light Cappuccino", "Rp 20.000"),
    Menu(R.drawable.menu4, "Choco Creamy Latte", "Rp 16.000")
)

val dummyBestSellerMenu = Menus.shuffled()