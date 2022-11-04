var sum: (Int, Int) -> Int = {a, b -> a+b}
var mul : (Int, Int) -> Int = {a,b -> a*b}

fun main(args: Array<String>) {
    println(sum(4, 5))
    println(mul(3, 4))

    var array = arrayOf(1,2,3,4,5,6)

    println("Higher order functions")
    array.forEach{item -> print(item)}
    
    println("\nfor short, kotlin provides it keyword")
    array.forEach{print(it)}
}