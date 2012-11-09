import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JSeparator;

public class QuizEditor {

    public static void main(String[] args) {
        // The left side
        JPanel panel = new JPanel(new GridLayout(1, 2));
        panel.setPreferredSize(new Dimension(600, 400));
        XMLTree xmlTreeGUI = XMLTree.getInstance(new TreeNodeQuiz("New Quiz"));
        panel.add(xmlTreeGUI);

        // The right side
        panel.add(CardPanels.getInstance());

        // The menu bar
        JMenuBar jMenuBar = new JMenuBar();
        JMenu fileMenu = new JMenu("File");

        JMenuItem newItem = new JMenuItem("New");
        newItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                XMLTree.getInstance(new TreeNodeQuiz("New Quiz"));
            }
        });

        JMenuItem openItem = new JMenuItem("Open");
        openItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fc = new JFileChooser();
                JFrame frame = new JFrame("open file");

                fc.setDialogTitle("Open File");

                int state = fc.showOpenDialog(frame);

                if (state == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    System.out.println(file.getAbsolutePath() + " open file----" + file.getName());
                    XMLFile.readXML(file.getAbsolutePath());
                }
            }
        });

        JMenuItem saveItem = new JMenuItem("Save");
        saveItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent ae) {
                JFileChooser fc = new JFileChooser();
                JFrame frame = new JFrame("save file");

                fc.setDialogTitle("Save File");

                int state = fc.showSaveDialog(frame);

                if (state == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();

                    System.out.println("open file----" + file.getName());
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
        frame.setVisible(true);
    }
}
