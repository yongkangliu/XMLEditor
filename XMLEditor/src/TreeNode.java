public abstract class TreeNode {
    protected String name;
    protected String description;

    @Override
    public String toString() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
