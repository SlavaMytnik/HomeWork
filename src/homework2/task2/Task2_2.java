package homework2.task2;

import java.util.Scanner;

/**
 * Класс Task2_2 выполняет Задание 2.2 Урока 2
 * (вывод каждого второго элемента массива)
 */
public class Task2_2 {
    public static void main(String[] args) {
        int myArraySize = 0;
        String[] myArray;

        String consoleText = "";

        // Присваиваем переменной consoleText текст введенной строки
        Scanner in = new Scanner(System.in);
        System.out.print("Введите элементы массива через пробел: ");
        consoleText = in.nextLine();
        in.close();

        Scanner inLine = new Scanner(consoleText);

        /* Присваиваем переменной myArraySize количество элементов
        во введенной строке, т.е. определяем размер массива */
        while (inLine.hasNext()) {
            inLine.next();
            myArraySize++;
        }

        inLine = new Scanner(consoleText);

        if (myArraySize > 0) {

            myArray = new String[myArraySize];

            // Формируем массив элементов
            int i = 0;
            while (inLine.hasNext()) {
                myArray[i] = inLine.next();
                i++;
            }

            // Выводим на печать только каждый второй элемент массива
            for (int j = 0; j < myArraySize; j++) {
                if (j % 2 != 0) {
                    System.out.println(myArray[j]);
                }
            }
        } else {
            System.out.println("Массив элементов пуст!");
        }
        inLine.close();
    }
}
