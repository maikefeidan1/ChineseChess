package com.maikefeidan1.manager;

import com.maikefeidan1.exception.CoordinateInitializationException;
import com.maikefeidan1.exception.CoordinateOverlapException;
import com.maikefeidan1.exception.InstanceCountExceededException;
import com.maikefeidan1.data.Flip;
import com.maikefeidan1.pieces.*;

public class PieceArrayManager {
    private static PieceArrayManager instance;

    private PieceArrayManager() {
    }

    public static PieceArrayManager getInstance() {
        if (instance == null) {
            instance = new PieceArrayManager();
        }
        return instance;
    }

    public Piece[] createAllPieceForNormal() {
        boolean isRedToTop = Flip.getInstance().getIsBlackOnBottom();

        Piece[] allPiece = isRedToTop ? new Piece[]{ShuaiOrJiang.CreatePiece(4, 9, 2), Shi.CreatePiece(5, 9, 2), Shi.CreatePiece(3, 9, 2), Xiang.CreatePiece(6, 9, 2), Xiang.CreatePiece(2, 9, 2), Ma.CreatePiece(7, 9, 2), Ma.CreatePiece(1, 9, 2),
                Ju.CreatePiece(8, 9, 2), Ju.CreatePiece(0, 9, 2), Pao.CreatePiece(7, 7, 2), Pao.CreatePiece(1, 7, 2), BingOrZu.CreatePiece(8, 6, 2), Xiang.CreatePiece(6, 0, 1), Xiang.CreatePiece(2, 0, 1), BingOrZu.CreatePiece(6, 6, 2),
                BingOrZu.CreatePiece(4, 6, 2), BingOrZu.CreatePiece(2, 6, 2), BingOrZu.CreatePiece(0, 6, 2), ShuaiOrJiang.CreatePiece(4, 0, 1), Shi.CreatePiece(5, 0, 1), Shi.CreatePiece(3, 0, 1), Ma.CreatePiece(7, 0, 1), Ma.CreatePiece(1, 0, 1),
                Ju.CreatePiece(8, 0, 1), Ju.CreatePiece(0, 0, 1), Pao.CreatePiece(7, 2, 1), Pao.CreatePiece(1, 2, 1), BingOrZu.CreatePiece(8, 3, 1), BingOrZu.CreatePiece(6, 3, 1), BingOrZu.CreatePiece(4, 3, 1),
                BingOrZu.CreatePiece(2, 3, 1), BingOrZu.CreatePiece(0, 3, 1)
        } : new Piece[]{ShuaiOrJiang.CreatePiece(4, 0, 2), Shi.CreatePiece(3, 0, 2), Shi.CreatePiece(5, 0, 2), Xiang.CreatePiece(2, 0, 2), Xiang.CreatePiece(6, 0, 2), Ma.CreatePiece(1, 0, 2), Ma.CreatePiece(7, 0, 2),
                Ju.CreatePiece(0, 0, 2), Ju.CreatePiece(8, 0, 2), Pao.CreatePiece(1, 2, 2), Pao.CreatePiece(7, 2, 2), BingOrZu.CreatePiece(0, 3, 2), Xiang.CreatePiece(2, 9, 1), Xiang.CreatePiece(6, 9, 1), BingOrZu.CreatePiece(2, 3, 2),
                BingOrZu.CreatePiece(4, 3, 2), BingOrZu.CreatePiece(6, 3, 2), BingOrZu.CreatePiece(8, 3, 2), ShuaiOrJiang.CreatePiece(4, 9, 1), Shi.CreatePiece(3, 9, 1), Shi.CreatePiece(5, 9, 1), Ma.CreatePiece(1, 9, 1), Ma.CreatePiece(7, 9, 1),
                Ju.CreatePiece(0, 9, 1), Ju.CreatePiece(8, 9, 1), Pao.CreatePiece(1, 7, 1), Pao.CreatePiece(7, 7, 1), BingOrZu.CreatePiece(0, 6, 1), BingOrZu.CreatePiece(2, 6, 1), BingOrZu.CreatePiece(4, 6, 1),
                BingOrZu.CreatePiece(6, 6, 1), BingOrZu.CreatePiece(8, 6, 1)};

        checkPieces(allPiece);

        return allPiece;
    }

    public Piece[] checkCustomPieces(Piece[] customPieces) {
        checkPieces(customPieces);
        return customPieces;
    }

    private void checkPieces(Piece[] customPieces) {
        //检查棋子的标记是否正确
        try {
            for (Piece piece : customPieces) {
                if (piece == null) {
                    throw new NullPointerException("某个棋子的标记错误");
                }
            }
        } catch (NullPointerException pieceSignInvalidException) {
            pieceSignInvalidException.printStackTrace();
            System.exit(1);
        }

        //检查棋子的数量是否超出对应数量
        try {
            for (Piece piece : customPieces) {
                if (piece.getInstanceCount() > piece.getMaxInstanceCount()) {
                    throw new InstanceCountExceededException(piece.getName() + "数量超出了" + (piece.getInstanceCount() - 1) + "!");
                }
            }
        } catch (InstanceCountExceededException instanceCountExceededException) {
            instanceCountExceededException.printStackTrace();
            System.exit(1);
        }

        //检查棋子的初始化坐标是否正确
        try {
            for (Piece piece : customPieces) {
                if (piece.checkCoordinate()) {
                    throw new CoordinateInitializationException(piece.getCoordinateInitializationErrorMessage());
                }
            }
        } catch (CoordinateInitializationException coordinateInitializationException) {
            coordinateInitializationException.printStackTrace();
            System.exit(1);
        }

        //检查棋子是否与其他棋子的坐标发生重叠
        try {
            for (int i = 0; i < customPieces.length - 1; i++) {
                Piece piece = customPieces[i];
                for (int j = i + 1; j < customPieces.length; j++) {
                    Piece nextPiece = customPieces[j];
                    if (piece.getPieceX() == nextPiece.getPieceX() && piece.getPieceY() == nextPiece.getPieceY()) {
                        throw new CoordinateOverlapException(piece.getName() + "与" + nextPiece.getName() + "的坐标发生了重叠！");
                    }
                }
            }
        } catch (CoordinateOverlapException coordinateOverlapException) {
            coordinateOverlapException.printStackTrace();
            System.exit(1);
        }
    }

//    public Piece[] flipCustomPieces(Piece[] customPieces) {
//
//    }
}