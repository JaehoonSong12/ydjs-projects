package coresecond;
import java.util.Scanner;

public class HigherOrLower {
    public static void main(String[] args) {
        higherOrLower();
    }



    static void higherOrLower() {
        int random_number = (int) (Math.random() * 100) + 1;
//        System.out.println(random_number);
        Scanner s = new Scanner(System.in);
        boolean session = true;

        System.out.println("Guess a number between 1 and 100");
        int count = 0;
        while (session) {
            count= count + 1;
            int userInput = s.nextInt();
            if (userInput == random_number) {
                session = false;
                System.out.println("you won and it took "+count +" tries.");
            } else {
                if (userInput > random_number) {
                    System.out.println("the number is smaller");
                } else {
                    System.out.println("the number is bigger");
                }

            }
        }
    }
}