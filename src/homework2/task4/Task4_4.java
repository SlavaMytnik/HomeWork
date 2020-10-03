package homework2.task4;

import java.util.Random;

/**
 * Класс Task4_4 выполняет Задание 4.4 Урока 2
 * (сортировка элементов массива случайных чисел)
 */
public class Task4_4 {
    public static void main(String[] args) {
        Random rand = new Random();

        // Создаем массив случайной длинны
        int[] myArray = new int[rand.nextInt(10)];

        // Наполняем массив случайными числами
        for (int i = 0; i < myArray.length; i++) {
            myArray[i] = rand.nextInt(200) - 100;
        }

        // flag = 1 - для пузырьковой сортировки
        Sorter.bubbleAndShakerSorter(1, myArray);

        // flag = 2 - для шейкерной сортировки
        Sorter.bubbleAndShakerSorter(2, myArray);
    }
}
