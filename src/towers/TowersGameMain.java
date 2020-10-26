package towers;

import java.util.Scanner;

public class TowersGameMain {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        TowersGame game = new TowersGame();

        String consoleString = "";

        boolean consoleBoolean;

        System.out.println("Игра Ханойская башня");
        System.out.println("Сделать ход: цифра->цифра, например, 1->3");

//        game.setRules(true);
        game.setSleepMillis(0);

        do {
            consoleBoolean = false;
            System.out.print("Введите режим игры: 1 - ручной, 2 - автоматический: ");
            if (scanner.hasNext()) {
                try {
                    consoleBoolean = game.setGameType(Integer.parseInt(scanner.nextLine()));
                } catch (NumberFormatException ignored) {}
            }
            if (!consoleBoolean) {
                System.out.println("Ошибка ввода!");
            }
        } while (!consoleBoolean);

//        if (game.getGameType() == 1) {
//            do {
//                consoleBoolean = false;
//                System.out.print("Введите: 1 - новая игра, 2 - загрузить игру, 3 - ход игры: ");
//                if (scanner.hasNext()) {
//                    try {
//                        consoleBoolean = game.setManualGameType(Integer.parseInt(scanner.nextLine()));
//                    } catch (NumberFormatException ignored) {}
//                }
//                if (!consoleBoolean) {
//                    System.out.println("Ошибка ввода!");
//                }
//            } while (!consoleBoolean);
//        }

        do {
            consoleBoolean = false;
            System.out.print("Введите количество башен от "
                    + game.getMinTowersCount() + " до " + game.getMaxTowersCount() + ": ");
            if (scanner.hasNext()) {
                try {
                    consoleBoolean = game.setTowersCount(Integer.parseInt(scanner.nextLine()));
                } catch (NumberFormatException ignored) {}
            }
            if (!consoleBoolean) {
                System.out.println("Ошибка ввода!");
            }
        } while (!consoleBoolean);

        do {
            consoleBoolean = false;
            System.out.print("Введите количество колец от "
                    + game.getMinCirclesCount() + " до " + game.getMaxCirclesCount() + ": ");
            if (scanner.hasNext()) {
                try {
                    consoleBoolean = game.setCirclesCount(Integer.parseInt(scanner.nextLine()));
                } catch (NumberFormatException ignored) {}
            }
            if (!consoleBoolean) {
                System.out.println("Ошибка ввода!");
            }
        } while (!consoleBoolean);

        game.start();
        game.printField();

        do {
            System.out.print("Сделайте ход: ");

            if (game.getGameType() == 1) {
                if (scanner.hasNext()) {
                    try {
                        consoleString = scanner.nextLine();
                    } catch (NumberFormatException ignored) {
                    }
                }

                if (game.step(consoleString)) {
                    game.printField();
                } else {
                    System.out.println("Ход невозможен!");
                }
            } else {
                boolean isStep = game.step();

                System.out.println(game.getGeneratedStepString());
                if (isStep) {
                    game.printField();
                } else {
                    System.out.println("Ход невозможен!");
                }
            }
        } while (game.isContinue());

        System.out.println("Победа!");
        System.out.println("Количество шагов: " + game.getStepsCount());

        scanner.close();
    }
}
