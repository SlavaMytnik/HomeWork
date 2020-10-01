package homework2.task1;

/**
 * Класс Task1_1 выполняет Задание 1.1 Урока 2
 * (перемножение чисел от 1 до заданного в аргументе программы числа)
 */
public class Task1_1 {
    public static void main(String[] args) {
        int maxInt = 1;
        int resultInt = 1;
        int isError = 0;

        String resultString = "";

        /* Формируем ошибку, если аргумент программы
        не является целым положительным числом.
        При отсутствии ошибок присваиваем переменной
        maxInt заданное число */
        try {
            maxInt = Integer.parseInt(args[0]);

            if (maxInt <= 0) {
                isError = 1;
            }
        } catch (Exception e) {
            isError = 1;
        }

        if (isError == 0) {

            // Формируем результат
            for (int i = 1; i <= maxInt; i++) {
                resultInt *=  i;

                if (i < maxInt) {
                    resultString += i + " * ";
                } else {
                    resultString += i + " = ";
                }
            }

            System.out.println(resultString + resultInt);
        } else {
            System.out.println("Ошибка задания аргумента!");
        }
    }
}
