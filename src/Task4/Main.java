package Task4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {
        String text;
        String word;
        boolean isTextCorrect = false;
        boolean isWordCorrect = false;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            do {
                System.out.println("Enter your text:");
                text = reader.readLine();
                if (text.equals("") || text.equals(" ")) {
                    System.out.println("Text must contain at least one word. Try again.");
                    continue;
                }
                text = text.replaceAll("[^a-zA-Zа-яА-Я\\s]", "").toLowerCase();
                isTextCorrect = true;
            } while (!isTextCorrect);

            do {
                System.out.println("Enter a word that should be checked:");
                word = reader.readLine();
                if (!checkWord(word)) {
                    continue;
                }
                isWordCorrect = true;
            } while (!isWordCorrect);

            countWordUsage(text, word);
        } catch (IOException e) {
            System.out.println("---IO exception. Text and word can't be checked.");
        }
    }

    /**
     * Checks given string if it is one single word.
     *
     * @param word string that should be checked
     * @return true - if given string is single word, false - if string is empty or has spaces and consists of several words
     */
    private static boolean checkWord(String word) {
        if (word.equals("")) {
            System.out.println("Word can't be empty. Try again.");
            return false;
        }

        ArrayList<String> words = new ArrayList<>(Arrays.asList(word.split(" ")));

        if (words.size() > 1) {
            System.out.println("Word can't include spaces or consist of several words. Try again.");
            return false;
        }
        return true;
    }

    /**
     * Method splits given string in the HashMap. Key is the word, value is a number of times this word appears.
     * Than all key words compare with given word. If there is such word, number of times is printed.
     *
     * @param text string text for searching the word
     * @param word word that should be counted
     */
    private static void countWordUsage(String text, String word) {
        boolean isSuchWord = false;
        HashMap<String, Integer> wordsMap = new HashMap<>();
        String[] words = text.split(" ");

        for (int i = 0; i < words.length; i++) {
            if (!wordsMap.containsKey(words[i])) {
                wordsMap.put(words[i], 1);
            } else {
                wordsMap.put(words[i], wordsMap.get(words[i]) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(word)) {
                isSuchWord = true;
                System.out.println("Word \"" + word + "\" used " + entry.getValue() + " times(time).");
            }
        }
        if (!isSuchWord) {
            System.out.println("There is no such word in the text.");
        }
    }
}
