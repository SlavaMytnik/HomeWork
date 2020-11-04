package homework5.task5;

import homework5.task3.ISearchEngine;
import homework5.task4.EasySearch;
import homework5.task4.RegExSearch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

/**
 * Класс EasyAndRegExSearchMain выполняет Задание 5 ДЗ 5
 * (определяет, сколько раз встречаются слова
 * в тексте вне зависимости от регистра)
 */
public class EasyAndRegExSearchMain {
    public static void main(String[] args) {
        StringBuffer stringBuffer = new StringBuffer();

        try (BufferedReader reader = new BufferedReader(new FileReader(
                Paths.get("HomeWork", "resources")
                        + File.separator + "Война и мир_книга.txt"))) {
            String line;

            while ((line = reader.readLine()) != null) {
                stringBuffer.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("EasySearch:");
        print(stringBuffer.toString(), "война", new EasySearch(), false);
        print(stringBuffer.toString(), "и", new EasySearch(), false);
        print(stringBuffer.toString(), "мир", new EasySearch(), false);

        System.out.println("\nRegExSearch:");
        print(stringBuffer.toString(), "война", new RegExSearch(), false);
        print(stringBuffer.toString(), "и", new RegExSearch(), false);
        print(stringBuffer.toString(), "мир", new RegExSearch(), false);
    }

    /**
     * Метод print выводит на печать результат поиска слова в тексте
     * @param text - текст, в котором происходит поиск
     * @param word - искомое слово
     * @param searchEngine определяет экземпляр класса поиска
     * @param caseSensitive определяет чувствительность к регистру при поиске:
     *      *                      true - регистр учитывается,
     *      *                      false - регистр не учитывается
     */
    private static void print(String text, String word,
                                    ISearchEngine searchEngine, boolean caseSensitive) {
        System.out.println("Слово \u001B[34m" + word + "\u001B[0m встречается в тексте "
                + searchEngine.search(text, word, caseSensitive) + " раз(а)");
    }
}
