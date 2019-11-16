package Task4;

import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String text = "Reading is the best way to improve your vocabulary! " +
                "The context of articles, stories, and conversations helps you figure out and " +
                "understand the meaning of English words in the text that are new to you. " +
                "Reading also provides repetition of vocabulary words " +
                "you have already learned to help you remember them.";
        String word = "the";

        text = text.replaceAll("[^a-zA-Zа-яА-Я0-9\\s]", "").toLowerCase();

        countWordUsage(text, word);
    }

    private static void countWordUsage(String text, String word)
    {
        boolean isSuchWord = false;
        HashMap<String, Integer> wordsMap = new HashMap<>();
        String[] words = text.split(" ");

        for (int i = 0; i < words.length; i++)
        {
            if (!wordsMap.containsKey(words[i]))
            {
                wordsMap.put(words[i], 1);
            } else
            {
                wordsMap.put(words[i], wordsMap.get(words[i]) + 1);
            }
        }
        for (Map.Entry<String, Integer> entry : wordsMap.entrySet()) {
            if (entry.getKey().equalsIgnoreCase(word))
            {
                isSuchWord = true;
                System.out.println("Word \"" + word + "\" used " + entry.getValue() + " times(time).");
            }
        }
        if (!isSuchWord)
        {
            System.out.println("There is no such word in the text.");
        }
    }
}
