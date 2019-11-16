package Task5;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        int lastIndexOfSequence = 100;
        ArrayList<Integer> palindromeList = new ArrayList<>();
        getPalindromes(palindromeList, lastIndexOfSequence);

        if (palindromeList.size() == 0)
        {
            System.out.println("No Palindromes found.");
        } else
        {
            for (int i = 0; i < palindromeList.size(); i++)
            {
                System.out.println(palindromeList.get(i));
            }
        }
    }

    private static void getPalindromes(ArrayList<Integer> list, int lastIndex)
    {
        String strNumber;
        int revertedNumber;

        for (int i = 0; i <= lastIndex; i++)
        {
            if (i < 10){
                list.add(i);
                continue;
            }
            strNumber = revertString(Integer.toString(i));
            revertedNumber = Integer.parseInt(strNumber);
            if (i == revertedNumber)
            {
                list.add(i);
            }
        }
    }

    private static String revertString(String sequence)
    {
        StringBuilder builder = new StringBuilder();
        char[] symbols = sequence.toCharArray();
        for (int i = symbols.length - 1; i >= 0; i--)
        {
            builder.append(symbols[i]);
        }
        return builder.toString();
    }
}
