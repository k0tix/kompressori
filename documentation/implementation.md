# Implementation

The application has a commandline interface class in package ui and an application logic class in package domain. File writing and reading is handled by the [FileHandler](../kompressori/src/main/java/kompressori/io/FileHandler.java) class in package io. This only reads and writes byte data to and from files. The goal of this project was to compare different compression algorithms, their compression rate and execution times. The two compression algorithms implemented are located in packages `huffman` and `lzw`. They also use custom data structures like [PriorityQueue](../kompressori/src/main/java/kompressori/datastructures/PriorityQueue.java), [BitSet](../kompressori/src/main/java/kompressori/datastructures/BitSet.java) and [ByteArray](../kompressori/src/main/java/kompressori/datastructures/ByteArray.java) that are in the [datastructures](../kompressori/src/main/java/kompressori/datastructures/) package.

## Algorithms

### Huffman encoding

Huffman encoding is an algorithm to create the lowest possible code for one character based on the frequency the given character occures in the input file. This way a character that occures many times gets a short code and vice versa. The program uses a tree ([HuffmanNode](../kompressori/src/main/java/kompressori/huffman/HuffmanNode.java) to give characters their codes. Each node stores the character that needs to be encoded. The tree is built using a [PriorityQueue](../kompressori/src/main/java/kompressori/datastructures/PriorityQueue.java) and an integer array that contains frequencies of every character (each characters index is the unsigned value of the byte):

1. Create nodes from characters that have a frequency and add them to the queue (orders nodes from lowest to highest frequency)
2. Loop while the queue has more than one node:
   1. Poll 2 nodes from the queue
   2. Create a new parent node for the 2 nodes. Its frequency is the sum of the 2 nodes.
   3. Add the parent node to the queue
3. Return the last value from the queue. This is the trees root.

With this tree it is easy to create mappings for all the different codes that are stored in a [BitSet](../kompressori/src/main/java/kompressori/datastructures/BitSet.java)
Encoding just loops through every input byte and writes the code for that byte.

Decoding:
Start from the trees root. If the next bit int the input is 1 take the roots right child, if 0 take the left child. Continue traversing until hitting a leaf node (node with no childs) and write that nodes stored byte value. Continue this process until there is no more input left.

Currently the program doesnt store the tree in the encoded file, but only stores the character frequencies as an integer array. This is not the most efficient way and wastes space.

Huffmans time complexity is O(n log n) as it traverses the Huffman tree to create codes.

## LZW

LZW or Lempel-Ziv-Welch is a compressiong algorithm that uses a dictionary to store references to already occured words. This way if a word is repeated many times we only need to store it once and then just point to the reference if the word occuress again. At the start the dictionary contains only the 255 different byte values. All dictionary entries after that contain only the reference index to the first words and then the last byte. Many implementations use some kind of HashMap for the dictionary and my implementation uses a tree based structure. To check if an entry is stored, it just traverses the tree down (based on the searched entry) and either hits an node with the entry or doesn't.

LZWs time complexity is O(n log n) as it traverses the tree to find dictionary values. With a hashmap implementation it would be O(n)

## Improvements and fixes

The current version of the project is much smaller than I initially wanted to and a bit rushed as I did not have much time doing a job at the same time. The main idea was to implement Huffman and LZSS encoding separately and then combine the two and create a Deflate clone where data is broken in to separate blocks that are either the original data, encoded with a static huffman tree or encoded with a dynamic huffman tree.