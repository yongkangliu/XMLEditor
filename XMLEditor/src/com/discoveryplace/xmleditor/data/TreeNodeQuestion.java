package com.discoveryplace.xmleditor.data;
import com.discoveryplace.xmleditor.ui.CardQuestionPanel;

public class TreeNodeQuestion extends TreeNode {
    public TreeNodeQuestion(String name) {
        super.name = name;
    }

    public void showNodeValue() {
        CardQuestionPanel questionPanel = CardQuestionPanel.getInstance();
        questionPanel.showNodeValue(this);
    }
}
