package homework3;

/**
 * Класс CalculatorWithCounter выполняет Задания 8.1 - 8.4 Урока 3
 * (декоратор подсчета количества использования калькулятора с применением интерфейса)
 */
public class CalculatorWithCounterDecorator implements ICalculator {
    private ICalculator calculator = null;

    private static int countOperation = 0;

    /**
     * Метод CalculatorWithCounter является конструктором класса
     * @param calculator принимает объект типа ICalculator
     */
    public CalculatorWithCounterDecorator(ICalculator calculator) {
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
        countOperation++;

        return calculator.sum(a, b);
    }

    /**
     * Метод diff вычисляет разность двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает разность двух чисел
     */
    @Override
    public double diff(double a, double b) {
        countOperation++;

        return calculator.diff(a, b);
    }

    /**
     * Метод div вычисляет результат деления двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат деления двух чисел
     */
    @Override
    public double div(double a, double b) {
        countOperation++;

        return calculator.div(a, b);
    }

    /**
     * Метод mult вычисляет результат умножения двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат умножения двух чисел
     */
    @Override
    public double mult(double a, double b) {
        countOperation++;

        return calculator.mult(a, b);
    }

    /**
     * Метод abs вычисляет модуль числа
     * @param a - число
     * @return возвращает модуль числа
     */
    @Override
    public double abs(double a) {
        countOperation++;

        return calculator.abs(a);
    }

    /**
     * Метод sqrt вычисляет корень числа
     * @param a - число
     * @return возвращает корень числа
     */
    @Override
    public double sqrt(double a) {
        countOperation++;

        return calculator.sqrt(a);
    }

    /**
     * Метод pow вычисляет результат возведения числа в степень
     * @param a - дробное положительное число
     * @param power - целочисленная степень
     * @return возвращает модуль числа
     */
    @Override
    public double pow(double a, int power) {
        countOperation++;

        return calculator.pow(a, power);
    }

    /**
     * Метод getCountOperation возвращает количество арифметических операций
     */
    public int getCountOperation() {
        return countOperation;
    }
}
