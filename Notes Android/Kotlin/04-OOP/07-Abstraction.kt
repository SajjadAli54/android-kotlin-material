abstract class User(var name: String, var age: Int) {
    abstract fun display()

    open fun greet(){
        println("Greet from User ${name}")
    }
}

class Admin(name: String, age: Int): User(name, age) {
    override fun display(){
        println("$name is $age years old")
    }

    override fun greet(){
        println("Greeting from Admin ${name}")
    }
}

class Moderator(name: String, age: Int, var country: String): User(name, age) {
    override fun display(){
        println("$name is from $country")
    }

    override fun greet(){
        println("Greeting from Moderator $name")
    }
}

fun main(args: Array<String>) {
    val admin: User = Admin("James", 42)
    val mod: User = Moderator("Amy", 23, "USA")
    
    admin.display()
    admin.greet()

    println()

    mod.display()
    mod.greet()
}