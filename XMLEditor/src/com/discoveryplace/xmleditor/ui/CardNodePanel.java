package com.discoveryplace.xmleditor.ui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.discoveryplace.xmleditor.data.TreeNode;

public abstract class CardNodePanel extends JPanel {

    private static final long serialVersionUID = -277833333995261230L;

    protected JTextField name = new JTextField();
    protected JTextField description = new JTextField();
    protected TreeNode treeNode;

    public CardNodePanel() {
        super(new GridLayout(12, 1));
        // Fields for Quiz information
        JPanel line1 = new JPanel(new GridLayout(1, 2));
        JLabel line1Label = new JLabel("Name:");
        line1Label.setHorizontalAlignment(SwingConstants.RIGHT);
        line1.add(line1Label);
        line1.add(this.name);
        this.add(line1);

        JPanel line2 = new JPanel(new GridLayout(1, 2));
        JLabel line2Label = new JLabel("Description:");
        line2Label.setHorizontalAlignment(SwingConstants.RIGHT);
        line2.add(line2Label);
        line2.add(this.description);
        this.add(line2);
    }

    public void showNodeValue(TreeNode node) {
        this.treeNode = node;

        this.name.setText(node.getName());
        this.description.setText(node.getDescription());
    }

    protected void setNodeValue() {
        if (this.treeNode != null) {
            this.treeNode.setName(this.name.getText());
            this.treeNode.setDescription(this.description.getText());
        }
    }
}
