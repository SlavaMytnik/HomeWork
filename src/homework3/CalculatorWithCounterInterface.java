package homework3;

/**
 * Класс CalculatorWithCounter выполняет Задания 6.1 - 6.3 Урока 3
 * (подсчет количества использования калькулятора с применением интерфейса)
 */
public class CalculatorWithCounterInterface {
    private ICalculator obj;

    private static int countOperation = 0;

    /**
     * Метод CalculatorWithCounter является конструктором класса
     * @param obj принимает объект типа ICalculator
     */
    public CalculatorWithCounterInterface(ICalculator obj) {
        this.obj = obj;
    }

    /**
     * Метод sum вычисляет сумму двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает сумму двух чисел
     */
    public double sum(double a, double b) {
        countOperation++;

        return obj.sum(a, b);
    }

    /**
     * Метод diff вычисляет разность двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает разность двух чисел
     */
    public double diff(double a, double b) {
        countOperation++;

        return obj.diff(a, b);
    }

    /**
     * Метод div вычисляет результат деления двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат деления двух чисел
     */
    public double div(double a, double b) {
        countOperation++;

        return obj.div(a, b);
    }

    /**
     * Метод mult вычисляет результат умножения двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат умножения двух чисел
     */
    public double mult(double a, double b) {
        countOperation++;

        return obj.mult(a, b);
    }

    /**
     * Метод abs вычисляет модуль числа
     * @param a - число
     * @return возвращает модуль числа
     */
    public double abs(double a) {
        countOperation++;

        return obj.abs(a);
    }

    /**
     * Метод sqrt вычисляет корень числа
     * @param a - число
     * @return возвращает корень числа
     */
    public double sqrt(double a) {
        countOperation++;

        return obj.sqrt(a);
    }

    /**
     * Метод pow вычисляет результат возведения числа в степень
     * @param a - дробное положительное число
     * @param power - целочисленная степень
     * @return возвращает модуль числа
     */
    public double pow(double a, int power) {
        countOperation++;

        return obj.pow(a, power);
    }

    /**
     * Метод getCountOperation возвращает количество арифметических операций
     */
    public int getCountOperation() {
        return countOperation;
    }
}
