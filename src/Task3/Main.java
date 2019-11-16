package Task3;

public class Main {
    public static void main(String[] args) {
        String[] words;
        String sentence = "Once it became clear where their quarry intended to land, the Delta Force would move in.";
        sentence = sentence.replaceAll("[^a-zA-Zа-яА-Я0-9\\s]", "");
        words = sentence.split(" ");

        System.out.println(words.length + " words in the sentence.");
        sortWords(words);
        changeFirstLetterUpperCase(words);
        for (int i = 0; i < words.length; i++)
        {
            System.out.println(words[i]);
        }
    }

    private static void sortWords(String[] words)
    {
        String temp;
        for (int i = 0; i < words.length - 1; i++)
        {
            for (int j = i + 1; j < words.length; j++)
            {
                if (words[i].compareToIgnoreCase(words[j]) > 0)
                {
                    temp = words[i];
                    words[i] = words[j];
                    words[j] = temp;
                }
            }
        }
    }

    private static void changeFirstLetterUpperCase(String[] words)
    {
        StringBuilder builder = new StringBuilder();
        String firstLetter;
        String otherLetters;

        for (int i = 0; i < words.length; i++)
        {
            char[] letters = words[i].toCharArray();
            builder.append(letters[0]);
            firstLetter = builder.toString().toUpperCase();
            builder = builder.deleteCharAt(0);
            for (int j = 1; j < letters.length; j++)
            {
                builder.append(letters[j]);
            }
            otherLetters = builder.toString();
            words[i] = firstLetter.concat(otherLetters);
            builder.delete(0, builder.length());
        }
    }
}
