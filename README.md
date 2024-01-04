
# Dictionary Data Structure
This Java program implements a dictionary data structure for a spell checker. The dictionary supports various operations such as initializing from a string, an empty dictionary, or a text file, adding new words, searching for words, removing words, and finding similar words.

## Class: Dictionary
### Constructors
public Dictionary(String s): Initializes the dictionary with a single string.

public Dictionary(): Initializes an empty dictionary.

public Dictionary(File f): Initializes the dictionary from a text file containing words.

### Methods
public void addWord(String s) throws WordAlreadyExistsException: Adds a new word to the dictionary.

public boolean findWord(String s): Searches for a word in the dictionary.

public void deleteWord(String s) throws WordNotFoundException: Removes a word from the dictionary.

public String[] findSimilar(String s): Searches for words that are similar to the given word.

### Main Method (for Testing)
The class includes a main method for testing the dictionary functionality. The testing operations include:

- Loading the dictionary from a file.
- Finding a word in the dictionary.
- Adding new words to the dictionary.
- Removing words from the dictionary.
- Searching for similar words to a given word.
- Saving the updated dictionary as a text file.

  # Test Cases:

  
