/*
 * UNC Charlotte ITCS 6112 Software Systems Design and Implementation
 * 
 * by Yongkang Liu, 12/02/2012
 */
package com.discoveryplace.xmleditor.ui;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSeparator;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import com.discoveryplace.xmleditor.data.TreeNodeQuiz;
import com.discoveryplace.xmleditor.data.XMLFile;

/**
 * The main class to run the application
 */
public class QuizEditor {
    // The XMLTree instance.
    private static XMLTree xmlTree;

    /**
     * The main function
     * 
     * @param args
     */
    public static void main(String[] args) {
        // The left side
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.setPreferredSize(new Dimension(600, 360));
        XMLTree xmlTreeGUI = XMLTree.getInstance(new TreeNodeQuiz("New Quiz"));
        QuizEditor.xmlTree = xmlTreeGUI;
        panel.add(xmlTreeGUI);

        // The right side
        panel.add(CardPanels.getInstance());

        // The menu bar
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem newItem = new JMenuItem("New Quiz");
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                XMLTree.getInstance(new TreeNodeQuiz("New Quiz"));
            }
        });

        JMenuItem openItem = new JMenuItem("Open File...");
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fc = new JFileChooser();
                JFrame frame = new JFrame("Open File");

                fc.setDialogTitle("Open File");

                int state = fc.showOpenDialog(frame);

                if (state == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    XMLFile.readXML(file.getAbsolutePath());
                }
            }
        });

        JMenuItem saveItem = new JMenuItem("Save As...");
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fc = new JFileChooser();
                JFrame frame = new JFrame("Save As");

                fc.setDialogTitle("Save As");

                int state = fc.showSaveDialog(frame);

                if (state == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();

                    try {
                        XMLFile.writeXML(QuizEditor.xmlTree, file.getAbsolutePath());
                        JOptionPane.showMessageDialog(null, "File saved.", "OK", JOptionPane.INFORMATION_MESSAGE);
                    } catch (ParserConfigurationException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.toString(), "Alert", JOptionPane.ERROR_MESSAGE);

                    } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.toString(), "Alert", JOptionPane.ERROR_MESSAGE);
                    } catch (TransformerException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                        JOptionPane.showMessageDialog(null, e.toString(), "Alert", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        });

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                System.exit(0);
            }
        });

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(new JSeparator());
        fileMenu.add(exitItem);
        jMenuBar.add(fileMenu);

        // The frame
        JFrame frame = new JFrame(" Quiz Editor");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(panel);
        frame.setJMenuBar(jMenuBar);

        frame.pack();
        frame.setResizable(false);
        frame.setVisible(true);
    }
}