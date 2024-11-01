package com.maikefeidan1.data;

public class GameSteps {
    private static GameSteps gameSteps;
    private int count = 0;
    private int firstWalk = 1;
    private int secondWalk = 2;

    private GameSteps(){}

    public static GameSteps getInstance() {
        if (gameSteps == null) {
            gameSteps = new GameSteps();
        }
        return gameSteps;
    }

    public void addCount() {
        count++;
    }

    public void subtractCount() {
        count--;
    }

    public void setBlackFirst() {
        firstWalk = 2;
        secondWalk = 1;
    }
    public int getCount() {
        return count;
    }

    public void resetCount() {
        count = 0;
    }

    public int getFirstWalk() {
        return firstWalk;
    }

    public int getSecondWalk() {
        return secondWalk;
    }
}
