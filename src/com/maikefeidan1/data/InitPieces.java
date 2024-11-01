package com.maikefeidan1.data;

import com.maikefeidan1.pieces.Piece;

import java.util.ArrayList;
import java.util.Arrays;

public class InitPieces {
    private static InitPieces instance;
    private final ArrayList<Piece> initPieces = new ArrayList<>();

    private InitPieces() {
    }

    public static InitPieces getInstance() {
        if (instance == null) {
            instance = new InitPieces();
        }
        return instance;
    }

    public void flipInitPieces() {
        for (Piece piece : initPieces) {
            int x = piece.getSymmetryX();
            int y = piece.getSymmetryY();

            piece.setLocation(x,y);

            piece.setPieceX(x);
            piece.setPieceY(y);
        }
    }

    public void setInitPieces(Piece[] stockPieces) {
        initPieces.addAll(Arrays.asList(stockPieces));
    }

    public ArrayList<Piece> getInitPieces() {
        return initPieces;
    }
}
