package com.maikefeidan1.pieces;

import com.maikefeidan1.data.Grid;
import com.maikefeidan1.exception.CoordinateInitializationException;
import com.maikefeidan1.panel.PreviewPanel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public abstract class Piece extends JLabel {
    protected int x;
    protected int y;

    private final int positiveX;
    private final int positiveY;
    private final int negativeX;
    private final int negativeY;

    protected final Grid grid = Grid.getInstance();

    public Piece(String path, int x, int y) {
        super(getEnlargedImage(path));
        this.x = x;
        this.y = y;

        positiveX = x;
        positiveY = y;
        negativeX = getSymmetryX();
        negativeY = getSymmetryY();

        setBounds(8 + this.x * 67, 9 + this.y * 67, 67, 67);
    }

    private static ImageIcon getEnlargedImage(String path) {
        ImageIcon icon = new ImageIcon(path);
        icon.setImage(icon.getImage().getScaledInstance(67, 67, Image.SCALE_DEFAULT));
        return icon;
    }

    @Override
    public void setLocation(int x, int y) {
        super.setLocation(8 + x * 67, 9 + y * 67);
    }


    public abstract ArrayList<PreviewPanel> placementPreview();

    public abstract String getName();

    public String getCoordinateInitializationErrorMessage() {
        return getName() + "坐标初始化错误！";
    }

    public abstract int getInstanceCount();

    public abstract int getSign();

    public int getSymmetryX() {
        return 8 - getPieceX();
    }

    public int getSymmetryY() {
        return 9 - getPieceY();
    }

    protected void placement(int x, int y, ArrayList<PreviewPanel> SelectPreviewPanels) {
        if (isValidPosition(x, y)) {
            if (getSign() != grid.getGrid()[x][y].getSign()) {
                PreviewPanel previewPanel = grid.getGrid()[x][y].getPreviewPanel();
                previewPanel.setVisible(true);
                SelectPreviewPanels.add(previewPanel);
            }
        }
    }

    public boolean setVisibleForMovingPiece(boolean aFlag) {
        super.setVisible(aFlag);
        return false;
    }

    protected boolean isValidPosition(int x, int y) {
        return x >= 0 && x < 9 && y >= 0 && y < 10;
    }

    public boolean checkCoordinate() throws CoordinateInitializationException {
        return !isValidPosition(x, y);
    }

    public int getMaxInstanceCount() {
        return 2;
    }

    public int getPieceX() {
        return x;
    }

    public int getPieceY() {
        return y;
    }

    public void setPieceX(int x) {
        this.x = x;
    }

    public void setPieceY(int y) {
        this.y = y;
    }

    public int getPositiveX() {
        return positiveX;
    }

    public int getPositiveY() {
        return positiveY;
    }

    public int getNegativeX() {
        return negativeX;
    }

    public int getNegativeY() {
        return negativeY;
    }
}

