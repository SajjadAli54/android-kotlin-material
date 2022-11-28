fun main(){
    println("Print values from 1 to 10")
    for(i in 1..10){
        print("$i ");
    }

    println("\nPrint values from 1 to 10. 10 excluded")
    for(i in 1 until 10){
        print("$i ");
    }

    println("\nPrint odd values from 1 to 10")
    for(i in 1..10 step 2){
        print("$i ")
    }

    println("\nPrint values from 10 to 1 in a descending order")
    for(i in 10 downTo 1){
        print("$i ");
    }

    println("\nPrint even values from 10 to 1 in a descending order")
    for(i in 10 downTo 1 step 2){
        print("$i ");
    }

}