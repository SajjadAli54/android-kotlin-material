fun main(args: Array<String>) {
    var contacts = arrayOf("John", "James", "Amy")
    for(obj in contacts)
        println(obj)
    println()
    // Functional Style
    contacts.forEach({el -> println(el)})
}