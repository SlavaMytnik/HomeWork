package homework3;

/**
 * Класс CalculatorWithMathCopyMain выполняет Задание 3.3 Урока 3
 * (вывод на консоль арифметического выражения
 * с помощью экземпляра класса CalculatorWithMathCopy)
 */
public class CalculatorWithMathCopyMain {
    public static void main(String[] args) {
        double result;

        CalculatorWithMathCopy calc = new CalculatorWithMathCopy();

        // 4.1 + 15 * 7 + (28 / 5) ^ 2
        result = calc.sum(calc.sum(4.1, calc.mult(15, 7)),
                calc.pow(calc.div(28, 5), 2));

        System.out.println("4.1 + 15 * 7 + (28 / 5) ^ 2 = " + result);
    }
}
