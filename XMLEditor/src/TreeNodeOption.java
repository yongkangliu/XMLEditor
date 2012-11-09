public class TreeNodeOption extends TreeNode {
    public TreeNodeOption(String name) {
        super.name = name;
    }

    public void showNodeValue() {
        CardOptionPanel panel = CardOptionPanel.getInstance();
        panel.showNodeValue(this);
    }
}
