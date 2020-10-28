package towers;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс TowersGame - класс игры Ханойские башни
 */
public class TowersGame implements Serializable {
    public static final String FILE_PATH = "saved_towers_games";
    public static final String FILE_TYPE = ".tg1";
    public static final String FILE_TYPE_FINISHED = ".tg0";

    private boolean isRules = false;
    private boolean isExit = false;

    private int gameType = 1;
    private int manualGameType = 1;
    private int[][] circles = null;
    private int sleepMillis = 0;
    private int stepsCount = 0;

    private int minTowersCount = 3;
    private int maxTowersCount = 8;
    private int towersCount = 3;

    private int minCirclesCount = 3;
    private int maxCirclesCount = 20;
    private int circlesCount = 3;

    private String generatedStep = "";
    private String exitText = "EXIT";

    private List<String> savedSteps;

    /**
     * Конструктор экземпляра класса базовый
     */
    public TowersGame() {
    }

    /**
     * Конструктор экземпляра класса с параметрами
     */
    public TowersGame(int gameType, int manualGameType, int towersCount, int circlesCount) {
        setGameType(gameType);
        setManualGameType(manualGameType);
        setTowersCount(towersCount);
        setCirclesCount(circlesCount);
    }

    /**
     * Метод setRules устанавливает известность
     * правил игры для автоматического режима
     * @param isRules: true - правила известны, false - правила не известны
     */
    public void setRules(boolean isRules) {
        this.isRules = isRules;
    }
    /**
     * Метод setIsExit устанавливает необходимость выхода из игры
     * @param isExit: true - выход требуется, false - выход не требуется
     */
    public void setIsExit(boolean isExit) {
        this.isExit = isExit;
    }

    /**
     * Метод setGameType устанавливает тип игры
     * @param gameType - тип игры: 1 - ручная, 2 - автоматическая
     * @return возвращает результат установки типа игры:
     * true - установка прошла успешно, false - установка прошла не успешно
     */
    public boolean setGameType(int gameType) {
        if (circles == null && (gameType == 1 || gameType == 2)) {
            this.gameType = gameType;
            return true;
        }
        return false;
    }

    /**
     * Метод setManualGameType устанавливает тип ручной игры
     * @param manualGameType - тип ручной игры: 1 - новая игра, 2 - загрузить игру
     * @return возвращает результат установки типа ручной игры:
     * true - установка прошла успешно, false - установка прошла не успешно
     */
    public boolean setManualGameType(int manualGameType) {
        if (circles == null && (manualGameType == 1 || manualGameType == 2)) {
            this.manualGameType = manualGameType;
            return true;
        }
        return false;
    }

    /**
     * Метод setSleepMillis устанавливает длительность
     * обдумывания решения автоматической игрой
     * @param sleepMillis - длительность в миллисекундах
     * @return возвращает результат установки длительности:
     * true - установка прошла успешно, false - установка прошла не успешно
     */
    public boolean setSleepMillis(int sleepMillis) {
        if (sleepMillis >= 0) {
            this.sleepMillis = sleepMillis;
            return true;
        }
        return false;
    }

    /**
     * Метод setMinTowersCount устанавливает минимальное значение количества башен
     * @param minTowersCount - количество башен
     * @return возвращает результат установки количества башен:
     * true - установка прошла успешно, false - установка прошла не успешно
     */
    public boolean setMinTowersCount(int minTowersCount) {
        if (minTowersCount >= 3 && minTowersCount <= maxTowersCount) {
            this.minTowersCount = minTowersCount;
            return true;
        }
        return false;
    }

    /**
     * Метод setMaxTowersCount устанавливает максимальное значение количества башен
     * @param maxTowersCount - количество башен
     * @return возвращает результат установки количества башен:
     * true - установка прошла успешно, false - установка прошла не успешно
     */
    public boolean setMaxTowersCount(int maxTowersCount) {
        if (maxTowersCount <= 8 && minTowersCount <= maxTowersCount) {
            this.maxTowersCount = maxTowersCount;
            return true;
        }
        return false;
    }

    /**
     * Метод setTowersCount устанавливает значение количества башен
     * @param towersCount - количество башен
     * @return возвращает результат установки количества башен:
     * true - установка прошла успешно, false - установка прошла не успешно
     */
    public boolean setTowersCount(int towersCount) {
        if (circles == null && (towersCount >= minCirclesCount && towersCount <= maxTowersCount)) {
            this.towersCount = towersCount;
            return true;
        }
        return false;
    }

    /**
     * Метод setMinCirclesCount устанавливает минимальное значение количества колец
     * @param minCirclesCount - количество колец
     * @return возвращает результат установки количества колец:
     * true - установка прошла успешно, false - установка прошла не успешно
     */
    public boolean setMinCirclesCount(int minCirclesCount) {
        if (minCirclesCount >= 3 && minCirclesCount <= maxCirclesCount) {
            this.minCirclesCount = minCirclesCount;
            return true;
        }
        return false;
    }

    /**
     * Метод setMaxCirclesCount устанавливает максимальное значение количества колец
     * @param maxCirclesCount - количество колец
     * @return возвращает результат установки количества колец:
     * true - установка прошла успешно, false - установка прошла не успешно
     */
    public boolean setMaxCirclesCount(int maxCirclesCount) {
        if (maxCirclesCount <= 20 && minCirclesCount <= maxCirclesCount) {
            this.maxCirclesCount = maxCirclesCount;
            return true;
        }
        return false;
    }

    /**
     * Метод setCirclesCount устанавливает значение количества колец
     * @param circlesCount - количество колец
     * @return возвращает результат установки количества колец:
     * true - установка прошла успешно, false - установка прошла не успешно
     */
    public boolean setCirclesCount(int circlesCount) {
        if (circles == null && (circlesCount >= minCirclesCount && circlesCount <= maxCirclesCount)) {
            this.circlesCount = circlesCount;
            return true;
        }
        return false;
    }

    /**
     * Метод setExitText устанавливает слово для выхода из ручной игры
     * @param exitText - слово
     * @return возвращает результат установки слова:
     * true - установка прошла успешно, false - установка прошла не успешно
     */
    public boolean setExitText(String exitText) {
        if (exitText.matches("[a-zA-Z]+")) {
            this.exitText = exitText;
            return true;
        }

        return false;
    }

    /**
     * Метод getIsRules возвращает известность
     * правил игры для автоматического режима
     * @return возвращает: true - правила известны, false - правила не известны
     */
    public boolean getIsRules() {
        return isRules;
    }

    /**
     * Метод getIsExit возвращает необходимость выхода из игры
     * @return возвращает: true - выход требуется, false - выход не требуется
     */
    public boolean getIsExit() {
        return isExit;
    }

    /**
     * Метод getGameType возвращает тип игры
     * @return возвращает тип игры: 1 - ручная игра, 2 - автоматическая игра
     */
    public int getGameType() {
        return gameType;
    }

    /**
     * Метод getGameType возвращает тип ручной игры
     * @return возвращает тип игры: 1 - новая игра, 2 - загрузить игру
     */
    public int getManualGameType() {
        return manualGameType;
    }

    /**
     * Метод getGameType возвращает длительность
     * обдумывания решения автоматической игрой
     * @return возвращает тип игры: 1 - новая игра, 2 - загрузить игру
     */
    public int getSleepMillis() {
        return sleepMillis;
    }

    /**
     * Метод getStepsCount возвращает количество выполненных шагов игры
     * @return возвращает количество выполненных шагов игры
     */
    public int getStepsCount() {
        return stepsCount;
    }

    /**
     * Метод getMinTowersCount возвращает минимальное количество башен
     * @return возвращает минимальное количество башен
     */
    public int getMinTowersCount() {
        return minTowersCount;
    }

    /**
     * Метод getMaxTowersCount возвращает максимальное количество башен
     * @return возвращает максимальное количество башен
     */
    public int getMaxTowersCount() {
        return maxTowersCount;
    }

    /**
     * Метод getTowersCount возвращает количество башен
     * @return возвращает количество башен
     */
    public int getTowersCount() {
        return towersCount;
    }

    /**
     * Метод getMinCirclesCount возвращает минимальное количество колец
     * @return возвращает минимальное количество колец
     */
    public int getMinCirclesCount() {
        return minCirclesCount;
    }

    /**
     * Метод getMaxCirclesCount возвращает максимальное количество колец
     * @return возвращает максимальное количество колец
     */
    public int getMaxCirclesCount() {
        return maxCirclesCount;
    }

    /**
     * Метод getCirclesCount возвращает количество колец
     * @return возвращает количество колец
     */
    public int getCirclesCount() {
        return circlesCount;
    }

    /**
     * Метод getGeneratedStep возвращает последний сгенерированный шаг
     * @return возвращает последний сгенерированный шаг
     */
    public String getGeneratedStep() {
        return generatedStep;
    }

    /**
     * Метод getExitText возвращает слово для выхода из ручной игры
     * @return возвращает слово для выхода из ручной игры
     */
    public String getExitText() {
        return exitText;
    }

    /**
     * Метод getSavedSteps возвращает сохраненные шаги
     * @return возвращает сохраненные шаги
     */
    public List<String> getSavedSteps() {
        return savedSteps;
    }

    /**
     * Метод start осуществляет первоначальное формирование поля игры
     */
    public void start() {
        if (towersCount > circlesCount) {
            towersCount = circlesCount;
        }

        circles = new int[towersCount][circlesCount];

        for (int i = 0; i < circlesCount; i++) {
            for (int j = 0; j < towersCount; j++) {
                if (j == 0) {
                    circles[0][i] = circlesCount - i - 1;
                } else {
                    circles[j][i] = -1;
                }
            }
        }

        savedSteps = new ArrayList<>();
        stepsCount = 0;
    }

    /**
     * Метод step осуществляет изменение поля игры, т.е. выполняет шаг игры
     * @param fromValue - номер башни, откуда будет передвигаться кольцо
     * @param toValue - номер башни, куда будет передвигаться кольцо
     * @return возвращает: true - шаг выполнен, false - шаг не выполнен
     */
    public boolean step(int fromValue, int toValue) {
        return step(fromValue + "->" + toValue);
    }

    /**
     * Метод step осуществляет изменение поля игры,
     * т.е. выполняет шаг игры, в автоматическом режиме
     * @return возвращает: true - шаг выполнен, false - шаг не выполнен
     */
    public boolean step() {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        do {
            generatedStep = (new Random().nextInt(towersCount) + 1)
                    + "->" + (new Random().nextInt(towersCount) + 1);
        } while (!(!isRules || checkStep(generatedStep)));

        return step(generatedStep);
    }

    /**
     * Метод step осуществляет изменение поля игры, т.е. выполняет шаг игры
     * @param consoleText - шаг игры в текстовом виде
     * @return возвращает: true - шаг выполнен, false - шаг не выполнен
     */
    public boolean step(String consoleText) {
        if (consoleText.equals(exitText)) {
            isExit = true;

            return false;
        }

        savedSteps.add(consoleText);
        stepsCount++;

        if (circles != null && checkStep(consoleText)) {
            int fromTower = -1;
            int toTower = -1;
            int fromCircle = -1;
            int toCircle = -1;
            int fromValue = circlesCount;
            int toValue = circlesCount;

            Pattern pattern = Pattern.compile("([0-9]+)->([0-9]+)");
            Matcher matcher = pattern.matcher(consoleText);

            if (matcher.find()) {
                fromTower = Integer.parseInt(matcher.group(1)) - 1;
                toTower = Integer.parseInt(matcher.group(2)) - 1;
            }

            for (int i = 0; i < circlesCount; i++) {
                if (circles[fromTower][i] > -1 && circles[fromTower][i] < fromValue) {
                    fromValue = circles[fromTower][i];
                    fromCircle = i;
                }
            }

            for (int i = 0; i < circlesCount; i++) {
                if (circles[toTower][i] > -1 && circles[toTower][i] < toValue) {
                    toValue = circles[toTower][i];
                    toCircle = i;
                }
            }

            if (toCircle == -1) {
                toCircle = 0;
            } else {
                toCircle++;
            }

            circles[toTower][toCircle] = fromValue;
            circles[fromTower][fromCircle] = -1;
            return true;
        }
        return false;
    }

    /**
     * Метод checkStep осуществляет проверку хода игры в текстовом виде
     * @param consoleText - ход игры в текстовом виде
     * @return возвращает: true - проверка пройдена, false - проверка не пройдена
     */
    public boolean checkStep(String consoleText) {
        try {
            Pattern pattern = Pattern.compile("([0-9]+)->([0-9]+)");
            Matcher matcher = pattern.matcher(consoleText);

            if (matcher.find() && consoleText.replaceAll("[0-9]+", "").equals("->")
                    && Integer.parseInt(matcher.group(1)) <= towersCount
                    && Integer.parseInt(matcher.group(2)) <= towersCount
                    && Integer.parseInt(matcher.group(1)) != Integer.parseInt(matcher.group(2))) {

                int fromTower = -1;
                int toTower = -1;
                int fromCircle = -1;
                int fromValue = circlesCount;
                int toValue = circlesCount;

                fromTower = Integer.parseInt(matcher.group(1)) - 1;
                toTower = Integer.parseInt(matcher.group(2)) - 1;

                for (int i = 0; i < circlesCount; i++) {
                    if (circles[fromTower][i] > -1 && circles[fromTower][i] < fromValue) {
                        fromValue = circles[fromTower][i];
                        fromCircle = i;
                    }
                }

                for (int i = 0; i < circlesCount; i++) {
                    if (circles[toTower][i] > -1 && circles[toTower][i] < toValue) {
                        toValue = circles[toTower][i];
                    }
                }

                if (fromCircle > -1 && fromValue < toValue) {
                    return true;
                }
            }
        } catch (NumberFormatException ignored) {}
        return false;
    }

    /**
     * Метод isContinue определяет продолжается игра или нет
     * @return возвращает: true - игра продолжается, false - игра закончена
     */
    public boolean isContinue() {
        if (isExit) {
            return false;
        }

        for (int j = 1; j < towersCount; j++) {
            int factorial1 = 0;
            int factorial2 = 0;

            for (int i = 0; i < circlesCount; i++) {
                factorial1 += circles[j][i];
                factorial2 += i;
            }

            if (factorial1 == factorial2) {
                return false;
            }
        }
        return true;
    }

    /**
     * Метод printField печатает поле игры
     */
    public void printField() {
        if (circles != null) {
            for (int i = circlesCount - 1; i >= 0; i--) {
                StringBuilder text = new StringBuilder();

                for (int j = 0; j < towersCount; j++) {
                    text.append(circles[j][i] == -1 ? "*" : (circles[j][i] + 1)).append(" ");
                }

                System.out.println(text.toString());
            }
        }
    }

    /**
     * Метод save сохраняет игру в файл
     * @param gameName - имя игры
     * @return возвращает: true - игра сохранена, false - игра не сохранена
     */
    public boolean save(String gameName) {
        if (!checkGameName(gameName)) {
            return false;
        }

        try {
            String fileType = FILE_TYPE;

            if (!isExit) {
                fileType = FILE_TYPE_FINISHED;
            }

            isExit = false;

            File file = new File(FILE_PATH);

            if (!file.exists()) {
                file.mkdir();
            }

            file = new File(FILE_PATH, gameName + FILE_TYPE);

            if (file.exists()) {
                file.delete();
            }

            file = new File(FILE_PATH, gameName + FILE_TYPE_FINISHED);

            if (file.exists()) {
                file.delete();
            }

            file = new File(FILE_PATH, gameName + fileType);

            if (!file.exists()) {
                file.createNewFile();
            }

            ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(file));
            objectStream.writeObject(this);
            objectStream.close();

            return true;
        } catch (IOException ignored) {}

        return false;
    }

    /**
     * Метод load загружает игру из файла
     * @param gameName - имя игры
     * @return возвращает ссылку на экземпляр игры
     */
    public TowersGame load(String gameName) {
        if (!checkGameName(gameName)) {
            return null;
        }

        try {
            File file = new File(FILE_PATH, gameName + FILE_TYPE_FINISHED);
            ObjectInputStream objectStream = new ObjectInputStream(
                    new FileInputStream(file));
            TowersGame loadedObject = (TowersGame) objectStream.readObject();
            objectStream.close();

            return loadedObject;
        } catch (IOException | ClassNotFoundException ignored) {}

        try {
            File file = new File(FILE_PATH, gameName + FILE_TYPE);
            ObjectInputStream objectStream = new ObjectInputStream(
                    new FileInputStream(file));
            TowersGame loadedObject = (TowersGame) objectStream.readObject();
            objectStream.close();

            return loadedObject;
        } catch (IOException | ClassNotFoundException ignored) {}

        return null;
    }

    /**
     * Метод checkGameName проверяет правильность имени игры
     * @param gameName - имя игры
     * @return возвращает: true - имя верно, false - имя не верно
     */
    public boolean checkGameName(String gameName) {
        if (gameName.matches("[0-9a-zA-Z]+")) {
            return true;
        }

        return false;
    }

    /**
     * Метод getGameNames возвращает имена файлов из директории сохраненных игр
     * @return возвращает имена файлов
     */
    public Map<String, Integer> getGameNames() {
        try {
            Map<String, Integer> filesMap = new TreeMap<>();

            File[] files = new File(FILE_PATH).listFiles();

            for (File file : files) {
                if (file.isFile() && file.toString().endsWith(FILE_TYPE)) {
                    filesMap.put(file.toString()
                            .replaceAll(FILE_PATH + "\\\\", "")
                            .replaceAll("\\" + FILE_TYPE, ""), 1);
                }

                if (file.isFile() && file.toString().endsWith(FILE_TYPE_FINISHED)) {
                    filesMap.put(file.toString()
                            .replaceAll(FILE_PATH + "\\\\", "")
                            .replaceAll("\\" + FILE_TYPE_FINISHED, ""), 0);
                }
            }

            return filesMap;
        } catch (Exception ignored) {}

        return null;
    }
}
