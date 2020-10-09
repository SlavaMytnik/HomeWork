package homework3;

/**
 * Класс CalculatorWithCounterInterfaceMain выполняет Задание 6.3 Урока 3
 * (вывод на консоль арифметического выражения
 * с помощью экземпляра класса CalculatorWithCounterInterface)
 */
public class CalculatorWithCounterInterfaceMain {
    public static void main(String[] args) {
        double result;

        CalculatorWithCounterInterface calc =
                new CalculatorWithCounterInterface(new CalculatorWithMathExtends());

        // 4.1 + 15 * 7 + (28 / 5) ^ 2
        result = calc.sum(calc.sum(4.1, calc.mult(15, 7)),
                calc.pow(calc.div(28, 5), 2));

        System.out.println("4.1 + 15 * 7 + (28 / 5) ^ 2 = " + result);
        System.out.println("Количество арифметических операций: " +
                calc.getCountOperation());
    }
}
