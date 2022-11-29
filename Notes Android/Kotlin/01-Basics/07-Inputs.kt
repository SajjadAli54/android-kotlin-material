// kotlinc 07-Inputs.kt -include-runtime -d 07-Inputs.jar && java -jar 07-Inputs.jar
fun main(args: Array<String>) {
    print("Enter your age : ")
    var age = readLine()!!.toInt()
    println("Uou entered ${age}")
}