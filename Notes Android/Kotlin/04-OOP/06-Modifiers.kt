class User(var name: String, private var age: Int) {
    
    fun getAge(): Int{
        return if(age < 18) 18 else age
    }

    fun setAge(age: Int){
        this.age = if(age < 0) 18 else age
    }
}

fun main(args: Array<String>) {
    val user = User("Amy", -12)

    // println(user.age) // causes error if uncommented
    println(user.getAge())
}