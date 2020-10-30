package homework5.task4;

import homework5.task3.ISearchEngine;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс RegExSearch выполняет Задание 4.2 ДЗ 5
 * (реализует интерфейс поиска слова в тексте ISearchEngine,
 * используя matcher)
 */
public class RegExSearch implements ISearchEngine {

    /**
     * Метод search реализует поиск слова в тексте
     * @param text - текст, в котором происходит поиск
     * @param word - искомое слово
     * @param caseSensitive определяет чувствительность к регистру при поиске:
     *                      true - регистр учитывается,
     *                      false - регистр не учитывается
     * @return возвращает количество, сколько раз слово встречается в тексте
     */
    @Override
    public long search(String text, String word, boolean caseSensitive) {
        int count = 0;

        if (!caseSensitive) {
            text = text.toLowerCase();
            word = word.toLowerCase();
        }

        Pattern pattern = Pattern.compile("\\b" + word + "\\b");
        Matcher matcher = pattern.matcher(text);

        while (matcher.find()) {
            count++;
        }

        return count;
    }
}
