package com.maikefeidan1.panel;

import javax.swing.*;

public class Board extends JLabel {

    private static Board instance;

    private Board() {
        super(new ImageIcon("resources/images/board/ZMBLL.png"));
        setBounds(0, 0, 622, 688);
    }

    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }
}
