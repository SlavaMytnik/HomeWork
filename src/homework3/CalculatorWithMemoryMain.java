package homework3;

/**
 * Класс CalculatorWithMemoryMain выполняет Задание 7.6 Урока 3
 * (вывод на консоль арифметического выражения
 * с помощью экземпляра класса CalculatorWithMemory)
 */
public class CalculatorWithMemoryMain {
    public static void main(String[] args) {
        CalculatorWithMemory calc =
                new CalculatorWithMemory(new CalculatorWithMathExtends());

        System.out.println("4.1 + 15 * 7 + (28 / 5) ^ 2 = "
                + calc.sum(calc.sum(4.1, calc.mult(15, 7)),
                calc.pow(calc.div(28, 5), 2)));

        calc.saveToMemory();

        System.out.println("Значение из памяти: "
                + calc.getFromMemory());

        // Пример для теста
        calc.sum(1.0, 2.0);

        // Результат отсутствует, т.к. память очищена при получении ее значения
        System.out.println("Значение из памяти: "
                + calc.getFromMemory());

        // Перезаписываем в память результат последней арифметической операции
        calc.saveToMemory();

        // Повторно выполняем операцию без сохранения в памяти
        calc.sum(5.0, 10.0);

        // 3.0
        System.out.println("Значение из памяти: "
                + calc.getFromMemory());
    }
}
