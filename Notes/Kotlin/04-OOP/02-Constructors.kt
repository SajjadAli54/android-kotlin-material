// Primary constructor class
class User(val name: String, val age: Int) {
    var salary = 0

    constructor(name: String, age: Int, salary: Int): this(name, age){
        this.salary = salary;
    }
}

fun main(args: Array<String>) {
    val user = User("James", 42, 300)

    println("${user.name} ${user.age} ${user.salary}");
}