package homework1;

import java.util.Arrays;

/**
 * Класс Task6 выполняет Задание 6 Урока 1
 * (вывод массива чисел в формате телефонного номера)
 */
public class Task6 {
    public static void main(String[] args) {
        int[] phoneNumberArray = {1, 2, 3, 4, 5, 6, 7, 8, 9, 0};

        System.out.println(createPhoneNumber(phoneNumberArray));
    }

    /**
     * Метод createPhoneNumber осуществляет преобразование и
     * вывод на печать массива чисел в формате телефонного номера
     */
    public static String createPhoneNumber(int[] receivedPhoneNumberArray) {

        /* Преобразование массива чисел в строку и
        удаление из нее скобок, запятых и пробелов */
        String result = Arrays.toString(receivedPhoneNumberArray).
                replaceAll("\\[|\\]|,|\\ ", "");

        // Преобразование строки в формат телефонного номера (Вариант 1)
        result = result.replaceFirst("(\\d{3})(\\d{3})(\\d+)", "($1) $2-$3");

        /* Преобразование строки в формат телефонного номера (Вариант 2, закомментирован)
        result = String.format("(%s) %s-%s", result.substring(0, 3), result.substring(3, 6),
                result.substring(6, 10)); */

        return result;
    }
}
