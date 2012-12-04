/*
 * UNC Charlotte ITCS 6112 Software Systems Design and Implementation
 * 
 * by Yongkang Liu, 12/02/2012
 */
package com.discoveryplace.xmleditor.data;

import com.discoveryplace.xmleditor.ui.CardQuestionPanel;

/**
 * The question nodes class
 */
public class TreeNodeQuestion extends TreeNode {

    // The media(image or video) url
    private String url;

    // The question type: Text, Image and Video
    private String questionType = CardQuestionPanel.TEXT;

    // The question level: Hard, Medium and Easy
    private String questionLevel = CardQuestionPanel.MEDIUM;

    /**
     * Constructor
     * 
     * @param name
     *            The question node name
     */
    public TreeNodeQuestion(String name) {
        super(name);
    }

    /**
     * Return the question level
     * 
     * @return Return the question level
     */
    public String getQuestionLevel() {
        return questionLevel;
    }

    /**
     * Set the question level
     * 
     * @param questionLevel
     *            The question level.
     */
    public void setQuestionLevel(String questionLevel) {
        this.questionLevel = questionLevel;
    }

    /**
     * Return the media url
     * 
     * @return The media url
     */
    public String getUrl() {
        return url;
    }

    /**
     * Set the media url
     * 
     * @param url
     *            The media url
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * Return the question type
     * 
     * @return The quesiton type
     */
    public String getQuestionType() {
        return questionType;
    }

    /**
     * Set the quesiton type
     * 
     * @param questionType
     *            The question type
     */
    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    /**
     * Show the quesiton node in GUI.
     */
    public void showNodeValue() {
        CardQuestionPanel questionPanel = CardQuestionPanel.getInstance();
        questionPanel.showNodeValue(this);
    }
}
