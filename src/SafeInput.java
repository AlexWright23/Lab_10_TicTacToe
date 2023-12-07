// SafeInput.java
import java.util.Scanner;

public class SafeInput {
    public static int getRangedInt(Scanner console, String prompt, int low, int high) {
        int input;
        do {
            System.out.print(prompt);
            while (!console.hasNextInt()) {
                System.out.println("Please enter a valid integer.");
                console.next();
            }
            input = console.nextInt();
        } while (input < low || input > high);
        return input;
    }

    public static boolean getYNConfirm(Scanner console, String prompt) {
        System.out.print(prompt + " (y/n): ");
        char response = console.next().toLowerCase().charAt(0);
        return response == 'y';
    }
}
