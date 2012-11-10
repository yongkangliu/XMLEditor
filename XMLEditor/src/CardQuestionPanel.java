import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class CardQuestionPanel extends CardNodePanel {

    private static final long serialVersionUID = -3942735705442838426L;
    public static String QUESTION = "question";

    private static CardQuestionPanel instance = new CardQuestionPanel();

    private JTextField questionField1 = new JTextField();
    private JTextField questionField2 = new JTextField();

    private CardQuestionPanel() {
        // Fields for Question information
        JPanel line20 = new JPanel(new GridLayout(1, 2));
        JLabel line20Label = new JLabel("Field1:");
        line20Label.setHorizontalAlignment(SwingConstants.RIGHT);
        line20.add(line20Label);
        line20.add(questionField1);
        this.add(line20);

        JPanel line21 = new JPanel(new GridLayout(1, 2));
        JLabel line21Label = new JLabel("Field2:");
        line21Label.setHorizontalAlignment(SwingConstants.RIGHT);
        line21.add(line21Label);
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
        super.showNodeValue(node);
    }

    protected void setNodeValue() {
        super.setNodeValue();
    }
}
