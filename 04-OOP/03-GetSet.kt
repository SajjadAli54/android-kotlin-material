class User {
    var name = ""
    get() = field

    set(value){
        field = value + "!"
    }

    var age = 0
    get() = field - 1

    set(value){
        field = if(value < 0) 18 else value
    }
}

fun main(args: Array<String>) {
    val user = User()

    user.name = "James"
    user.age = -12

    println("${user.name} ${user.age}")
}