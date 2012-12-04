/*
 * UNC Charlotte ITCS 6112 Software Systems Design and Implementation
 * 
 * by Yongkang Liu, 12/02/2012
 */
package com.discoveryplace.xmleditor.ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

import com.discoveryplace.xmleditor.data.TreeNode;
import com.discoveryplace.xmleditor.data.TreeNodeOption;
import com.discoveryplace.xmleditor.data.TreeNodeQuestion;

/**
 * The XML Tree class. It's a singleton class.
 * 
 */
public class XMLTree extends JPanel implements ActionListener {

    private static final long serialVersionUID = 1340647621383657976L;

    // There two button at the botton the tree.
    private static String ADD_COMMAND = "add";
    private static String REMOVE_COMMAND = "remove";

    private JButton addB;
    private JButton removeB;

    // The root node
    private DefaultMutableTreeNode rootNode;

    // The tree model for the data in the tree
    private DefaultTreeModel treeModel;

    // The JTree object.
    private JTree tree = new JTree();

    // The XML Tree instance.
    private static XMLTree instance = new XMLTree();

    /**
     * Private constructor.
     */
    private XMLTree() {
        super(new BorderLayout());

        // Add the tree
        tree.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        tree.setShowsRootHandles(true);
        tree.addTreeSelectionListener(new MyTreeSelectionListener());

        JScrollPane scrollPane = new JScrollPane(tree);
        super.add(scrollPane, BorderLayout.CENTER);

        // Add two buttons
        JButton addButton = new JButton("Add");
        addButton.setActionCommand(ADD_COMMAND);
        addButton.addActionListener(this);
        addB = addButton;

        JButton removeButton = new JButton("Remove");
        removeButton.setActionCommand(REMOVE_COMMAND);
        removeButton.addActionListener(this);
        removeB = removeButton;

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(addButton);
        panel.add(removeButton);

        super.add(panel, BorderLayout.SOUTH);
    }

    /**
     * Get the XMLTree instance with a input data object.
     * 
     * @param object
     *            The input data object
     * @return Return the XMLTree instance.
     */
    public static XMLTree getInstance(Object object) {
        instance.clear();
        instance.rootNode = new DefaultMutableTreeNode(object);
        instance.treeModel = new DefaultTreeModel(instance.rootNode);
        instance.tree.setModel(instance.treeModel);

        return instance;
    }

    /**
     * Get the JTee instance.
     * 
     * @return Return the JTree instance.
     */
    public JTree getJTree() {
        return this.tree;
    }

    /**
     * Remove all nodes except the root node
     */
    public void clear() {
        tree.removeAll();
    }

    /**
     * Remove the currently selected node.
     */
    public void removeSelectedNode() {
        TreePath currentSelection = tree.getSelectionPath();
        if (currentSelection != null) {
            DefaultMutableTreeNode currentNode = (DefaultMutableTreeNode) (currentSelection.getLastPathComponent());
            MutableTreeNode parent = (MutableTreeNode) (currentNode.getParent());
            if (parent != null) {
                treeModel.removeNodeFromParent(currentNode);
                return;
            }
        }
    }

    /**
     * Add child to the currently selected node.
     * 
     * @return Return the selected node.
     */
    public DefaultMutableTreeNode addObject() {
        DefaultMutableTreeNode parentNode = null;
        TreePath parentPath = tree.getSelectionPath();

        TreeNode newNode = null;

        if (parentPath == null) {
            parentNode = rootNode;
            newNode = new TreeNodeQuestion("New Question");
        } else {
            parentNode = (DefaultMutableTreeNode) (parentPath.getLastPathComponent());
            if (parentPath.getPathCount() == 1) {
                newNode = new TreeNodeQuestion("New Question");
            } else if (parentPath.getPathCount() == 2) {
                newNode = new TreeNodeOption("New Option");
            }
        }

        return addObject(parentNode, newNode, true);
    }

    /**
     * Add new node.
     * 
     * @param parent
     *            The parent node
     * @param child
     *            The child node
     * @return Return the new tree node.
     */
    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child) {
        return addObject(parent, child, false);
    }

    /**
     * Add new node.
     * 
     * @param parent
     *            The parent node
     * @param child
     *            The child node
     * @param shouldBeVisible
     * @return Return the new tree node.
     */
    public DefaultMutableTreeNode addObject(DefaultMutableTreeNode parent, Object child, boolean shouldBeVisible) {
        DefaultMutableTreeNode childNode = new DefaultMutableTreeNode(child);

        if (parent == null) {
            parent = rootNode;
        }

        // It is key to invoke this on the TreeModel, and NOT DefaultMutableTreeNode
        treeModel.insertNodeInto(childNode, parent, parent.getChildCount());

        // Make sure the user can see the lovely new node.
        if (shouldBeVisible) {
            tree.scrollPathToVisible(new TreePath(childNode.getPath()));
        }
        return childNode;
    }

    /**
     * Display the add button, or not.
     * 
     * @param name
     *            Doesn't display the add button if the name is empty.
     */
    public void setAddButtonName(String name) {
        if ("".equals(name)) {
            addB.setEnabled(false);
        } else {
            addB.setEnabled(true);
        }
        addB.setText(name);
    }

    /**
     * Display the remove button, or not.
     * 
     * @param name
     *            Doesn't display the remove button if the name is empty.
     */
    public void setRemoveButtonName(String name) {
        if ("".equals(name)) {
            removeB.setEnabled(false);
        } else {
            removeB.setEnabled(true);
        }
        removeB.setText(name);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (ADD_COMMAND.equals(command)) {
            addObject();
        } else if (REMOVE_COMMAND.equals(command)) {
            removeSelectedNode();
        }
    }

    /**
     * The tree selection listener
     */
    class MyTreeSelectionListener implements TreeSelectionListener {
        @Override
        public void valueChanged(TreeSelectionEvent e) {
            int level = e.getPath().getPathCount();
            CardPanels cardPanels = CardPanels.getInstance();
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) tree.getLastSelectedPathComponent();

            if (level == 1) {
                setAddButtonName("Add Question");
                setRemoveButtonName("");
                if (node != null) {
                    cardPanels.displayPanel(CardQuizPanel.QUIZ, (TreeNode) node.getUserObject());
                }
            } else if (level == 2) {
                setAddButtonName("Add Option");
                setRemoveButtonName("Remove Question");
                if (node != null) {
                    cardPanels.displayPanel(CardQuestionPanel.QUESTION, (TreeNode) node.getUserObject());
                }
            } else {
                setAddButtonName("");
                setRemoveButtonName("Remove Option");
                if (node != null) {
                    cardPanels.displayPanel(CardOptionPanel.OPTION, (TreeNode) node.getUserObject());
                }
            }
        }
    }
}
