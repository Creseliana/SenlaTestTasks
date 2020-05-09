package Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {

        int userInput;
        boolean isNextNumber = true;

        while (isNextNumber) {
            System.out.print("Enter an integer: ");
            userInput = getUserNumber();
            checkIsNumberEvenOrOdd(userInput);
            checkIsNumberSimpleOrCompound(userInput);

            System.out.println("Do you want to continue?(Y/N)");
            if (!askYesNo()) {
                isNextNumber = false;
            }
        }
    }

    /**
     * Method checks if number is even or odd.
     * @param number number that should be checked
     */
    private static void checkIsNumberEvenOrOdd(int number) {
        if (number == 0) {
            System.out.println("0 is neither even nor odd.");
        } else if ((number % 2) == 0) {
            System.out.println(number + " is even.");
        } else if ((number % 2) != 0) {
            System.out.println(number + " is odd.");
        } else {
            System.out.println("---This number can't be checked.");
        }
    }

    /**
     * Method checks if number is simple or compound (only natural numbers).
     * @param number number that should be checked
     */
    private static void checkIsNumberSimpleOrCompound(int number) {
        double numberSqrt = Math.sqrt(number);

        if (number < 0) {
            System.out.println(number + " is not a natural number.");
        } else if (number == 0) {
            System.out.println("0 is not a natural number.");
        } else if (number == 1) {
            System.out.println("1 is neither simple nor compound.");
        } else if (number == 2) {
            System.out.println(number + " is simple.");
        } else if ((number % 2) == 0) {
            System.out.println(number + " is compound.");
        } else if ((number % 5) == 0 && (number != 5)) {
            System.out.println(number + " is compound.");
        } else if ((number % 2) != 0) {
            for (int i = 2; i <= numberSqrt; i++) {
                if ((number % i) == 0 && number != i) {
                    System.out.println(number + " is compound.");
                    return;
                }
            }
            System.out.println(number + " is simple.");
        }
    }

    /**
     * Method gets user input from console.
     * @return string user input
     * @throws IOException if an input or output exception has occurred
     */
    private static String getUserInput() throws IOException {
        String userInput;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        userInput = reader.readLine();

        return userInput;
    }

    /**
     * Method checks user's answer to the question with 2 possible answers. Method requests user input until it matches.
     * @return true - if answer is positive, false - if answer is negative and if IO exception has occurred
     */
    private static boolean askYesNo() {
        String userInput;

        do {
            try {
                userInput = getUserInput();
            } catch (IOException e) {
                return false;
            }

            if (userInput.equalsIgnoreCase("Y")) {
                return true;
            } else if (userInput.equalsIgnoreCase("N")) {
                return false;
            }
        }
        while (true);
    }

    /**
     * Method checks if given line is integer.
     * @param line line that should be checked
     * @return true - if line is integer, false - if it is not
     */
    private static boolean isInteger(String line) {
        if (line == null)
        {
            return false;
        }
        try {
            Integer.parseInt(line);
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    /**
     * Method gets user input, checks if it is a number. If it is not, then asks for the input again.
     * @return user input number. If IO exception has occurred it returns 0 (zero)
     */
    private static int getUserNumber() {
        String userInput;
        boolean isNumber = true;

        do {
            try {
                userInput = getUserInput();

                if (isInteger(userInput)) {
                    return Integer.parseInt(userInput);
                } else {
                    System.out.println("Input should be integer. Try again.");
                }
            } catch (IOException e) {
                System.out.println("---Number can't be got.");
                isNumber = false;
            }
        } while (isNumber);
        return 0;
    }
}
