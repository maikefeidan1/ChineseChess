package com.maikefeidan1.data;

import com.maikefeidan1.pieces.Piece;

public class SelectPiece {

    private static SelectPiece instance;
    private Piece selectPiece;

    private SelectPiece() {
    }

    public static SelectPiece getInstance() {
        if (instance == null) {
            instance = new SelectPiece();
        }
        return instance;
    }

    public Piece getSelectPiece() {
        return selectPiece;
    }

    public void setSelectPiece(Piece selectPiece) {
        this.selectPiece = selectPiece;
    }
}
