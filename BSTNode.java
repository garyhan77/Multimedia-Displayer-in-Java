public class BSTNode {
    //initializing instance variable
    private Record record;
    private BSTNode leftChild;
    private BSTNode rightChild;
    private BSTNode parent;

    //BSTNode constructor
    public BSTNode(Record item){
        this.record = item;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    //accessor method to retrieve the record of the BSTNode object
    public Record getRecord(){
        return record;
    }

    //setter method to set the record of the BSTNode object to d
    public void setRecord (Record d){
        this.record = d;
    }

    //accessor method to retrieve the left child node of the BSTNode object
    public BSTNode getLeftChild(){
        return leftChild;
    }

    //accessor method to retrieve the right child node of the BSTNode object
    public BSTNode getRightChild(){
        return rightChild;
    }

    //accessor method to retrieve the parent node of the BSTNode object
    public BSTNode getParent(){
        return parent;
    }

    //setter method to set the left child node of the BSTNode object to BSTNode u
    public void setLeftChild(BSTNode u){
        this.leftChild = u;
    }

    //setter method to set the right child node of the BSTNode object to BSTNode u
    public void setRightChild(BSTNode u){
        this.rightChild = u;
    }

    //setter method to set the parent node of the BSTNode object to BSTNode u
    public void setParent(BSTNode u){
        this.parent = u;
    }

    //This method returns true if both left and right child is null, therefore it is a leaf node
    public boolean isLeaf(){
        return (leftChild == null && rightChild == null);
    }
}
