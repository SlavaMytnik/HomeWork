package homework1;

/**
 * Класс Task4 выполняет Задание 4 Урока 1
 * (определяет спать дальше или нет)
 */
public class Task4 {
    public static void main(String[] args) {
        boolean isWeekday = true;
        boolean isVacation = true;

        if (sleepln(isWeekday, isVacation)) {
            System.out.println("Спать...");
        } else {
            System.out.println("Подъем!");
        }
    }

    /**
     * Метод getBinaryString определяет спать дальше или нет
     */
    public static boolean sleepln(boolean weekday, boolean vacation) {

        // Если не рабочий день или отпуск, то можно спать
        if (!weekday || vacation) {
            return true;
        }
        return false;
    }
}
