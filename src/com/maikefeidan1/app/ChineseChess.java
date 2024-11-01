package com.maikefeidan1.app;

import com.maikefeidan1.factory.GameMainFactory;
import com.maikefeidan1.core.InitGame;
import com.maikefeidan1.pieces.Piece;

public class ChineseChess {
    private static ChineseChess instance;
    private final InitGame initGame = GameMainFactory.getInstance().createInitGame();

    private Piece[] customPieces;
    private boolean isSetBlackOnBottom;
    private boolean isStarted;
    private boolean isSetBlackFirst;

    private ChineseChess() {
    }

    public static ChineseChess getInstance() {
        if (instance == null) {
            instance = new ChineseChess();
        }
        return instance;
    }

    public void customAllPiece(Piece[] customPieces) {
        this.customPieces = customPieces;
    }

    public void setBlackOnBottom() {
        isSetBlackOnBottom = true;
    }

    public void setBlackFirst() {
        isSetBlackFirst = true;
    }

    public void start() {
        if (!isStarted) {
            isStarted = true;
            initGame.init(customPieces, isSetBlackOnBottom, isSetBlackFirst);
        }
    }
}