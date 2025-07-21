/**
INSTRUCTIONS: 
    Demonstrates the Decorator design pattern by modeling a coffee beverage
    system where you can dynamically add ingredients (decorators) to a base beverage.

COMPILE & EXECUTE & CLEANUP (Java):

     javac  -d out              DecoratorPractice*.java
     java           -cp out     DecoratorPractice
     rm -rf out/
     












 */



public class DecoratorPractice {
    public static void main(String[] args) {
        print("Hello World");
    }

public interface Pizza {
    String Description();
    double cost();
}

public class NormalPizza implements Pizza {
    public String Description() {
        return "Pizza";
    }

    public double cost() {
        return 1.00;
    }
}

public abstract class Decorator implements Pizza {
    protected Pizza pizza;

    public Decorator(Pizza pizza) {
        this.pizza = pizza;
    }

    // public string Decorator() {
    //     return pizza.Decorator();
    // }

    public double cost() {
        return pizza.cost();
    }
}

















    public static void print(String text) {
        System.out.println(text);
    }
}