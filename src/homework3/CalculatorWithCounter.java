package homework3;

import static java.lang.Double.NaN;

/**
 * Класс CalculatorWithCounter выполняет Задания 5.1 - 5.5 Урока 3
 * (подсчет количества использования калькулятора)
 */
public class CalculatorWithCounter {
    private CalculatorWithOperator calculator1;
    private CalculatorWithMathCopy calculator2;
    private CalculatorWithMathExtends calculator3;

    private int countOperation = 0;
    private int calculatorNumber;

    /**
     * Метод CalculatorWithCounter является конструктором класса
     * @param obj принимает объект типа CalculatorWithOperator
     */
    public CalculatorWithCounter(CalculatorWithOperator obj) {
        calculator1 = obj;
        calculatorNumber = 1;
    }

    /**
     * Метод CalculatorWithCounter является конструктором класса
     * @param obj принимает объект типа CalculatorWithMathCopy
     */
    public CalculatorWithCounter(CalculatorWithMathCopy obj) {
        calculator2 = obj;
        calculatorNumber = 2;
    }

    /**
     * Метод CalculatorWithCounter является конструктором класса
     * @param obj принимает объект типа CalculatorWithMathExtends
     */
    public CalculatorWithCounter(CalculatorWithMathExtends obj) {
        calculator3 = obj;
        calculatorNumber = 3;
    }

    /**
     * Метод sum вычисляет сумму двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает сумму двух чисел
     */
    public double sum(double a, double b) {
        countOperation++;

        switch (calculatorNumber) {
            case 1:
                return calculator1.sum(a, b);
            case 2:
                return calculator2.sum(a, b);
            case 3:
                return calculator3.sum(a, b);
            default:
                return NaN;
        }
    }

    /**
     * Метод diff вычисляет разность двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает разность двух чисел
     */
    public double diff(double a, double b) {
        countOperation++;

        switch (calculatorNumber) {
            case 1:
                return calculator1.diff(a, b);
            case 2:
                return calculator2.diff(a, b);
            case 3:
                return calculator3.diff(a, b);
            default:
                return NaN;
        }
    }

    /**
     * Метод div вычисляет результат деления двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат деления двух чисел
     */
    public double div(double a, double b) {
        countOperation++;

        switch (calculatorNumber) {
            case 1:
                return calculator1.div(a, b);
            case 2:
                return calculator2.div(a, b);
            case 3:
                return calculator3.div(a, b);
            default:
                return NaN;
        }
    }

    /**
     * Метод mult вычисляет результат умножения двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат умножения двух чисел
     */
    public double mult(double a, double b) {
        countOperation++;

        switch (calculatorNumber) {
            case 1:
                return calculator1.mult(a, b);
            case 2:
                return calculator2.mult(a, b);
            case 3:
                return calculator3.mult(a, b);
            default:
                return NaN;
        }
    }

    /**
     * Метод abs вычисляет модуль числа
     * @param a - число
     * @return возвращает модуль числа
     */
    public double abs(double a) {
        countOperation++;

        switch (calculatorNumber) {
            case 1:
                return calculator1.abs(a);
            case 2:
                return calculator2.abs(a);
            case 3:
                return calculator3.abs(a);
            default:
                return NaN;
        }
    }

    /**
     * Метод sqrt вычисляет корень числа
     * @param a - число
     * @return возвращает корень числа
     */
    public double sqrt(double a) {
        countOperation++;

        switch (calculatorNumber) {
            case 1:
                return calculator1.sqrt(a);
            case 2:
                return calculator2.sqrt(a);
            case 3:
                return calculator3.sqrt(a);
            default:
                return NaN;
        }
    }

    /**
     * Метод pow вычисляет результат возведения числа в степень
     * @param a - дробное положительное число
     * @param power - целочисленная степень
     * @return возвращает модуль числа
     */
    public double pow(double a, int power) {
        countOperation++;

        switch (calculatorNumber) {
            case 1:
                return calculator1.pow(a, power);
            case 2:
                return calculator2.pow(a, power);
            case 3:
                return calculator3.pow(a, power);
            default:
                return NaN;
        }
    }

    /**
     * Метод getCountOperation возвращает количество арифметических операций
     */
    public int getCountOperation() {
        return countOperation;
    }
}
