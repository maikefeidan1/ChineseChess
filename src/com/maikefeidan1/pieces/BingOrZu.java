package com.maikefeidan1.pieces;

import com.maikefeidan1.data.Flip;
import com.maikefeidan1.exception.CoordinateInitializationException;
import com.maikefeidan1.panel.PreviewPanel;

import java.util.ArrayList;

public abstract class BingOrZu extends Piece {

    protected final Flip flip = Flip.getInstance();

    protected BingOrZu(String path, int x, int y) {
        super(path, x, y);
    }

    public static Piece CreatePiece(int x, int y, int sign) {
        return sign == 1 ? new HongBing(x, y) : sign == 2 ? new HeiZu(x, y) : null;
    }

    @Override
    public int getMaxInstanceCount() {
        return 5;
    }
}

class HongBing extends BingOrZu {

    public static int instanceCount;

    public HongBing(int x, int y) {
        super("resources/images/pieces/RP.GIF", x, y);
        instanceCount++;
    }

    @Override
    public ArrayList<PreviewPanel> placementPreview() {
        ArrayList<PreviewPanel> SelectPreviewPanels = new ArrayList<>();

        if (flip.getIsBoardFlipped()) {
            placement(x, y + 1, SelectPreviewPanels);

            if (y > 4) {
                placement(x - 1, y, SelectPreviewPanels);
                placement(x + 1, y, SelectPreviewPanels);
            }
        } else {
            placement(x, y - 1, SelectPreviewPanels);

            if (y < 5) {
                placement(x - 1, y, SelectPreviewPanels);
                placement(x + 1, y, SelectPreviewPanels);
            }
        }

        return SelectPreviewPanels;
    }

    @Override
    public boolean checkCoordinate() throws CoordinateInitializationException {
        super.checkCoordinate();

        boolean isBlackOnBottom = flip.getIsBlackOnBottom();

        if (isBlackOnBottom) {
            return y < 3 || (y < 5 && x % 2 != 0);
        } else {
            return y > 6 || (y > 4 && x % 2 != 0);
        }
    }


    @Override
    public int getSign() {
        return 1;
    }

    @Override
    public String getName() {
        return "红兵";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}

class HeiZu extends BingOrZu {

    public static int instanceCount;

    public HeiZu(int x, int y) {
        super("resources/images/pieces/BP.GIF", x, y);
        instanceCount++;
    }

    @Override
    public ArrayList<PreviewPanel> placementPreview() {
        ArrayList<PreviewPanel> SelectPreviewPanels = new ArrayList<>();

        if (flip.getIsBoardFlipped()) {
            placement(x, y - 1, SelectPreviewPanels);

            if (y < 5) {
                placement(x - 1, y, SelectPreviewPanels);
                placement(x + 1, y, SelectPreviewPanels);
            }
        } else {
            placement(x, y + 1, SelectPreviewPanels);

            if (y > 4) {
                placement(x - 1, y, SelectPreviewPanels);
                placement(x + 1, y, SelectPreviewPanels);
            }
        }

        return SelectPreviewPanels;
    }

    @Override
    public boolean checkCoordinate() throws CoordinateInitializationException {
        super.checkCoordinate();

        boolean isBlackOnBottom = flip.getIsBlackOnBottom();

        if (isBlackOnBottom) {
            return y > 6 || (y > 4 && x % 2 != 0);
        } else {
            return y < 3 || (y < 5 && x % 2 != 0);
        }
    }

    @Override
    public int getSign() {
        return 2;
    }

    @Override
    public String getName() {
        return "黑卒";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}