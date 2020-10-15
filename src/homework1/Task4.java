package homework1;

/**
 * Класс Task4 выполняет Задание 4 Урока 1
 * (определяет спать дальше или нет)
 */
public class Task4 {
    public static void main(String[] args) {
        boolean isWeekday = true;
        boolean isVacation = true;

        System.out.println(sleepln(isWeekday, isVacation) ? "Спать..." : "Подъем!");
    }

    /**
     * Метод sleepln определяет спать дальше или нет
     */
    public static boolean sleepln(boolean weekday, boolean vacation) {

        // Если не рабочий день или отпуск, то можно спать
        return (!weekday || vacation) ? true : false;
    }
}
