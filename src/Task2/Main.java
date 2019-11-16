package Task2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int firstNum = 0;
        int secondNum = 0;
        int GCD;
        int SCM;
        boolean isCorrectInput = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (!isCorrectInput)
        {
            isCorrectInput = true;
            try
            {
                System.out.println("Enter first integer number:");
                firstNum = Integer.parseInt(reader.readLine());
                System.out.println("Enter second integer number:");
                secondNum = Integer.parseInt(reader.readLine());
            } catch (IOException e)
            {
                System.out.println("!!!Input-output exception!!!");
            } catch (NumberFormatException e)
            {
                System.out.println("!!!Number should be integer!!!");
                isCorrectInput = false;
            }
            if (firstNum <= 0 || secondNum <= 0)
            {
                System.out.println("!!!Number should be > 0!!!");
                isCorrectInput = false;
            }
        }
        GCD = getGCD(firstNum, secondNum);
        SCM = getSCM(firstNum, secondNum, GCD);
        System.out.println("Greatest Common Divisor is: " + GCD);
        System.out.println("Smallest Common Multiple is: " + SCM);
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

    private static int getSCM(int firstNum, int secondNum, int GCD) //Smallest Common Multiple (НОК)
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
