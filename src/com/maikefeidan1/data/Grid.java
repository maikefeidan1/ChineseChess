package com.maikefeidan1.data;

import com.maikefeidan1.pieces.Piece;
import com.maikefeidan1.panel.PreviewPanel;

public class Grid {
    private static Grid instance;
    private final Spot[][] grid = new Spot[9][10];

    private Grid() {
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                grid[i][j] = new Spot();
            }
        }
    }

    public static Grid getInstance() {
        if (instance == null) {
            instance = new Grid();
        }
        return instance;
    }

    public Spot[][] getGrid() {
        return grid;
    }

    public void resetGrid() {
        for (Spot[] row : grid) {
            for (Spot spot : row) {
                spot.resetSignAndPiece();
            }
        }
    }

    public void updateGridAndPieceForMoveOrRedo(Piece piece, int oldX, int oldY, int newX, int newY) {
        updateGridAndPiece(piece, oldX, oldY, newX, newY, null, false);
    }

    public void updateGridAndPieceForUndo(Piece piece, int oldX, int oldY, int newX, int newY, Piece potentialCapturedPiece) {
        updateGridAndPiece(piece, oldX, oldY, newX, newY, potentialCapturedPiece, true);
    }

    private void updateGridAndPiece(Piece piece, int oldX, int oldY, int newX, int newY, Piece potentialCapturedPiece, boolean isUndo) {
        grid[oldX][oldY].setSignAndPiece(isUndo ? piece : null);
        grid[newX][newY].setSignAndPiece(isUndo ? potentialCapturedPiece : piece);

        piece.setPieceX(isUndo ? oldX : newX);
        piece.setPieceY(isUndo ? oldY : newY);
    }

    public static class Spot {
        private int sign;
        private Piece piece;
        private PreviewPanel previewPanel;

        private Spot() {
        }

        public int getSign() {
            return sign;
        }

        public Piece getPiece() {
            return piece;
        }

        public void setSignAndPiece(Piece piece) {
            boolean isNull = piece == null;
            this.sign = isNull ? 0 : piece.getSign();
            this.piece = isNull ? null : piece;
        }

        public PreviewPanel getPreviewPanel() {
            return previewPanel;
        }

        public void setPreviewPanel(PreviewPanel previewPanel) {
            this.previewPanel = previewPanel;
        }

        public void resetSignAndPiece() {
            sign = 0;
            piece = null;
        }

    }
}
