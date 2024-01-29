import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//User interface method to initialize all the user commands
public class Interface {
    public static void main(String[] args) throws IOException, DictionaryException, MultimediaException {
        String inputFile = args[0];
        BSTDictionary bstDict = new BSTDictionary();
        //using BufferedReader to read the input file storing all the records that needs to be insertedd into the dictionary
        BufferedReader br = new BufferedReader(new FileReader(inputFile));
        String line = br.readLine();
        int type;
        //line counter
        int count = 0;
        String data;
        String label = "";
        while (line != null) {
            //using mod to find labels since labels are stored in line even number lines
            if (count % 2 == 0) {
                label = line;
            }
            //if it's not a label it will be a data
            else {
                String cData = "";
                //filter to determine the type of the data/file
                if (line.startsWith("/")) {
                    type = 2;
                    //used to ignore to special character at the front
                    cData = line.substring(1);
                } else if (line.startsWith("-")) {
                    type = 3;
                    cData = line.substring(1);
                } else if (line.startsWith("+")) {
                    type = 4;
                    cData = line.substring(1);
                } else if (line.startsWith("*")) {
                    type = 5;
                    cData = line.substring(1);
                } else if (line.endsWith("jpg")) {
                    type = 6;
                } else if (line.endsWith("gif")) {
                    type = 7;
                } else if (line.endsWith("html")) {
                    type = 8;
                } else {
                    //if it is none of the type checked above, type will be set to one
                    type = 1;
                }
                data = line;
                if (!cData.isEmpty()) {
                    data = cData;
                }

                //creating a new record after reading the line
                Record newRecord = new Record(new Key(label, type), data);
                //inserting the record into the dictionary
                bstDict.put(newRecord);

            }
            //traverse through all lines
            line = br.readLine();
            //line counter increase
            count++;
        }


        while (true) {
            String a = "";
            String b = "";
            //flag checker
            boolean checker;
            StringReader keyboard = new StringReader();
            String input = keyboard.read("Enter next command: ");
            //splitting user input into 2 parts where one part is the command and the other is the argument
            String[] sParts = input.split(" ", 2);
            //making sure the input was split into atleast 2 parts, avoiding ArrayIndexOutOfBound
            if (sParts.length >= 2) {
                a = sParts[0];
                b = sParts[1];
                checker = false;
            //if there is only one word for command, trim the space at the end
            } else if (sParts.length == 1) {
                a = sParts[0].trim();
                checker = true;
            } else {
                checker = false;
            }
            switch (a) {
                //defining the "define" command
                case "define" -> {
                    Key k1 = new Key(b, 1);
                    Record r1 = bstDict.get(k1);
                    if (r1 != null) {
                        System.out.println(r1.getDataItem());
                    } else {
                        System.out.println("The word " + b + " is not in the ordered dictionary");
                    }
                }
                //defining the "translate" command
                case "translate" -> {
                    Key k2 = new Key(b, 2);
                    Record r2 = bstDict.get(k2);
                    if (r2 != null) {
                        System.out.println(r2.getDataItem());
                    } else {
                        System.out.println("There is no definition for the word " + b);
                    }
                }
                //defining the "sound" command
                case "sound" -> {
                    Key k3 = new Key(b, 3);
                    Record r3 = bstDict.get(k3);
                    if (r3 != null) {
                        SoundPlayer newSound = new SoundPlayer();
                        newSound.play(r3.getDataItem());
                    } else {
                        System.out.println("There is no sound file for " + b);
                    }
                }
                //defining the "play" command
                case "play" -> {
                    Key k4 = new Key(b, 4);
                    Record r4 = bstDict.get(k4);
                    if (r4 != null) {
                        SoundPlayer newMusic = new SoundPlayer();
                        newMusic.play(r4.getDataItem());
                    } else {
                        System.out.println("There is no music file for " + b);
                    }
                }
                //defining the "say" command
                case "say" -> {
                    Key k5 = new Key(b, 5);
                    Record r5 = bstDict.get(k5);
                    if (r5 != null) {
                        SoundPlayer newMusic = new SoundPlayer();
                        newMusic.play(r5.getDataItem());
                    } else {
                        System.out.println("There is no voice file for " + b);
                    }
                }
                //defining the "show" command
                case "show" -> {
                    Key k6 = new Key(b, 6);
                    Record r6 = bstDict.get(k6);
                    if (r6 != null) {
                        PictureViewer newPic = new PictureViewer();
                        newPic.show(r6.getDataItem());
                    } else {
                        System.out.println("There is no image file for " + b);
                    }
                }
                //defining the "animate" command
                case "animate" -> {
                    Key k7 = new Key(b, 7);
                    Record r7 = bstDict.get(k7);
                    if (r7 != null) {
                        PictureViewer newPic = new PictureViewer();
                        newPic.show(r7.getDataItem());
                    } else {
                        System.out.println("There is no animated image file for " + b);
                    }
                }
                //defining the "browse" command
                case "browse" -> {
                    Key k8 = new Key(b, 8);
                    Record r8 = bstDict.get(k8);
                    if (r8 != null) {
                        ShowHTML newHTML = new ShowHTML();
                        newHTML.show(r8.getDataItem());
                    } else {
                        System.out.println("There is no webpage called " + b);
                    }
                }
                //defining the "delete" command
                case "delete" -> {
                    //spliting again cause there is 2 arguments for this command
                    String w = "";
                    String k = "";
                    String[] parts = b.split(" ", 2);

                    if (parts.length >= 2) {
                        w = parts[0];
                        k = parts[1];

                    }
                    try {
                        Key rmKey = new Key(w, Integer.parseInt(k));
                        if (bstDict.get(rmKey) != null) {
                            bstDict.remove(rmKey);
                        } else {
                            System.out.println("No record in the ordered dictionary has key (" + w + "," + k + ")");
                        }
                    } catch(Exception e) {
                        System.out.println("Invalid command");
                    }

                }
                //defining the "add" command
                case "add" -> {
                    String w = "";
                    String bSecond = "";
                    String[] parts = b.split(" ", 2);

                    //spliting 2 more times as there are 3 arguments for this command
                    if (parts.length >= 2) {
                        w = parts[0];
                        //System.out.println(w);
                        bSecond = parts[1];
                        //System.out.println(bSecond);
                    }

                    String t = "";
                    String c = "";
                    String[] bParts = bSecond.split(" ", 2);

                    if (bParts.length >= 2) {
                        t = bParts[0];
                        //System.out.println(w);
                        c = bParts[1];
                        //System.out.println(bSecond);
                    }
                    try {
                        Key addKey = new Key(w, Integer.parseInt(t));
                        Record addRec = new Record(addKey, c);

                        if (bstDict.get(addKey) == null) {
                            bstDict.put(addRec);
                        } else {
                            System.out.println("A record with the given key already key (" + w + "," + t + ") is already in the ordered dictionary");
                        }

                    } catch (Exception e) {
                        System.out.println("Invalid command");
                    }
                }
                //defining the "list" command
                case "list" -> {
                    Key listKey = new Key(b, 5);
                    //flags to check conditions
                    boolean check1 = false;
                    boolean check2 = false;
                    //Record listSuc = bstDict.successor(listKey);
                    if (bstDict.get(listKey) == null) {
                        Record listRec = new Record(listKey, "");
                        bstDict.put(listRec);
                        check2 = true;
                    }
                    try {
                        while (bstDict.successor(listKey).getKey().getLabel().startsWith(b)) {
                            System.out.println(bstDict.successor(listKey).getKey().getLabel());
                            listKey = bstDict.successor(listKey).getKey();
                            check1 = true;

                        }
                    } catch (Exception e) {
                        continue;
                    }
                    if (check1) {
                        continue;
                    } else {
                        System.out.println("No label attributes in the ordered dictionary start with \"" + b + "\"");
                    }
                    if (check2) {
                        bstDict.remove(listKey);
                    }
                }
                //defining the "first" command
                case "first" -> {
                    if(checker){
                    System.out.print(bstDict.smallest().getKey().getLabel() + ",");
                    System.out.print(bstDict.smallest().getKey().getType() + ",");
                    System.out.println(bstDict.smallest().getDataItem() + ".");
                    } else {
                        System.out.println("Invalid command");
                    }
                }
                //defining the "last" command
                case "last" -> {
                    if(checker) {
                        System.out.print(bstDict.largest().getKey().getLabel() + ",");
                        System.out.print(bstDict.largest().getKey().getType() + ",");
                        System.out.println(bstDict.largest().getDataItem() + ".");
                    } else {
                        System.out.println("Invalid command");
                    }
                }
                //defining the "exit" command
                case "exit" -> {
                    if(checker) {
                        //System.out.println("Ayooo");
                        System.exit(0);
                    } else {
                        System.out.println("Invalid command");
                    }
                }
                //default case to print invalid command if the input is a command that was not defined
                default -> {
                    System.out.println("Invalid command");
                }
            }
        }
    }
}
