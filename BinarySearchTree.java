public class BinarySearchTree{
    //initalizing instance variable
    private BSTNode root;
    //BinarySearchTree constructor
    public BinarySearchTree(){
        this.root = new BSTNode(null);
    }

    //accessor method to retrieve the root node of the BST
    public BSTNode getRoot(){
        return root;
    }

    //accessor method to retrieve the node with the given key
    public BSTNode get(BSTNode r, Key k){
        //if the tree is empty/null
        if (r.isLeaf()) {
            return null;
        }else{
            int compare = k.compareTo(r.getRecord().getKey());

            if (compare == 0) {
                return r;
            } else if (compare < 0) {
                return get(r.getLeftChild(), k);
            } else {
                return get(r.getRightChild(), k);
            }
        }


    }

    //method to insert the given node in the correct place of the BST
    public void insert(BSTNode r, Record d) throws DictionaryException{

        //if the tree is empty, create new node for tree and set left and right child null which are leafs
        if(r.isLeaf()){
            r.setRecord(d);
            r.setLeftChild(new BSTNode(null));
            r.setRightChild(new BSTNode(null));
            r.getLeftChild().setParent(r);
            r.getRightChild().setParent(r);
        }
        else if(r.getRecord().getKey().compareTo(d.getKey())==0){
            //exception thrown if the record being inserted already exist in the BST
            throw new DictionaryException("The record associated with key of \"" + d.getKey() + "\" already exist in the tree");
        } else if (r.getRecord().getKey().compareTo(d.getKey())==1) {
            insert(r.getLeftChild(),d);
        }
        else {
            insert(r.getRightChild(),d);
        }

    }

    //method to remove the given node
    public void remove(BSTNode r, Key k) throws DictionaryException{
        BSTNode p = get(r,k);
        if(p == null){
            //exception thrown if the record being removed is not in the BST
            throw new DictionaryException("The record associated with key of \"" + k + "\" does not exist in the tree");
        }
        else{
            if(p.getRightChild().isLeaf()){
                BSTNode n1 = p.getLeftChild();
                BSTNode pParent = p.getParent();
                if(pParent==null){
                    r.setRecord(n1.getRecord());
                    r.setLeftChild(n1.getLeftChild());
                    r.setRightChild(n1.getRightChild());
                }
                else{
                    n1.setParent(pParent);
                    if(p == pParent.getRightChild()){
                        pParent.setRightChild(n1);
                    }
                    else{
                        pParent.setLeftChild(n1);
                    }
                }
            } else if (p.getLeftChild().isLeaf()) {
                BSTNode n1 = p.getRightChild();
                BSTNode pParent = p.getParent();
                if(pParent==null){
                    r.setRecord(n1.getRecord());
                    r.setLeftChild(n1.getLeftChild());
                    r.setRightChild(n1.getRightChild());
                }
                else{
                    n1.setParent(pParent);
                    if(p == pParent.getRightChild()){
                        pParent.setRightChild(n1);
                    }
                    else{
                        pParent.setLeftChild(n1);
                    }
                }
            }
            else{
                BSTNode small = smallest(p.getRightChild());
                p.setRecord(small.getRecord());
                remove(small,small.getRecord().getKey());
            }
        }
    }

    //method to find the node with the next immediate a bigger key
    BSTNode successor(BSTNode r, Key k){
        BSTNode p = get(r,k);
        if(!p.getRightChild().isLeaf()){
            return smallest(p.getRightChild());
        }
        else{
            p = p.getParent();
            while((p != null) && (p.getRecord().getKey().compareTo(k) == -1)){
                p = p.getParent();
            }
            return p;
        }
    }

    //method to find the value with the next immediate smaller key
    BSTNode predecessor(BSTNode r, Key k){
        BSTNode p = get(r,k);
        if(!p.getLeftChild().isLeaf()){
            return largest(p.getLeftChild());
        }
        else{
            p = p.getParent();
            while(p != null && p.getRecord().getKey().compareTo(k) == 1){
                p = p.getParent();
            }
            return p;
        }
    }

    //method to find and return the smallest key in the ordered dictionary
    BSTNode smallest(BSTNode r){
        while (!r.getLeftChild().isLeaf()){
            r = r.getLeftChild();
        }
        return r;
    }
    //method to find and return the largest key in the ordered dictionary
    BSTNode largest(BSTNode r){
        while (!r.getRightChild().isLeaf()){
            r = r.getRightChild();
        }
        return r;
    }
}
