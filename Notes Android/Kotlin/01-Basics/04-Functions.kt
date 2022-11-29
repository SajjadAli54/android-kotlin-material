class Customer{
    var fname: String = "Sajjad"
    var lname: String = "Ali"
    var salary: Int = 500


    fun set(fname: String="Sajjad", lname: String="Ali", salary: Int=500){
        this.fname = fname;
        this.lname = lname;
        this.salary = salary;
    }    
}

// Block functions. Return type is optional
fun getFullName(cust: Customer): String{
    return "${cust.fname} ${cust.lname}";
}

// Single line functions
fun getSalary(cust: Customer) = cust.salary; 

fun main(){
    var cust: Customer = Customer()
    cust.set("Shayan", salary=1200)

    println(getFullName(cust))
    println(getSalary(cust))
}