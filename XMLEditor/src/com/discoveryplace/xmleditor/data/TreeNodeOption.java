package com.discoveryplace.xmleditor.data;
import com.discoveryplace.xmleditor.ui.CardOptionPanel;

public class TreeNodeOption extends TreeNode {
    public TreeNodeOption(String name) {
        super.name = name;
    }

    public void showNodeValue() {
        CardOptionPanel panel = CardOptionPanel.getInstance();
        panel.showNodeValue(this);
    }
}
