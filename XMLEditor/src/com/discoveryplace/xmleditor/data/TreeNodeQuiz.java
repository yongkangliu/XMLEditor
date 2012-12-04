/*
 * UNC Charlotte ITCS 6112 Software Systems Design and Implementation
 * 
 * by Yongkang Liu, 12/02/2012
 */
package com.discoveryplace.xmleditor.data;

import com.discoveryplace.xmleditor.ui.CardQuizPanel;

/**
 * The quiz nodes class
 */
public class TreeNodeQuiz extends TreeNode {
    /**
     * Constructor
     * 
     * @param name
     *            The quiz node name.
     */
    public TreeNodeQuiz(String name) {
        super(name);
    }

    /**
     * Show the quiz node in GUI.
     */
    public void showNodeValue() {
        CardQuizPanel quizPanel = CardQuizPanel.getInstance();
        quizPanel.showNodeValue(this);
    }
}
