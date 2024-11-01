package com.maikefeidan1.data;

import com.maikefeidan1.panel.PreviewPanel;

import java.util.ArrayList;

public class SelectPreviewPanels {
    private static SelectPreviewPanels instance;
    private ArrayList<PreviewPanel> selectPreviewPanels;

    private SelectPreviewPanels() {};

    public static SelectPreviewPanels getInstance() {
        if (instance == null) {
            instance = new SelectPreviewPanels();
        }
        return instance;
    }

    public ArrayList<PreviewPanel> getSelectPreviewPanels() {
        return selectPreviewPanels;
    }

    public void setSelectPreviewPanels(ArrayList<PreviewPanel> selectPreviewPanels) {
        this.selectPreviewPanels = selectPreviewPanels;
    }

}
