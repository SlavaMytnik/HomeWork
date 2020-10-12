package homework3;

/**
 * Класс CalculatorWithCounterMain выполняет Задание 5.6 Урока 3
 * (вывод на консоль арифметического выражения
 * с помощью экземпляра класса CalculatorWithCounter)
 */
public class CalculatorWithCounterMain {
    public static void main(String[] args) {
        double result;

        CalculatorWithCounter calc =
                new CalculatorWithCounter(new CalculatorWithMathExtends());

        // 4.1 + 15 * 7 + (28 / 5) ^ 2
        result = calc.sum(calc.sum(4.1, calc.mult(15, 7)),
                calc.pow(calc.div(28, 5), 2));

        System.out.println("4.1 + 15 * 7 + (28 / 5) ^ 2 = " + result);
        System.out.println("Количество арифметических операций: "
                + calc.getCountOperation());
    }
}
