package com.maikefeidan1.panel;

import javax.swing.*;
import java.awt.*;

public class Selected extends JLabel {

    private static Selected instance;

    public Selected() {
        super(getEnlargedImage());
        setSize(71, 71);
        setVisible(false);
    }

    public static Selected getInstance() {
        if (instance == null) {
            instance = new Selected();
        }
        return instance;
    }

    private static ImageIcon getEnlargedImage() {
        ImageIcon icon = new ImageIcon("resources/images/pieces/OOS.GIF");
        icon.setImage(icon.getImage().getScaledInstance(71, 71, Image.SCALE_DEFAULT));
        return icon;
    }

    @Override
    public void setLocation(int x, int y) {
        super.setLocation(6 + x * 67, 7 + y * 67);
    }
}
