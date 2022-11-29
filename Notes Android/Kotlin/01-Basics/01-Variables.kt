fun main(){
    // val keyword is used to declare constants
    val PI: Double = 3.14;

    // Mutable variables
    var radius: Double = 12.0;
    var area: Double = PI * radius * radius

    val LANG = "KOTLIN"
    println("Area = ${area} ${LANG}")
}

class Variable{
    var name: String? = null    // Nullable variable since can not just declare. Must give a value
    var caste: String = ""
    lateinit var dept: String   // Can be initialized later
}