package homework1.task5;

import java.util.Objects;
import java.util.Scanner;

/**
 * Класс Task5Variant3 выполняет Задание 5.3 Урока 1
 * (выбирает приветствие по имени с помощью switch)
 */
public class Task5Variant3 {
    public static void main(String[] args) {

        // Присваиваем переменной name введенное имя
        Scanner in = new Scanner(System.in);
        System.out.print("Введите имя: ");
        String name = in.nextLine();

        switch (name){
            case "Вася":
                System.out.println("Привет!");

            case "Анастасия":
                System.out.println("Я тебя так долго ждал");
                break;

            default:
                System.out.println("Добрый день, а вы кто?");
                break;
        }
    }
}
