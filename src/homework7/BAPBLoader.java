package homework7;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс BAPBLoader выполняет Задание 3 ДЗ 7
 * (получение курсов валют с сайта Белагропромбанка)
 */
public class BAPBLoader extends SiteLoader {

    /**
     * Метод load получает курс валюты и возвращает её значение
     * @param currencyName - имя валюты
     * @param date - дата
     * @return возвращает значение курса валюты
     */
    @Override
    public double load(Currency currencyName, Date date) {
        return load("https://belapb.by/ExCardsDaily.php" + "?ondate="
                + new SimpleDateFormat("MM/dd/yyyy").format(date), currencyName);
    }

    /**
     * Метод handle находит в тексте курс валюты и возвращает её значение
     * @param content - текст для поиска курса вылюты
     * @param currencyName - название валюты
     * @return возвращает значение курса валюты
     */
    @Override
    protected double handle(String content, Currency currencyName) {
        Pattern pattern = Pattern.compile(
                "<CharCode>" + currencyName.toString() + "</CharCode>"
                + "<NumCode>[0-9]+</NumCode>"
                + "<Scale>([0-9]+)</Scale>"
                + "<RateBuy>([0-9\\.]+)</RateBuy>"
                + "<RateSell>([0-9\\.]+)</RateSell>");
        Matcher matcher = pattern.matcher(content.replaceAll("\\s", ""));

        if (matcher.find()) {
            return Double.parseDouble(matcher.group(2)) / Double.parseDouble(matcher.group(1));
        }

        return 0;
    }
}
