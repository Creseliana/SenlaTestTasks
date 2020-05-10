package Task3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        String sentence = getSentence();
        ArrayList<String> words = new ArrayList<>();
        ArrayList<String> sortedWords = new ArrayList<>();


        if (getWords(sentence, words)) {
            System.out.print((words.size() == 1) ? "There is 1 word " : "There are " + words.size() + " words ");
            System.out.println("in the sentence.");

            System.out.println("-----");
            System.out.println("Sorted words: ");
            sortWords(words, sortedWords);
            for (int i = 0; i < sortedWords.size(); i++) {
                System.out.println(sortedWords.get(i));
            }
            System.out.println("-----");

            System.out.println("All words with upper case first letter: ");
            changeFirstLetterUpperCase(words);
        } else {
            System.out.println("There are no words in the sentence.");
        }
    }

    /**
     * Method gets user input using BufferedReader
     *
     * @return user input sentence as a string, or empty string, if IO exception has occurred
     */
    private static String getSentence() {
        String sentence = "";

        System.out.println("Enter a sentence: ");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            sentence = reader.readLine();
        } catch (IOException e) {
            System.out.println("---IO exception.");
        }
        return sentence;
    }

    /**
     * Method separates string sentence in list of words. It replaces all symbols that are not letters and spaces.
     * Than adds all words to the List and removes from list rest spaces (in case if there were several spaces near in the sentence.
     *
     * @param sentence sentence that should be separated
     * @param words    list of words from the sentence
     * @return false - if there are no words in the sentence, true - if there are words.
     */
    private static boolean getWords(String sentence, List<String> words) {

        if (sentence.equals("") || sentence.equals(" ")) {
            return false;
        }

        String tempSentence = sentence.replaceAll("[^a-zA-Zа-яА-Я\\s]", "");
        words.addAll(Arrays.asList(tempSentence.split(" ")));

        for (int i = 0; i < words.size(); i++) {
            if (words.get(i).equals(" ") || words.get(i).equals("")) {
                words.remove(i);
                i -= 1;
            }
        }
        return true;
    }

    /**
     * Sorts words in list alphabetically
     *
     * @param words       list of words
     * @param sortedWords list of sorted words
     */
    private static void sortWords(List<String> words, List<String> sortedWords) {
        String temp;
        sortedWords.addAll(words);

        for (int i = 0; i < sortedWords.size() - 1; i++) {
            for (int j = i + 1; j < sortedWords.size(); j++) {
                if (sortedWords.get(i).compareToIgnoreCase(sortedWords.get(j)) > 0) {
                    temp = sortedWords.get(i);
                    sortedWords.set(i, sortedWords.get(j));
                    sortedWords.set(j, temp);
                }
            }
        }
    }

    /**
     * Changes first letter in the word to upper case and prints full word.
     *
     * @param words list of words that should be changed
     */
    private static void changeFirstLetterUpperCase(List<String> words) {
        String firstLetter;
        String otherLetters;

        for (int i = 0; i < words.size(); i++) {
            firstLetter = words.get(i).substring(0, 1);
            otherLetters = words.get(i).substring(1);
            System.out.print(firstLetter.toUpperCase().concat(otherLetters) + " ");
        }
        System.out.println("\n");
    }
}
