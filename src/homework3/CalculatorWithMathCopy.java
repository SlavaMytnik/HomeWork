package homework3;

import static java.lang.Double.NEGATIVE_INFINITY;
import static java.lang.Double.POSITIVE_INFINITY;

/**
 * Класс CalculatorWithMathCopy выполняет Задания 3.1, 3.2 Урока 3
 * (калькулятор с использованием Math)
 */
public class CalculatorWithMathCopy {

    /**
     * Метод sum вычисляет сумму двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает сумму двух чисел
     */
    public double sum(double a, double b) {
        return a + b;
    }

    /**
     * Метод diff вычисляет разность двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает разность двух чисел
     */
    public double diff(double a, double b) {
        return a - b;
    }

    /**
     * Метод div вычисляет результат деления двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат деления двух чисел
     */
    public double div(double a, double b) {
        if (a >= 0 && b == 0) {
            return POSITIVE_INFINITY;
        } else if (a < 0 && b == 0) {
            return NEGATIVE_INFINITY;
        }

        return a / b;
    }

    /**
     * Метод mult вычисляет результат умножения двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат умножения двух чисел
     */
    public double mult(double a, double b) {
        return a * b;
    }

    /**
     * Метод abs вычисляет модуль числа
     * @param a - число
     * @return возвращает модуль числа
     */
    public double abs(double a) {
        return Math.abs(a);
    }

    /**
     * Метод sqrt вычисляет корень числа
     * @param a - число
     * @return возвращает корень числа
     */
    public double sqrt(double a) {
        return Math.sqrt(a);
    }

    /**
     * Метод pow вычисляет результат возведения числа в степень
     * @param a - дробное положительное число
     * @param b - целочисленная степень
     * @return возвращает модуль числа
     */
    public double pow(double a, int b) {
        return Math.pow(a, b);
    }
}
