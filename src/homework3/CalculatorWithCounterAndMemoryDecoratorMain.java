package homework3;

import java.lang.reflect.InvocationTargetException;

/**
 * Класс CalculatorWithCounterAndMemoryDecoratorMain выполняет Задание 8.5 Урока 3
 * (вывод на консоль арифметического выражения с помощью декораторов)
 */
public class CalculatorWithCounterAndMemoryDecoratorMain {
    public static void main(String[] args) {
        double result;

        ICalculator calc =
                new CalculatorWithCounterDecorator(
                        new CalculatorWithMemoryDecorator(new CalculatorWithMathExtends()));

        // 4.1 + 15 * 7 + (28 / 5) ^ 2
        result = calc.sum(calc.sum(4.1, calc.mult(15, 7)),
                calc.pow(calc.div(28, 5), 2));

        System.out.println("4.1 + 15 * 7 + (28 / 5) ^ 2 = " + result);

        try {
            System.out.println("Количество арифметических операций: " +
                    calc.getClass().getMethod("getCountOperation").invoke(calc));

            calc.getClass().getMethod("getCalculator").invoke(calc)
                    .getClass().getMethod("saveToMemory")
                    .invoke(calc.getClass().getMethod("getCalculator").invoke(calc));

            System.out.println("Значение из памяти: " +
                    calc.getClass().getMethod("getCalculator").invoke(calc)
                            .getClass().getMethod("getFromMemory")
                            .invoke(calc.getClass().getMethod("getCalculator").invoke(calc)));
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }
}
