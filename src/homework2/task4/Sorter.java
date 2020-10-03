package homework2.task4;

import java.util.Arrays;

/**
 * Класс Sorter выполняет пузырьковую и шейкерную сортировку
 */
public class Sorter {

    /**
     * Метод bubbleAndShakerSorter выполняет пузырьковую и шейкерную сортировку
     * @param flag определяет вид сортировки: 1 - пузырьковая, 2 - шейкерная
     * @param receivedArray принимает массив чисел
     */
    public static void bubbleAndShakerSorter(int flag, int[] receivedArray) {
        int[] sortedArray = receivedArray.clone();

        // Пузырьковая или шейкерная сортировка
        if (flag == 1 || flag == 2) {
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

                if (flag == 2) {

                    // Цикл для шейкерной сортировки
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

            if (flag == 1) {
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
