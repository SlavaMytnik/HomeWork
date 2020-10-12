package homework3;

import static java.lang.Double.NaN;

/**
 * Класс CalculatorWithCounter выполняет Задания 8.1 - 8.4 Урока 3
 * (декоратор использования памяти калькулятора)
 */
public class CalculatorWithMemoryDecorator implements ICalculator {
    private ICalculator calculator = null;

    private double memoryLast = NaN;
    private double memoryResult = NaN;

    /**
     * Метод CalculatorWithCounter является конструктором класса
     * @param calculator принимает объект типа ICalculator
     */
    public CalculatorWithMemoryDecorator(ICalculator calculator) {
        this.calculator = calculator;
    }

    /**
     * Метод getCalculator возвращает калькулятор
     */
    public ICalculator getCalculator() {
        return calculator;
    }

    /**
     * Метод sum вычисляет сумму двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает сумму двух чисел
     */
    @Override
    public double sum(double a, double b) {
        memoryLast = calculator.sum(a, b);

        return memoryLast;
    }

    /**
     * Метод diff вычисляет разность двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает разность двух чисел
     */
    @Override
    public double diff(double a, double b) {
        memoryLast = calculator.diff(a, b);

        return memoryLast;
    }

    /**
     * Метод div вычисляет результат деления двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат деления двух чисел
     */
    @Override
    public double div(double a, double b) {
        memoryLast = calculator.div(a, b);

        return memoryLast;
    }

    /**
     * Метод mult вычисляет результат умножения двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат умножения двух чисел
     */
    @Override
    public double mult(double a, double b) {
        memoryLast = calculator.mult(a, b);

        return memoryLast;
    }

    /**
     * Метод abs вычисляет модуль числа
     * @param a - число
     * @return возвращает модуль числа
     */
    @Override
    public double abs(double a) {
        memoryLast = calculator.abs(a);

        return memoryLast;
    }

    /**
     * Метод sqrt вычисляет корень числа
     * @param a - число
     * @return возвращает корень числа
     */
    @Override
    public double sqrt(double a) {
        memoryLast = calculator.sqrt(a);

        return memoryLast;
    }

    /**
     * Метод pow вычисляет результат возведения числа в степень
     * @param a - дробное положительное число
     * @param power - целочисленная степень
     * @return возвращает модуль числа
     */
    @Override
    public double pow(double a, int power) {
        memoryLast = calculator.pow(a, power);

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
