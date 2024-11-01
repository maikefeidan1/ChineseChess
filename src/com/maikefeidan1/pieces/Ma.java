package com.maikefeidan1.pieces;

import com.maikefeidan1.panel.PreviewPanel;

import java.util.ArrayList;

public abstract class Ma extends Piece {
    protected Ma(String path, int x, int y) {
        super(path, x, y);
    }

    public static Piece CreatePiece(int x, int y, int sign) {
        return sign == 1 ? new HongMa(x, y) : sign == 2 ? new HeiMa(x, y) : null;
    }

    public boolean isPositionAndMarkInfoValid(int x, int y) {
        return super.isValidPosition(x, y) && grid.getGrid()[x][y].getSign() == 0;
    }
}

class HongMa extends Ma {
    public static int instanceCount;

    public HongMa(int x, int y) {
        super("resources/images/pieces/RN.GIF", x, y);
        instanceCount++;
    }

    @Override
    public ArrayList<PreviewPanel> placementPreview() {
        ArrayList<PreviewPanel> SelectPreviewPanels = new ArrayList<>();

        if (isPositionAndMarkInfoValid(x, y - 1)) {
            placement(x - 1, y - 2, SelectPreviewPanels);
            placement(x + 1, y - 2, SelectPreviewPanels);
        }

        if (isPositionAndMarkInfoValid(x + 1, y)) {
            placement(x + 2, y - 1, SelectPreviewPanels);
            placement(x + 2, y + 1, SelectPreviewPanels);
        }

        if (isPositionAndMarkInfoValid(x, y + 1)) {
            placement(x + 1, y + 2, SelectPreviewPanels);
            placement(x - 1, y + 2, SelectPreviewPanels);
        }

        if (isPositionAndMarkInfoValid(x - 1, y)) {
            placement(x - 2, y + 1, SelectPreviewPanels);
            placement(x - 2, y - 1, SelectPreviewPanels);
        }

        return SelectPreviewPanels;
    }

    @Override
    public int getSign() {
        return 1;
    }

    @Override
    public String getName() {
        return "红马";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}

class HeiMa extends Ma {

    public static int instanceCount;

    public HeiMa(int x, int y) {
        super("resources/images/pieces/BN.GIF", x, y);
        instanceCount++;
    }

    @Override
    public ArrayList<PreviewPanel> placementPreview() {
        ArrayList<PreviewPanel> SelectPreviewPanels = new ArrayList<>();

        if (isPositionAndMarkInfoValid(x, y - 1)) {
            placement(x - 1, y - 2, SelectPreviewPanels);
            placement(x + 1, y - 2, SelectPreviewPanels);
        }

        if (isPositionAndMarkInfoValid(x + 1, y)) {
            placement(x + 2, y - 1, SelectPreviewPanels);
            placement(x + 2, y + 1, SelectPreviewPanels);
        }

        if (isPositionAndMarkInfoValid(x, y + 1)) {
            placement(x + 1, y + 2, SelectPreviewPanels);
            placement(x - 1, y + 2, SelectPreviewPanels);
        }

        if (isPositionAndMarkInfoValid(x - 1, y)) {
            placement(x - 2, y + 1, SelectPreviewPanels);
            placement(x - 2, y - 1, SelectPreviewPanels);
        }

        return SelectPreviewPanels;
    }

    @Override
    public int getSign() {
        return 2;
    }

    @Override
    public String getName() {
        return "黑马";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}
