public class Tree {

    private TreeState state;
    private boolean atRisk;

    /**
     * Initializes the tree in a healthy state with no initial risk.
     */
    public Tree() {
        this.state = TreeState.HEALTHY;
        this.atRisk = false;
    }

    public void ignite() {
        this.state = TreeState.BURNING;
        this.atRisk = false;
    }

    public void extinguish() {
        this.state = TreeState.ASH;
        this.atRisk = false;
    }


    public boolean isAtRisk() {
        return atRisk;
    }

    public void setAtRisk(boolean atRisk) {
        this.atRisk = atRisk;
    }

    public TreeState getState() {
        return state;
    }

    public Tree copy() {
        Tree copy = new Tree();
        copy.state = this.state;
        copy.atRisk = this.atRisk;
        return copy;
    }
}
