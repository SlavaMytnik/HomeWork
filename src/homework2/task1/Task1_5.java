package homework2.task1;

/**
 * Класс Task1_5 выполняет Задание 1.5 Урока 2
 * (таблица умножения)
 */
public class Task1_5 {
    public static void main(String[] args) {
        String printText = "";

        // Вывод таблицы умножения для цифр от 2 до 5
        for (int i = 1; i <= 10; i++) {
            printText = "";

            for (int j = 2; j <= 5; j++) {
                printText += j + " * " + String.format("%2s", i) + " = " + String.format("%2s", j * i);

                if (j != 5) {
                    printText += " | ";
                }
            }
            System.out.println(printText);
        }

        System.out.println(String.format("%53s", "").replaceAll(" ", "-"));

        // Вывод таблицы умножения для цифр от 6 до 9
        for (int i = 1; i <= 10; i++) {
            printText = "";

            for (int j = 6; j <= 9; j++) {
                printText += j + " * " + String.format("%2s", i) + " = " + String.format("%2s", j * i);

                if (j != 9) {
                    printText += " | ";
                }
            }
            System.out.println(printText);
        }
    }
}
