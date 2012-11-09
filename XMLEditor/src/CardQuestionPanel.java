import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CardQuestionPanel extends CardNodePanel {

    private static final long serialVersionUID = -3942735705442838426L;
    public static String QUESTION = "question";

    private static CardQuestionPanel instance = new CardQuestionPanel();

    private JTextField questionField1 = new JTextField();
    private JTextField questionField2 = new JTextField();
    private TreeNodeQuestion nodeQuestion;

    private CardQuestionPanel() {
        // Fields for Question information
        JPanel line20 = new JPanel(new GridLayout(1, 2));
        line20.add(new JLabel("line20"));
        line20.add(questionField1);
        this.add(line20);

        JPanel line21 = new JPanel(new GridLayout(1, 2));
        line21.add(new JLabel("line21"));
        line21.add(questionField2);
        this.add(line21);

        JButton questionButton = new JButton("save");
        this.add(questionButton);

        questionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNodeValue();
            }
        });
    }

    public static CardQuestionPanel getInstance() {
        return CardQuestionPanel.instance;
    }

    public String getName() {
        return CardQuestionPanel.QUESTION;
    }

    public void showNodeValue(TreeNode node) {
        nodeQuestion = (TreeNodeQuestion) node;

        this.questionField1.setText(nodeQuestion.toString());
    }

    private void setNodeValue() {
        if (nodeQuestion != null) {
            nodeQuestion.setName(questionField1.getText());
        }
    }
}
