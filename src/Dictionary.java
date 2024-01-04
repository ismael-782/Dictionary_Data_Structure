import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class Dictionary extends AVLTree {

    public Dictionary() { // empty constructor
        super();
    }

    public Dictionary(String s) { // constructor with argument to add a node to the AVL Tree
        super(new BSTNode<String>(s)); // Since the AVL was implemented by BST, so we need to creat an instance of BST
                                       // class
    }

    public Dictionary(File f) { // Ths constructor is to read a file and insert its content inside the AVL
        try {
            Scanner input = new Scanner(f);
            while (input.hasNext()) {
                try {
                    insertAVL(input.next());
                } catch (IllegalArgumentException e) {
                }
            }
            input.close();
            System.out.print("Dictionary loaded successfully\n");
        } catch (IOException e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
    }

    public boolean findWord(String s) { // fine is word exist or not
        if (search(s) == null) {
            return false;
        } else {
            return true;
        }
    }

    public void addWord(String s) throws WordAlreadyExistsException { // adding a word to the AVL tree
        try {
            insertAVL(s);
        } catch (IllegalArgumentException e) {
            throw new WordAlreadyExistsException(s);
        }
    }

    public void deleteWord(String s) throws WordNotFoundException { // deleting a word from the AVL tree
        try {
            deleteAVL(s);
        } catch (Exception e) {
            throw new WordNotFoundException(s);
        }
    }

    // There are 3 methods I used to implement the FindSimilar methode.
    // first one is the helper method to the recursive one below it, and isSimilar
    // is a methode used to identifay
    // if a word in the AVL tree is similar in terms of the length of the word and
    // the difference in the letters.
    public String[] findSimilar(String s) { // this is the original method.
        SLL<String> wordsList = new SLL<String>();
        findSimilarHelper(s, root, wordsList);
        String[] similarWordsArray = wordsList.toString().split(",");

        return similarWordsArray;
    }

    public void findSimilarHelper(String key, BSTNode<String> node, SLL<String> list) { // this is the helper method
        if (node == null) {
            return;
        }

        if (isSimilar(key, node.el)) {
            list.addToHead(node.el);
        }
        findSimilarHelper(key, node.left, list);
        findSimilarHelper(key, node.right, list);
    }

    private boolean isSimilar(String key, String word) { /*
                                                          * this method starts with comparing the key with the word
                                                          * in the AVL tree to chech if the length difference is 1 or
                                                          * more, if it is 1 it will procced otherwise it will return
                                                          * false
                                                          * after that it starts comparing the letters of the two words.
                                                          * if the length of the words is different, there will be
                                                          * a problim. So we keep track of the index of both words so
                                                          * that if a conflict occured we will balance the indicies
                                                          */
        int lengthDifference = Math.abs(word.length() - key.length());
        if (lengthDifference > 1) {
            return false;
        }
        // if you reacheed here, you are ok with the first condition of similarity of
        // the words.
        int differenceDegree = 0; /*
                                   * this variable is used to count the number of diffrenses we encounters while
                                   * comparing the woeds
                                   */
        int i = 0, j = 0;
        while (i < key.length() && j < word.length()) {
            if (key.charAt(i) != word.charAt(j)) { // compare the letters if they do not match
                differenceDegree++; // if do not, add 1 to the differenceDegree
                if (differenceDegree > 1) {
                    return false;
                }
                if (key.length() > word.length()) { // if the key is bigger than the word in the AVL, move the index of
                                                    // the key by 1 to match
                    i++;
                } else if (key.length() < word.length()) { // if the word is bigger than the key in the AVL, move the
                                                           // index of the woed by 1 to match
                    j++;
                } else { // if both the same size
                    i++;
                    j++;
                }
            } else { // if there is no conflict we will continue
                i++;
                j++;
            }
        }
        if (i < key.length() || j < word.length()) {
            differenceDegree++;
        }
        return differenceDegree == 1; // return the result, if the difference is 1 this means that the words are
                                      // similar
    }

    public File saveFile(String fileName) { // methode to save the changes made in a new file
        File newFile = new File(fileName);
        try {
            PrintWriter output = new PrintWriter(newFile);
            printElements(output, root);
            System.out.println("Dictionary saved successfully");
            output.close();
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return newFile;
    }

    private void printElements(PrintWriter output, BSTNode<String> node) { // to print the elements of the AVL tree
        if (node != null) {
            printElements(output, node.left);
            output.println(node.el);
            printElements(output, node.right);
        } else {
            return;
        }
    }
}