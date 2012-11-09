public class TreeNodeQuiz extends TreeNode {
    public TreeNodeQuiz(String name) {
        super.name = name;
    }

    public void showNodeValue() {
        CardQuizPanel quizPanel = CardQuizPanel.getInstance();
        quizPanel.showNodeValue(this);
    }
}
