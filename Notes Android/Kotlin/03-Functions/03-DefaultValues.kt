fun createBook(title: String, 
    subtitle: String? = null,
    paperBack: Boolean = false,
    price: Double = 7.99) {

        println(
            """
            Title    = ${title}
            subtitle = ${subtitle}
            Back     = ${paperBack}
            Price    = ${price}
            """
        )
}

fun main(args: Array<String>) {
    createBook("Ice and Fire", price = 16.9)
}