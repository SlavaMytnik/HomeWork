package homework1.task5;

import java.util.Objects;
import java.util.Scanner;

/**
 * Класс Task5Variant1 выполняет Задание 5.1 Урока 1
 * (выбирает приветствие по имени с помощью if)
 */
public class Task5Variant1 {
    public static void main(String[] args) {

        // Присваиваем переменной name введенное имя
        Scanner in = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = in.nextLine();

        if (Objects.equals(name, "Вася")) {
            System.out.println("Привет!");
        }

        if (Objects.equals(name, "Вася") || Objects.equals(name, "Анастасия")) {
            System.out.println("Я тебя так долго ждал");
        }

        if (!Objects.equals(name, "Вася") && !Objects.equals(name, "Анастасия")) {
            System.out.println("Добрый день, а вы кто?");
        }
    }
}
