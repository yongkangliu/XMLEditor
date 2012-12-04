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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.discoveryplace.xmleditor.data.TreeNode;
import com.discoveryplace.xmleditor.data.TreeNodeQuestion;

/**
 * The class of question node attributes panel. It's a singleton class.
 */
public class CardQuestionPanel extends CardNodePanel {

    private static final long serialVersionUID = -3942735705442838426L;
    public static String QUESTION = "question";

    public static String TEXT = "Text";
    private static String IMAGE = "Image";
    private static String VIDEO = "Video";

    private static String HARD = "Hard";
    public static String MEDIUM = "Medium";
    private static String EASY = "Easy";

    // The CardQuestionPanel class instance.
    private static CardQuestionPanel instance = new CardQuestionPanel();

    // The layout panel
    private JPanel lineUrl = new JPanel(new GridLayout(1, 2));

    // The question url field
    private JTextField questionURL = new JTextField();

    // The question type Combobox
    private JComboBox<String> questionType = new JComboBox<String>(new String[] { TEXT, IMAGE, VIDEO });

    // The question level Combobox
    private JComboBox<String> questionLevel = new JComboBox<String>(new String[] { EASY, MEDIUM, HARD });

    /**
     * Private constructor
     */
    private CardQuestionPanel() {
        // Fields for Question information
        JPanel line20 = new JPanel(new GridLayout(1, 2));
        JLabel line20Label = new JLabel("Question Level:");
        line20Label.setHorizontalAlignment(SwingConstants.RIGHT);
        line20.add(line20Label);
        this.questionLevel.setSelectedItem(0);
        line20.add(this.questionLevel);
        this.add(line20);

        JPanel line21 = new JPanel(new GridLayout(1, 2));
        JLabel line21Label = new JLabel("Question Type:");
        line21Label.setHorizontalAlignment(SwingConstants.RIGHT);
        line21.add(line21Label);
        this.questionType.setSelectedItem(0);
        line21.add(this.questionType);
        this.add(line21);

        JLabel line22Label = new JLabel("Image/Video URL:");
        line22Label.setHorizontalAlignment(SwingConstants.RIGHT);
        this.lineUrl.add(line22Label);
        this.lineUrl.add(this.questionURL);
        this.add(this.lineUrl);
        this.lineUrl.setVisible(false);

        JButton questionButton = new JButton("save");
        this.add(questionButton);

        questionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNodeValue();
            }
        });

        this.questionType.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JComboBox<String> source = (JComboBox<String>) e.getSource();
                String type = (String) source.getSelectedItem();
                if (TEXT.equals(type)) {
                    lineUrl.setVisible(false);
                    questionURL.setText("");
                } else {
                    lineUrl.setVisible(true);
                }
            }
        });
    }

    /**
     * Get the CardQuestionPanel instance.
     * 
     * @return Return the CardQuestionPanel instance.
     */
    public static CardQuestionPanel getInstance() {
        return CardQuestionPanel.instance;
    }

    /**
     * Get the question node name.
     */
    public String getName() {
        return CardQuestionPanel.QUESTION;
    }

    /**
     * Show the question node in GUI.
     */
    public void showNodeValue(TreeNode node) {
        super.showNodeValue(node);
        this.questionLevel.setSelectedItem(((TreeNodeQuestion) node).getQuestionLevel());
        String type = ((TreeNodeQuestion) node).getQuestionType();
        this.questionType.setSelectedItem(type);
        if (TEXT.equals(type)) {
            this.lineUrl.setVisible(false);
        } else {
            this.lineUrl.setVisible(true);
            this.questionURL.setText(((TreeNodeQuestion) node).getUrl());
        }
    }

    /**
     * Set the question node value from UI to data objects.
     */
    protected void setNodeValue() {
        super.setNodeValue();
        ((TreeNodeQuestion) this.treeNode).setQuestionType((String) this.questionType.getSelectedItem());
        ((TreeNodeQuestion) this.treeNode).setUrl(this.questionURL.getText());
        ((TreeNodeQuestion) this.treeNode).setQuestionLevel((String) this.questionLevel.getSelectedItem());
    }
}
