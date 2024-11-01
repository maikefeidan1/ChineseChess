package com.maikefeidan1;

import com.maikefeidan1.app.ChineseChess;
import com.maikefeidan1.pieces.*;

public class Start {
    public static void main(String[] args) {
        ChineseChess chineseChess = ChineseChess.getInstance();

        /* 示例1和示例2需要单独释放运行。
        Examples 1 and 2 require separate release runs. */

        /* example-1
        设置黑方在下。
        Set black side down. */
//        chineseChess.setBlackOnBottom();

        /* example-2
        自定义开局初始化数组。
        Custom opening initialization array. */
//        chineseChess.customAllPiece(new Piece[]{ShuaiOrJiang.CreatePiece(4, 0, 2), Shi.CreatePiece(3, 0, 2), Shi.CreatePiece(5, 0, 2), Xiang.CreatePiece(2, 0, 2), Xiang.CreatePiece(6, 0, 2), Ma.CreatePiece(2, 2, 2), Ma.CreatePiece(6, 2, 2),
//                Ju.CreatePiece(0, 0, 2), Ju.CreatePiece(7, 0, 2), Pao.CreatePiece(1, 2, 2), Pao.CreatePiece(6, 1, 2), BingOrZu.CreatePiece(0, 3, 2), Xiang.CreatePiece(2, 9, 1), Xiang.CreatePiece(6, 9, 1), BingOrZu.CreatePiece(2, 3, 2),
//                BingOrZu.CreatePiece(4, 3, 2), BingOrZu.CreatePiece(6, 4, 2), BingOrZu.CreatePiece(8, 3, 2), ShuaiOrJiang.CreatePiece(4, 9, 1), Shi.CreatePiece(3, 9, 1), Shi.CreatePiece(5, 9, 1), Ma.CreatePiece(1, 9, 1), Ma.CreatePiece(6, 7, 1),
//                Ju.CreatePiece(0, 9, 1), Ju.CreatePiece(5, 3, 1), Pao.CreatePiece(3, 7, 1), Pao.CreatePiece(4, 7, 1), BingOrZu.CreatePiece(0, 6, 1), BingOrZu.CreatePiece(2, 5, 1), BingOrZu.CreatePiece(4, 6, 1),
//                BingOrZu.CreatePiece(6, 6, 1), BingOrZu.CreatePiece(8, 6, 1)});

        /* 自定义开局初始化数组时，该数组中的棋子初始化位置应对应各自红黑方的上下方向（否则会报错），比如如果你设置的是黑方在下，则黑方棋子位置应该在下方，红方反之,
        比如如果示例1和示例2同时释放运行则会报错,因为示例2中自定义的是黑方在上的自定义开局初始化数组而不是黑方在下。
        When customizing the opening initialization array, the initialization position of the pieces in the array should be the direction of the red and black sides (otherwise, an error will be reported), for example, if you set the black side down, the black side position should be below, and the red side vice versa.
        For example, if example 1 and Example 2 are released at the same time, an error will be reported because the custom opening initialization array with black on top is customized in Example 2 instead of black on bottom. */

        /* 设置是否黑方先走。
        Set whether black goes first. */
//        chineseChess.setBlackFirst();

        /* 最后开始游戏。
        Finally start the game. */
        chineseChess.start();
    }
}
