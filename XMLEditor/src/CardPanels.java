import java.awt.CardLayout;

import javax.swing.JPanel;

public class CardPanels extends JPanel {

    private static final long serialVersionUID = -1274239241918798094L;

    private static CardPanels instance = new CardPanels();

    private CardPanels() {
        super(new CardLayout());

        CardQuizPanel quizInstance = CardQuizPanel.getInstance();
        this.add(quizInstance, quizInstance.getName());

        CardQuestionPanel questionInstance = CardQuestionPanel.getInstance();
        this.add(questionInstance, questionInstance.getName());

        CardOptionPanel optionInstance = CardOptionPanel.getInstance();
        this.add(optionInstance, optionInstance.getName());

    }

    public static CardPanels getInstance() {
        return CardPanels.instance;
    }

    public void displayPanel(String name, TreeNode node) {
        CardLayout cl = (CardLayout) (this.getLayout());
        cl.show(this, name);
        node.showNodeValue();
    }
}
