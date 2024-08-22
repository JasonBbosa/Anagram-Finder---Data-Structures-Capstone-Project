# Anagram-Finder---Data-Structures-Capstone-Project


Data Structures in Java: Final Exam Capstone Project
Anagram Finder
1. Objective
Your goal is to use a sorting algorithm and several data structures you developed this
semester to create a program that finds all the anagrams of a given word. Insertion sort
works well on short amounts of data, which is perfect for this task. BSTMap, AVLTreeMap,
and MyHashMap implement the MyMap interface. So, with proper object-oriented
programming techniques, specifically the use of polymorphism, you can create any one of
these data structures and refer to it from a MyMap reference. Though all three classes
implement the same interface, their methods are implemented very differently, leading to
different execution times on the computer. You will time your program’s execution to see
how each of the data structures performs.
2. Problem
You will develop a command line Java application to find all the anagrams of a word. An
anagram is another word that uses all the letters of the given word, just in a different order
or with a different capitalization. For instance, “tea” and “eat” are anagrams; “tae” and
“aet” are merely permutations of the letters of the word and are not considered anagrams
because they are not words provided in the dictionary file. The name of the public class
must be AnagramFinder, and it must reside in a file named AnagramFinder.java.
Parsing Command Line Arguments
The program will take in exactly three command line arguments. If the number of arguments
supplied is incorrect, the program will print the usage message
"Usage: java AnagramFinder <word> <dictionary file> <bst|avl|hash>"
and a new line character to stderr and exit in failure.
No validation is required on the word itself. It need not even be a word, but just a string of
letters that may or may not have anagrams within the supplied dictionary file.
If the number of command line arguments is correct, the program will then verify that the
dictionary file exists. If it does not, the program will print the message
"Error: Cannot open file '" + args[1] + "' for input."
and a new line character to stderr, where args[1] contains the name of the dictionary file the
user is attempting to open, and exit in failure.
The program will then try to parse the command line for which data structure the user
wishes to use. Valid strings are “bst”, “avl”, and “hash”. Any other string including those with
different capitalization is considered invalid, causing the program to print the message
"Error: Invalid data structure '" + args[2] + "' received."
and a new line character to stderr, where args[2] contains the data structure string name,
and exit in failure.
COMS W3134, Fall 2023, Capstone - 2
Parsing the Input File
After validating the command line arguments, you’ll need to instantiate either a BSTMap,
AVLTreeMap, or MyHashMap. You must create a reference to the interface rather than the
class itself, as in:
MyMap<String, Integer> map = new BSTMap<>();
No credit will be given if your map variable is not of type MyMap. The key-to-value mapping
is String-to-MyList<String>. The key must be the sorted, lowercase spelling of the word. You
must use/modify the insertion sort method given in class to sort the letters of the string.
Strings are immutable, so use an array of chars. As an example, when processing the word
“Tea”, the key should be “aet”, since all letters have been made lowercase and the letters
within the word have been sorted lexicographically. The value is MyList<String>, a
MyLinkedList of all the words that are anagrams of the key. The elements in the
MyLinkedList may be stored in any order and must be the word with its original
capitalization.
The dictionary file contains a list of all the words to process. The file is stored in Unix/Mac
format, where each line ends with a single '\n' character. No word will appear twice in the
dictionary with the same exact capitalization. The order of the words in the file is random; it
may or may not be sorted. For each word in the file, convert it to lowercase letters, sort the
letters, and then insert the key-value pair into the map.
If an I/O error occurs as the reading takes place, the program must print the message
"Error: An I/O error occurred reading '" + args[1] + "'."
and a new line character to stderr, where args[1] contains the name of the file the user is
attempting to process, and exit in failure.
Displaying the Output
The output is relatively straightforward. Do not include the args[0] itself with its original
capitalization in the list of anagrams, though it will be stored in the map if it is indeed
present in the dictionary file. After excluding the original word, the output is either:
1. The string literal "No anagrams found." and a new line character.
2. A list of anagrams in lexicographical order, each ending with a new line character. You
must use/modify the insertion sort method given in class.
3. Sample Input / Output
For the supplied dictionary.txt file, the command:
java AnagramFinder least dictionary.txt hash
produces:
Stael
Tesla
slate
stale
steal
tales
teals
COMS W3134, Fall 2023, Capstone - 3
For the supplied words.txt file, the command:
java AnagramFinder least words.txt hash
produces:
Altes
Tesla
astel
salet
setal
slate
stale
steal
stela
taels
tales
teals
4. Requirements
You must use the supplied implementations of the BST, AVL tree, and hashmap. You must not modify
these files in any way. You must write all your code in AnagramFinder.java. We will copy the source
for the data structures into the same folder as your source code when grading your submission. If
you wish to write additional classes to help you encapsulate the data you process, put the non-public
class inside AnagramFinder.java.
You may and likely need to import from the following packages:
java.io.*;
java.util.Iterator;
You must not import any other packages from the Java API. They will be stripped away before
grading, and your code will not compile.
You are expected to comment your code. Use Javadoc for methods and inline comments where
appropriate.
Any method implementing the insertion sort algorithm must be named "insertionSort". The
argument list and return type is left to your discretion.
You may use the Internet to help with various parts of this assignment, including reading from a file,
using Iterators, etc. Be sure to cite the source in an inline comment above the lines of code you
reference. You may not consult with another human. You may not use openai.com or any other AI
tools. Since the project is open-ended, there are many different ways you can approach solving the
problem. We will use Moss to check your code against that of every other student in the class. If
your code is too similar to someone else’s in the class, you will earn an automatic 0 on the
capstone project. Please do everything possible to avoid this scenario!
COMS W3134, Fall 2023, Capstone - 4
5. Timing Your Work and Evaluating the Results
After you are confident you are getting the correct output for smaller files, try processing the
words.txt supplied in Canvas. It contains 466,550 words. Time it from the command line in Cygwin or
the Linux/Mac terminal for the word “least” as follows:
time java AnagramFinder least words.txt bst
time java AnagramFinder least words.txt avl
time java AnagramFinder least words.txt hash
You will see something like the following:
real 0m0.DDDs ← use this value, and take the average of 5 executions
user 0m0.DDDs
sys 0m0.DDDs
You should time your program 5 times for each data structure used, and take the average execution
time, since it will likely vary every time you run the program. The real value that appears tells you
how much time it takes your program to fully complete. The DDD stands for 3 digits, so you might
see something like
real 0m0.287s
which means the program took 287 ms to complete on that particular run.
Answer the following questions in a readme.txt file.
How long does your program take to produce the answer when using the bst, avl, and hash flags?
Copy the results into this readme file.
What data structure do you expect to have the best (fastest) performance? Which one do you expect
to be the slowest? Do the results of timing your program’s execution match your expectations? If so,
briefly explain the correlation. If not, what run times deviated and briefly explain why you think this
is the case.
6. Submission
Create a zip file, called AnagramFinder.zip, containing only AnagramFinder.java and readme.txt, and
upload it to Canvas.
