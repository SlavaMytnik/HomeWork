package homework5.task2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс Task2_2 выполняет Задание 2.2 ДЗ 5
 * (поиск топ-10 слов и вывод их количества)
 */
public class Task2_2 {
    public static void main(String[] args) {
        Map<String, Integer> wordsUnsorted = new HashMap<>();
        Map<Integer, List<String>> wordsSorted = new TreeMap<>(Collections.reverseOrder());

        try (BufferedReader reader = new BufferedReader(new FileReader(
                Paths.get("HomeWork", "src", "resources")
                        + File.separator + "Война и мир_книга.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile(
                        "[^\\`\\~\\!\\@\\#\\$\\%\\^\\&\\*\\(\\)"
                                + "\\_\\=\\+\\-\\*\\/\\|\\{\\}"
                                + "\\[\\]\\:\\;\\'\\,\\.\\<\\>\\?"
                                + "\\u00AB\\u00AA\\u0022\\ \\\\]+");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    if (wordsUnsorted.containsKey(matcher.group())) {
                        wordsUnsorted.put(matcher.group(),
                                wordsUnsorted.get(matcher.group()) + 1);
                    } else {
                        wordsUnsorted.put(matcher.group(), 1);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Map.Entry<String, Integer> entry : wordsUnsorted.entrySet()) {
            List<String> entryList = wordsSorted.get(entry.getValue());

            if (entryList == null) {
                entryList = new ArrayList<>();
            }

            entryList.add(entry.getKey());
            wordsSorted.put(entry.getValue(), entryList);
        }

        System.out.println("Топ-10 слов по количеству раз использования в тексте: ");
        int i = 0;
        for (Map.Entry<Integer, List<String>> entry : wordsSorted.entrySet()) {
            i++;

            for (String s : entry.getValue()) {
                System.out.println(i + ". \u001B[34m" + s + "\u001B[0m - " + entry.getKey() + " раз(а)");
            }

            if (i == 10) {
                break;
            }
        }
    }
}

