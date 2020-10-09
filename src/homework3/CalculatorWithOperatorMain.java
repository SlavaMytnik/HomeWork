package homework3;

/**
 * Класс CalculatorWithOperatorMain выполняет Задание 2.5 Урока 3
 * (вывод на консоль арифметического выражения
 * с помощью экземпляра класса CalculatorWithOperator)
 */
public class CalculatorWithOperatorMain {
    public static void main(String[] args) {
        double result;

        CalculatorWithOperator calc = new CalculatorWithOperator();

        // 4.1 + 15 * 7 + (28 / 5) ^ 2
        result = calc.sum(calc.sum(4.1, calc.mult(15, 7)),
                calc.pow(calc.div(28, 5), 2));

        System.out.println("4.1 + 15 * 7 + (28 / 5) ^ 2 = " + result);
    }
}
