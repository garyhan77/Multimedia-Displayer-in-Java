//this class implements all the method from the BSTDictionaryADT
public class BSTDictionary implements BSTDictionaryADT{
    //initializing instance variable
    private final BinarySearchTree dict;

    //BSTDictionary constructor
    public BSTDictionary(){
        dict = new BinarySearchTree();
    }

    //accessor method to retrieve the record that has the key of k
    @Override
    public Record get(Key k) {
        if(dict.get(dict.getRoot(),k)==null){
            return null;
        }
        return dict.get(dict.getRoot(), k).getRecord();
    }

    //method to "insert" the record d into the dictionary
    @Override
    public void put(Record d) throws DictionaryException {
        dict.insert(dict.getRoot(), d);
    }

    //method to remove the record with key k from the dictionary
    @Override
    public void remove(Key k) throws DictionaryException {
        dict.remove(dict.getRoot(), k);
    }

    //method to find the successor of record with key k using the successor method from BinarySearchTree
    @Override
    public Record successor(Key k) {
        if(dict.successor(dict.getRoot(), k)==null){
            return null;
        }
        return dict.successor(dict.getRoot(), k).getRecord();
    }

    //method to find the predecessor of record with key k using the predecessor method from BinarySearchTree
    @Override
    public Record predecessor(Key k) {
        if(dict.predecessor(dict.getRoot(), k)==null){
            return null;
        }
        return dict.predecessor(dict.getRoot(), k).getRecord();
    }
    //method to find the smallest record using the smallest method from BinarySearchTree
    @Override
    public Record smallest() {
        return dict.smallest(dict.getRoot()).getRecord();
    }
    //method to find the largest record using the largest method from BinarySearchTree
    @Override
    public Record largest() {
        return dict.largest(dict.getRoot()).getRecord();
    }
}
