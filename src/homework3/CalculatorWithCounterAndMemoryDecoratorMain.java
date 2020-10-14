package homework3;

import java.lang.reflect.InvocationTargetException;

/**
 * Класс CalculatorWithCounterAndMemoryDecoratorMain выполняет Задание 8.5 Урока 3
 * (вывод на консоль арифметического выражения с помощью декораторов)
 */
public class CalculatorWithCounterAndMemoryDecoratorMain {
    public static void main(String[] args) {
        ICalculator calc =
                new CalculatorWithCounterDecorator(
                        new CalculatorWithMemoryDecorator(new CalculatorWithMathExtends()));

        System.out.println("4.1 + 15 * 7 + (28 / 5) ^ 2 = "
                + calc.sum(calc.sum(4.1, calc.mult(15, 7)),
                calc.pow(calc.div(28, 5), 2)));

        if (calc instanceof CalculatorWithCounterDecorator) {
            CalculatorWithCounterDecorator calcCounter = (CalculatorWithCounterDecorator) calc;

            System.out.println("Количество арифметических операций: "
                    + calcCounter.getCountOperation());

            if (calcCounter.getCalculator() instanceof CalculatorWithMemoryDecorator) {
                CalculatorWithMemoryDecorator calcMemory =
                        (CalculatorWithMemoryDecorator) calcCounter.getCalculator();

                calcMemory.saveToMemory();
                System.out.println("Значение из памяти: " + calcMemory.getFromMemory());
            }
        }
    }
}
