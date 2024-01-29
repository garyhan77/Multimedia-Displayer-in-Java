public class Record {
    //initializing instance variable
    private Key theKey;
    private String data;

    //Record constructor
    public Record(Key k, String theData){
        this.theKey = k;
        this.data = theData;
    }

    //accessor method to retrieve the key of the record object
    public Key getKey(){
        return theKey;
    }

    //accessor method to retrieve the data item of the record object
    public String getDataItem(){
        return data;
    }
}
