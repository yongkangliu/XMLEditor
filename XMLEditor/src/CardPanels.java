import java.awt.CardLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CardPanels extends JPanel {

    private static final long serialVersionUID = -1274239241918798094L;
    public static String QUIZ = "quiz";
    public static String QUESTION = "question";
    public static String OPTION = "option";

    private JPanel quizPanel = new JPanel(new GridLayout(12, 1));
    private JTextField quizField1 = new JTextField();
    private JTextField quizField2 = new JTextField();
    private TreeNodeQuiz nodeQuiz;

    private JPanel questionPanel = new JPanel(new GridLayout(12, 1));
    private JTextField questionField1 = new JTextField();
    private JTextField questionField2 = new JTextField();
    private TreeNodeQuestion nodeQuestion;

    private JPanel optionPanel = new JPanel(new GridLayout(12, 1));
    private JTextField optionField1 = new JTextField();
    private TreeNodeOption nodeOption;

    private static CardPanels instance = new CardPanels();

    private CardPanels() {
        super(new CardLayout());

        // Fields for Quiz information
        JPanel line10 = new JPanel(new GridLayout(1, 2));
        line10.add(new JLabel("line10"));
        line10.add(this.quizField1);
        this.quizPanel.add(line10);

        JPanel line11 = new JPanel(new GridLayout(1, 2));
        line11.add(new JLabel("line11"));
        line11.add(this.quizField2);
        this.quizPanel.add(line11);

        JButton quizButton = new JButton("save");
        this.quizPanel.add(quizButton);
        this.add(this.quizPanel, CardPanels.QUIZ);

        // Fields for Question information
        JPanel line20 = new JPanel(new GridLayout(1, 2));
        line20.add(new JLabel("line20"));
        line20.add(questionField1);
        this.questionPanel.add(line20);

        JPanel line21 = new JPanel(new GridLayout(1, 2));
        line21.add(new JLabel("line21"));
        line21.add(questionField2);
        this.questionPanel.add(line21);

        JButton questionButton = new JButton("save");
        this.questionPanel.add(questionButton);
        this.add(this.questionPanel, CardPanels.QUESTION);

        // Fields for Option information
        JPanel line30 = new JPanel(new GridLayout(1, 2));
        line30.add(new JLabel("line30"));
        line30.add(optionField1);
        this.optionPanel.add(line30);

        JButton optionButton = new JButton("save");
        this.optionPanel.add(optionButton);
        this.add(this.optionPanel, CardPanels.OPTION);

        // Button listener
        quizButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                nodeQuiz.setName(quizField1.getText());
            }
        });

        questionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                nodeQuestion.setName(questionField1.getText());
            }
        });

        optionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                nodeOption.setName(optionField1.getText());
            }
        });
    }

    public static CardPanels getInstance() {
        return CardPanels.instance;
    }

    public void displayPanel(String name, TreeNode node) {
        CardLayout cl = (CardLayout) (this.getLayout());
        cl.show(this, name);
        if (CardPanels.QUIZ.equals(name)) {
            this.nodeQuiz = (TreeNodeQuiz) node;
            if (this.nodeQuiz != null) {
                this.quizField1.setText(this.nodeQuiz.toString());
            }
        } else if (CardPanels.QUESTION.equals(name)) {
            this.nodeQuestion = (TreeNodeQuestion) node;
            if (this.nodeQuestion != null) {
                this.questionField1.setText(this.nodeQuestion.toString());
            }
        } else if (CardPanels.OPTION.equals(name)) {
            this.nodeOption = (TreeNodeOption) node;
            if (this.nodeOption != null) {
                this.optionField1.setText(this.nodeOption.toString());
            }
        }
    }
}
