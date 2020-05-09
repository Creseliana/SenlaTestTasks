package Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        int number1;
        int number2;
        int gcd; //greatest common divisor
        int lcm; //lowest common multiple

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            number1 = getNumber(reader);
            number2 = getNumber(reader);
        } catch (IOException e) {
            System.out.println("---Input exception" + e.getMessage());
            return;
        }

        gcd = getGCD(number1, number2);
        lcm = getSCM(number1, number2, gcd);
        System.out.println("Greatest Common Divisor is: " + gcd);
        System.out.println("Smallest Common Multiple is: " + lcm);
    }

    /**
     * Method gets user input, parse it into integer if it is parsable.
     * If it is not, than ask for input again until an integer will be entered.
     *
     * @param reader reading user input
     * @return number input by user
     */
    private static int getNumber(BufferedReader reader) {
        String line;
        int number = 0;
        boolean isCorrect;
        do {
            System.out.println("Enter an integer: ");
            line = getUserInput(reader);
            if (!isInteger(line)) {
                System.out.println("Input should be integer. Try again.");
                isCorrect = false;
                continue;
            }

            isCorrect = true;
            number = Integer.parseInt(line);
            if (number < 0) {
                number = number * (-1);
            }
        } while (!isCorrect);
        return number;
    }

    /**
     * Method gets user input. If IO exception has occurred it returns empty string.
     *
     * @param reader reading user input
     * @return user input line or empty line if exception has occurred
     */
    private static String getUserInput(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("---Input exception " + e.getMessage());
        }
        return "";
    }

    /**
     * Checks is input line integer or not
     *
     * @param line line that should be checked
     * @return true - if line is integer, false - if it is not or if line is empty
     */
    private static boolean isInteger(String line) {
        if (line == null) {
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
     * Consistently checks numbers from previous simple and returns next simple number.
     *
     * @param lastSimpleNumber previous simple number
     * @return next simple number
     */
    private static int getNextSimpleNumber(int lastSimpleNumber) {
        double numberSqrt;
        int countDiv = 0;

        for (int i = 1 + lastSimpleNumber; ; i++) {
            if (i == 2) {
                return 2;
            } else if ((i % 2) == 0) {
                continue;
            } else if ((i % 5) == 0 && (i != 5)) {
                continue;
            } else if ((i % 2) != 0) {
                numberSqrt = Math.sqrt(i);
                for (int j = 3; j <= numberSqrt; j++) {
                    if ((i % j) == 0 && i != j) {
                        countDiv++;
                    }
                }
                if (countDiv != 0) {
                    countDiv = 0;
                } else return i;
            }
        }
    }

    /**
     * Calculates GCD. It gets all simple divisors for both numbers and puts them in the HashMaps.
     * If keys (simple numbers) are equal and values (numbers count) are more than 0 (null)
     * and than multiple result number and key 'value' times.
     *
     * @param firstNum  first number for calculating
     * @param secondNum second number for calculating
     * @return Greatest Common Divisor
     */
    private static int getGCD(int firstNum, int secondNum) //Greatest Common Divisor (НОД)
    {
        int GCD = 1;
        HashMap<Integer, Integer> firstSimpleNums = new HashMap<>();
        HashMap<Integer, Integer> secondSimpleNums = new HashMap<>();
        getSimpleDivisors(firstNum, firstSimpleNums);
        getSimpleDivisors(secondNum, secondSimpleNums);

        for (Map.Entry<Integer, Integer> entryFirst : firstSimpleNums.entrySet()) {
            for (Map.Entry<Integer, Integer> entrySecond : secondSimpleNums.entrySet()) {
                if (entryFirst.getKey().equals(entrySecond.getKey())) {
                    if (entryFirst.getValue() != 0 && entrySecond.getValue() != 0) {
                        if (entryFirst.getValue() < entrySecond.getValue()) {
                            for (int i = 0; i < entryFirst.getValue(); i++) {
                                GCD *= entryFirst.getKey();
                            }
                        } else {
                            for (int i = 0; i < entrySecond.getValue(); i++) {
                                GCD *= entryFirst.getKey();
                            }
                        }
                    }
                }
            }
        }
        return GCD;
    }

    private static int getSCM(int firstNum, int secondNum, int GCD) //Lowest Common Multiple (НОК)
    {
        return (firstNum * secondNum) / GCD;
    }

    /**
     * Gets all simple divisors for given number and puts them into HashMap.
     * In map key is simple number, value is how many times number can be divided by this number.
     *
     * @param number number for division
     * @param map    HashMap for all divisors
     */
    private static void getSimpleDivisors(int number, HashMap<Integer, Integer> map) {
        int simpleNumber = 2;
        boolean isDividable;
        map.put(1, 1);
        while (number != 1) {
            isDividable = true;
            map.put(simpleNumber, 0);
            while (isDividable) {
                if ((number % simpleNumber) == 0) {
                    number /= simpleNumber;
                    map.replace(simpleNumber, map.get(simpleNumber) + 1);
                } else {
                    simpleNumber = getNextSimpleNumber(simpleNumber);
                    isDividable = false;
                }
            }
        }
    }
}
