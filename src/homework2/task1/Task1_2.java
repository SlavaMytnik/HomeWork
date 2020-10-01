package homework2.task1;

/**
 * Класс Task1_2 выполняет Задание 1.2 Урока 2
 * (перемножение всех чисел, из которого состоит
 * заданное в аргументе программы число)
 */
public class Task1_2 {
    public static void main(final String[] args) {
        int resultInt = 1;
        int isError = 0;

        String resultString = "";

        /* Формируем ошибку, если аргумент программы
        не является целым неотрицательным числом */
        try {
            if (Integer.parseInt(args[0]) < 0) {
                isError = 1;
            }
        } catch (Exception e) {
            isError = 1;
        }

        if (isError == 0) {
            // Формируем результат
            for (int i = 0; i < args[0].length(); i++) {
                int nextVal = Integer.parseInt(args[0].substring(i, i + 1));

                resultInt *=  nextVal;

                if (i < args[0].length() - 1) {
                    resultString += nextVal + " * ";
                } else {
                    resultString += nextVal + " = ";
                }
            }

            System.out.println(resultString + resultInt);
        } else {
            System.out.println("Ошибка задания аргумента!");
        }
    }
}
