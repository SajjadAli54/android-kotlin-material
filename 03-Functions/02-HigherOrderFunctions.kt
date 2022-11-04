fun apply(x: Int, action: (Int) -> Int): Int{
    return action(x)
}

fun main(args: Array<String>) {
    println(apply(4, {x -> x*x}))
    println(apply(4, {x -> x/2}))

    var array = arrayOf(1,2,3,4,5,6)
    var even = array.filter({it % 2 == 0})

    even.forEach({print("${it} ")})
}