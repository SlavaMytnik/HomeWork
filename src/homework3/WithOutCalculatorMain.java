package homework3;

/**
 * Класс WithoutCalculatorMain выполняет Задание 1 Урока 3
 * (вывод на консоль арифметического выражения)
 */
public class WithOutCalculatorMain {
    public static void main(String[] args) {
        double result = 4.1 + 15 * 7 + Math.pow((double) 28 / 5, 2);

        System.out.println("4.1 + 15 * 7 + (28 / 5) ^ 2 = " + result);
    }
}
