package com.maikefeidan1.pieces;

import com.maikefeidan1.data.Flip;
import com.maikefeidan1.exception.CoordinateInitializationException;
import com.maikefeidan1.panel.PreviewPanel;

import java.util.ArrayList;

public abstract class Xiang extends Piece {

    protected final Flip flip = Flip.getInstance();

    protected Xiang(String path, int x, int y) {
        super(path, x, y);
    }

    public static Piece CreatePiece(int x, int y, int sign) {
        return sign == 1 ? new HongXiang(x, y) : sign == 2 ? new HeiXiang(x, y) : null;
    }

    protected boolean isPositionAndMarkInfoValid(int x, int y) {
        return isValidPosition(x, y) && grid.getGrid()[x][y].getSign() == 0;
    }

}

class HongXiang extends Xiang {
    public static int instanceCount;

    public HongXiang(int x, int y) {
        super("resources/images/pieces/RB.GIF", x, y);
        instanceCount++;
    }

    @Override
    public ArrayList<PreviewPanel> placementPreview() {
        ArrayList<PreviewPanel> SelectPreviewPanels = new ArrayList<>();

        if (isPositionAndMarkInfoValid(x - 1, y - 1)) {
            placement(x - 2, y - 2, SelectPreviewPanels);
        }
        if (isPositionAndMarkInfoValid(x + 1, y - 1)) {
            placement(x + 2, y - 2, SelectPreviewPanels);
        }
        if (isPositionAndMarkInfoValid(x + 1, y + 1)) {
            placement(x + 2, y + 2, SelectPreviewPanels);
        }
        if (isPositionAndMarkInfoValid(x - 1, y + 1)) {
            placement(x - 2, y + 2, SelectPreviewPanels);
        }

        return SelectPreviewPanels;
    }

    @Override
    public boolean isValidPosition(int x, int y) {
        if (x < 0 || x > 9) {
            return false;
        }
        return flip.getIsBoardFlipped() ? y >= 0 && y < 5 : y > 4 && y < 10;
    }

    @Override
    public boolean checkCoordinate() throws CoordinateInitializationException {
        super.checkCoordinate();

        boolean isBlackOnBottom = flip.getIsBlackOnBottom();

        if (isBlackOnBottom) {
            return ((x != 2 && x != 6) || (y != 0 && y != 4)) && ((x != 0 && x != 4 && x != 8) || y != 2);
        } else {
            return ((x != 2 && x != 6) || (y != 9 && y != 5)) && ((x != 0 && x != 4 && x != 8) || y != 7);
        }
    }

    @Override
    public int getSign() {
        return 1;
    }

    @Override
    public String getName() {
        return "红相";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}

class HeiXiang extends Xiang {
    public static int instanceCount;

    public HeiXiang(int x, int y) {
        super("resources/images/pieces/BB.GIF", x, y);
        instanceCount++;
    }

    @Override
    public ArrayList<PreviewPanel> placementPreview() {
        ArrayList<PreviewPanel> SelectPreviewPanels = new ArrayList<>();

        if (isPositionAndMarkInfoValid(x - 1, y - 1)) {
            placement(x - 2, y - 2, SelectPreviewPanels);
        }
        if (isPositionAndMarkInfoValid(x + 1, y - 1)) {
            placement(x + 2, y - 2, SelectPreviewPanels);
        }
        if (isPositionAndMarkInfoValid(x + 1, y + 1)) {
            placement(x + 2, y + 2, SelectPreviewPanels);
        }
        if (isPositionAndMarkInfoValid(x - 1, y + 1)) {
            placement(x - 2, y + 2, SelectPreviewPanels);
        }

        return SelectPreviewPanels;
    }

    @Override
    public boolean isValidPosition(int x, int y) {
        if (x < 0 || x > 9) {
            return false;
        }
        return flip.getIsBoardFlipped() ? y > 4 && y < 10 : y >= 0 && y < 5;
    }

    @Override
    public boolean checkCoordinate() throws CoordinateInitializationException {
        super.checkCoordinate();

        boolean isBlackOnBottom = flip.getIsBlackOnBottom();

        if (isBlackOnBottom) {
            return ((x != 2 && x != 6) || (y != 9 && y != 5)) && ((x != 0 && x != 4 && x != 8) || y != 7);
        } else {
            return ((x != 2 && x != 6) || (y != 0 && y != 4)) && ((x != 0 && x != 4 && x != 8) || y != 2);
        }
    }

    @Override
    public int getSign() {
        return 2;
    }

    @Override
    public String getName() {
        return "黑象";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}
