package com.maikefeidan1.data;

import com.maikefeidan1.pieces.Piece;

import java.util.ArrayDeque;

public class History {

    private static History instance;
    private final ArrayDeque<MoveData> moveHistory = new ArrayDeque<>();

    private final ArrayDeque<MoveData> undoHistory = new ArrayDeque<>();

    private History() {
    }

    public static History getInstance() {
        if (instance == null) {
            instance = new History();
        }
        return instance;
    }

    public ArrayDeque<MoveData> getMoveHistory() {
        return moveHistory;
    }

    public ArrayDeque<MoveData> getUndoHistory() {
        return undoHistory;
    }

    public void addMoveHistoryData(SelectPiece selectPiece, int oldX, int oldY, int newX, int newY, Piece capturedPiece) {
        moveHistory.add(new MoveData(selectPiece.getSelectPiece(), oldX, oldY, newX, newY, capturedPiece));
    }

    public void inversionBoardData() {
        if (!moveHistory.isEmpty()) {
            adjustMoveData(moveHistory);
        }

        if (!undoHistory.isEmpty()) {
            adjustMoveData(undoHistory);
        }
    }

    private void adjustMoveData(ArrayDeque<MoveData> moveDataList) {
        for (MoveData moveData : moveDataList) {
            moveData.oldX = 8 - moveData.oldX;
            moveData.oldY = 9 - moveData.oldY;

            moveData.newX = 8 - moveData.newX;
            moveData.newY = 9 - moveData.newY;
        }
    }

    public static class MoveData {
        private final Piece piece;

        private int oldX;
        private int oldY;
        private int newX;
        private int newY;
        private final Piece capturedPiece;

        public MoveData(Piece piece, int oldX, int oldY, int newX, int newY, Piece capturedPiece) {
            this.piece = piece;
            this.oldX = oldX;
            this.oldY = oldY;
            this.newX = newX;
            this.newY = newY;
            this.capturedPiece = capturedPiece;
        }

        public Piece getPiece() {
            return piece;
        }

        public int getOldX() {
            return oldX;
        }

        public int getOldY() {
            return oldY;
        }

        public int getNewX() {
            return newX;
        }

        public int getNewY() {
            return newY;
        }

        public Piece getCapturedPiece() {
            return capturedPiece;
        }
    }

}
