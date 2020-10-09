package homework3;

/**
 * Интерфейс ICalculator выполняет Задание 6 Урока 3
 * (интерфейс базовых арифметических действий)
 */
public interface ICalculator {

    /**
     * Метод sum вычисляет сумму двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает сумму двух чисел
     */
    double sum(double a, double b);

    /**
     * Метод diff вычисляет разность двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает разность двух чисел
     */
    double diff(double a, double b);

    /**
     * Метод div вычисляет результат деления двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат деления двух чисел
     */
    double div(double a, double b);

    /**
     * Метод mult вычисляет результат умножения двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат умножения двух чисел
     */
    double mult(double a, double b);

    /**
     * Метод abs вычисляет модуль числа
     * @param a - число
     * @return возвращает модуль числа
     */
    double abs(double a);

    /**
     * Метод sqrt вычисляет корень числа
     * @param a - число
     * @return возвращает корень числа
     */
    double sqrt(double a);

    /**
     * Метод pow вычисляет результат возведения числа в степень
     * @param a - дробное положительное число
     * @param power - целочисленная степень
     * @return возвращает модуль числа
     */
    double pow(double a, int power);
}
