package homework2.task2;

import java.util.Scanner;

/**
 * Класс Task2_3 выполняет Задание 2.3 Урока 2
 * (вывод массива чисел в обратном порядке)
 */
public class Task2_3 {
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

            // Вывод массива чисел в обратном порядке с помощью do while
            System.out.println("Результат do while: ");

            i = myArraySize;
            do {
                i--;
                System.out.println(myArray[i]);
            } while (i > 0);

            // Вывод массива чисел в обратном порядке с помощью while
            System.out.println("Результат while: ");

            i = myArraySize;
            while (i > 0) {
                i--;
                System.out.println(myArray[i]);
            }

            // Вывод массива чисел в обратном порядке с помощью for
            System.out.println("Результат for: ");

            for (int j = myArraySize - 1; j >= 0; j--) {
                System.out.println(myArray[j]);
            }

            // Вывод массива чисел в обратном порядке с помощью foreach
            System.out.println("Результат foreach: ");

            // Инвертируем массив
            for (int j = 0; j < myArraySize / 2; j++) {
                int temp = myArray[i];
                myArray[i] = myArray[myArraySize - i - 1];
                myArray[myArraySize - i - 1] = temp;
            }

            for (int j : myArray) {
                System.out.println(j);
            }
        } else {
            System.out.println("Массив чисел пуст!");
        }
        inLine.close();
    }
}
