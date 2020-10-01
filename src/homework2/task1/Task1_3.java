package homework2.task1;

import java.util.Scanner;

/**
 * Класс Task1_3 выполняет Задание 1.3 Урока 2
 * (возведение числа в степень)
 */
public class Task1_3 {
    public static void main(String[] args) {
        float result = 1;
        float base = 0;

        int exponent = 0;

        String errorText = "Ошибочный формат введенных данных!";

        Scanner in = new Scanner(System.in);

        /* Присваиваем переменной base введенное число,
        * если оно введено без ошибки */
        while (true) {
            System.out.print("Введите число: ");
            try {
                base = Float.parseFloat(in.nextLine());
                break;
            } catch (Exception e) {
                System.out.println(errorText);
            }
        }

        /* Присваиваем переменной exponent введенное число,
         * если оно введено без ошибки и является целым и положительным */
        while (true) {
            System.out.print("Введите целую положительную степень: ");
            try {
                exponent = Integer.parseInt(in.nextLine());

                if (exponent > 0) {
                    break;
                } else {
                    System.out.println(errorText);
                }
            } catch (Exception e) {
                System.out.println(errorText);
            }
        }

        for (int i = 0; i < exponent; i++) {
            result *= base;
        }

        System.out.println(base + " в степени " + exponent + " равно " + result);
    }
}
