package com.maikefeidan1.panel;

import javax.swing.*;
import java.awt.*;

public class PreviewPanel extends JPanel {
    private final int x;
    private final int y;

    public PreviewPanel(int x, int y) {
        this.x = x;
        this.y = y;
        setBounds(33 + this.x * 67, 34 + this.y * 67, 15, 15);
        setVisible(false);
    }

    public int getPreviewPanelX() {
        return x;
    }

    public int getPreviewPanelY() {
        return y;
    }

    @Override
    public void paintComponent(Graphics g) {
        g.setColor(Color.orange);
        g.fillOval(0, 0, 15, 15);
    }
}
