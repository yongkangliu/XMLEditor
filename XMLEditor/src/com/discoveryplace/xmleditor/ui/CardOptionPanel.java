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

public class CardOptionPanel extends CardNodePanel {

    private static final long serialVersionUID = -8229602809184488899L;
    public static String OPTION = "option";

    private JCheckBox isAnswerCheckBox = new JCheckBox("Correct Option");

    private static CardOptionPanel instance = new CardOptionPanel();

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

    public static CardOptionPanel getInstance() {
        return CardOptionPanel.instance;
    }

    public String getName() {
        return CardOptionPanel.OPTION;
    }

    public void showNodeValue(TreeNode node) {
        super.showNodeValue(node);
        this.isAnswerCheckBox.setSelected(((TreeNodeOption) node).isAnswer());
    }

    protected void setNodeValue() {
        super.setNodeValue();
        ((TreeNodeOption) this.treeNode).setAnswer(String.valueOf(this.isAnswerCheckBox.isSelected()));
    }
}
