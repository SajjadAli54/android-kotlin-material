fun String.toCamelCase(): String{
    return this.split("").map({it -> it.lowercase().capitalize()}).joinToString(separator="")
}

fun main(args: Array<String>) {
    println("this is a test".toCamelCase())
}