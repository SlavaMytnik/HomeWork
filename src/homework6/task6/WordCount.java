package homework6.task6;

import homework5.task3.ISearchEngine;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Класс WordCount выполняет Задание 6.1 - 6.3 ДЗ 6
 * (определяет, сколько раз слово встречается в тексте,
 * используя ExecutorService, Callable лямбду, Callable класс)
 */
public class WordCount {

    /**
     * Метод get определяет, сколько раз слово встречается в тексте
     * @param text - текст, в котором происходит поиск
     * @param textPartsCount - на сколько частей разбивается текст
     * @param word - искомое слово
     * @param searchEngine - экземпляр класса поиска
     * @param caseSensitive - чувствительность к регистру при поиске:
     *                        true - регистр учитывается,
     *                        false - регистр не учитывается
     * @param useLambda - необходимость использования лямбды:
     *                    true - используется Callable лямбда,
     *                    false - используется Callable класс
     * @return возвращает количество, сколько раз слово встречается в тексте
     */
    public static long get(String text, int textPartsCount,
                           String word, ISearchEngine searchEngine,
                           boolean caseSensitive, boolean useLambda) {
        long wordCount = 0;

        boolean isThreadsDone;

        ExecutorService executorService = Executors.newCachedThreadPool();

        Map<Future<Long>, Boolean> futureMap = new HashMap<>();

        for (int i = 0; i < textPartsCount; i++) {
            int fromIndex = i == 0 ? 0
                    : text.indexOf(" ",i * text.length() / textPartsCount);

            int toIndex = text.indexOf(" ",
                    (i + 1) * text.length() / textPartsCount);

            if (fromIndex == -1) fromIndex = text.length();
            if (toIndex == -1) toIndex = text.length();

            if (fromIndex != toIndex) {
                String part = text.substring(fromIndex, toIndex);

                Future<Long> future;
                if (useLambda) {
                    future = executorService.submit(() ->
                            searchEngine.search(part, word, caseSensitive));
                } else {
                    future = executorService.submit(new WordCountCallable(
                            part, word, searchEngine, caseSensitive));
                }

                futureMap.put(future, false);
            }
        }

        executorService.shutdown();

        do {
            isThreadsDone = true;

            for (Map.Entry<Future<Long>, Boolean> futureEntry
                    : futureMap.entrySet()) {
                if (!futureEntry.getValue()) {
                    if (futureEntry.getKey().isDone()) {
                        futureEntry.setValue(true);

                        try {
                            wordCount += futureEntry.getKey().get();
                        } catch (InterruptedException | ExecutionException e) {
                            e.printStackTrace();
                        }
                    } else {
                        isThreadsDone = false;
                    }
                }
            }

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (!isThreadsDone);

        return wordCount;
    }
}
