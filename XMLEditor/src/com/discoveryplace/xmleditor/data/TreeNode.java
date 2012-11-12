package com.discoveryplace.xmleditor.data;

public abstract class TreeNode {
    protected String name;
    protected String description;

    public TreeNode() {

    }

    public TreeNode(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void showNodeValue() {

    }

}
