/**
INSTRUCTIONS: 
    Running two different main methods (DecoratorPracticeKt.main / ClassShell.main) in Kotlin
    The 'Kt' suffix is only for `top-level functions`, not for `class-based main methods`.



COMPILE & EXECUTE & CLEANUP (Kotlin #1 - top-level main function):

     kotlinc    -d out              DecoratorPractice*.kt 
     kotlin             -cp out     DecoratorPracticeKt
     rm -rf out/

COMPILE & EXECUTE & CLEANUP (Kotlin #2 - class-based main method):

     kotlinc    -d out              DecoratorPractice*.kt
     kotlin             -cp out     ClassShell
     rm -rf out/




     

     
 */
fun main() {
    print("Hello World")
}

interface Pizza {
    fun description(): String
    fun cost(): Double
}

class NormalPizza : Pizza {
    override fun description(): String = "Pizza"
    override fun cost(): Double = 1.00
}

abstract class Decorator(protected val pizza: Pizza) : Pizza {
    override fun description(): String = pizza.description()
    override fun cost(): Double = pizza.cost()
}
