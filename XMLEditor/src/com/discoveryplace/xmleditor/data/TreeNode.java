/*
 * UNC Charlotte ITCS 6112 Software Systems Design and Implementation
 * 
 * by Yongkang Liu, 12/02/2012
 */
package com.discoveryplace.xmleditor.data;

/**
 * The abstract class of XML tree node
 */
public abstract class TreeNode {
    // The node name
    protected String name;

    // The node description
    protected String description;

    /**
     * Constructor
     */
    public TreeNode() {
        // do nothing.
    }

    /**
     * Constructor
     * 
     * @param name
     *            The node name.
     */
    public TreeNode(String name) {
        this.name = name;
    }

    /**
     * Return the node name.
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * Return the node name.
     * 
     * @return Return the node name.
     */
    public String getName() {
        return this.name;
    }

    /**
     * Set the node name
     * 
     * @param name
     *            The node name.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Get the node description
     * 
     * @return Return the onde description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Set the node description.
     * 
     * @param description
     *            The description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Show the node value in GUI.
     */
    public void showNodeValue() {
        // do nothing in abstract class.
    }

}
