package homework5.task3;

/**
 * Интервейс ISearchEngine выполняет Задание 3 ДЗ 5
 * (интерфейс поиска слова в тексте)
 */
public interface ISearchEngine {

    /**
     * Метод search реализует поиск слова в тексте
     * @param text - текст, в котором происходит поиск
     * @param word - искомое слово
     * @param caseSensitive определяет чувствительность к регистру при поиске:
     *                      true - регистр учитывается,
     *                      false - регистр не учитывается
     * @return возвращает количество, сколько раз слово встречается в тексте
     */
    long search(String text, String word, boolean caseSensitive);
}
