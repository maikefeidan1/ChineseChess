package com.maikefeidan1.data;

public class Flip {
    private static Flip instance;

    private boolean isBoardFlipped;

    private boolean isBlackOnBottom;

    private Flip() {
    }

    public static Flip getInstance() {
        if (instance == null) {
            instance = new Flip();
        }
        return instance;
    }

    public boolean getIsBoardFlipped() {
        return isBoardFlipped;
    }

    public void setBoardFlipped(boolean boardFlipped) {
        isBoardFlipped = boardFlipped;
    }

    public boolean getIsBlackOnBottom() {
        return isBlackOnBottom;
    }

    public void setBlackOnBottom() {
        isBlackOnBottom = true;
        isBoardFlipped = true;
    }
}
