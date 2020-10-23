package towers;

public class TowersGameMain {
    public static void main(String[] args) {
        System.out.println("Игра Ханойская башня");
        System.out.println("Сделать ход: цифра->цифра (например, 1->3)");

        TowersGame game = new TowersGame();

        game.setGameType("Введите режим игры (1 - ручной, 2 - автоматический): ",
                "Ошибка ввода!");
        game.setTowersCount("Введите количество башен от 3 до 8: ",
                "Ошибка ввода!");
        game.setCirclesCount("Введите количество колец от 3 до 20: ",
                "Ошибка ввода!");

        game.setSleepMillis(0);
        game.start();
        game.printField();

        do {
            game.step("Сделайте ход: ", "Ход невозможен!");
            game.printField();
        } while (game.isContinue());

        System.out.println("Победа!");
    }
}
