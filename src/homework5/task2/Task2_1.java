package homework5.task2;

import java.io.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс Task2_1 выполняет Задание 2.1 ДЗ 5
 * (найти в тексте все уникальные слова)
 */
public class Task2_1 {
    public static void main(String[] args) {
        Set<String> words = new HashSet<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(
                "Война и мир_книга.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                Pattern pattern = Pattern.compile("[а-яА-Я]+");
                Matcher matcher = pattern.matcher(line);

                while (matcher.find()) {
                    words.add(matcher.group());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
