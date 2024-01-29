public class Key {
    //initalizing instance variable
    private final String label;
    private final int type;

    //key constructor
    public Key(String theLabel, int theType){
        this.label = theLabel.toLowerCase();
        this.type = theType;
    }

    //accessor method for key object to retrieve label of key
    public String getLabel(){
        return label;
    }

    //accessor method for key object to retrieve type of key
    public int getType(){
        return type;
    }

    //compare method returns 0 this key is equal to k, -1 if this key is smaller than k, 1 otherwise
    public int compareTo(Key k){
        //this key is equal to k
        if(this.label.equals(k.getLabel()) && this.type == k.getType()){
            return 0;
        }
        //this key is smaller than k
        if (this.label.compareTo(k.getLabel()) < 0 || (this.label.equals(k.getLabel()) && this.type < k.getType())) {
            return -1;
        }
        return 1;
    }

}
