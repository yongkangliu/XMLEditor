package com.discoveryplace.xmleditor.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.discoveryplace.xmleditor.data.TreeNode;

public class CardOptionPanel extends CardNodePanel {

    private static final long serialVersionUID = -8229602809184488899L;
    public static String OPTION = "option";

    private JTextField optionField1 = new JTextField();

    private static CardOptionPanel instance = new CardOptionPanel();

    private CardOptionPanel() {
        // Fields for Option information
        JPanel line30 = new JPanel(new GridLayout(1, 2));
        JLabel line30Label = new JLabel("Field1:");
        line30Label.setHorizontalAlignment(SwingConstants.RIGHT);
        line30.add(line30Label);
        line30.add(optionField1);
        this.add(line30);

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
    }

    protected void setNodeValue() {
        super.setNodeValue();
    }
}
