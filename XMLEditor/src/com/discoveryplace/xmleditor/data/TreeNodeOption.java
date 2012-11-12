package com.discoveryplace.xmleditor.data;

import com.discoveryplace.xmleditor.ui.CardOptionPanel;

public class TreeNodeOption extends TreeNode {
    private boolean isAnswer = false;

    public boolean isAnswer() {
        return isAnswer;
    }

    public void setAnswer(String isAnswer) {
        if ("true".equals(isAnswer)) {
            this.isAnswer = true;
        } else {
            this.isAnswer = false;
        }
    }

    public TreeNodeOption(String name) {
        super(name);
    }

    public void showNodeValue() {
        CardOptionPanel panel = CardOptionPanel.getInstance();
        panel.showNodeValue(this);
    }

    public String toString() {
        if (this.isAnswer) {
            return "*" + this.name;
        } else {
            return this.name;
        }
    }
}
