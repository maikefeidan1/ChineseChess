package com.maikefeidan1.pieces;

import com.maikefeidan1.data.Flip;
import com.maikefeidan1.exception.CoordinateInitializationException;
import com.maikefeidan1.panel.PreviewPanel;

import java.util.ArrayList;

public abstract class Shi extends Piece {

    protected final Flip flip = Flip.getInstance();

    protected Shi(String path, int x, int y) {
        super(path, x, y);
    }

    public static Piece CreatePiece(int x, int y, int sign) {
        return sign == 1 ? new HongShi(x, y) : sign == 2 ? new HeiShi(x, y) : null;
    }

    @Override
    public void placement(int x, int y, ArrayList<PreviewPanel> SelectPreviewPanels) {
        if (getSign() != grid.getGrid()[x][y].getSign()) {
            PreviewPanel previewPanel = grid.getGrid()[x][y].getPreviewPanel();
            previewPanel.setVisible(true);
            SelectPreviewPanels.add(previewPanel);
        }
    }
}

class HongShi extends Shi {
    public static int instanceCount;

    public HongShi(int x, int y) {
        super("resources/images/pieces/RA.GIF", x, y);
        instanceCount++;
    }

    @Override
    public ArrayList<PreviewPanel> placementPreview() {
        ArrayList<PreviewPanel> SelectPreviewPanels = new ArrayList<>();

        if (flip.getIsBoardFlipped()) {
            if (x == 3 || x == 5) {
                placement(4, 1, SelectPreviewPanels);
            }

            if (x == 4 && y == 1) {
                placement(3, 0, SelectPreviewPanels);
                placement(5, 0, SelectPreviewPanels);
                placement(3, 2, SelectPreviewPanels);
                placement(5, 2, SelectPreviewPanels);
            }
        } else {
            if (x == 3 || x == 5) {
                placement(4, 8, SelectPreviewPanels);
            }

            if (x == 4 && y == 8) {
                placement(3, 7, SelectPreviewPanels);
                placement(5, 7, SelectPreviewPanels);
                placement(3, 9, SelectPreviewPanels);
                placement(5, 9, SelectPreviewPanels);
            }
        }

        return SelectPreviewPanels;
    }

    @Override
    public boolean checkCoordinate() throws CoordinateInitializationException {
        super.checkCoordinate();

        boolean isBlackOnBottom = flip.getIsBlackOnBottom();

        if (isBlackOnBottom) {
            return ((x != 3 && x != 5) || (y != 0 && y != 2)) && (x != 4 || y != 1);
        } else {
            return ((x != 3 && x != 5) || (y != 7 && y != 9)) && (x != 4 || y != 8);
        }
    }

    @Override
    public int getSign() {
        return 1;
    }

    @Override
    public String getName() {
        return "红士";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}

class HeiShi extends Shi {
    public static int instanceCount;

    public HeiShi(int x, int y) {
        super("resources/images/pieces/BA.GIF", x, y);
        instanceCount++;
    }

    @Override
    public ArrayList<PreviewPanel> placementPreview() {
        ArrayList<PreviewPanel> SelectPreviewPanels = new ArrayList<>();

        if (flip.getIsBoardFlipped()) {
            if (x == 3 || x == 5) {
                placement(4, 8, SelectPreviewPanels);
            }

            if (x == 4 && y == 8) {
                placement(3, 7, SelectPreviewPanels);
                placement(5, 7, SelectPreviewPanels);
                placement(3, 9, SelectPreviewPanels);
                placement(5, 9, SelectPreviewPanels);
            }
        } else {
            if (x == 3 || x == 5) {
                placement(4, 1, SelectPreviewPanels);
            }

            if (x == 4 && y == 1) {
                placement(3, 0, SelectPreviewPanels);
                placement(5, 0, SelectPreviewPanels);
                placement(3, 2, SelectPreviewPanels);
                placement(5, 2, SelectPreviewPanels);
            }
        }

        return SelectPreviewPanels;
    }

    @Override
    public boolean checkCoordinate() throws CoordinateInitializationException {
        super.checkCoordinate();

        boolean isBlackOnBottom = flip.getIsBlackOnBottom();

        if (isBlackOnBottom) {
            return ((x != 3 && x != 5) || (y != 7 && y != 9)) && (x != 4 || y != 8);
        } else {
            return ((x != 3 && x != 5) || (y != 0 && y != 2)) && (x != 4 || y != 1);
        }
    }

    @Override
    public int getSign() {
        return 2;
    }

    @Override
    public String getName() {
        return "黑士";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}
