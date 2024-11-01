package com.maikefeidan1.pieces;

import com.maikefeidan1.panel.PreviewPanel;

import java.util.ArrayList;

public abstract class Ju extends Piece {
    protected Ju(String path, int x, int y) {
        super(path, x, y);
    }

    public static Piece CreatePiece(int x, int y, int sign) {
        return sign == 1 ? new HongJu(x, y) : sign == 2 ? new HeiJu(x, y) : null;
    }

    @Override
    public ArrayList<PreviewPanel> placementPreview() {
        ArrayList<PreviewPanel> SelectPreviewPanels = new ArrayList<>();

        placement(SelectPreviewPanels);

        return SelectPreviewPanels;
    }

    public void placement(ArrayList<PreviewPanel> SelectPreviewPanels) {
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        for (int[] direction : directions) {
            int newX = x + direction[0];
            int newY = y + direction[1];

            while (isValidPosition(newX, newY)) {
                int SpotSign = grid.getGrid()[newX][newY].getSign();

                if (SpotSign == 0 || getSign() != SpotSign) {
                    PreviewPanel previewPanel = grid.getGrid()[newX][newY].getPreviewPanel();
                    previewPanel.setVisible(true);
                    SelectPreviewPanels.add(previewPanel);

                    if (SpotSign != 0) {
                        break;
                    }
                } else {
                    break;
                }

                newX += direction[0];
                newY += direction[1];
            }
        }
    }
}

class HongJu extends Ju {
    public static int instanceCount;

    public HongJu(int x, int y) {
        super("resources/images/pieces/RR.GIF", x, y);
        instanceCount++;
    }

    @Override
    public int getSign() {
        return 1;
    }

    @Override
    public String getName() {
        return "红车";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}

class HeiJu extends Ju {
    public static int instanceCount;

    public HeiJu(int x, int y) {
        super("resources/images/pieces/BR.GIF", x, y);
        instanceCount++;
    }

    @Override
    public int getSign() {
        return 2;
    }

    @Override
    public String getName() {
        return "黑车";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}