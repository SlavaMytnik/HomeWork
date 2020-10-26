package towers;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TowersGame {
    private boolean isRules = false;

    private int gameType = 1;
    private int manualGameType = 1;
    private int[][] circles;
    private int sleepMillis = 0;
    private int stepsCount = 0;

    private int minTowersCount = 3;
    private int maxTowersCount = 8;
    private int towersCount = 3;

    private int minCirclesCount = 3;
    private int maxCirclesCount = 20;
    private int circlesCount = 3;

    private String generatedStepString = "";

    public TowersGame() {
    }

    public TowersGame(int gameType, int manualGameType, int towersCount, int circlesCount) {
        setGameType(gameType);
        setManualGameType(manualGameType);
        setTowersCount(towersCount);
        setCirclesCount(circlesCount);
    }

    public boolean setRules(boolean rules) {
        isRules = rules;
        return true;
    }

    public boolean setGameType(int gameType) {
        if (gameType == 1 || gameType == 2) {
            this.gameType = gameType;
            return true;
        }
        return false;
    }

    public boolean setManualGameType(int manualGameType) {
        if (manualGameType == 1 || manualGameType == 2 || manualGameType == 3) {
            this.manualGameType = manualGameType;
            return true;
        }
        return false;
    }

    public boolean setSleepMillis(int sleepMillis) {
        if (sleepMillis >= 0) {
            this.sleepMillis = sleepMillis;
            return true;
        }
        return false;
    }

    public boolean setMinTowersCount(int minTowersCount) {
        if (minTowersCount >= 3 && minTowersCount <= maxTowersCount) {
            this.minTowersCount = minTowersCount;
            return true;
        }
        return false;
    }

    public boolean setMaxTowersCount(int maxTowersCount) {
        if (maxTowersCount <= 8 && minTowersCount <= maxTowersCount) {
            this.maxTowersCount = maxTowersCount;
            return true;
        }
        return false;
    }

    public boolean setTowersCount(int towersCount) {
        if (towersCount >= minCirclesCount && towersCount <= maxTowersCount) {
            this.towersCount = towersCount;
            return true;
        }
        return false;
    }

    public boolean setMinCirclesCount(int minCirclesCount) {
        if (minCirclesCount >= 3 && minCirclesCount <= maxCirclesCount) {
            this.minCirclesCount = minCirclesCount;
            return true;
        }
        return false;
    }

    public boolean setMaxCirclesCount(int maxCirclesCount) {
        if (maxCirclesCount <= 20 && minCirclesCount <= maxCirclesCount) {
            this.maxCirclesCount = maxCirclesCount;
            return true;
        }
        return false;
    }

    public boolean setCirclesCount(int circlesCount) {
        if (circlesCount >= minCirclesCount && circlesCount <= maxCirclesCount) {
            this.circlesCount = circlesCount;
            return true;
        }
        return false;
    }

    public boolean isRules() {
        return isRules;
    }

    public int getGameType() {
        return gameType;
    }

    public int getManualGameType() {
        return manualGameType;
    }

    public int getSleepMillis() {
        return sleepMillis;
    }

    public int getStepsCount() {
        return stepsCount;
    }

    public int getMinTowersCount() {
        return minTowersCount;
    }

    public int getMaxTowersCount() {
        return maxTowersCount;
    }

    public int getTowersCount() {
        return towersCount;
    }

    public int getMinCirclesCount() {
        return minCirclesCount;
    }

    public int getMaxCirclesCount() {
        return maxCirclesCount;
    }

    public int getCirclesCount() {
        return circlesCount;
    }

    public String getGeneratedStepString() {
        return generatedStepString;
    }

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
    }

    public boolean step(int fromValue, int toValue) {
        return step(fromValue + "->" + toValue);
    }

    public boolean step() {
        try {
            Thread.sleep(sleepMillis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        generatedStepString = (new Random().nextInt(towersCount) + 1)
                + "->" + (new Random().nextInt(towersCount) + 1);

        return step(generatedStepString);
    }

    public boolean step(String consoleString) {
        stepsCount++;

        if (checkStep(consoleString)) {
            int fromTower = -1;
            int toTower = -1;
            int fromCircle = -1;
            int toCircle = -1;
            int fromValue = circlesCount;
            int toValue = circlesCount;

            Pattern pattern = Pattern.compile("([0-9]+)->([0-9]+)");
            Matcher matcher = pattern.matcher(consoleString);

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

    public boolean checkStep(String consoleString) {
        try {
            Pattern pattern = Pattern.compile("([0-9]+)->([0-9]+)");
            Matcher matcher = pattern.matcher(consoleString);

            if (matcher.find() && consoleString.replaceAll("[0-9]+", "").equals("->")
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
