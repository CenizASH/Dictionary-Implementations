import java.io.*;
import java.util.*;

public class Dict_Imp {

    public static void main(String[] args) {

        String inputFile = "GreatExpectations.txt";
        String searchFile = "TestWords.txt.txt";

        DictionaryOrdered allWordsOrdered; // complete dictionary, using an ordered array
        DictionaryOpen allWordsOpen; // complete dictionary, using open addressing
        DictionaryChain allWordsChain; // complete dictionary, using separate chaining

        long startTime, endTime, elapsedTime;

        // Fill the dictionaries
        System.out.println("\nFilling ordered array dictionary...");
        startTime = System.nanoTime();
        allWordsOrdered = new DictionaryOrdered(100);
        buildOrdered(allWordsOrdered, inputFile);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("The time to fill the ordered array dictionary with " + allWordsOrdered.getSize()
                + " words was: " + elapsedTime + " ns.");

        System.out.println("\nFilling open addressing dictionary...");
        allWordsOpen = new DictionaryOpen(100);
        startTime = System.nanoTime();
        buildOpen(allWordsOpen, inputFile);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("The time to fill the open addressing dictionary with " + allWordsOpen.getSize()
                + " words was: " + elapsedTime + " ns.");

        System.out.println("\nFilling separate chaining dictionary...");
        allWordsChain = new DictionaryChain(100);
        startTime = System.nanoTime();
        buildChain(allWordsChain, inputFile);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("The time to fill the open addressing dictionary with " + allWordsChain.getSize()
                + " words was: " + elapsedTime + " ns.");

        // Search the dictionaries
        System.out.println("\nSearching ordered array dictionary...");
        startTime = System.nanoTime();
        searchOrdered(allWordsOrdered, searchFile);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("The time to search the ordered array dictionary was: " + elapsedTime + " ns.");

        System.out.println("\nSearching open addressing dictionary...");
        startTime = System.nanoTime();
        searchOpen(allWordsOpen, searchFile);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("The time to search the open addressing dictionary was: " + elapsedTime + " ns.");

        System.out.println("\nSearching separate chaining dictionary...");
        startTime = System.nanoTime();
        searchChain(allWordsChain, searchFile);
        endTime = System.nanoTime();
        elapsedTime = endTime - startTime;
        System.out.println("The time to search the separate chaining dictionary was: " + elapsedTime + " ns.");

        System.out.println("\nEnd of Processing.");
    }// end main

    // ==========================================================
    // buildOrdered
    //
    // PURPOSE: Fill the given dictionary with the given text.
    //
    // PARAMETERS:
    // allWordsOrdered - the dictionary to be filled
    // inputFile - the file containing words to add to dictionary
    //
    // RETURNS:
    // none
    // ==========================================================
    static void buildOrdered(DictionaryOrdered allWordsOrdered, String inputFile) {
        String temp;
        String[] words;

        try {
            BufferedReader buff = new BufferedReader(new FileReader(new File(inputFile)));

            temp = buff.readLine();
            while (temp != null) {
                words = temp.split("[.,;: --\"?]");

                for (int i = 0; i < words.length; i++)
                    allWordsOrdered.insert(words[i]);

                temp = buff.readLine();
            }

        } // end try
        catch (IOException e) {
            System.out.println("File I/O Error: " + inputFile);
        }

    }// end buildOrdered

    // ==========================================================
    // buildOpen
    //
    // PURPOSE: Fill the given dictionary with the given text.
    //
    // PARAMETERS:
    // allWordsOpen - the dictionary to be filled
    // inputFile - the file containing words to add to dictionary
    //
    // RETURNS:
    // none
    // ==========================================================
    static void buildOpen(DictionaryOpen allWordsOpen, String inputFile) {
        String temp;
        String[] words;

        try {
            BufferedReader buff = new BufferedReader(new FileReader(new File(inputFile)));

            temp = buff.readLine();
            while (temp != null) {
                words = temp.split("[.,;: --\"?]");

                for (int i = 0; i < words.length; i++)
                    allWordsOpen.insert(words[i]);

                temp = buff.readLine();
            }

        } // end try
        catch (IOException e) {
            System.out.println("File I/O Error: " + inputFile);
        }

    }// end buildOpen

    // ==========================================================
    // buildChain
    //
    // PURPOSE: Fill the given dictionary with the given text.
    //
    // PARAMETERS:
    // allWordsChain - the dictionary to be filled
    // inputFile - the file containing words to add to dictionary
    //
    // RETURNS:
    // none
    // ==========================================================
    static void buildChain(DictionaryChain allWordsChain, String inputFile) {
        String temp;
        String[] words;

        try {
            BufferedReader buff = new BufferedReader(new FileReader(new File(inputFile)));

            temp = buff.readLine();
            while (temp != null) {
                words = temp.split("[.,;: --\"?]");

                for (int i = 0; i < words.length; i++)
                    allWordsChain.insert(words[i]);

                temp = buff.readLine();
            }

        } // end try
        catch (IOException e) {
            System.out.println("File I/O Error: " + inputFile);
        }

    }// end buildChain

    // ==========================================================
    // searchOrdered
    //
    // PURPOSE: Search the given dictionary for the given words.
    //
    // PARAMETERS:
    // allWordsOrdered - the dictionary to be searched
    // searchFile - the file containing words to look for
    //
    // RETURNS:
    // none (prints number of words found)
    // ==========================================================
    static void searchOrdered(DictionaryOrdered allWordsOrdered, String searchFile) {
        String temp;
        int numFound = 0;
        int numMissing = 0;

        try {

            Scanner scnner = new Scanner(new File(searchFile));
            while (scnner.hasNext()) {

                temp = scnner.next();
                if (allWordsOrdered.search(temp))
                    numFound++;
                else
                    numMissing++;
            }
            System.out.println(
                    "Number of words found = " + numFound + ". Number of words not found = " + numMissing + ".");

        } // end try
        catch (IOException e) {
            System.out.println("File I/O Error: " + searchFile);
        }

    }// end searchOrdered

    // ==========================================================
    // searchOpen
    //
    // PURPOSE: Search the given dictionary for the given words.
    //
    // PARAMETERS:
    // allWordsOpen - the dictionary to be searched
    // searchFile - the file containing words to look for
    //
    // RETURNS:
    // none (prints number of words found)
    // ==========================================================
    static void searchOpen(DictionaryOpen allWordsOpen, String searchFile) {
        String temp;
        int numFound = 0;
        int numMissing = 0;

        try {

            Scanner scnner = new Scanner(new File(searchFile));
            while (scnner.hasNext()) {

                temp = scnner.next();
                if (allWordsOpen.search(temp))
                    numFound++;
                else
                    numMissing++;
            }
            System.out.println(
                    "Number of words found = " + numFound + ". Number of words not found = " + numMissing + ".");

        } // end try
        catch (IOException e) {
            System.out.println("File I/O Error: " + searchFile);
        }

    }// end searchOpen

    // ==========================================================
    // searchChain
    //
    // PURPOSE: Search the given dictionary for the given words.
    //
    // PARAMETERS:
    // allWordsChain - the dictionary to be searched
    // searchFile - the file containing words to look for
    //
    // RETURNS:
    // none (prints number of words found)
    // ==========================================================
    static void searchChain(DictionaryChain allWordsChain, String searchFile) {
        String temp;
        int numFound = 0;
        int numMissing = 0;

        try {

            Scanner scnner = new Scanner(new File(searchFile));
            while (scnner.hasNext()) {

                temp = scnner.next();
                if (allWordsChain.search(temp))
                    numFound++;
                else
                    numMissing++;
            }
            System.out.println(
                    "Number of words found = " + numFound + ". Number of words not found = " + numMissing + ".");

        } // end try
        catch (IOException e) {
            System.out.println("File I/O Error: " + searchFile);
        }

    }// end searchChain

}// end class A3Q2template

// ==============================================================
// DictionaryOrdered class
//
// PURPOSE: Store a list of words, in an ordered array.
//
// PUBLIC METHODS: - constructor: public Dictionary(int size)
// - public int getSize() - return the current number of words
// - public void insert(String newWord) - insert new word in list
// - public boolean search(String wordToFind) - search for
// given word and return true if found
// FOR TESTING: public void print() - print contents of dictionary
// ==============================================================
class DictionaryOrdered {
    int size = 0;
    String[] arr = new String[size];
    int counter = 0;

    public DictionaryOrdered(int size) {
        arr = new String[size];
    }

    public int getSize() {
        return counter;
    }

    public void insert(String newWord) {
        if (arr[counter].equals("")) {
            arr[counter] = newWord;
            counter++;
        } else {
            String[] arr2 = new String[(size * 2)];
            for (int i = 0; i < size; i++) {
                arr2[i] = arr[i];
            }
            counter++;
            arr2[counter] = newWord;
            counter++;
        }
    }

    public boolean search(String wordToFind) {
        boolean found = false;
        int i = 0;
        while ((i < counter) && (!found)) {
            if (arr[i].equals(wordToFind)) {
                found = true;
            }
            i++;
        }
        return found;
    }

    public void print() {
        String print = "";
        for (int i = 0; i < size; i++) {
            print += arr[i] + ", ";
        }
        System.out.println(print);
    }
}// end class DictionaryOrdered

// ==============================================================
// DictionaryOpen class
//
// PURPOSE: Store a list of words, in a hash table using open addressing.
//
// PUBLIC METHODS: - constructor: public Dictionary(int size)
// - public int getSize() - return the current number of words
// - public void insert(String newWord) - insert new word in list
// - public boolean search(String wordToFind) - search for
// given word and return true if found
// FOR TESTING: public void print() - print contents of dictionary
//
// ==============================================================
class DictionaryOpen {
    int size = 0;
    String[] arr = new String[size];
    int counter = 0;

    public DictionaryOpen(int n) {
        int j = 2;
        boolean prime = false;
        while ((j * j <= n) && (!prime)) {
            if (n % j == 0) {
                n++;
            } else {
                prime = true;
                arr = new String[n];
            }
        }
    }

    public int getSize() {
        return counter;
    }

    public void insert(String newWord) {
        int percentFull = (counter * 100) / size;
        if (percentFull < 60) {
            int hashVal = hash(newWord);
            arr[hashVal] = newWord;
        } else {
            int newSize = size * 2;
            int j = 2;
            boolean prime = false;
            String[] arr2 = new String[newSize];
            while ((j * j <= newSize) && (!prime)) {
                if (newSize % j == 0) {
                    newSize++;
                } else {
                    prime = true;
                    arr2 = new String[newSize];
                }
            }

            for (int i = 0; i < size; i++) {
                arr2[i] = arr[i];
            }

            int hashVal = hash(newWord);
            arr[hashVal] = newWord;
        }
    }

    private int hash(String word) {
        int hashVal = 0;

        for (int i = 0; i < word.length(); i++) {
            int letter = word.charAt(i) - 96;
            if (letter >= 97 && letter <= 122) {
                hashVal = (hashVal * 41 + letter) % (arr.length);
            }
        }

        return hashVal;
    }

    public boolean search(String wordToFind) {
        boolean found = false;
        int i = 0;
        while ((i < counter) && (!found)) {
            if (arr[i].equals(wordToFind)) {
                found = true;
            }
            i++;
        }
        return found;
    }

}// end class DictionaryOpen

// ==============================================================
// DictionaryChain class
//
// PURPOSE: Store a list of words, in a hash table using separate
// chaining. Words are converted to lowercase as inserted.
//
// PUBLIC METHODS: - constructor: public Dictionary(int size)
// - public int getSize() - return the current number of words
// - public void insert(String newWord) - insert new word in list
// - public boolean search(String wordToFind) - search for
// given word and return true if found
// FOR TESTING: public void print() - print contents of dictionary
//
// ==============================================================
class DictionaryChain {
    int size = 0;
    Node[] hashArray = new Node[size];
    int counter = 0;

    public int getSize() {

        return counter;
    }

    public boolean search(String wordToFind) {
        boolean found = false;
        int i = 0;
        while ((i < counter) && (!found)) {
            if (hashArray[i].data.equals(wordToFind)) {
                found = true;
            }
            i++;
        }
        return found;
    }

    public DictionaryChain(int size) {
        for (int i = 0; i < size; i++) {
            hashArray[i] = null;
        }
    }

    private int hash(String word) {
        int hashVal = 0;

        for (int i = 0; i < word.length(); i++) {
            int letter = word.charAt(i);
            hashVal = (hashVal * 41 + letter) % (hashArray.length);
        }

        return hashVal;
    }

    public void insert(String word) {
        int hashVal = hash(word);

        if (hashArray[hashVal] == null) {
            hashArray[hashVal] = new Node(word, null);
        } else {
            hashArray[hashVal] = new Node(word, hashArray[hashVal]);
        }
        counter++;
    }

    public void print(LinkedList list) {
        Node currNode = list.head;
        System.out.print("LinkedList: ");
        while (currNode != null) {
            System.out.print(currNode.data + " ");
            currNode = currNode.next;
        }
    }

}// end class DictionaryChain

class Node {
    String data;
    Node next;

    Node(String word, Node n) {
        data = word;
        next = n;
    }
}

class LinkedList {

    Node head;

    public LinkedList() {
        head = null;
    }

}
