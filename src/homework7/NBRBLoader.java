package homework7;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс NBRBLoader выполняет Задание 2 ДЗ 7
 * (получение курсов валют с сайта НБРБ)
 */
public class NBRBLoader extends SiteLoader {

    /**
     * Метод load получает курс валюты и возвращает её значение
     * @param currencyName - имя валюты
     * @param date - дата
     * @return возвращает значение курса валюты
     */
    @Override
    public double load(Currency currencyName, Date date) {
        return load("https://www.nbrb.by/api/exrates/rates/"
                + currencyName.getId() + "?ondate="
                + new SimpleDateFormat("yyyy-MM-dd").format(date), currencyName);
    }

    /**
     * Метод handle находит в тексте курс валюты и возвращает её значение
     * @param content - текст для поиска курса вылюты
     * @param currencyName - название валюты
     * @return возвращает значение курса валюты
     */
    @Override
    protected double handle(String content, Currency currencyName) {
        String siteAbbreviationText = "Cur_Abbreviation";
        String siteScaleText1 = "Cur_Scale";
        String siteScaleText2 = "Cur_Name";
        String siteRateText = "Cur_OfficialRate";
        String siteCurrency;

        double siteRate;
        double siteScale;

        siteCurrency = content.substring(
                content.indexOf(siteAbbreviationText)
                        + siteAbbreviationText.length() + 3,
                content.indexOf(siteAbbreviationText)
                        + siteAbbreviationText.length() + 6);

        if (siteCurrency.equals(currencyName.toString())) {
            siteScale = Double.parseDouble(content.substring(
                    content.indexOf(siteScaleText1) + siteScaleText1.length() + 2,
                    content.indexOf(siteScaleText2) - 2));

            siteRate = Double.parseDouble(content.substring(
                    content.indexOf(siteRateText) + siteRateText.length() + 2,
                    content.length() - 1));

            return siteRate / siteScale;
        }

        return 0;
    }
}
