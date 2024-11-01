package com.maikefeidan1.pieces;

import com.maikefeidan1.data.Flip;
import com.maikefeidan1.exception.CoordinateInitializationException;
import com.maikefeidan1.panel.PreviewPanel;


import java.util.ArrayList;

public abstract class ShuaiOrJiang extends Piece {

    protected final Flip flip = Flip.getInstance();

    protected ShuaiOrJiang(String path, int x, int y) {
        super(path, x, y);
    }

    public static Piece CreatePiece(int x, int y, int sign) {
        return sign == 1 ? new HongShuai(x, y) : sign == 2 ? new HeiJiang(x, y) : null;
    }

    @Override
    public boolean setVisibleForMovingPiece(boolean aFlag) {
        if (aFlag) {
            super.setVisible(true);
            return false;
        }
        return true;
    }

    @Override
    public int getMaxInstanceCount() {
        return 1;
    }
}

class HongShuai extends ShuaiOrJiang {
    public static int instanceCount;

    public HongShuai(int x, int y) {
        super("resources/images/pieces/RK.GIF", x, y);
        instanceCount++;
    }

    @Override
    public ArrayList<PreviewPanel> placementPreview() {
        ArrayList<PreviewPanel> SelectPreviewPanels = new ArrayList<>();

        placement(x, y - 1, SelectPreviewPanels);
        placement(x - 1, y, SelectPreviewPanels);
        placement(x + 1, y, SelectPreviewPanels);
        placement(x, y + 1, SelectPreviewPanels);

        return SelectPreviewPanels;
    }

    @Override
    public void placement(int x, int y, ArrayList<PreviewPanel> SelectPreviewPanels) {
        int minY = flip.getIsBoardFlipped() ? -1 : 6;
        int maxY = flip.getIsBoardFlipped() ? 3 : 10;

        if (x > 2 && x < 6 && y > minY && y < maxY) {
            if (getSign() != grid.getGrid()[x][y].getSign()) {
                PreviewPanel previewPanel = grid.getGrid()[x][y].getPreviewPanel();
                previewPanel.setVisible(true);
                SelectPreviewPanels.add(previewPanel);
            }
        }
    }

    @Override
    public boolean checkCoordinate() throws CoordinateInitializationException {
        super.checkCoordinate();

        boolean isBlackOnBottom = flip.getIsBlackOnBottom();

        if (isBlackOnBottom) {
            return x < 3 || x > 5 || y > 2;
        } else {

            return x < 3 || x > 5 || y < 7;
        }
    }

    @Override
    public int getSign() {
        return 1;
    }

    @Override
    public String getName() {
        return "红帅";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}

class HeiJiang extends ShuaiOrJiang {
    public static int instanceCount;

    public HeiJiang(int x, int y) {
        super("resources/images/pieces/BK.GIF", x, y);
        instanceCount++;
    }

    @Override
    public ArrayList<PreviewPanel> placementPreview() {
        ArrayList<PreviewPanel> SelectPreviewPanels = new ArrayList<>();

        placement(x, y - 1, SelectPreviewPanels);
        placement(x - 1, y, SelectPreviewPanels);
        placement(x + 1, y, SelectPreviewPanels);
        placement(x, y + 1, SelectPreviewPanels);

        return SelectPreviewPanels;
    }

    @Override
    public void placement(int x, int y, ArrayList<PreviewPanel> SelectPreviewPanels) {
        int minY = flip.getIsBoardFlipped() ? 6 : -1;
        int maxY = flip.getIsBoardFlipped() ? 10 : 3;

        if (x > 2 && x < 6 && y > minY && y < maxY) {
            if (getSign() != grid.getGrid()[x][y].getSign()) {
                PreviewPanel previewPanel = grid.getGrid()[x][y].getPreviewPanel();
                previewPanel.setVisible(true);
                SelectPreviewPanels.add(previewPanel);
            }
        }
    }

    @Override
    public boolean checkCoordinate() throws CoordinateInitializationException {
        super.checkCoordinate();

        boolean isBlackOnBottom = flip.getIsBlackOnBottom();

        if (isBlackOnBottom) {
            return x < 3 || x > 5 || y < 7;
        } else {
            return x < 3 || x > 5 || y > 2;
        }
    }

    @Override
    public int getSign() {
        return 2;
    }

    @Override
    public String getName() {
        return "黑将";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}
