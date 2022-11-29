data class Person(val id: String, val name: String, val surname: String)

fun main(args: Array<String>) {
    val person = Person("ebdcf1234", "James", "Scott")

    println(person)
}