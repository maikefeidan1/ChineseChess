package com.maikefeidan1.pieces;

import com.maikefeidan1.panel.PreviewPanel;

import java.util.ArrayList;

public abstract class Pao extends Piece {
    protected Pao(String path, int x, int y) {
        super(path, x, y);
    }

    public static Piece CreatePiece(int x, int y, int sign) {
        return sign == 1 ? new HongPao(x, y) : sign == 2 ? new HeiPao(x, y) : null;
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

            outerLoop:
            while (isValidPosition(newX, newY)) {
                int firstStageSpotSign = grid.getGrid()[newX][newY].getSign();

                if (firstStageSpotSign == 0) {
                    PreviewPanel firstStagePreviewPanel = grid.getGrid()[newX][newY].getPreviewPanel();
                    firstStagePreviewPanel.setVisible(true);
                    SelectPreviewPanels.add(firstStagePreviewPanel);
                } else {
                    newX += direction[0];
                    newY += direction[1];

                    while (isValidPosition(newX, newY)) {
                        int secondStageSpotSign = grid.getGrid()[newX][newY].getSign();

                        if (secondStageSpotSign == 0 || getSign() == secondStageSpotSign) {
                            if (secondStageSpotSign != 0) {
                                break outerLoop;
                            }
                        } else {
                            PreviewPanel secondStagePreviewPanel = grid.getGrid()[newX][newY].getPreviewPanel();
                            secondStagePreviewPanel.setVisible(true);
                            SelectPreviewPanels.add(secondStagePreviewPanel);
                            break outerLoop;
                        }
                        newX += direction[0];
                        newY += direction[1];
                    }
                }

                newX += direction[0];
                newY += direction[1];
            }
        }
    }
}

class HongPao extends Pao {
    public static int instanceCount;

    public HongPao(int x, int y) {
        super("resources/images/pieces/RC.GIF", x, y);
        instanceCount++;
    }

    @Override
    public int getSign() {
        return 1;
    }

    @Override
    public String getName() {
        return "红炮";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}

class HeiPao extends Pao {
    public static int instanceCount;

    public HeiPao(int x, int y) {
        super("resources/images/pieces/BC.GIF", x, y);
        instanceCount++;
    }

    @Override
    public int getSign() {
        return 2;
    }

    @Override
    public String getName() {
        return "黑炮";
    }

    @Override
    public int getInstanceCount() {
        return instanceCount;
    }
}

