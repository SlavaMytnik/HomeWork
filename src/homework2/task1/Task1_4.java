package homework2.task1;

/**
 * Класс Task1_4 выполняет Задание 1.4 Урока 2
 * (переполнение long)
 */
public class Task1_4 {
    public static void main(String[] args) {
        long a = 1;

        while (Long.MAX_VALUE / 3 >= a) {
            a *= 3;
        }

        System.out.println("Значение до переполнения: " + a);
        System.out.println("Значение после переполнения: " + a * 3);
    }
}
