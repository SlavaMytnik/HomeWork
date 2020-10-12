package homework3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс CalculatorStringExpression выполняет Задание 9 Урока 3
 * (калькулятор арифметического выражения, введенного с консоли)
 */
public class CalculatorStringExpression {
    public static void main(String[] args) {
        String text;

        Scanner in = new Scanner(System.in);

        System.out.println("Допустимые символы: (, ), |, ^, *, /, +, -");
        System.out.println("Допустимые константы: PI, E");
        System.out.println("Арифметическое выражение можно вводить с пробелами или без");

        while (true) {
            System.out.print("Введите арифметическое выражение: ");
            text = in.nextLine();

            System.out.println(goCalculate(text));
        }
    }

    /**
     * Метод goCalculate вычисляет значение выражения
     * @param receivedText принимает арифметическое выражение
     * @return возвращает результат вычисления
     */
    public static String goCalculate(String receivedText) {
        boolean goAgain;

        // Замена констант и удаление пробелов
        String modifiedText = receivedText
                .replaceAll("PI", "" + Math.PI)
                .replaceAll("E", "" + Math.E)
                .replaceAll("\\ ", "");

        Pattern pattern;

        Matcher matcher;

        // Раскрытие скобок или модуля
        goAgain = true;
        pattern = Pattern
                .compile("(\\([-?0-9\\.\\+\\*\\/\\^]+\\))|(\\|[-?0-9\\.\\+\\*\\/\\^]+\\|)");

        while (goAgain) {
            matcher = pattern.matcher(modifiedText);
            goAgain = false;

            if (matcher.find()) {
                goAgain = true;

                // Формирование заменяемого выражения без специальных символов
                StringBuilder symbolsReplacer = new StringBuilder();
                for (int i = 0; i < matcher.group().length(); i++) {
                    if (matcher.group().substring(i, i + 1).equals("(")
                            || matcher.group().substring(i, i + 1).equals(")")
                            || matcher.group().substring(i, i + 1).equals("|")
                            || matcher.group().substring(i, i + 1).equals("+")
                            || matcher.group().substring(i, i + 1).equals("*")
                            || matcher.group().substring(i, i + 1).equals("/")
                            || matcher.group().substring(i, i + 1).equals("^")) {
                        symbolsReplacer.append("\\");
                    }

                    symbolsReplacer.append(matcher.group().substring(i, i + 1));
                }

                if (matcher.group().substring(0, 1).equals("(")) {
                    modifiedText = modifiedText
                            .replaceAll(symbolsReplacer.toString(),
                                    "" + goCalculate(matcher.group()
                                            .substring(1, matcher.group().length() - 1)));
                } else {
                    modifiedText = modifiedText
                            .replaceAll(symbolsReplacer.toString(),
                                    "" + Math.abs(Double.parseDouble(goCalculate(matcher.group()
                                            .substring(1, matcher.group().length() - 1)))));
                }

                // Удаление последовательности символов после раскрытия скобок
                modifiedText = replacePlusMinus(modifiedText);
            }
        }

        // Возведение в степень
        goAgain = true;
        pattern = Pattern.compile("([0-9]+\\.?([0-9]+)?)\\^(-?[0-9]+\\.?([0-9]+)?)");

        while (goAgain) {
            matcher = pattern.matcher(modifiedText);
            goAgain = false;

            if (matcher.find()) {
                goAgain = true;
                modifiedText = modifiedText
                        .replaceAll(matcher.group(1) + "\\^" + matcher.group(3),
                                "" + Math.pow(Double.parseDouble(matcher.group(1)),
                                        Double.parseDouble(matcher.group(3))));
                modifiedText = replacePlusMinus(modifiedText);
            }
        }

        // Умножение чисел
        goAgain = true;
        pattern = Pattern.compile("([0-9]+\\.?([0-9]+)?)\\*(-?[0-9]+\\.?([0-9]+)?)");

        while (goAgain) {
            matcher = pattern.matcher(modifiedText);
            goAgain = false;

            if (matcher.find()) {
                goAgain = true;
                modifiedText = modifiedText
                        .replaceAll(matcher.group(1) + "\\*" + matcher.group(3),
                                "" + (Double.parseDouble(matcher.group(1)) *
                                        Double.parseDouble(matcher.group(3))));
                modifiedText = replacePlusMinus(modifiedText);
            }
        }

        // Деление чисел
        goAgain = true;
        pattern = Pattern.compile("([0-9]+\\.?([0-9]+)?)\\/(-?[0-9]+\\.?([0-9]+)?)");

        while (goAgain) {
            matcher = pattern.matcher(modifiedText);
            goAgain = false;

            if (matcher.find()) {
                goAgain = true;
                modifiedText = modifiedText
                        .replaceAll(matcher.group(1) + "\\/" + matcher.group(3),
                                "" + (Double.parseDouble(matcher.group(1)) /
                                        Double.parseDouble(matcher.group(3))));
                modifiedText = replacePlusMinus(modifiedText);
            }
        }

        // Сложение и вычитание чисел
        goAgain = true;
        pattern = Pattern.compile("(-?[0-9]+\\.?([0-9]+)?)\\+(-?[0-9]+\\.?([0-9]+)?)");

        while (goAgain) {

            // Замена операции вычитания на сложение с минусом
            modifiedText = modifiedText.replaceAll("-", "\\+-");
            matcher = pattern.matcher(modifiedText);
            goAgain = false;

            if (matcher.find()) {
                goAgain = true;
                modifiedText = modifiedText
                        .replaceAll(matcher.group(1) + "\\+" + matcher.group(3),
                                "" + (Double.parseDouble(matcher.group(1)) +
                                        Double.parseDouble(matcher.group(3))));
                modifiedText = replacePlusMinus(modifiedText);
            }
        }

        modifiedText = replacePlusMinus(modifiedText);

        return modifiedText;
    }

    /**
     * Метод replacePlusMinus заменяет последовательность арифметических символов
     * @param plusMinusText принимает арифметическое выражение
     * @return возвращает результат после замены
     */
    public static String replacePlusMinus(String plusMinusText) {
        String receivedPlusMinusText = plusMinusText;

        receivedPlusMinusText = receivedPlusMinusText.replaceAll("--", "\\+");
        receivedPlusMinusText = receivedPlusMinusText.replaceAll("\\+-", "-");
        receivedPlusMinusText = receivedPlusMinusText.replaceAll("-\\+", "-");

        if (receivedPlusMinusText.substring(0, 1).equals("+")) {
            receivedPlusMinusText = receivedPlusMinusText.substring(1);
        }

        return receivedPlusMinusText;
    }
}
