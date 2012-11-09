import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CardQuizPanel extends CardNodePanel {

    private static final long serialVersionUID = -6788291539698426532L;
    public static String QUIZ = "quiz";

    private static CardQuizPanel instance = new CardQuizPanel();

    private JTextField quizField1 = new JTextField();
    private JTextField quizField2 = new JTextField();
    private TreeNodeQuiz nodeQuiz;

    private CardQuizPanel() {
        // Fields for Quiz information
        JPanel line10 = new JPanel(new GridLayout(1, 2));
        line10.add(new JLabel("line10"));
        line10.add(this.quizField1);
        this.add(line10);

        JPanel line11 = new JPanel(new GridLayout(1, 2));
        line11.add(new JLabel("line11"));
        line11.add(this.quizField2);
        this.add(line11);

        JButton quizButton = new JButton("save");
        this.add(quizButton);

        // Button listener
        quizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNodeValue();
            }
        });
    }

    public static CardQuizPanel getInstance() {
        return CardQuizPanel.instance;
    }

    public String getName() {
        return CardQuizPanel.QUIZ;
    }

    public void showNodeValue(TreeNode node) {
        nodeQuiz = (TreeNodeQuiz) node;

        this.quizField1.setText(nodeQuiz.toString());
    }

    private void setNodeValue() {
        if (nodeQuiz != null) {
            nodeQuiz.setName(quizField1.getText());
        }
    }
}
