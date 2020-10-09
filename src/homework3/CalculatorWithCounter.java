package homework3;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Класс CalculatorWithCounter выполняет Задания 5.1 - 5.5 Урока 3
 * (подсчет количества использования калькулятора)
 */
public class CalculatorWithCounter {
    private Object obj;
    private int countOperation = 0;

    /**
     * Метод CalculatorWithCounter является конструктором класса
     * @param obj принимает объект типа CalculatorWithOperator
     */
    public CalculatorWithCounter(CalculatorWithOperator obj) {
        this.obj = obj;
    }

    /**
     * Метод CalculatorWithCounter является конструктором класса
     * @param obj принимает объект типа CalculatorWithMathCopy
     */
    public CalculatorWithCounter(CalculatorWithMathCopy obj) {
        this.obj = obj;
    }

    /**
     * Метод CalculatorWithCounter является конструктором класса
     * @param obj принимает объект типа CalculatorWithMathExtends
     */
    public CalculatorWithCounter(CalculatorWithMathExtends obj) {
        this.obj = obj;
    }

    /**
     * Метод sum вычисляет сумму двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает сумму двух чисел
     */
    public double sum(double a, double b) {
        double result = 0;

        try {
            Method callMethod = obj.getClass().getMethod("sum", double.class, double.class);
            try {
                result = (double) callMethod.invoke(obj, a, b);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        countOperation++;

        return result;
    }

    /**
     * Метод diff вычисляет разность двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает разность двух чисел
     */
    public double diff(double a, double b) {
        double result = 0;

        try {
            Method callMethod = obj.getClass().getMethod("diff", double.class, double.class);
            try {
                result = (double) callMethod.invoke(obj, a, b);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        countOperation++;

        return result;
    }

    /**
     * Метод div вычисляет результат деления двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат деления двух чисел
     */
    public double div(double a, double b) {
        double result = 0;

        try {
            Method callMethod = obj.getClass().getMethod("div", double.class, double.class);
            try {
                result = (double) callMethod.invoke(obj, a, b);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        countOperation++;

        return result;
    }

    /**
     * Метод mult вычисляет результат умножения двух чисел
     * @param a - первое число
     * @param b - второе число
     * @return возвращает результат умножения двух чисел
     */
    public double mult(double a, double b) {
        double result = 0;

        try {
            Method callMethod = obj.getClass().getMethod("mult", double.class, double.class);
            try {
                result = (double) callMethod.invoke(obj, a, b);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        countOperation++;

        return result;
    }

    /**
     * Метод abs вычисляет модуль числа
     * @param a - число
     * @return возвращает модуль числа
     */
    public double abs(double a) {
        double result = 0;

        try {
            Method callMethod = obj.getClass().getMethod("abs", double.class);
            try {
                result = (double) callMethod.invoke(obj, a);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        countOperation++;

        return result;
    }

    /**
     * Метод sqrt вычисляет корень числа
     * @param a - число
     * @return возвращает корень числа
     */
    public double sqrt(double a) {
        double result = 0;

        try {
            Method callMethod = obj.getClass().getMethod("sqrt", double.class);
            try {
                result = (double) callMethod.invoke(obj, a);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        countOperation++;

        return result;
    }

    /**
     * Метод pow вычисляет результат возведения числа в степень
     * @param a - дробное положительное число
     * @param b - целочисленная степень
     * @return возвращает модуль числа
     */
    public double pow(double a, int b) {
        double result = 0;

        try {
            Method callMethod = obj.getClass().getMethod("pow", double.class, int.class);
            try {
                result = (double) callMethod.invoke(obj, a, b);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }

        countOperation++;

        return result;
    }

    /**
     * Метод getCountOperation возвращает количество арифметических операций
     */
    public int getCountOperation() {
        return countOperation;
    }
}
