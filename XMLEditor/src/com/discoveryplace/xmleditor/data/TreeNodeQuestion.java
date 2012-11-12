package com.discoveryplace.xmleditor.data;

import com.discoveryplace.xmleditor.ui.CardQuestionPanel;

public class TreeNodeQuestion extends TreeNode {
    private String url;

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

    private String questionType;

    public TreeNodeQuestion(String name) {
        super(name);
    }

    public void showNodeValue() {
        CardQuestionPanel questionPanel = CardQuestionPanel.getInstance();
        questionPanel.showNodeValue(this);
    }
}
