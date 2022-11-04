fun main() {
    var num = -7;

    var result = if(num > 0) "Positive" else "Negative";
    println(result)

    num = 0
    result = when{
        num > 0 -> "Positive"
        num < 0 -> "Negative"
        else -> "Zero"
    }
    println(result)
}