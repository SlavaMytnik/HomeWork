package homework7;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Класс RatesTools является набором методов для работы с курсами валют
 */
public class RatesTools {

    /**
     * Метод getDates возвращает список дат из текстового массива
     * @param datesString - текстовый массив дат
     * @return возвращает список дат
     */
    public static List<Date> getDates(String[] datesString) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");

        List<Date> dateList = new ArrayList<>();

        if (datesString.length > 0) {
            for (String arg : datesString) {
                try {
                    String[] argParts = arg.split(",");

                    for (String argPart : argParts) {
                        if (argPart.contains("-")) {
                            String[] fromDateToDate = argPart.split("-");

                            Date fromDate = dateFormat.parse(fromDateToDate[0]);
                            Date toDate = dateFormat.parse(fromDateToDate[1]);

                            while (fromDate.before(toDate)
                                    || fromDate.equals(toDate)) {
                                dateList.add((Date) fromDate.clone());
                                fromDate.setTime(fromDate.getTime()
                                        + TimeUnit.DAYS.toMillis(1));
                            }
                        } else {
                            dateList.add(dateFormat.parse(argPart));
                        }
                    }
                } catch (ParseException e) {
                    return null;
                }
            }
        }

        dateList.removeIf(date -> date.after(new Date()));

        if (dateList.size() == 0) dateList.add(new Date());

        return dateList;
    }

    /**
     * Метод getRatesFromSite возвращает курсы валют из класса-источника
     * @param loader - класс-источник курсов валют
     * @param currencies - список валют
     * @param dates - список дат
     * @return возвращает курсы валют
     */
    public static Map<Date, Map<SiteLoader.Currency, Double>> getRatesFromSite(
            SiteLoader loader,
            List<SiteLoader.Currency> currencies,
            List<Date> dates) {
        Map<Date, Map<SiteLoader.Currency, Double>> resultMap = new TreeMap<>();

        for (Date date : dates) {
            Map<SiteLoader.Currency, Double> innerResultMap = new HashMap<>();

            for (SiteLoader.Currency currency : currencies) {
                Double rate = loader.load(currency, date);

                innerResultMap.put(currency, rate);
            }

            resultMap.put(date, innerResultMap);
        }

        return resultMap.size() > 0 ? resultMap : null;
    }

    /**
     * Метод getRatesFromFile возвращает курсы валют из файла
     * @param file - файл с курсами валют
     * @return возвращает курсы валют
     */
    @SuppressWarnings("unchecked")
    public static Map<Date, Map<SiteLoader.Currency, Double>> getRatesFromFile(File file) {
        Object[] loadedObject = loadFile(file);

        if (loadedObject != null) {
            return (Map<Date, Map<SiteLoader.Currency, Double>>) loadedObject[1];
        }

        return null;
    }

    /**
     * Метод saveFile сохраняет курсы валют в файл
     * и возвращает результат сохранения файла
     * @param file - файл для сохранения курсов валют
     * @param loaderName - имя класса-источника курсов валют
     * @param resultMap - курсы валют для сохранения
     * @return возвращает результат сохранения файла:
     *         true - успешно,
     *         false - неуспешно
     */
    @SuppressWarnings("unchecked")
    public static boolean saveFile(File file,
                                   String loaderName,
                                   Map<Date, Map<SiteLoader.Currency, Double>> resultMap) {
        try {
            if (file.toString().contains(File.separator)) {
                File path = new File(file.toString().substring(0,
                        file.toString().lastIndexOf(File.separator)));

                if (!path.exists()) {
                    path.mkdirs();
                }
            }

            if (file.exists())  {
                Object[] loadedObject = loadFile(file);

                if (loadedObject != null) {
                    if (loadedObject[0].equals(loaderName)) {
                        Map<Date, Map<SiteLoader.Currency, Double>> loadedMap =
                                (Map<Date, Map<SiteLoader.Currency, Double>>) loadedObject[1];

                        if (loadedMap != null) resultMap.putAll(loadedMap);
                    } else {
                        return false;
                    }
                }

                file.delete();
            }

            if (file.createNewFile()) {
                ObjectOutputStream objectStream = new ObjectOutputStream(
                        new FileOutputStream(file));
                objectStream.writeObject(loaderName);
                objectStream.writeObject(resultMap);
                objectStream.close();

                return true;
            }
        } catch (IOException ignored) {}

        return false;
    }

    /**
     * Метод loadFile возвращает данные из файла
     * @param file - файл данных
     * @return возвращает данные из файла
     */
    @SuppressWarnings("unchecked")
    public static Object[] loadFile(File file) {
        try {
            ObjectInputStream objectStream = new ObjectInputStream(
                    new FileInputStream(file));
            String loadedString = (String) objectStream.readObject();
            Map<Date, Map<SiteLoader.Currency, Double>> loadedMap =
                    (Map<Date, Map<SiteLoader.Currency, Double>>)
                            objectStream.readObject();
            objectStream.close();

            return new Object[]{loadedString, loadedMap};
        } catch (IOException | ClassNotFoundException ignored) {}

        return null;
    }

    /**
     * Метод makeMap формирует переменную типа Map
     * из массива и возвращает её
     * @param array - массив данных
     * @param <V> - тип данных
     * @return возвращает переменную типа Map
     */
    public static <V> Map<Integer, V> makeMap(List<V> array){
        AtomicInteger i0 = new AtomicInteger();
        i0.set(0);

        return array.stream().collect(Collectors.toMap(
                i -> i0.incrementAndGet(), Function.identity()));
    }

    /**
     * Метод consoleQuestion формирует вопрос к пользователю
     * через консоль и возвращает переменную из источника данных
     * @param scanner - сканер консоли
     * @param map - источник данных для вопроса
     * @param text - текст вопроса
     * @param errorText - текст ошибки
     * @param <K> - тип данных
     * @param <V> - тип данных
     * @return возвращает переменную из источника данных,
     * соответствующую введенному через консоль параметру
     */
    public static <K, V> V consoleQuestion(Scanner scanner,
                                           Map<K, V> map,
                                           String text,
                                           String errorText) {
        while (true) {
            System.out.print(text);

            if (scanner.hasNext()) {
                Map<String, V> newMap = map.entrySet().stream().collect(
                        Collectors.toMap(k -> String.valueOf(k.getKey()),
                                Map.Entry::getValue));

                String consoleText = scanner.nextLine();

                if (newMap.containsKey(consoleText)) {
                    return newMap.get(consoleText);
                }
            }

            System.out.println(errorText);
        }
    }

    /**
     * Метод consoleFileName формирует запрос имени файла
     * у пользователя через консоль и возвращает файл
     * @param scanner - сканер консоли
     * @param text - текст запроса
     * @param errorText - текст ошибки
     * @return возвращает файл
     */
    public static File consoleFileName(Scanner scanner,
                                       String text,
                                       String errorText) {
        while (true) {
            System.out.print(text);

            if (scanner.hasNext()) {
                return new File(scanner.nextLine());
            }

            System.out.println(errorText);
        }
    }

    /**
     * Метод printRates выводит курсы валют на печать в консоль
     * @param resultMap - данные курсов валют
     */
    public static void printRates(Map<Date, Map<SiteLoader.Currency, Double>> resultMap){
        if (resultMap != null && resultMap.size() > 0) {
            for (Map.Entry<Date, Map<SiteLoader.Currency, Double>> dateEntry
                    : resultMap.entrySet()) {
                for (Map.Entry<SiteLoader.Currency, Double> currencyEntry
                        : dateEntry.getValue().entrySet()) {

                    System.out.println(new SimpleDateFormat("dd.MM.yyyy")
                            .format(dateEntry.getKey())
                            + " " + currencyEntry.getKey().toString() + ": "
                            + currencyEntry.getValue());
                }
            }
        }
    }
}
