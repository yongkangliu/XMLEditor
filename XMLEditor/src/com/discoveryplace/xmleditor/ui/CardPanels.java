/*
 * UNC Charlotte ITCS 6112 Software Systems Design and Implementation
 * 
 * by Yongkang Liu, 12/02/2012
 */
package com.discoveryplace.xmleditor.ui;

import java.awt.CardLayout;

import javax.swing.JPanel;

import com.discoveryplace.xmleditor.data.TreeNode;

/**
 * The node attributes layout panel. It's a singleton class.
 */
public class CardPanels extends JPanel {

    private static final long serialVersionUID = -1274239241918798094L;

    // The CardPanels instance.
    private static CardPanels instance = new CardPanels();

    /**
     * Private constructor.
     */
    private CardPanels() {
        super(new CardLayout());

        CardQuizPanel quizInstance = CardQuizPanel.getInstance();
        this.add(quizInstance, quizInstance.getName());

        CardQuestionPanel questionInstance = CardQuestionPanel.getInstance();
        this.add(questionInstance, questionInstance.getName());

        CardOptionPanel optionInstance = CardOptionPanel.getInstance();
        this.add(optionInstance, optionInstance.getName());

    }

    /**
     * Return the CardPanels instance.
     * 
     * @return The CardPanels instance.
     */
    public static CardPanels getInstance() {
        return CardPanels.instance;
    }

    /**
     * Show the node information.
     * 
     * @param name
     *            The node name
     * @param node
     *            The node
     */
    public void displayPanel(String name, TreeNode node) {
        CardLayout cl = (CardLayout) (this.getLayout());
        cl.show(this, name);
        node.showNodeValue();
    }
}
