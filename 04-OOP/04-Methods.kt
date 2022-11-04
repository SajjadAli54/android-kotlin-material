class User(var name: String, var age: Int) {
    
    fun login(){
        println("""Login from ${name}. 
        Adult = ${isAdult()}""")
    }

    fun isAdult(): Boolean{
        return if(age >= 18) true else false
    }

    fun greet(){
        println("Greet")
    }
}

fun main(args: Array<String>) {
    val user = User("James", 42)

    user.login()
}