/**
INSTRUCTIONS: 
    Running two different main methods (DecoratorDemoKt.main / ClassShell.main) in Kotlin
    The 'Kt' suffix is only for `top-level functions`, not for `class-based main methods`.



COMPILE & EXECUTE & CLEANUP (Kotlin #1 - top-level main function):

     kotlinc    -d out              DecoratorDemo*.kt 
     kotlin             -cp out     DecoratorDemoKt
     rm -rf out/

COMPILE & EXECUTE & CLEANUP (Kotlin #2 - class-based main method):

     kotlinc    -d out              DecoratorDemo*.kt
     kotlin             -cp out     ClassShell
     rm -rf out/




     

     
 */

fun main() {
    // 1. Basic Espresso
    var beverage: Beverage = Espresso()
    println("Base: ${beverage.getDescription()} | Cost: $${"%.2f".format(beverage.getCost())}")

    // 2. Add Milk
    beverage = MilkDecorator(beverage)
    println("With Milk: ${beverage.getDescription()} | Cost: $${"%.2f".format(beverage.getCost())}")

    // 3. Add Mocha on top of Milk
    beverage = MochaDecorator(beverage)
    println("With Milk and Mocha: ${beverage.getDescription()} | Cost: $${"%.2f".format(beverage.getCost())}")
}

interface Beverage {
    fun getCost(): Double
    fun getDescription(): String
}

class Espresso : Beverage {
    override fun getCost(): Double = 1.99
    override fun getDescription(): String = "Espresso"
}

abstract class BeverageDecorator(protected val beverage: Beverage) : Beverage {
    override fun getCost(): Double = beverage.getCost()
    override fun getDescription(): String = beverage.getDescription()
}

class MilkDecorator(beverage: Beverage) : BeverageDecorator(beverage) {
    override fun getCost(): Double = super.getCost() + 0.50
    override fun getDescription(): String = super.getDescription() + ", Milk"
}

class MochaDecorator(beverage: Beverage) : BeverageDecorator(beverage) {
    override fun getCost(): Double = super.getCost() + 0.75
    override fun getDescription(): String = super.getDescription() + ", Mocha"
}
