/**
 * Interface for a collection that maps keys to values.
 * @author Jason Bbosa jb4773
 * @version 1.0 December 14, 2023
 */
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;

public class AnagramFinder{
    private static void verifyFileExistence(String fileName) throws IOException{
        // Ensures file existence using try and catch block to close the BufferedReader automatically
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            // Checks if the file can be opened by attempting to read the first line
            if (br.readLine() == null){
                // Throws IOException if the file is empty or cannot be read
                throw new IOException("File '" + fileName + "' is empty or cannot be read.");
            }
        }
        catch (IOException error){
            // Handles IOException thrown during the try block and provides an error message
            throw new IOException("Error validating file '" + fileName + "': " + error.getMessage(), error);
        }
    }

    private static MyMap<String, MyList<String>> instantiateDataStructure(String dataStructure){
        // Converts the input data structure name to lowercase for case-insensitive comparison
        dataStructure = dataStructure.toLowerCase();

        // Checks the type of data structure requested and instantiates the corresponding map
        if ("bst".equals(dataStructure)){
            // Returns a new instance of BST map
            return new BSTMap<>();
        }
        else if ("avl".equals(dataStructure)){
            // Returns a new instance of AVL Tree map
            return new AVLTreeMap<>();
        }
        else if ("hash".equals(dataStructure)){
            // Returns a new instance of Hash Map
            return new MyHashMap<>();
        }
        else{
            // Throws an IllegalArgumentException if an invalid data structure type is provided
            throw new IllegalArgumentException("Error: Invalid data structure type '" + dataStructure + "' received.");
        }
    }

    private static void sortFile(String fileName, MyMap<String, MyList<String>> map){
        // Ensures file existence using try and catch block to close the BufferedReader automatically
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            // Declares a new variable to be each line from the inputted file
            String fileLine;

            // Reads each line from the file
            while ((fileLine = br.readLine()) != null){
                // Converts the line to lowercase
                String word = fileLine.toLowerCase();
                // Converts the word to a character array
                char[] charArray = word.toCharArray();

                // Performs insertion sort on the character array
                for (int i = 1; i < charArray.length; i++){
                    char key = charArray[i];
                    int j = i - 1;

                    // Shifts elements greater than key to the right
                    while (j >= 0 && charArray[j] > key){
                        charArray[j + 1] = charArray[j];
                        j = j - 1;
                    }
                    charArray[j + 1] = key;
                }

                // Converts the sorted character array back to a string
                String sortedCharArray = new String(charArray);

                // Retrieves or creates a list for the sorted key in the map
                MyList<String> sortedAnagramsList = map.get(sortedCharArray);
                if (sortedAnagramsList == null){
                    sortedAnagramsList = new MyLinkedList<>();
                    map.put(sortedCharArray, sortedAnagramsList);
                }
                // Adds the original line to the list of anagrams
                sortedAnagramsList.add(fileLine);
            }
        }
        catch (IOException error){
            // Handles I/O errors and exits the program in failure
            System.err.println("Error: An I/O error occurred reading '" + fileName + "'.");
            System.exit(1);
        }
    }

    private static void displayOutput(String word, MyMap<String, MyList<String>> map){
        // Converts the input word to lowercase and stores it in a character array
        char[] charArray = word.toLowerCase().toCharArray();

        // Performs insertion sort on the character array
        for (int i = 1; i < charArray.length; i++){
            char key = charArray[i];
            int j = i - 1;

            // Shifts elements greater than key to the right
            while (j >= 0 && charArray[j] > key){
                charArray[j + 1] = charArray[j];
                j = j - 1;
            }
            charArray[j + 1] = key;
        }

        // Creates a sorted key from the sorted character array
        String sortedCharArray2 = new String(charArray);
        // Retrieves the list of anagrams for the sorted key from the map
        MyList<String> sortedAnagramsList2 = map.get(sortedCharArray2);

        // Checks if there are no anagrams found for the given word
        if (sortedAnagramsList2 == null || sortedAnagramsList2.isEmpty()){
            System.out.println("No anagrams found.");
        }
        else{
            // Creates an array to store valid anagrams
            String[] anagramsArray = new String[sortedAnagramsList2.size()];
            int index = 0;

            // Iterates through the anagrams list
            Iterator<String> iterator = sortedAnagramsList2.iterator();
            while (iterator.hasNext()){
                String anagram = iterator.next();

                // Excludes the input word from the list of anagrams
                if (anagram != null && !anagram.equals(word)){
                    anagramsArray[index++] = anagram;
                }
            }
            // Checks if there are no valid anagrams found
            if (index == 0){
                System.out.println("No anagrams found.");
            }
            else{
                // Performs insertion sort on the array of anagrams
                for (int i = 1; i < index; i++){
                    String key = anagramsArray[i];
                    int j = i - 1;

                    // Shifts elements greater than key to the right
                    while (j >= 0 && anagramsArray[j].compareTo(key) > 0){
                        anagramsArray[j + 1] = anagramsArray[j];
                        j = j - 1;
                    }
                    anagramsArray[j + 1] = key;
                }
                // Displays the sorted list of valid anagrams
                for (int i = 0; i < index; i++){
                    System.out.println(anagramsArray[i]);
                }
            }
        }
    }

    public static void main(String[] args){
        // Checks if the correct number of command-line arguments is provided
        if (args.length != 3){
            System.err.println("Usage: java AnagramFinder <word> <dictionary file> <bst|avl|hash>");
            // Exits the program with an error code
            System.exit(1);
        }

        // Extracts command-line arguments
        String word = args[0];
        String dictionaryFileName = args[1];
        String dataStructureType = args[2];

        try{
            // Verifies the existence of the specified file
            verifyFileExistence(dictionaryFileName);
        }
        catch (IOException error){
            // Handles and print an error message if file verification fails
            System.err.println("Error: Cannot open file '" + dictionaryFileName + "' for input.");
            System.exit(1); // Exit the program with an error code
        }

        // Instantiates a map data structure based on the provided type
        MyMap<String, MyList<String>> map = instantiateDataStructure(dataStructureType);
        // Sorts the contents of the file and populate the map data structure
        sortFile(dictionaryFileName, map);
        // Displays the output for the given word and map
        displayOutput(word, map);

    }
}