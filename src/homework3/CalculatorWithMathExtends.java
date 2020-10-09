package homework3;

/**
 * Класс CalculatorWithMathExtends выполняет Задания 4.1 - 4.3 Урока 3
 * (калькулятор с наследованием класса CalculatorWithOperator)
 */
public class CalculatorWithMathExtends extends CalculatorWithOperator {

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
