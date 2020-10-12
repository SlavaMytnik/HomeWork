package homework3;

import static java.lang.Double.NaN;

/**
 * Класс CalculatorWithCounter выполняет Задания 7.1 - 7.5 Урока 3
 * (использование памяти калькулятора)
 */
public class CalculatorWithMemory {
    private ICalculator obj;

    private static double memoryLast = NaN;
    private static double memoryResult = NaN;

    /**
     * Метод CalculatorWithCounter является конструктором класса
     * @param obj принимает объект типа ICalculator
     */
    public CalculatorWithMemory(ICalculator obj) {
        this.obj = obj;
    }

    /**
     * Метод sum вычисляет сумму двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает сумму двух чисел
     */
    public double sum(double a, double b) {
        memoryLast = obj.sum(a, b);

        return memoryLast;
    }

    /**
     * Метод diff вычисляет разность двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает разность двух чисел
     */
    public double diff(double a, double b) {
        memoryLast = obj.diff(a, b);

        return memoryLast;
    }

    /**
     * Метод div вычисляет результат деления двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат деления двух чисел
     */
    public double div(double a, double b) {
        memoryLast = obj.div(a, b);

        return memoryLast;
    }

    /**
     * Метод mult вычисляет результат умножения двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат умножения двух чисел
     */
    public double mult(double a, double b) {
        memoryLast = obj.mult(a, b);

        return memoryLast;
    }

    /**
     * Метод abs вычисляет модуль числа
     * @param a - число
     * @return возвращает модуль числа
     */
    public double abs(double a) {
        memoryLast = obj.abs(a);

        return memoryLast;
    }

    /**
     * Метод sqrt вычисляет корень числа
     * @param a - число
     * @return возвращает корень числа
     */
    public double sqrt(double a) {
        memoryLast = obj.sqrt(a);

        return memoryLast;
    }

    /**
     * Метод pow вычисляет результат возведения числа в степень
     * @param a - дробное положительное число
     * @param power - целочисленная степень
     * @return возвращает модуль числа
     */
    public double pow(double a, int power) {
        memoryLast = obj.pow(a, power);

        return memoryLast;
    }

    /**
     * Метод saveToMemory сохраняет в памяти результат последней операции
     */
    public void saveToMemory() {
        memoryResult = memoryLast;
    }

    /**
     * Метод getFromMemory возвращает записанное в памяти значение
     * @return возвращает записанное в памяти значение
     */
    public double getFromMemory() {
        double task = memoryResult;
        memoryResult = NaN;

        return task;
    }
}
