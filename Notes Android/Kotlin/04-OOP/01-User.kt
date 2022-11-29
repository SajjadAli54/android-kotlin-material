class User {
    var name = ""
    var age = 0
}

fun main(args: Array<String>) {
    val user = User()

    user.name = "James"
    user.age = 42

    println("${user.name} ${user.age}")
}