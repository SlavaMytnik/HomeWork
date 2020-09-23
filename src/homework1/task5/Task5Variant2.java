package homework1.task5;

import java.util.Objects;
import java.util.Scanner;

/**
 * Класс Task5Variant2 выполняет Задание 5.2 Урока 1
 * (выбирает приветствие по имени с помощью if else if)
 */
public class Task5Variant2 {
    public static void main(String[] args) {

        // Присваиваем переменной name введенное имя
        Scanner in = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = in.nextLine();

        if (Objects.equals(name, "Вася")){
            System.out.println("Привет!");
            System.out.println("Я тебя так долго ждал");
        } else if (Objects.equals(name, "Анастасия")){
            System.out.println("Я тебя так долго ждал");
        } else {
            System.out.println("Добрый день, а вы кто?");
        }
    }
}
