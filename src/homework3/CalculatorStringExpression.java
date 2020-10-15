package homework3;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс CalculatorStringExpression выполняет Задание 9 Урока 3
 * (калькулятор арифметического выражения, введенного с консоли)
 */
public class CalculatorStringExpression {
    private static final String ERROR_TEXT = "Ошибочный формат данных!";

    public static void main(String[] args) {
        boolean isError;

        String enteredText;

        Scanner in = new Scanner(System.in);

        System.out.println("Допустимые символы: (, ), |, ^, *, /, +, -");
        System.out.println("Допустимые константы: PI, E");
        System.out.println("Арифметическое выражение можно вводить с пробелами и без");

        while (true) {
            System.out.print("Введите арифметическое выражение: ");
            enteredText = in.nextLine()
                    .replaceAll("PI", "@" + Math.PI + "@")
                    .replaceAll("E", "@" + Math.E + "@")
                    .replaceAll("\\ +", "\\ ");
            isError = false;

            // Проверка на ошибки во введенном выражении
            if (enteredText.length() == 0
                    || enteredText.replaceAll("[-0-9\\.\\^\\(\\)\\|\\*\\/\\+\\ @]", "").length() > 0
                    || enteredText.replaceAll("[0-9\\.]\\ +[0-9\\.]", "").length() < enteredText.length()) {
                isError = true;
            } else {
                enteredText = enteredText.replaceAll("\\ ", "");

                if (enteredText.replaceAll("[0-9\\.]\\@+[0-9\\.]", "").length() < enteredText.length()) {
                    isError = true;
                } else {
                    enteredText = enteredText.replaceAll("@", "");

                    if (enteredText.replaceAll("(\\-\\-)|(\\-\\+)|(\\-\\*)|(\\-\\/)|(\\-\\^)|(\\-\\))", "").length() < enteredText.length()
                            || enteredText.replaceAll("(\\+\\+)|(\\+\\*)|(\\+\\/)|(\\+\\^)|(\\+\\))|(\\+\\-)", "").length() < enteredText.length()
                            || enteredText.replaceAll("(\\*\\+)|(\\*\\*)|(\\*\\/)|(\\*\\^)|(\\*\\))|(\\*\\-)", "").length() < enteredText.length()
                            || enteredText.replaceAll("(\\/\\+)|(\\/\\*)|(\\/\\/)|(\\/\\^)|(\\/\\))|(\\/\\-)", "").length() < enteredText.length()
                            || enteredText.replaceAll("(\\^\\+)|(\\^\\*)|(\\^\\/)|(\\^\\^)|(\\^\\))|(\\^\\-)", "").length() < enteredText.length()
                            || enteredText.replaceAll("(\\(\\+)|(\\(\\*)|(\\(\\/)|(\\(\\^)|(\\(\\))", "").length() < enteredText.length()
                            || enteredText.replaceAll("(^\\+)|(^\\*)|(^\\/)|(^\\))|(^\\+)|(^\\^)", "").length() < enteredText.length()
                            || enteredText.replaceAll("(\\+$)|(\\-$)|(\\*$)|(\\/$)|(\\^$)|(\\($)", "").length() < enteredText.length()) {
                        isError = true;
                    }
                }
            }

            if (!isError) {
                System.out.println(goCalculate(enteredText));
            } else {
                System.out.println(ERROR_TEXT);
            }
        }
    }

    /**
     * Метод goCalculate вычисляет значение выражения
     * @param receivedText принимает арифметическое выражение
     * @return возвращает результат вычисления
     */
    public static String goCalculate(String receivedText) {
        boolean goAgain;

        Pattern pattern;

        Matcher matcher;

        String modifiedText = receivedText;

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
                    double powerText = Double.parseDouble(goCalculate(matcher.group()
                            .substring(1, matcher.group().length() - 1)));

                    if (powerText > 0) {
                        modifiedText = modifiedText
                                .replaceAll(symbolsReplacer.toString(),
                                        "" + powerText);
                    } else {
                        modifiedText = modifiedText
                                .replaceAll(symbolsReplacer.toString(),
                                        "@" + powerText + "@");

                        // Возведение в степень значения после раскрытия скобок, если оно отрицательное
                        boolean goAgainPower = true;
                        Pattern patternPower = Pattern.compile("@(-+[0-9]+\\.?([0-9]+)?)@\\^(-?[0-9]+\\.?([0-9]+)?)");

                        while (goAgainPower) {
                            matcher = patternPower.matcher(modifiedText);
                            goAgainPower = false;
                            modifiedText = modifiedText.replaceAll("@", "");

                            if (matcher.find()) {
                                goAgainPower = true;
                                modifiedText = modifiedText
                                        .replaceAll(matcher.group(1) + "\\^" + matcher.group(3),
                                                "" + Math.pow(Double.parseDouble(matcher.group(1)),
                                                        Double.parseDouble(matcher.group(3))));
                                modifiedText = replacePlusMinus(modifiedText);
                            }
                        }
                    }
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

        // Возведение в степень отрицательных чисел
        goAgain = true;
        pattern = Pattern.compile("(^|\\*|\\/)(-+[0-9]+\\.?([0-9]+)?)\\^(-?[0-9]+\\.?([0-9]+)?)");

        while (goAgain) {
            matcher = pattern.matcher(modifiedText);
            goAgain = false;

            if (matcher.find()) {
                goAgain = true;
                modifiedText = modifiedText
                        .replaceAll(matcher.group(2) + "\\^" + matcher.group(4),
                                "" + Math.pow(Double.parseDouble(matcher.group(2)),
                                        Double.parseDouble(matcher.group(4))));
                modifiedText = replacePlusMinus(modifiedText);
            }
        }

        // Возведение в степень положительных чисел
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

        // Окончательная проверка на ошибку
        try {
            Double.parseDouble(modifiedText);
        } catch (NumberFormatException e) {
            return ERROR_TEXT;
        }

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
