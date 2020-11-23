package homework6.task6;

import homework5.task3.ISearchEngine;

import java.util.concurrent.Callable;

/**
 * Класс WordCountCallable выполняет Задание 6.2.2 ДЗ 6
 * (определяет, сколько раз слово встречается в тексте)
 */
public class WordCountCallable implements Callable<Long> {
    private final String text;
    private final String word;

    private final ISearchEngine searchEngine;

    private final boolean caseSensitive;

    /**
     * WordCountCallable является конструктором класса
     * @param text - текст, в котором происходит поиск
     * @param word - искомое слово
     * @param searchEngine - экземпляр класса поиска
     * @param caseSensitive - чувствительность к регистру при поиске:
     *                        true - регистр учитывается,
     *                        false - регистр не учитывается
     */
    public WordCountCallable(String text, String word,
                             ISearchEngine searchEngine,
                             boolean caseSensitive) {
        this.text = text;
        this.word = word;
        this.caseSensitive = caseSensitive;
        this.searchEngine = searchEngine;
    }

    /**
     * Метод call определяет, сколько раз слово встречается в тексте
     * @return возвращает количество, сколько раз слово встречается в тексте
     */
    @Override
    public Long call() {
        return searchEngine.search(text, word, caseSensitive);
    }
}
