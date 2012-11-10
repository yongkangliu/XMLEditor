package com.discoveryplace.xmleditor.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import com.discoveryplace.xmleditor.data.TreeNode;

public class CardQuizPanel extends CardNodePanel {

    private static final long serialVersionUID = -6788291539698426532L;
    public static String QUIZ = "quiz";

    private static CardQuizPanel instance = new CardQuizPanel();

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

    public static CardQuizPanel getInstance() {
        return CardQuizPanel.instance;
    }

    public String getName() {
        return CardQuizPanel.QUIZ;
    }

    public void showNodeValue(TreeNode node) {
        super.showNodeValue(node);
    }

    protected void setNodeValue() {
        super.setNodeValue();
    }
}
