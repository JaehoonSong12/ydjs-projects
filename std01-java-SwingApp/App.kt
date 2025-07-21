/**
INSTRUCTIONS: 
    Running two different main methods (AppKt.main / ClassShell.main) in Kotlin
    The 'Kt' suffix is only for `top-level functions`, not for `class-based main methods`.



COMPILE & EXECUTE & CLEANUP (Kotlin #1 - top-level main function):

     kotlinc    -d out              App*.kt 
     kotlin             -cp out     AppKt
     rm -rf out/

COMPILE & EXECUTE & CLEANUP (Kotlin #2 - class-based main method):

     kotlinc    -d out              App*.kt
     kotlin             -cp out     ClassShell
     rm -rf out/




     

     
 */


val name: String = "Nate" // "val" means "val-ue" immutable (read-only), equivalent to "final" in JAVA
val sigma: String? = null // "?" means "nullable" (can be null), no equivalent in JAVA
var greeting: String? = "Hello" // "var" means "var-iable"  mutable (can be changed)
    get() = field ?: "dafdfdsfx" // customize getter for null case
val exampleThings = arrayOf("apple","tree","coding") // array example
val exampleTwoThings = mutableListOf("um","umm","ummm") // list example
val map = mutableMapOf(1 to "a", 2 to "b", 3 to "c") // map example
// By Kotlinc, the following methods are automatically setup.
// public String getGreeting()
// public void setGreeting(String greeting)



fun sayHello(): Unit { // "Unit" means "void" for function prototype in JAVA
    println(greeting)
}

fun example(example_stuff:String): Boolean {
    return greeting == "Hello $example_stuff"
}



fun main(args: Array<String>) {




    println("Hello from top-level main! (AppKt)")
    if (args.isNotEmpty()) {
    println("Arguments passed: ${args.joinToString(", ")}")
    } else {
    println("No arguments passed.")
    }

    ///////////////////////////////////////////////////////////////////

    println(exampleThings.size)
    println(exampleThings[1])
    println(exampleThings.get(0))




    for (exampleThing in exampleThings) {
        println(exampleThing)
    } // Instead you can use forEach -->

    exampleThings.forEach { thing ->
        println(thing)
    }// Like this

    exampleThings.forEachIndexed { index, thing ->
        println("$thing is at index $index")
    }// Or this

    ///////////////////////////////////////////////////////////////////

    exampleTwoThings.add("Cat")// add new thing
    println(exampleTwoThings)

    ///////////////////////////////////////////////////////////////////

    map.forEach { (key, value): Map.Entry<Int, String> -> println("$key -> $value") }
    map.put(4, "d")// add new thing
    map.forEach { (key, value): Map.Entry<Int, String> -> println("$key -> $value") }




    ///////////////////////////////////////////////////////////////////

    
    greeting = "test"
    // greeting = null





    when(greeting) { // equivalent to "switch-case" in JAVA
        null -> println("nothing")
        else -> println("greeting: $greeting")
    }




    if (greeting != null) {
        println(greeting)
    } 



    ///////////////////////////////////////////////////////////////////

    for (i in 1..5) {
        println("Iteration: $i")
    }

    println(name)

    greeting = null
    
    sayHello()

    println(example("Jayden"))
}
/*
     kotlinc    -d out              App*.kt 
     kotlin             -cp out     AppKt
     rm -rf out/

     
*/





class ClassShell {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) { 
            println("Hello from ClassShell (any name can be done), Kotlin!")
        }
    }
}