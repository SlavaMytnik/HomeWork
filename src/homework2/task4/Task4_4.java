package homework2.task4;

import java.util.Random;

/**
 * Класс Task4_4 выполняет Задание 4.4 Урока 2
 * (сортировка элементов массива случайных чисел)
 */
public class Task4_4 {
    public static void main(String[] args) {
        Random rand = new Random();

        // Случайным образом определяем размер массива
        int myArraySize = rand.nextInt(10);
        int[] myArray;

        myArray = new int[myArraySize];

        // Наполняем массив случайными числами
        for (int i = 0; i < myArraySize; i++) {
            myArray[i] = rand.nextInt(200) - 100;
        }

        Sorter.bubbleAndShakerSorter(1, myArray);
        Sorter.bubbleAndShakerSorter(2, myArray);
    }
}
