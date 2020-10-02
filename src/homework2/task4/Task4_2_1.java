package homework2.task4;

/**
 * Класс Task4_2_1 выполняет Задание 4.2.1 Урока 2
 * (пузырьковая сортировка)
 */
public class Task4_2_1 {
    public static void main(String[] args) {

        // tag = 1 - для пузырьковой сортировки
        int[] myArray = new int[]{1, 2, 3, 4, 5, 6};
        Sorter.bubbleAndShakerSorter(1, myArray);

        myArray = new int[]{1, 1, 1, 1};
        Sorter.bubbleAndShakerSorter(1, myArray);

        myArray = new int[]{9, 1, 5, 99, 9, 9};
        Sorter.bubbleAndShakerSorter(1, myArray);

        myArray = new int[]{};
        Sorter.bubbleAndShakerSorter(1, myArray);
    }
}
