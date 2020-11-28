package homework7;

import java.io.File;
import java.util.*;

/**
 * Класс RatesMain выполняет Задания 4 - 7 ДЗ 7
 * (получение с сайта и сохранение в файле курсов
 *  валют за даты, указанные в аргументах класса)
 */
public class RatesMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<SiteLoader.Currency> currencies =
                Arrays.asList(SiteLoader.Currency.class.getEnumConstants());

        List<Date> dates = RatesTools.getDates(args);

        if (dates != null) {

            // Определение сайта-источника курсов валют
            SiteLoader source = RatesTools.consoleQuestion(scanner,
                    RatesTools.makeMap(Arrays.asList(new NBRBLoader(), new BAPBLoader())),
                    "Введите источник курсов валют (1 - НБРБ, 2 - Белагропромбанк): ",
                    "Ошибка ввода!");

            System.out.println("Получение курсов валют...");

            Map<Date, Map<SiteLoader.Currency, Double>> results =
                    RatesTools.getRatesFromSite(source, currencies, dates);

            if (results != null) {

                // Печать полученных с сайта-источника курсов валют
                RatesTools.printRates(results);

                // Определение необходимости сохранения курсов валют в файле
                if (RatesTools.consoleQuestion(scanner,
                        RatesTools.makeMap(Arrays.asList(true, false)),
                        "Сохранить курсы валют в файл (1 - да, 2 - нет)? ",
                        "Ошибка ввода!")) {


                    boolean error;

                    int errorCount = 0;
                    int maxErrorCount = 5;

                    do {
                        error = false;

                        // Определение имени файла для сохранения курсов валют
                        File file = RatesTools.consoleFileName(scanner,
                                "Введите имя файла: ");

                        if (RatesTools.saveFile(file,
                                source.getClass().getSimpleName(), results)) {
                            System.out.println("Курсы валют сохранены в файл!");

                            // Определение необходимости печати
                            // курсов валют из сохраненного файла
                            if (RatesTools.consoleQuestion(scanner,
                                    RatesTools.makeMap(Arrays.asList(true, false)),
                                    "Распечатать сохраненный файл (1 - да, 2 - нет)? ",
                                    "Ошибка ввода!")) {
                                Map<Date, Map<SiteLoader.Currency, Double>> loadedResults =
                                        RatesTools.getRatesFromFile(file);

                                if (loadedResults != null) {

                                    // Печать курсов из файла
                                    RatesTools.printRates(loadedResults);
                                } else {
                                    System.out.println("Ошибка чтения курсов валют из файла!");
                                }
                            }
                        } else {
                            error = true;
                            errorCount++;

                            System.out.println("Ошибка сохранения курсов валют!");

                            if (errorCount == maxErrorCount)
                                System.out.println("Попытки записи исчерпаны!");
                        }
                    } while (error && errorCount < maxErrorCount);
                }
            } else {
                System.out.println("Ошибка получения курсов валют!");
            }
        } else {
            System.out.println("Ошибка чтения аргументов!");
        }

        scanner.close();
    }
}
