/**
INSTRUCTIONS: 
    Demonstrates the Decorator design pattern by modeling a coffee beverage
    system where you can dynamically add ingredients (decorators) to a base beverage.

COMPILE & EXECUTE & CLEANUP (Java):

     javac  -d out              DecoratorDemo*.java
     java           -cp out     DecoratorDemo
     rm -rf out/
     












 */
public class DecoratorDemo {

    /**
     * Entry point: constructs an Espresso, then decorates it with Milk and Mocha,
     * printing out descriptions and costs at each step.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        // 1. Basic Espresso
        Beverage beverage = new Espresso();
        System.out.printf("Base: %s | Cost: $%.2f%n",
            beverage.getDescription(), beverage.getCost());

        // 2. Add Milk
        beverage = new MilkDecorator(beverage);
        System.out.printf("With Milk: %s | Cost: $%.2f%n",
            beverage.getDescription(), beverage.getCost());

        // 3. Add Mocha on top of Milk
        beverage = new MochaDecorator(beverage);
        System.out.printf("With Milk and Mocha: %s | Cost: $%.2f%n",
            beverage.getDescription(), beverage.getCost());
    }
}

/**
 * The component interface for beverages. Defines operations to get
 * cost and description. Both concrete components and decorators implement this.
 */
interface Beverage {
    /**
     * Calculates the cost of this beverage (including any decorators).
     *
     * @return the total cost in USD
     */
    double getCost();

    /**
     * Provides a description of this beverage (including any decorators).
     *
     * @return a comma-separated description string
     */
    String getDescription();
}

/**
 * A concrete component: Espresso. Implements the base beverage.
 */
class Espresso implements Beverage {

    /**
     * {@inheritDoc}
     * @return base cost of an Espresso
     */
    @Override
    public double getCost() {
        return 1.99;
    }

    /**
     * {@inheritDoc}
     * @return base description of an Espresso
     */
    @Override
    public String getDescription() {
        return "Espresso";
    }
}

/**
 * The abstract decorator class. Holds a reference to a Beverage and
 * delegates calls to it. Concrete decorators extend this to add behavior.
 */
abstract class BeverageDecorator implements Beverage {
    /** The wrapped beverage */
    protected final Beverage beverage;

    /**
     * Constructs a decorator wrapping the given beverage.
     *
     * @param beverage the Beverage to wrap
     */
    public BeverageDecorator(Beverage beverage) {
        this.beverage = beverage;
    }

    /**
     * {@inheritDoc}
     * Delegates to the wrapped beverage.
     */
    @Override
    public double getCost() {
        return beverage.getCost();
    }

    /**
     * {@inheritDoc}
     * Delegates to the wrapped beverage.
     */
    @Override
    public String getDescription() {
        return beverage.getDescription();
    }
}

/**
 * A concrete decorator that adds milk to a beverage.
 */
class MilkDecorator extends BeverageDecorator {

    /**
     * Constructs a MilkDecorator wrapping the given beverage.
     *
     * @param beverage the Beverage to wrap
     */
    public MilkDecorator(Beverage beverage) {
        super(beverage);
    }

    /**
     * {@inheritDoc}
     * Adds the cost of milk to the wrapped beverage.
     *
     * @return the new total cost
     */
    @Override
    public double getCost() {
        return super.getCost() + 0.50;
    }

    /**
     * {@inheritDoc}
     * Appends ", Milk" to the wrapped beverage's description.
     *
     * @return the new description
     */
    @Override
    public String getDescription() {
        return super.getDescription() + ", Milk";
    }
}

/**
 * A concrete decorator that adds mocha to a beverage.
 */
class MochaDecorator extends BeverageDecorator {

    /**
     * Constructs a MochaDecorator wrapping the given beverage.
     *
     * @param beverage the Beverage to wrap
     */
    public MochaDecorator(Beverage beverage) {
        super(beverage);
    }

    /**
     * {@inheritDoc}
     * Adds the cost of mocha to the wrapped beverage.
     *
     * @return the new total cost
     */
    @Override
    public double getCost() {
        return super.getCost() + 0.75;
    }

    /**
     * {@inheritDoc}
     * Appends ", Mocha" to the wrapped beverage's description.
     *
     * @return the new description
     */
    @Override
    public String getDescription() {
        return super.getDescription() + ", Mocha";
    }
}
