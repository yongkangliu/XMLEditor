import java.awt.Component;


import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;

public class MyDefaultTreeCellRenderer extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 4840350971927480578L;

    public MyDefaultTreeCellRenderer() {
    }

    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded,
            boolean leaf, int row, boolean hasFocus) {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Object a = (Object)node.getUserObject();

        System.out.println(a.getClass()+" "+value +" "+row);
        // switch (tmp) {

        // case 1:
        // this.setIcon(new ImageIcon("E:/workspace/Tree/show/imgs/purple.jpg"));
        // break;
        // case 2:
        // this.setIcon(new ImageIcon("E:/workspace/Tree/show/imgs/gray.jpg"));
        // break;
        // default:
        // this.setIcon(new ImageIcon("E:/workspace/Tree/show/imgs/yellow.jpg"));
        // break;
        // }
        return this;

    }

}