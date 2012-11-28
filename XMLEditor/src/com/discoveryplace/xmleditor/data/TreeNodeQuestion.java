package com.discoveryplace.xmleditor.data;

import com.discoveryplace.xmleditor.ui.CardQuestionPanel;

public class TreeNodeQuestion extends TreeNode {
    private String url;
    private String questionType = CardQuestionPanel.TEXT;
    private String questionLevel = CardQuestionPanel.MEDIUM;

    public String getQuestionLevel() {
        return questionLevel;
    }

    public void setQuestionLevel(String questionLevel) {
        this.questionLevel = questionLevel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public TreeNodeQuestion(String name) {
        super(name);
    }

    public void showNodeValue() {
        CardQuestionPanel questionPanel = CardQuestionPanel.getInstance();
        questionPanel.showNodeValue(this);
    }
}
