package com.discoveryplace.xmleditor.ui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.discoveryplace.xmleditor.data.TreeNode;
import com.discoveryplace.xmleditor.data.TreeNodeQuestion;

public class CardQuestionPanel extends CardNodePanel {

    private static final long serialVersionUID = -3942735705442838426L;
    public static String QUESTION = "question";

    private static String TEXT = "text";
    private static String IMAGE = "image";
    private static String VIDEO = "video";
    private static CardQuestionPanel instance = new CardQuestionPanel();

    private JTextField questionURL = new JTextField();
    private JComboBox<String> questionType = new JComboBox<String>(new String[] { TEXT, IMAGE, VIDEO });

    private CardQuestionPanel() {
        // Fields for Question information
        JPanel line20 = new JPanel(new GridLayout(1, 2));
        JLabel line20Label = new JLabel("Question Type:");
        line20Label.setHorizontalAlignment(SwingConstants.RIGHT);
        line20.add(line20Label);
        line20.add(this.questionType);
        this.add(line20);

        JPanel line21 = new JPanel(new GridLayout(1, 2));
        JLabel line21Label = new JLabel("Image/Video URL:");
        line21Label.setHorizontalAlignment(SwingConstants.RIGHT);
        line21.add(line21Label);
        line21.add(this.questionURL);
        this.add(line21);

        JButton questionButton = new JButton("save");
        this.add(questionButton);

        questionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNodeValue();
            }
        });
    }

    public static CardQuestionPanel getInstance() {
        return CardQuestionPanel.instance;
    }

    public String getName() {
        return CardQuestionPanel.QUESTION;
    }

    public void showNodeValue(TreeNode node) {
        super.showNodeValue(node);
        this.questionType.setSelectedItem(((TreeNodeQuestion) node).getQuestionType());
        this.questionURL.setText(((TreeNodeQuestion) node).getUrl());
    }

    protected void setNodeValue() {
        super.setNodeValue();
        ((TreeNodeQuestion) this.treeNode).setQuestionType((String) this.questionType.getSelectedItem());
        ((TreeNodeQuestion) this.treeNode).setUrl(this.questionURL.getText());
    }
}
