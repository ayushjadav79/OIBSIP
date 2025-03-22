import java.util.Scanner;
import java.util.Random;


public class Oibsip_2
{
    public static void main(String[] args) 
    {
        Scanner sc = new Scanner(System.in);
        Random r = new Random();
        
        int min = 1, max = 100;
        
        System.out.println("Welcome to the Number Guessing Game!!!!");
        System.out.println("Here, you have to guess the number between " + min + " and " + max + ".");
        System.out.println("RULES:");
        System.out.println("There will be two rounds for the game and each round will have two different numbers to guess. \nYou will get 5 attempts to guess the number in each round.");
        System.out.println("Let's start the game!!!" + "\n");
        
        int score = 0;
        int round = 1;
        while(round <= 2)
        {
            int num = r.nextInt(max - min + 1) + min;
            int attempt = 5;
            while(attempt > 0)
            {
                System.out.println("Round " + round + " Attempt " + attempt);
                System.out.print("Enter your guess for number of round " + round + ": ");
                int guess = sc.nextInt();
                
                if(guess == num)
                {
                    score += attempt;
                    System.out.println("Congratulations!!! You have guessed the correct number.");
                    break;
                }
                else if(guess < num)
                {
                    System.out.println("Your guess is smaller than the number.");
                }
                else
                {
                    System.out.println("Your guess is greater than the number.");
                }
                
                attempt--;
            }
            System.out.println();
            round++;
        }

        System.out.println("Your score: " + score);
        System.out.println("\nGame Over!!!");
        System.out.println("Thank you for playing the game!!!");

        sc.close();
    }
}
