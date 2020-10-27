package towers;

import java.util.List;
import java.util.Scanner;

public class TowersGameMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TowersGame game = new TowersGame();

        String consoleText = "";
        String gameName = "";

        boolean isCorrect;
        boolean isNewSteps = false;

        game.setExitText("EXIT");
        game.setRules(true);
        game.setSleepMillis(0);

        System.out.println("Игра Ханойская башня");
        System.out.println("Сделать ход: цифра->цифра (например, 1->3) или введите "
                + game.getExitText());

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
            List<String> gameNames = game.getGameNames();

            if (gameNames != null) {
                System.out.println("Существующие игры: ");
                for (String name : gameNames) {
                    System.out.println("\u001B[34m" + name + "\u001B[0m");
                }
            }

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

            if ((game = game.load(gameName)) != null && game != null) {
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
        }

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

        scanner.close();
    }
}
