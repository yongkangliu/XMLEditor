/*
 * UNC Charlotte ITCS 6112 Software Systems Design and Implementation
 * 
 * by Yongkang Liu, 12/02/2012
 */
package com.discoveryplace.xmleditor.data;

import com.discoveryplace.xmleditor.ui.CardOptionPanel;

/**
 * The question option nodes class
 * 
 */
public class TreeNodeOption extends TreeNode {
    // if isAnswer is true, the option is the correct answer of the question.
    private boolean isAnswer = false;

    /**
     * Get the answer.
     * 
     * @return Return the answer.
     */
    public boolean isAnswer() {
        return isAnswer;
    }

    /**
     * Set the answer
     * 
     * @param isAnswer
     *            The answer.
     */
    public void setAnswer(String isAnswer) {
        if ("true".equals(isAnswer)) {
            this.isAnswer = true;
        } else {
            this.isAnswer = false;
        }
    }

    /**
     * Set the node name
     * 
     * @param name
     */
    public TreeNodeOption(String name) {
        super(name);
    }

    /**
     * Show the node in GUI.
     */
    public void showNodeValue() {
        CardOptionPanel panel = CardOptionPanel.getInstance();
        panel.showNodeValue(this);
    }

    /**
     * Add "*" to indicate the option is the correct answer.
     */
    public String toString() {
        if (this.isAnswer) {
            return "*" + this.name;
        } else {
            return this.name;
        }
    }
}
