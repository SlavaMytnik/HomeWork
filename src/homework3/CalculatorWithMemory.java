package homework3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static java.lang.Double.NaN;

/**
 * Класс CalculatorWithCounter выполняет Задания 7.1 - 7.5 Урока 3
 * (использование памяти калькулятора)
 */
public class CalculatorWithMemory {
    private Object obj;
    private double memoryLast = NaN;
    private double memoryResult = NaN;

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
        return getResultFromMethod(1, "sum", a, b, 0);
    }

    /**
     * Метод diff вычисляет разность двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает разность двух чисел
     */
    public double diff(double a, double b) {
        return getResultFromMethod(1, "diff", a, b, 0);
    }

    /**
     * Метод div вычисляет результат деления двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат деления двух чисел
     */
    public double div(double a, double b) {
        return getResultFromMethod(1, "div", a, b, 0);
    }

    /**
     * Метод mult вычисляет результат умножения двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат умножения двух чисел
     */
    public double mult(double a, double b) {
        return getResultFromMethod(1, "mult", a, b, 0);
    }

    /**
     * Метод abs вычисляет модуль числа
     * @param a - число
     * @return возвращает модуль числа
     */
    public double abs(double a) {
        return getResultFromMethod(2, "abs", a, 0, 0);
    }

    /**
     * Метод sqrt вычисляет корень числа
     * @param a - число
     * @return возвращает корень числа
     */
    public double sqrt(double a) {
        return getResultFromMethod(2, "sqrt", a, 0, 0);
    }

    /**
     * Метод pow вычисляет результат возведения числа в степень
     * @param a - дробное положительное число
     * @param power - целочисленная степень
     * @return возвращает модуль числа
     */
    public double pow(double a, int power) {
        return getResultFromMethod(3, "pow", a, 0, power);
    }

    /**
     * Метод getResultFromMethod осуществляет расчет арифметического выражения
     * @param flag определяет структуру вызываемого метода
     * @param methodName - имя вызываемого метода
     * @param a - первое число
     * @param b - второе число
     * @param power - степень числа
     * @return возвращает результат расчета
     */
    private double getResultFromMethod(int flag, String methodName, double a, double b, int power) {
        double result = NaN;
        Method callMethod;

        try {
            if (flag == 1) {
                callMethod = obj.getClass().getMethod(methodName, double.class, double.class);
            } else if (flag == 2) {
                callMethod = obj.getClass().getMethod(methodName, double.class);
            } else {
                callMethod = obj.getClass().getMethod(methodName, double.class, int.class);
            }

            try {
                if (flag == 1) {
                    result = (double) callMethod.invoke(obj, a, b);
                } else if (flag == 2) {
                    result = (double) callMethod.invoke(obj, a);
                } else {
                    result = (double) callMethod.invoke(obj, a, power);
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        memoryLast = result;

        return result;
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
