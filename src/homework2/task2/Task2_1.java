package homework2.task2;

import java.util.Scanner;

/**
 * Класс Task2_1 выполняет Задание 2.1 Урока 2
 * (вывод массива чисел)
 */
public class Task2_1 {
    public static void main(String[] args) {
        int myArraySize = 0;
        int[] myArray;

        String consoleText = "";

        // Присваиваем переменной consoleText текст введенной строки
        Scanner in = new Scanner(System.in);
        System.out.print("Введите числа через пробел: ");
        consoleText = in.nextLine();
        in.close();

        Scanner inLine = new Scanner(consoleText);

        /* Присваиваем переменной myArraySize количество чисел
        во введенной строке, т.е. определяем размер массива */
        while (inLine.hasNextInt()) {
            inLine.nextInt();
            myArraySize++;
        }

        inLine = new Scanner(consoleText);

        if (myArraySize > 0) {

            myArray = new int[myArraySize];

            // Формируем массив чисел
            int i = 0;
            while (inLine.hasNextInt()) {
                myArray[i] = inLine.nextInt();
                i++;
            }

            // Ниже в коде вместо myArraySize можно использовать myArray.length

            // Вывод массива чисел с помощью do while
            System.out.println("Результат do while: ");

            i = 0;
            do {
                System.out.println(myArray[i]);
                i++;
            } while (i < myArraySize);

            // Вывод массива чисел с помощью while
            System.out.println("Результат while: ");

            i = 0;
            while (i < myArraySize) {
                System.out.println(myArray[i]);
                i++;
            }

            // Вывод массива чисел с помощью for
            System.out.println("Результат for: ");

            for (int j = 0; j < myArraySize; j++) {
                System.out.println(myArray[j]);
            }

            // Вывод массива чисел с помощью foreach
            System.out.println("Результат foreach: ");

            for (int j : myArray) {
                System.out.println(j);
            }
        } else {
            System.out.println("Массив чисел пуст!");
        }
        inLine.close();
    }
}
