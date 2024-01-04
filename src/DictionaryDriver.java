import java.io.File;
import java.util.Scanner;

public class DictionaryDriver {
    public static void main(String[] args) throws WordAlreadyExistsException {

        // ============== Starts with creating the a dictionary file ===============//
        Scanner input = new Scanner(System.in);
        System.out.print("Enter file name: ");
        String fileName = input.next();
        Dictionary dictionary = new Dictionary();
        try {
            File file = new File(fileName); // pass the file name to bee red by the system, if it is not found a message
                                            // will be printed
            dictionary = new Dictionary(file);
            printOptions(); // if the file is uploaded, you will see these options printed

            // loop to continue asking the user until he stops the program manually
            boolean termiate = false;
            while (!termiate) {
                System.out.print("Choose an operation you want to perform: ");
                String operationNumber = input.next();
                Operations(dictionary, input, operationNumber);
                if (operationNumber.equals("7"))
                    termiate = true;
            }
            System.out.println("Ends of the program");
            input.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    // ============================Below is Just Methods=========================//

    static public void Operations(Dictionary dictionary, Scanner input, String operationNumber) { // this method
                                                                                                  // contains the
                                                                                                  // options and all
                                                                                                  // deatailse related
                                                                                                  // to them
        try {
            if (operationNumber.equals("1")) { // Searching option
                System.out.print("Search for: ");
                String checkedWord = input.next();
                if (checkedWord.isEmpty())
                    System.out.println("not a valid Input\n");
                else if (dictionary.findWord(checkedWord)) {
                    System.out.println("Word has been found successfully\n");
                } else
                    System.out.println("Word not found!\n");

            } else if (operationNumber.equals("2")) { // Adding a eord to a list option
                System.out.print("Add the word: ");
                String newWord = input.next();
                if (newWord.isEmpty()) {
                    System.out.println("Not Valid Input!\n");
                } else if (!dictionary.findWord(newWord)) {
                    dictionary.addWord(newWord);
                    System.out.println("Word has been added successfully\n");
                } else
                    System.out.println("Word already exist\n");

            } else if (operationNumber.equals("3")) { // removing a word from the list
                System.out.print("Remove the word: ");
                String removedWord = input.next();
                if (removedWord.isEmpty()) {
                    System.out.println("Not Valid Input!\n");
                } else if (dictionary.findWord(removedWord)) {
                    dictionary.deleteWord(removedWord);
                    System.out.println("Word has been deleted successfully\n");
                } else
                    System.out.println("Failed in deleting the word because the word not found\n");

            } else if (operationNumber.equals("4")) { // Searching for similar words
                System.out.print("Search for: ");
                String searchedWord = input.next();
                System.out.println("Similar words are: ");
                System.out.print("[ ");
                for (int i = 0; i < dictionary.findSimilar(searchedWord).length - 1; i++) {
                    System.out.print(dictionary.findSimilar(searchedWord)[i] + " , ");
                }
                System.out.print(
                        dictionary.findSimilar(searchedWord)[dictionary.findSimilar(searchedWord).length - 1] + " ]\n");

            } else if (operationNumber.equals("5")) { // saving the changes to a new file
                System.out.print("Save Updated Dictionary (Y/N): ");
                String response = input.next();
                if (response.toUpperCase().equals("Y")) {
                    System.out.print("Enter file name: ");
                    String newFileName = input.next();
                    File newFile = dictionary.saveFile(newFileName);
                }
            } else if (operationNumber.equals("6")) { // this step is to print the options again, after trying multiple
                                                      // times the list of the options will be disappeared above so you
                                                      // can choose option 6 to see it again
                printOptions();
            } else if (operationNumber.equals("7")) { // this is to terminate the program, I have done this in the while
                                                      // loop
                return;
            } else {
                System.out.print("\nYou entered wrong inputو Please enter number from 1 to 7 \n");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public static void printOptions() {
        System.out.print("\n Operations list:" // if the file is uploaded, you will see these options printed
                + "\n1) Search for Word"
                + "\n2) Add Element to the dictionary"
                + "\n3) Delete Element from the dictionary"
                + "\n4) Search for similar words"
                + "\n5) Save changes to new file"
                + "\n6) Print Options"
                + "\n7) Terminate"
                + "\n\n");
    }
}