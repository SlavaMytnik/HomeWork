package homework2.task4;

/**
 * Класс Task4_2_2 выполняет Задание 4.2.2 Урока 2
 * (шейкерная сортировка)
 */
public class Task4_2_2 {
    public static void main(String[] args) {

        // tag = 2 - для шейкерной сортировки
        int[] myArray = new int[]{1, 2, 3, 4, 5, 6};
        Sorter.bubbleAndShakerSorter(2, myArray);

        myArray = new int[]{1, 1, 1, 1};
        Sorter.bubbleAndShakerSorter(2, myArray);

        myArray = new int[]{9, 1, 5, 99, 9, 9};
        Sorter.bubbleAndShakerSorter(2, myArray);

        myArray = new int[]{};
        Sorter.bubbleAndShakerSorter(2, myArray);
    }
}
