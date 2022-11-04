class App(name: String) {
    init{
        println("Instances created for $name");
    }
}

class Person {

    var name: String
    var age: Int

    constructor(name:String, age:Int){

        this.name = name;
        this.age = age;
    }

    override fun toString():String{
        return "${name} ${age}";
    }    
}

data class Student(var id:Int, var name: String,var age:Int)

fun main(args: Array<String>) {
    var person = Person("Sajjad Ali", 21)
    println(person)

    var std = Student(1, "Sajjad Ali", 22)

    println(std)
}