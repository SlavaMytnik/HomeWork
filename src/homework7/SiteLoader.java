package homework7;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;

/**
 * Класс SiteLoader выполняет Задание 1 ДЗ 7
 * (получение курсов валют с сайта)
 */
public abstract class SiteLoader {

    /**
     * Перечисление валют
     */
    public enum Currency{
        USD("145"),
        EUR("292"),
        RUB("298");

        String id;

        Currency(String id) {
            this.id = id;
        }

        public String getId() {
            return id;
        }
    }

    /**
     * Метод load получает курс валюты и возвращает её значение
     * @param link - ссылка на сайт-источник курсов валют
     * @param currencyName - имя валюты
     * @return возвращает значение курса валюты
     */
    protected final double load(String link, SiteLoader.Currency currencyName){
        StringBuilder content;

        boolean error;

        int retryCount = 0;

        do{
            content = new StringBuilder();
            error = false;

            try {
                URL url = new URL(link);
                URLConnection urlConnection = url.openConnection();
                urlConnection.setConnectTimeout(30000);
                urlConnection.setReadTimeout(30000);

                BufferedReader bufferedReader = new BufferedReader(
                        new InputStreamReader(urlConnection.getInputStream()));

                String line;

                while ((line = bufferedReader.readLine()) != null) {
                    content.append(line);
                }
            } catch(Exception e) {
                error = true;
                retryCount++;

                System.out.println("Ошибка подключения к сайту!");
            }
        } while (error && retryCount < 5);

        if (error) {
            System.out.println("Ошибка загрузки курсов валют!");
        }

        return handle(content.toString(), currencyName);
    }

    /**
     * Метод load определяет сигнатуру метода получения
     * курса валюты и возвращения её значения
     * @param currencyName - имя валюты
     * @param date - дата
     * @return возвращает значение курса валюты
     */
    public abstract double load(SiteLoader.Currency currencyName, Date date);

    /**
     * Метод handle определяет сигнатуру метода нахождения
     * в тексте курса валюты и возвращения её значения
     * @param content - текст для поиска курса вылюты
     * @param currencyName - название валюты
     * @return возвращает значение курса валюты
     */
    protected abstract double handle(String content, SiteLoader.Currency currencyName);
}
