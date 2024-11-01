package com.maikefeidan1.core;

import com.maikefeidan1.data.*;
import com.maikefeidan1.pieces.Piece;
import com.maikefeidan1.panel.PreviewPanel;
import com.maikefeidan1.panel.Selected;

public class MainControl {
    private static MainControl instance;
    //↓-注入的依赖项//
    private final Grid grid;
    private final InitPieces initPieces;
    private final Flip flip;
    private final SelectPiece selectPiece;
    private final History history;
    private final Selected selected;
    private final GameSteps gameSteps;
    private final SelectPreviewPanels selectPreviewPanels;
    //↑-注入的依赖项//

    private MainControl(Grid grid, InitPieces initPieces, Flip flip, SelectPiece selectPiece, History history, Selected selected, GameSteps gameSteps, SelectPreviewPanels selectPreviewPanels) {
        this.grid = grid;
        this.initPieces = initPieces;
        this.flip = flip;
        this.selectPiece = selectPiece;
        this.history = history;
        this.selected = selected;
        this.gameSteps = gameSteps;
        this.selectPreviewPanels = selectPreviewPanels;
    }

    public static MainControl getInstance(Grid grid, InitPieces initPieces, Flip flip, SelectPiece selectPiece, History history, Selected selected, GameSteps gameSteps, SelectPreviewPanels selectPreviewPanels) {
        if (instance == null) {
            instance = new MainControl(grid, initPieces, flip, selectPiece, history, selected,gameSteps,selectPreviewPanels);
        }
        return instance;
    }

    public void selectPiece(Piece piece) {
        if ((gameSteps.getCount() % 2 == 0 && piece.getSign() == gameSteps.getFirstWalk() || gameSteps.getCount() % 2 == 1 && piece.getSign() == gameSteps.getSecondWalk()) && piece != selectPiece.getSelectPiece()) {
            selectPiece.setSelectPiece(piece);

            if (selectPreviewPanels.getSelectPreviewPanels() != null) {
                for (PreviewPanel previewPanel : selectPreviewPanels.getSelectPreviewPanels()) {
                    previewPanel.setVisible(false);
                }
            }

            selected.setLocation(piece.getPieceX(), piece.getPieceY());
            selected.setVisible(true);
            selectPreviewPanels.setSelectPreviewPanels(piece.placementPreview());
        }
    }

    public void movingPiece(PreviewPanel panel) {
        int oldX = selectPiece.getSelectPiece().getPieceX();
        int oldY = selectPiece.getSelectPiece().getPieceY();
        int newX = panel.getPreviewPanelX();
        int newY = panel.getPreviewPanelY();

        Piece capturedPiece = null;
        boolean isCaptured = grid.getGrid()[newX][newY].getSign() != 0;

        //↓-动画区域//
        offAssist();

        if (isCaptured) {
            capturedPiece = grid.getGrid()[newX][newY].getPiece();
            boolean isGameOver = capturedPiece.setVisibleForMovingPiece(false);
            if (isGameOver) {
                resetGame();
                return;
            }
        }

        selectPiece.getSelectPiece().setLocation(newX, newY);
        //↑-动画区域//

        if (!history.getUndoHistory().isEmpty()) {
            history.getUndoHistory().clear();
        }

        grid.updateGridAndPieceForMoveOrRedo(selectPiece.getSelectPiece(), oldX, oldY, newX, newY);

        history.addMoveHistoryData(selectPiece, oldX, oldY, newX, newY, capturedPiece);

        gameSteps.addCount();
    }

    public void undo() {
        if (gameSteps.getCount() > 0) {
            History.MoveData moveData = history.getMoveHistory().removeLast();

            Piece piece = moveData.getPiece();
            int oldX = moveData.getOldX();
            int oldY = moveData.getOldY();
            int newX = moveData.getNewX();
            int newY = moveData.getNewY();
            Piece potentialCapturedPiece = moveData.getCapturedPiece();

            boolean isCaptured = potentialCapturedPiece != null;

            //↓-动画区域//
            offAssist();

            piece.setLocation(oldX, oldY);

            if (isCaptured) {
                potentialCapturedPiece.setVisible(true);
            }
            //↑-动画区域//

            grid.updateGridAndPieceForUndo(piece, oldX, oldY, newX, newY, potentialCapturedPiece);

            selectPiece.setSelectPiece(null);

            history.getUndoHistory().add(moveData);

            gameSteps.subtractCount();
        }
    }

    public void redo() {
        if (!history.getUndoHistory().isEmpty()) {
            History.MoveData moveData = history.getUndoHistory().removeLast();

            Piece piece = moveData.getPiece();
            int oldX = moveData.getOldX();
            int oldY = moveData.getOldY();
            int newX = moveData.getNewX();
            int newY = moveData.getNewY();
            Piece capturedPiece = moveData.getCapturedPiece();

            boolean isCaptured = capturedPiece != null;

            //↓-动画区域//
            offAssist();

            if (isCaptured) {
                capturedPiece.setVisible(false);
            }

            piece.setLocation(newX, newY);
            //↑-动画区域//

            grid.updateGridAndPieceForMoveOrRedo(piece, oldX, oldY, newX, newY);

            history.getMoveHistory().add(moveData);

            gameSteps.addCount();
        }
    }

    public void boardFlip() {
        flip.setBoardFlipped(!flip.getIsBoardFlipped());

        boolean isSelect = selected.isVisible();

        if (isSelect) {
            offAssist();
        }

        grid.resetGrid();

        for (Piece piece : initPieces.getInitPieces()) {
            int x = piece.getSymmetryX();
            int y = piece.getSymmetryY();

            piece.setLocation(x, y);

            if (piece.isVisible()) {
                grid.getGrid()[x][y].setSignAndPiece(piece);
            }

            piece.setPieceX(x);
            piece.setPieceY(y);
        }

        if (isSelect) {
            selected.setLocation(selectPiece.getSelectPiece().getPieceX(), selectPiece.getSelectPiece().getPieceY());
            selected.setVisible(true);
            selectPreviewPanels .setSelectPreviewPanels(selectPiece.getSelectPiece().placementPreview());
        }

        history.inversionBoardData();
    }

    public void resetGame() {
        offAssist();

        grid.resetGrid();

        for (Piece piece : initPieces.getInitPieces()) {
            int x, y;

            if (flip.getIsBlackOnBottom()) {
                x = flip.getIsBoardFlipped() ? piece.getPositiveX() : piece.getNegativeX();
                y = flip.getIsBoardFlipped() ? piece.getPositiveY() : piece.getNegativeY();
            } else {
                x = flip.getIsBoardFlipped() ? piece.getNegativeX() : piece.getPositiveX();
                y = flip.getIsBoardFlipped() ? piece.getNegativeY() : piece.getPositiveY();
            }

            piece.setLocation(x, y);

            grid.getGrid()[x][y].setSignAndPiece(piece);

            piece.setPieceX(x);
            piece.setPieceY(y);

            if (!piece.isVisible()) {
                piece.setVisible(true);
            }
        }

        selectPiece.setSelectPiece(null);

        history.getMoveHistory().clear();
        history.getUndoHistory().clear();

        gameSteps.resetCount();
    }

    private void offAssist() {
        selected.setVisible(false);
        if (selectPreviewPanels != null) {
            for (PreviewPanel previewPanel : selectPreviewPanels.getSelectPreviewPanels()) {
                previewPanel.setVisible(false);
            }
        }
    }
}
