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

        try(BufferedReader reader = new BufferedReader(new InputStreamReader(System.in)))
        {
            number1 = getNumber(reader);
            number2 = getNumber(reader);
        } catch (IOException e)
        {
            System.out.println("---Input exception" + e.getMessage());
            return;
        }

        gcd = getGCD(number1, number2);
        lcm = getSCM(number1, number2, gcd);
        System.out.println("Greatest Common Divisor is: " + gcd);
        System.out.println("Smallest Common Multiple is: " + lcm);
    }

    private static int getNumber(BufferedReader reader)
    {
        String line;
        int number = 0;
        boolean isCorrect;
        do {
            System.out.println("Enter an integer: ");
            line = getUserInput(reader);
            if (!isInteger(line))
            {
                System.out.println("Input should be integer. Try again.");
                isCorrect = false;
                continue;
            }

            isCorrect = true;
            number = Integer.parseInt(line);
            if (number < 0)
            {
                number = number * (-1);
            }
        } while (!isCorrect);
        return number;
    }

    private static String getUserInput(BufferedReader reader) {
        try {
            return reader.readLine();
        } catch (IOException e) {
            System.out.println("---Input exception " + e.getMessage());
        }
        return "";
    }

    private static boolean isInteger(String line)
    {
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

    private static int getNextSimpleNumber(int lastSimpleNumber)
    {
        double numberSqrt;
        int countDiv = 0;

        for (int i = 1 + lastSimpleNumber; ; i++)
        {
            if (i == 2)
            {
                return 2;
            } else if ((i % 2) == 0)
            {

            } else if ((i % 5) == 0 && (i != 5))
            {

            } else if ((i % 2) != 0)
            {
                numberSqrt = Math.sqrt(i);
                for (int j = 3; j <= numberSqrt; j++)
                {
                    if ((i % j) == 0 && i != j)
                    {
                        countDiv++;
                    }
                }
                if (countDiv != 0)
                {
                    countDiv = 0;
                } else return i;
            }
        }
    }

    private static int getGCD(int firstNum, int secondNum) //Greatest Common Divisor (НОД)
    {
        int GCD = 1;
        HashMap<Integer, Integer> firstSimpleNums = new HashMap<>();
        HashMap<Integer, Integer> secondSimpleNums = new HashMap<>();
        getSimpleDivisors(firstNum, firstSimpleNums);
        getSimpleDivisors(secondNum, secondSimpleNums);

        for (Map.Entry<Integer, Integer> entryFirst : firstSimpleNums.entrySet()) {
            for (Map.Entry<Integer, Integer> entrySecond : secondSimpleNums.entrySet()) {
                if (entryFirst.getValue() != 0 && entrySecond.getValue() != 0)
                {
                    if (entryFirst.getKey().equals(entrySecond.getKey()))
                    {
                        if (entryFirst.getValue() == 1 || entrySecond.getValue() == 1)
                        {
                            GCD *= entryFirst.getKey();
                        } else
                        {
                            if (entryFirst.getValue() < entrySecond.getValue())
                            {
                                for (int i = 0; i < entryFirst.getValue(); i++)
                                {
                                    GCD *= entryFirst.getKey();
                                }
                            } else
                            {
                                for (int i = 0; i < entrySecond.getValue(); i++)
                                {
                                    GCD *= entryFirst.getKey();
                                }
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

    private static void getSimpleDivisors(int number, HashMap<Integer, Integer> map)
    {
        int simpleNumber = 2;
        boolean isDividable;
        map.put(1, 1);
        while (number != 1)
        {
            isDividable = true;
            map.put(simpleNumber, 0);
            while (isDividable)
            {
                if ((number % simpleNumber) == 0)
                {
                    number /= simpleNumber;
                    map.replace(simpleNumber, map.get(simpleNumber) + 1);
                } else
                {
                    simpleNumber = getNextSimpleNumber(simpleNumber);
                    isDividable = false;
                }
            }
        }
    }
}
