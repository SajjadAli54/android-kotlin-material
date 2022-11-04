open class User(var name: String, var age: Int) {
    
}

class Admin(name: String, age: Int): User(name, age) {
    
}

class Moderator(name: String, age: Int, var country: String): User(name, age) {
    
}

fun main(args: Array<String>) {
    val mod = Moderator("Amy", 23, "USA")

    println(mod.country)
}