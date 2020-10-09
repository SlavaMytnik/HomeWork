package homework3;

import static java.lang.Double.*;

/**
 * Класс CalculatorWithOperator выполняет Задания 2.1 - 2.4 Урока 3
 * (калькулятор без использования Math)
 */
public class CalculatorWithOperator {

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
        if (a < 0) {
            a *= -1;
        }

        return a;
    }

    /**
     * Метод sqrt вычисляет корень числа
     * @param a - число
     * @return возвращает корень числа
     */
    public double sqrt(double a) {
        if (a < 0) {
            return NaN;
        }

        double temp;
        double result = a / 2;

        do {
            temp = result;
            result = (temp + (a / temp)) / 2;
        } while ((temp - result) != 0);

        return result;
    }

    /**
     * Метод pow вычисляет результат возведения числа в степень
     * @param a - дробное положительное число
     * @param b - целочисленная степень
     * @return возвращает модуль числа
     */
    public double pow(double a, int b) {
        if (a < 0) {
            return NaN;
        }

        double result = 1;

        for (int i = 0; i < b; i++) {
            result *= a;
        }

        return result;
    }
}
