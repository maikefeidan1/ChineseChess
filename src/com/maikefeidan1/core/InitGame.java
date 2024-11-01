package com.maikefeidan1.core;

import com.maikefeidan1.data.Flip;
import com.maikefeidan1.data.GameSteps;
import com.maikefeidan1.data.Grid;
import com.maikefeidan1.data.InitPieces;
import com.maikefeidan1.manager.PieceArrayManager;
import com.maikefeidan1.panel.Board;
import com.maikefeidan1.pieces.*;
import com.maikefeidan1.panel.PreviewPanel;
import com.maikefeidan1.panel.Selected;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.*;

public class InitGame {
    private static InitGame instance;
    //↓-注入的依赖项//
    private final MainControl mainControl;
    private final PieceArrayManager pieceArrayManager;
    private final Flip flip;
    private final InitPieces initPieces;
    //↑-注入的依赖项//
    private boolean isExecuting = false;
    private final Object lock = new Object();

    private InitGame(MainControl moving, PieceArrayManager pieceArrayManager, Flip flip, InitPieces initPieces) {
        this.mainControl = moving;
        this.pieceArrayManager = pieceArrayManager;
        this.flip = flip;
        this.initPieces = initPieces;
    }

    public static InitGame getInstance(MainControl mainControl, PieceArrayManager pieceArrayManager, Flip flip, InitPieces initPieces) {
        if (instance == null) {
            instance = new InitGame(mainControl, pieceArrayManager, flip, initPieces);
        }
        return instance;
    }

    private void initGame() {
        Grid grid = Grid.getInstance();
        Selected selected = Selected.getInstance();

        JFrame frame = new JFrame("ChineseChess");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setBounds(0, 0, 622, 688);

        layeredPane.addKeyListener(new BoardKeyListener());

        Board board = Board.getInstance();
        layeredPane.add(board, Integer.valueOf(0));

        PieceListener pieceListener = new PieceListener();

        for (Piece piece : initPieces.getInitPieces()) {
            piece.addMouseListener(pieceListener);

            layeredPane.add(piece, Integer.valueOf(1));

            int x = piece.getPieceX();
            int y = piece.getPieceY();

            grid.getGrid()[x][y].setSignAndPiece(piece);
        }

        layeredPane.add(selected, Integer.valueOf(1));

        PreviewPanelListener previewPanelListener = new PreviewPanelListener();

        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 10; j++) {
                PreviewPanel previewPanel = new PreviewPanel(i, j);
                previewPanel.addMouseListener(previewPanelListener);
                layeredPane.add(previewPanel, Integer.valueOf(2));
                grid.getGrid()[i][j].setPreviewPanel(previewPanel);
            }
        }

        frame.getContentPane().add(layeredPane);

        frame.setBounds(435, 36, 634, 726);
        frame.setResizable(false);
        frame.setVisible(true);
        layeredPane.requestFocus();
    }

    public void init(Piece[] customPieces, boolean isSetBlackOnBottom, boolean isSetBlackFirst) {
        if (isSetBlackOnBottom) {
            flip.setBlackOnBottom();
        }

        if (isSetBlackFirst) {
            GameSteps.getInstance().setBlackFirst();
        }

        initPieces.setInitPieces(customPieces == null ? pieceArrayManager.createAllPieceForNormal() : pieceArrayManager.checkCustomPieces(customPieces));

        initGame();
    }

    private class PieceListener implements MouseListener {
        @Override
        public void mouseClicked(MouseEvent e) {
            synchronized (lock) {
                if (!isExecuting) {
                    isExecuting = true;
                    mainControl.selectPiece((Piece) e.getSource());
                    isExecuting = false;
                }
            }
        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class PreviewPanelListener implements MouseListener {

        @Override
        public void mouseClicked(MouseEvent e) {
            synchronized (lock) {
                if (!isExecuting) {
                    isExecuting = true;
                    mainControl.movingPiece((PreviewPanel) e.getSource());
                    isExecuting = false;
                }
            }

        }

        @Override
        public void mousePressed(MouseEvent e) {

        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class BoardKeyListener implements KeyListener {

        @Override
        public void keyTyped(KeyEvent e) {

        }

        @Override
        public void keyPressed(KeyEvent e) {
            synchronized (lock) {
                int code = e.getKeyCode();

                if (code == 'A') {
                    if (!isExecuting) {
                        isExecuting = true;
                        mainControl.undo();
                        isExecuting = false;
                    }
                }

                if (code == 'D') {
                    if (!isExecuting) {
                        isExecuting = true;
                        mainControl.redo();
                        isExecuting = false;
                    }
                }

                if (code == 'R') {
                    if (!isExecuting) {
                        isExecuting = true;
                        mainControl.resetGame();
                        isExecuting = false;
                    }
                }

                if (code == 'F') {
                    if (!isExecuting) {
                        isExecuting = true;
                        mainControl.boardFlip();
                        isExecuting = false;
                    }
                }
            }
        }

        @Override
        public void keyReleased(KeyEvent e) {

        }
    }
}

