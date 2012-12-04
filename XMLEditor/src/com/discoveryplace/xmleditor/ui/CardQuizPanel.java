/*
 * UNC Charlotte ITCS 6112 Software Systems Design and Implementation
 * 
 * by Yongkang Liu, 12/02/2012
 */
package com.discoveryplace.xmleditor.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.discoveryplace.xmleditor.data.TreeNode;

/**
 * The class of quiz node attributes panel. It's a singleton class.
 */
public class CardQuizPanel extends CardNodePanel {

    private static final long serialVersionUID = -6788291539698426532L;
    public static String QUIZ = "quiz";

    private static CardQuizPanel instance = new CardQuizPanel();

    /**
     * Private constructor
     */
    private CardQuizPanel() {
        JButton quizButton = new JButton("save");
        this.add(quizButton);

        // Button listener
        quizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNodeValue();
            }
        });
    }

    /**
     * Get the CardQuizPanel instance.
     * 
     * @return Return the CardQuizPanel instance.
     */
    public static CardQuizPanel getInstance() {
        return CardQuizPanel.instance;
    }

    /**
     * Get the quiz name.
     */
    public String getName() {
        return CardQuizPanel.QUIZ;
    }

    /**
     * Show the quiz node in GUI.
     */
    public void showNodeValue(TreeNode node) {
        super.showNodeValue(node);
    }

    /**
     * Set the quiz node value from UI to data objects.
     */
    protected void setNodeValue() {
        super.setNodeValue();
    }
}
