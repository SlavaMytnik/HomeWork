package homework2.task4;

import java.util.Scanner;

/**
 * Класс Task4_5 выполняет Задание 4.5 Урока 2
 * (сортировка элементов введенного массива)
 */
public class Task4_5 {
    public static void main(String[] args) {
        int myArraySize = 0;
        int[] myArray;

        String consoleText = "";

        // Присваиваем переменной consoleText текст введенной строки
        Scanner in = new Scanner(System.in);
        System.out.print("Введите элементы массива через пробел: ");
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

            // Формируем массив элементов
            int i = 0;
            while (inLine.hasNextInt()) {
                myArray[i] = inLine.nextInt();
                i++;
            }

            // flag = 1 - для пузырьковой сортировки
            Sorter.bubbleAndShakerSorter(1, myArray);

            // flag = 2 - для шейкерной сортировки
            Sorter.bubbleAndShakerSorter(2, myArray);
        } else {
            System.out.println("Массив элементов пуст!");
        }
        inLine.close();
    }
}
