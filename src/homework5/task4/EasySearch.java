package homework5.task4;

import homework5.task3.ISearchEngine;

/**
 * Класс EasySearch выполняет Задание 4.1 ДЗ 5
 * (реализует интерфейс поиска слова в тексте ISearchEngine,
 * используя метод indexOf)
 */
public class EasySearch implements ISearchEngine {

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
        int index = -1 * word.length();

        if (!caseSensitive) {
            text = text.toLowerCase();
            word = word.toLowerCase();
        }

        do {
            if ((index = text.indexOf(word, index + word.length())) >= 0
                    && (index == 0 || !Character.isLetter(text.charAt(index - 1)))
                    && (index + word.length() >= text.length()
                    || !Character.isLetter(text.charAt(index + word.length())))) {
                count++;
            }
        } while (index >= 0);

        return count;
    }
}
