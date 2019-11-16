package Task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static void main(String[] args) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int userInput;
        boolean isNextInput = true;
        String userAnswer;

        while (isNextInput)
        {
            userInput = getUserInput();
            checkIsNumberEvenOrOdd(userInput);
            checkIsNumberSimpleOrCompound(userInput);
            System.out.println("Do you want to check another number? (Press Y to continue)");
            try {
                userAnswer = reader.readLine();
                if (!userAnswer.equalsIgnoreCase("Y"))
                {
                    isNextInput = false;
                }
            } catch (IOException e) {
                System.out.println("!!!Input-output exception!!!");
            }
        }
    }

    private static int getUserInput()
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean isCorrectInput = false;
        int userNumber = -1;

        while (!isCorrectInput)
        {
            System.out.println("Enter number (number should be >= 0):");
            try {
                userNumber = Integer.parseInt(reader.readLine());
            } catch (IOException e) {
                System.out.println("!!!Input-output exception!!!");
                continue;
            } catch (NumberFormatException e)
            {
                System.out.println("!!!Input should be integer number!!!");
                continue;
            }
            if (userNumber < 0)
            {
                System.out.println("!!!Number should be >= 0!!!");
                continue;
            }
            isCorrectInput = true;
        }
        return userNumber;
    }

    private static void checkIsNumberEvenOrOdd(int number)
    {
        if (number == 0)
        {
            System.out.println("0 is neither even nor odd.");
        } else if ((number % 2) == 0)
        {
            System.out.println(number + " is even.");
        } else if ((number % 2) != 0)
        {
            System.out.println(number + " is odd.");
        } else
        {
            System.out.println("!!!Can't check this number!!!");
        }
    }

    private static void checkIsNumberSimpleOrCompound(int number)
    {
        double numberSqrt = Math.sqrt(number);

        if (number == 0)
        {
            System.out.println("0 isn't natural number.");
        } else if (number == 1)
        {
            System.out.println("1 is neither simple nor compound.");
        } else if (number == 2)
        {
            System.out.println(number + " is simple.");
        } else if ((number % 2) == 0)
        {
            System.out.println(number + " is compound.");
        } else if ((number % 5) == 0 && (number != 5))
        {
            System.out.println(number + " is compound.");
        } else if ((number % 2) != 0)
        {
            for (int i = 2; i <= numberSqrt; i++)
            {
                if ((number % i) == 0 && number != i)
                {
                    System.out.println(number + " is compound.");
                    return;
                }
            }
            System.out.println(number + " is simple.");
        }
    }
}
