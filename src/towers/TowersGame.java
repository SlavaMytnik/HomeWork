package towers;

import java.util.Random;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TowersGame {
    private int gameType;
    private int towersCount;
    private int circlesCount;
    private int[][] circles;
    private int sleepMillis = 0;

    private static Scanner scanner = new Scanner(System.in);

    public TowersGame() {
        this.gameType = 1;
        this.towersCount = 3;
        this.circlesCount = 3;
    }

    public TowersGame(int gameType, int towersCount, int circlesCount) {
        this.gameType = gameType;
        this.towersCount = towersCount;
        this.circlesCount = circlesCount;
    }

    public void start() {
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
        if (circles != null) {
            boolean rulesAccordance;

            do {
                int moveFromTower = -1;
                int moveToTower = -1;
                int minCircleFrom = circlesCount;
                int minCircleTo = circlesCount;
                int minCircleFromIndex = -1;
                int minCircleToIndex = -1;

                int[] getStepResult = getStep(textForUser, textForError);
                moveFromTower = getStepResult[0];
                moveToTower = getStepResult[1];

                moveFromTower--;
                moveToTower--;

                for (int i = 0; i < circlesCount; i++) {
                    if (circles[moveFromTower][i] > -1 && circles[moveFromTower][i] < minCircleFrom) {
                        minCircleFrom = circles[moveFromTower][i];
                        minCircleFromIndex = i;
                    }
                }

                for (int i = 0; i < circlesCount; i++) {
                    if (circles[moveToTower][i] > -1 && circles[moveToTower][i] < minCircleTo) {
                        minCircleTo = circles[moveToTower][i];
                        minCircleToIndex = i;
                    }
                }

                rulesAccordance = true;

                if (minCircleFromIndex > -1 && minCircleFrom < minCircleTo) {
                    if (minCircleToIndex == -1) {
                        minCircleToIndex = 0;
                    } else {
                        minCircleToIndex++;
                    }

                    circles[moveToTower][minCircleToIndex] = minCircleFrom;
                    circles[moveFromTower][minCircleFromIndex] = -1;
                } else {
                    rulesAccordance = false;
                    System.out.println(textForError);
                }
            } while (!rulesAccordance);
        }
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

    public void setSleepMillis(int sleepMillis) {
        this.sleepMillis = sleepMillis;
    }

    public void setGameType(String textForUser, String textForError) {
        gameType = setGameVariables(textForUser, textForError, 1, 2);
    }

    public void setTowersCount(String textForUser, String textForError) {
        towersCount = setGameVariables(textForUser, textForError, 3, 8);
    }

    public void setCirclesCount(String textForUser, String textForError) {
        circlesCount = setGameVariables(textForUser, textForError, 3, 8);

        if (towersCount > circlesCount) {
            towersCount = circlesCount;
        }
    }

    private int[] getStep(String textForUser, String textForError) {
        boolean isTextCorrect = true;

        String userOrDeviceText = "";

        do {
            System.out.print(textForUser);

            if (gameType == 1) {
                if (scanner.hasNext()) {
                    userOrDeviceText = scanner.nextLine();
                    isTextCorrect = checkTextForStep(userOrDeviceText);
                }
            } else {
                try {
                    Thread.sleep(sleepMillis);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                userOrDeviceText = (new Random().nextInt(towersCount) + 1)
                        + "->" + (new Random().nextInt(towersCount) + 1);
                isTextCorrect = checkTextForStep(userOrDeviceText);
                System.out.println(userOrDeviceText);
            }

            if (!isTextCorrect) {
                System.out.println(textForError);
            }
        } while (!isTextCorrect);

        Pattern pattern = Pattern.compile("([0-9])->([0-9])");
        Matcher matcher = pattern.matcher(userOrDeviceText);

        if (matcher.find()) {
            return new int[]{Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2))};
        }

        return new int[]{};
    }

    private boolean checkTextForStep(String userText) {
        try {
            Pattern pattern = Pattern.compile("([0-9])->([0-9])");
            Matcher matcher = pattern.matcher(userText);

            if (matcher.find() && userText.length() == 4
                    && Integer.parseInt(matcher.group(1)) <= towersCount
                    && Integer.parseInt(matcher.group(2)) <= towersCount
                    && Integer.parseInt(matcher.group(1)) != Integer.parseInt(matcher.group(2))) {
                return true;
            }
        } catch (NumberFormatException ignored) {
        }

        return false;
    }

    private int setGameVariables(String textForUser, String textForError,
                                 int minValue, int maxValue) {
        boolean isUserTextCorrect = true;

        String userText = "";

        do {
            System.out.print(textForUser);

            if (scanner.hasNext()) {
                userText = scanner.nextLine();
                isUserTextCorrect = checkUserTextForVariables(userText, minValue, maxValue);
            }

            if (!isUserTextCorrect) {
                System.out.println(textForError);
            }
        } while (!isUserTextCorrect);

        return Integer.parseInt(userText);
    }

    private boolean checkUserTextForVariables(String userText, int minValue, int maxValue) {
        try {
            if (Integer.parseInt(userText) >= minValue
                    && Integer.parseInt(userText) <= maxValue) {
                return true;
            }
        } catch (NumberFormatException ignored) {}

        return false;
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
