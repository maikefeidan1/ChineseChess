package com.maikefeidan1.factory;

import com.maikefeidan1.core.InitGame;
import com.maikefeidan1.core.MainControl;
import com.maikefeidan1.data.*;
import com.maikefeidan1.manager.PieceArrayManager;
import com.maikefeidan1.panel.Selected;

public class GameMainFactory {
    private static GameMainFactory instance;
    private final Grid grid = Grid.getInstance();
    private final InitPieces initPieces = InitPieces.getInstance();
    private final Flip flip = Flip.getInstance();
    private final Selected selected = Selected.getInstance();

    private GameMainFactory() {
    }

    public static GameMainFactory getInstance() {
        if (instance == null) {
            instance = new GameMainFactory();
        }
        return instance;
    }

    public InitGame createInitGame() {
        MainControl movingPiece = createMainControl();
        PieceArrayManager pieceArrayManager = PieceArrayManager.getInstance();
        return InitGame.getInstance(movingPiece, pieceArrayManager, flip, initPieces);
    }

    private MainControl createMainControl() {
        SelectPiece selectPiece = SelectPiece.getInstance();
        History history = History.getInstance();
        GameSteps gameSteps = GameSteps.getInstance();
        SelectPreviewPanels selectPreviewPanels = SelectPreviewPanels.getInstance();
        return MainControl.getInstance(grid, initPieces, flip, selectPiece, history, selected, gameSteps,selectPreviewPanels);
    }
}
