package homework1.task1;

/**
 * Класс Task1Variant2 выполняет Задания 1.2, 1.3, 1.5 Урока 1
 * (побитовые операции)
 *
 * Класс Task1Variant2 представляет собой Вариант 2 решения Заданий
 * (с выводом двоичных чисел и побитовых операций с ними на печать)
 */
public class Task1Variant2 {
    public static int STRING_SIZE = 20;

    public static void main(String[] args) {

        // Задание 1.2 (побитовые операции с числами 42 и 15)
        getResult(42, 15);
        getResult(15, 42);

        // Задание 1.3 (побитовые операции с числами -42 и -15)
        getResult(-42, -15);
        getResult(-15, -42);

        /* Задание 1.5 (побитовая операция с числом 42.5)
        Попытка осуществления побитовой операции
        (например, System.out.println(~42.5);)
        с вещественным числом приводит к появлению ошибки:
        Operator '~' cannot be applied to 'double' */

        // Побитовая операция с числом 42.5 в формате IEEE 754
        double d1 = 42.5;
        long l1 = Double.doubleToLongBits(d1);
        System.out.println(String.format("%" + STRING_SIZE + "s",
                "Побитовая операция: ") + "~" + d1);
        System.out.println(String.format("%" + STRING_SIZE + "s",
                "Двоичное число: ") + "0" + Long.toBinaryString(l1));
        System.out.println(String.format("%" + STRING_SIZE + "s",
                "Результат: ") + Long.toBinaryString(~l1) + " (" +
                Double.longBitsToDouble(~l1) + ")");
    }

    /**
     * Метод getResult осуществляет вычисление результата побитовых операций
     */
    public static void getResult(int a, int b) {
        printResult("&", a, b, a & b, 0);
        printResult("|", a, b, a | b, 0);
        printResult("^", a, b, a ^ b, 0);
        printResult("~", a, 0, ~a, 1);
        printResult("<<", a, b, a << b, 2);
        printResult(">>", a, b, a >> b, 2);
        printResult(">>>", a, b, a >>> b, 2);
    }

    /**
     * Метод printResult осуществляет печать результата побитовых операций
     */
    public static void printResult(String text, int leftOperand, int rightOperand,
                                   int result, int tag) {
        if (tag == 1) {
            System.out.println(String.format("%" + STRING_SIZE + "s",
                    "Побитовая операция: ") + text + leftOperand);
        } else {
            System.out.println(String.format("%" + STRING_SIZE + "s",
                    "Побитовая операция: ") + leftOperand + " " + text + " " +
                    rightOperand);
        }

        if (tag == 0) {
            System.out.println(String.format("%" + STRING_SIZE + "s",
                    "Левый операнд: ") + getBinaryString(leftOperand));
            System.out.println(String.format("%" + STRING_SIZE + "s",
                    "Действие: ") + text);
            System.out.println(String.format("%" + STRING_SIZE + "s",
                    "Правый операнд: ") + getBinaryString(rightOperand));
        } else {
            System.out.println(String.format("%" + STRING_SIZE + "s",
                    "Двоичное число: ") + getBinaryString(leftOperand));
        }

        System.out.println(String.format("%" + STRING_SIZE + "s",
                "Результат: ") + getBinaryString(result) + " (" + result + ")\n");
    }

    /**
     * Метод getBinaryString осуществляет преобразование
     * значения целого числа в текст в виде двоичного числа
     */
    public static String getBinaryString(int value) {
        return String.format("%32s",
                Integer.toBinaryString(value)).replace(' ', '0');
    }
}
