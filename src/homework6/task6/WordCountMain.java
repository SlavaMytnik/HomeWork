package homework6.task6;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import homework5.task3.ISearchEngine;
import homework5.task4.EasySearch;
import homework5.task4.RegExSearch;

/**
 * Класс WordCountMain выполняет Задание 6.4 ДЗ 6
 * (определяет, сколько раз слова встречаются
 * в тексте вне зависимости от регистра)
 */
public class WordCountMain {
    public static void main(String[] args) {
        int textPartsCount = 4;

        boolean useLambda = true;
        boolean firstLine = true;

        ISearchEngine[] engines = new ISearchEngine[]{
                new EasySearch(), new RegExSearch()};

        String[] words = new String[]{"война", "и", "мир"};

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

        for (ISearchEngine engine : engines) {
            System.out.println("\u001B[31m" + (firstLine ? "" : "\n")
                    + engine.getClass().getSimpleName()
                    + "\u001B[0m + \u001B[31mCallable "
                    + (useLambda ? "лямбда" : "класс") + "\u001B[0m:");

            for (String word : words) {
                long startMillis = System.currentTimeMillis();

                System.out.println("Cлово \u001B[34m" + word
                        + "\u001B[0m встречается в тексте "
                        + WordCount.get(stringBuffer.toString(), textPartsCount,
                        word, engine, false, useLambda) + " раз(а) ("
                        + (System.currentTimeMillis() - startMillis) + " мс)");
            }

            useLambda = false;
            firstLine = false;
        }
    }
}







