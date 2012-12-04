/*
 * UNC Charlotte ITCS 6112 Software Systems Design and Implementation
 * 
 * by Yongkang Liu, 12/02/2012
 */
package com.discoveryplace.xmleditor.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import com.discoveryplace.xmleditor.data.TreeNode;
import com.discoveryplace.xmleditor.data.TreeNodeOption;

/**
 * The class of option node attributes panel. It's a singleton class.
 */
public class CardOptionPanel extends CardNodePanel {

    private static final long serialVersionUID = -8229602809184488899L;
    public static String OPTION = "option";

    // The check box for the correct answer.
    private JCheckBox isAnswerCheckBox = new JCheckBox("Correct Option");

    // The CardOptionPanel object instance.
    private static CardOptionPanel instance = new CardOptionPanel();

    /**
     * Constructor
     */
    private CardOptionPanel() {
        // Fields for Option information
        JPanel line01 = new JPanel(new GridLayout(1, 2));
        JLabel line01Label = new JLabel("");
        line01Label.setHorizontalAlignment(SwingConstants.RIGHT);
        line01.add(line01Label);
        line01.add(isAnswerCheckBox);
        this.add(line01);

        JButton optionButton = new JButton("save");
        this.add(optionButton);

        optionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNodeValue();
            }
        });
    }

    /**
     * Get the CardOptionPanel instance.
     * 
     * @return
     */
    public static CardOptionPanel getInstance() {
        return CardOptionPanel.instance;
    }

    /**
     * Get the option node name
     */
    public String getName() {
        return CardOptionPanel.OPTION;
    }

    /**
     * Show the option node value in GUI
     * 
     * @param node
     *            The XML node.
     */
    public void showNodeValue(TreeNode node) {
        super.showNodeValue(node);
        this.isAnswerCheckBox.setSelected(((TreeNodeOption) node).isAnswer());
    }

    /**
     * Set the option node value from UI to data objects.
     */
    protected void setNodeValue() {
        super.setNodeValue();
        ((TreeNodeOption) this.treeNode).setAnswer(String.valueOf(this.isAnswerCheckBox.isSelected()));
    }
}
