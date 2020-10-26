package towers;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TowersGame {
    private boolean isRules = false;

    private int gameType = 1;
    private int towersCount = 3;
    private int circlesCount = 3;
    private int maxTowersCount = 8;
    private int maxCirclesCount = 20;
    private int[][] circles;
    private int sleepMillis = 0;

    private static Scanner scanner = new Scanner(System.in);

    public TowersGame() {
    }

    public TowersGame(int gameType, int towersCount, int circlesCount) {
        this.gameType = gameType;
        this.towersCount = towersCount;
        this.circlesCount = circlesCount;
    }

    public void setGameType(String textForUser, String textForError) {
        gameType = setGameVariables(textForUser, textForError, 1, 2);
    }

    public void setTowersCount(String textForUser, String textForError) {
        towersCount = setGameVariables(textForUser, textForError, 3, maxTowersCount);
    }

    public void setCirclesCount(String textForUser, String textForError) {
        circlesCount = setGameVariables(textForUser, textForError, 3, maxCirclesCount);
    }

    public void setSleepMillis(int sleepMillis) {
        this.sleepMillis = sleepMillis;
    }

    public void setRules(boolean isRules) {
        this.isRules = isRules;
    }

    private int setGameVariables(String textForUser, String textForError,
                                 int minValue, int maxValue) {
        boolean isCorrect = true;

        String userText = "";

        do {
            System.out.print(textForUser);

            if (scanner.hasNext()) {
                userText = scanner.nextLine();
                isCorrect = checkTextForVariables(userText, minValue, maxValue);
            }

            if (!isCorrect) {
                System.out.println(textForError);
            }
        } while (!isCorrect);

        return Integer.parseInt(userText);
    }

    private boolean checkTextForVariables(String userText, int minValue, int maxValue) {
        try {
            if (Integer.parseInt(userText) >= minValue
                    && Integer.parseInt(userText) <= maxValue) {
                return true;
            }
        } catch (NumberFormatException ignored) {}

        return false;
    }

    public void start() {
        if (gameType != 1 && gameType != 2) {
            gameType = 1;
        }

        if (towersCount < 3) {
            towersCount = 3;
        }

        if (towersCount > maxTowersCount) {
            towersCount = maxTowersCount;
        }

        if (circlesCount < 3) {
            circlesCount = 3;
        }

        if (circlesCount > maxCirclesCount) {
            circlesCount = maxCirclesCount;
        }

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
    }

    public void step(String textForUser, String textForError) {
        boolean isCorrect = true;

        int fromTower = -1;
        int toTower = -1;
        int fromCircle = -1;
        int toCircle = -1;
        int fromValue = circlesCount;
        int toValue = circlesCount;

        String userOrDeviceText = "";

        do {
            do {
                if (userOrDeviceText.equals("") || gameType == 1 || (gameType == 2 && !isRules)) {
                    System.out.print(textForUser);
                }

                if (gameType == 1) {
                    if (scanner.hasNext()) {
                        userOrDeviceText = scanner.nextLine();
                        isCorrect = checkTextForStep(userOrDeviceText);
                    }
                } else {
                    if (!isRules) {
                        try {
                            Thread.sleep(sleepMillis);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }

                    userOrDeviceText = (new Random().nextInt(towersCount) + 1)
                            + "->" + (new Random().nextInt(towersCount) + 1);
                    isCorrect = checkTextForStep(userOrDeviceText);

                    if (gameType == 2 && !isRules) {
                        System.out.println(userOrDeviceText);
                    }
                }

                if (!isCorrect) {
                    if (gameType == 1 || (gameType == 2 && !isRules)) {
                        System.out.println(textForError);
                    }
                }
            } while (!isCorrect);

            Pattern pattern = Pattern.compile("([0-9]+)->([0-9]+)");
            Matcher matcher = pattern.matcher(userOrDeviceText);

            if (matcher.find()) {

                fromTower = Integer.parseInt(matcher.group(1)) - 1;
                toTower = Integer.parseInt(matcher.group(2)) - 1;

                fromCircle = -1;
                toCircle = -1;
                fromValue = circlesCount;
                toValue = circlesCount;

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

                if (fromCircle > -1 && fromValue < toValue) {
                    if (toCircle == -1) {
                        toCircle = 0;
                    } else {
                        toCircle++;
                    }

                    if (gameType == 2 && isRules) {
                        try {
                            Thread.sleep(sleepMillis);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        System.out.println(userOrDeviceText);
                    }
                } else {
                    isCorrect = false;
                    if (gameType == 1 || (gameType == 2 && !isRules)) {
                        System.out.println(textForError);
                    }
                }
            }
        } while (!isCorrect);

        circles[toTower][toCircle] = fromValue;
        circles[fromTower][fromCircle] = -1;
    }

    private boolean checkTextForStep(String userText) {
        try {
            Pattern pattern = Pattern.compile("([0-9]+)->([0-9]+)");
            Matcher matcher = pattern.matcher(userText);

            if (matcher.find() && userText.replaceAll("[0-9]+", "").equals("->")
                    && Integer.parseInt(matcher.group(1)) <= towersCount
                    && Integer.parseInt(matcher.group(2)) <= towersCount
                    && Integer.parseInt(matcher.group(1)) != Integer.parseInt(matcher.group(2))) {
                return true;
            }
        } catch (NumberFormatException ignored) {
        }

        return false;
    }

    public boolean isContinue() {
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
}
