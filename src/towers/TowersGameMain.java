package towers;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * Класс TowersGameMain - игра Ханойские башни
 */
public class TowersGameMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TowersGame game = new TowersGame();

        String consoleText = "";
        String gameName = "";

        boolean isCorrect;
        boolean isNewSteps = false;
        boolean isExit = false;

        // Установка слова для выхода из ручной игры
        game.setExitText("EXIT");

        // Установка знания правил автоматической игрой
        game.setRules(true);

        // Установка длительности обдумывания решения автоматической игрой
        game.setSleepMillis(0);

        System.out.println("Игра Ханойская башня");
        System.out.println("Сделать ход: цифра->цифра (например, 1->3) или введите "
                + game.getExitText());

        // Запрос режима игры
        do {
            isCorrect = false;
            System.out.print("Введите режим игры: 1 - ручной, 2 - автоматический: ");
            if (scanner.hasNext()) {
                try {
                    isCorrect = game.setGameType(Integer.parseInt(scanner.nextLine()));
                } catch (NumberFormatException ignored) {}
            }
            if (!isCorrect) {
                System.out.println("Ошибка ввода!");
            }
        } while (!isCorrect);

        // Запрос режима ручной игры
        if (game.getGameType() == 1) {
            do {
                isCorrect = false;
                System.out.print("Введите: 1 - новая игра, 2 - загрузить игру: ");
                if (scanner.hasNext()) {
                    try {
                        isCorrect = game.setManualGameType(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException ignored) {}
                }
                if (!isCorrect) {
                    System.out.println("Ошибка ввода!");
                }
            } while (!isCorrect);
        }

        if ((game.getGameType() == 1 && game.getManualGameType() == 1)
                || game.getGameType() == 2) {

            // Запрос количества башен
            do {
                isCorrect = false;
                System.out.print("Введите количество башен от "
                        + game.getMinTowersCount() + " до " + game.getMaxTowersCount() + ": ");
                if (scanner.hasNext()) {
                    try {
                        isCorrect = game.setTowersCount(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException ignored) {
                    }
                }
                if (!isCorrect) {
                    System.out.println("Ошибка ввода!");
                }
            } while (!isCorrect);

            // Запрос количества колец
            do {
                isCorrect = false;
                System.out.print("Введите количество колец от "
                        + game.getMinCirclesCount() + " до " + game.getMaxCirclesCount() + ": ");
                if (scanner.hasNext()) {
                    try {
                        isCorrect = game.setCirclesCount(Integer.parseInt(scanner.nextLine()));
                    } catch (NumberFormatException ignored) {
                    }
                }
                if (!isCorrect) {
                    System.out.println("Ошибка ввода!");
                }
            } while (!isCorrect);

            game.start();
            game.printField();
        }

        if (game.getGameType() == 1 && game.getManualGameType() == 2) {
            Map<String, Integer> gameNames = game.getGameNames();

            // Вывод списка сохраненных игр
            if (gameNames != null && !gameNames.isEmpty()) {
                System.out.println("Сохраненные игры (\u001B[31mзавершенные\u001B[0m, \u001B[32mнезавершенные\u001B[0m): ");
                for (String name : gameNames.keySet()) {
                    if (gameNames.get(name) == 1) {
                        System.out.println("\u001B[32m" + name + "\u001B[0m");
                    } else {
                        System.out.println("\u001B[31m" + name + "\u001B[0m");
                    }
                }

                // Запрос названия игры
                do {
                    isCorrect = false;
                    System.out.print("Введите название игры: ");
                    if (scanner.hasNext()) {
                        try {
                            gameName = scanner.nextLine();
                            isCorrect = game.checkGameName(gameName);
                            if (isCorrect) {
                                if (!gameNames.containsKey(gameName)) {
                                    isCorrect = false;
                                }
                            }
                        } catch (NumberFormatException ignored) {
                        }
                    }
                    if (!isCorrect) {
                        System.out.println("Ошибка ввода!");
                    }
                } while (!isCorrect);

                // Запрос хода из файла
                if ((game = game.load(gameName)) != null) {
                    List<String> loadedSteps = game.getSavedSteps();

                    game.start();
                    game.printField();

                    for (String s : loadedSteps) {
                        System.out.print("Сделайте ход: ");
                        System.out.println(s);

                        isCorrect = game.step(s);

                        if (isCorrect) {
                            game.printField();
                        } else {
                            System.out.println("Ход невозможен!");
                        }
                    }
                } else {
                    System.out.println("Ошибка чтения файла!");
                }
            } else {
                isExit = true;
                System.out.println("Сохраненные игры отсутствуют!");
            }
        }

        // Запрос хода
        if (!isExit) {
            while (game != null && game.isContinue()) {
                isNewSteps = true;
                System.out.print("Сделайте ход: ");

                if (game.getGameType() == 1) {
                    if (scanner.hasNext()) {
                        try {
                            consoleText = scanner.nextLine();
                        } catch (NumberFormatException ignored) {
                        }
                    }

                    isCorrect = game.step(consoleText);
                } else {
                    isCorrect = game.step();
                    System.out.println(game.getGeneratedStep());
                }

                if (!game.getIsExit()) {
                    if (isCorrect) {
                        game.printField();
                    } else {
                        System.out.println("Ход невозможен!");
                    }
                }
            }

            if (game != null && !game.getIsExit()) {
                System.out.println("Победа!");
                System.out.println("Количество шагов: " + game.getStepsCount());
            }

            if (isNewSteps) {
                int console = 0;

                // Запрос сохранения игры
                do {
                    isCorrect = false;
                    System.out.print("Сохранить игру: 1 - да, 2 - нет: ");
                    if (scanner.hasNext()) {
                        try {
                            console = Integer.parseInt(scanner.nextLine());

                            if (console == 1 || console == 2) {
                                isCorrect = true;
                            }
                        } catch (NumberFormatException ignored) {
                        }
                    }
                    if (!isCorrect) {
                        System.out.println("Ошибка ввода!");
                    }
                } while (!isCorrect);

                // Запрос названия игры
                if (console == 1) {
                    do {
                        isCorrect = false;
                        System.out.print("Введите название игры: ");
                        if (scanner.hasNext()) {
                            try {
                                gameName = scanner.nextLine();
                                isCorrect = game.checkGameName(gameName);
                            } catch (NumberFormatException ignored) {
                            }
                        }
                        if (!isCorrect) {
                            System.out.println("Ошибка ввода!");
                        }
                    } while (!isCorrect);

                    if (game.save(gameName)) {
                        System.out.println("Игра сохранена!");
                    } else {
                        System.out.println("Ошибка записи файла!");
                    }
                }
            }
        }

        scanner.close();
    }
}
