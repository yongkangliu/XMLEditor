import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CardOptionPanel extends CardNodePanel {

    private static final long serialVersionUID = -8229602809184488899L;
    public static String OPTION = "option";

    private JTextField optionField1 = new JTextField();
    private TreeNodeOption nodeOption;

    private static CardOptionPanel instance = new CardOptionPanel();

    private CardOptionPanel() {
        // Fields for Option information
        JPanel line30 = new JPanel(new GridLayout(1, 2));
        line30.add(new JLabel("line30"));
        line30.add(optionField1);
        this.add(line30);

        JButton optionButton = new JButton("save");
        this.add(optionButton);

        optionButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent event) {
                setNodeValue();
            }
        });
    }

    public static CardOptionPanel getInstance() {
        return CardOptionPanel.instance;
    }

    public String getName() {
        return CardOptionPanel.OPTION;
    }

    public void showNodeValue(TreeNode node) {
        nodeOption = (TreeNodeOption) node;

        this.optionField1.setText(nodeOption.toString());
    }

    private void setNodeValue() {
        if (nodeOption != null) {
            nodeOption.setName(optionField1.getText());
        }
    }
}
