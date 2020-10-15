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
    public static String createPhoneNumber(int[] arr) {

        return String.format("(%d%d%d) %d%d%d-%d%d%d%d",
                arr[0], arr[1], arr[2], arr[3], arr[4],
                arr[5], arr[6], arr[7], arr[8], arr[9]);
    }
}
