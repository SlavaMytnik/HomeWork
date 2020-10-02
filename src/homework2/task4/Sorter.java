package homework2.task4;

import java.util.Arrays;

/**
 * Класс Sorter выполняет пузырьковую и шейкерную сортировку
 */
public class Sorter {

    // Класс bubbleAndShakerSorter выполняет пузырьковую и шейкерную сортировку
    public static void bubbleAndShakerSorter(int tag, final int[] receivedArray) {
        int[] sortedArray = receivedArray.clone();

        // Пузырьковая tag == 1 или шейкерная tag == 2 сортировка
        if (tag == 1 || tag == 2) {
            int left = 0;
            int right = sortedArray.length - 1;

            String sortType = "";

            do {

                // Цикл для пузырьковой и шейкерной сортировки
                for (int j = left; j < right; j++) {
                    if (sortedArray[j] > sortedArray[j + 1]) {
                        int temp = sortedArray[j];
                        sortedArray[j] = sortedArray[j + 1];
                        sortedArray[j + 1] = temp;
                    }
                }

                right--;

                // Цикл для шейкерной сортировки
                if (tag == 2) {
                    for (int j = right; j > left; j--) {
                        if (sortedArray[j] < sortedArray[j - 1]) {
                            int temp = sortedArray[j];
                            sortedArray[j] = sortedArray[j - 1];
                            sortedArray[j - 1] = temp;
                        }
                    }

                    left++;
                }
            } while (left < right);

            if (tag == 1) {
                sortType = "пузырьковой";
            } else {
                sortType = "шейкерной";
            }

            System.out.println(String.format("%37s",
                    "Массив до " + sortType + " сортировки: ") +
                    Arrays.toString(receivedArray));
            System.out.println(String.format("%37s",
                    "Массив после " + sortType + " сортировки: ") +
                    Arrays.toString(sortedArray) + "\n");
        }
    }
}
