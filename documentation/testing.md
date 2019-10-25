# Testing document

The program is tested manually by hand and automatically with JUnit tests that test all the classes that do not depend on javas data structures.

## Unit testing
I have tests for data structures and algorithms that do not depend on javas data structures

### I/O testing
Not much testing here but the program writes and reads from the filepath it is given.

### Algorithm testing
In classes [LZWTest](https://github.com/k0tix/kompressori/blob/master/kompressori/src/test/java/kompressori/LZWTest.java) and [HuffmanTest](https://github.com/k0tix/kompressori/blob/master/kompressori/src/test/java/kompressori/HuffmanTest.java)

### Performance testing
Algorithms were tested with different lenght input data (files from [cantebury corpus](http://corpus.canterbury.ac.nz)). All file sizes are in bytes.

Filename | Original size | Huffman size | Huffman ratio | Huffman encoding | Huffman decoding | LZW size | LZW ratio | LZW encoding | LZW decoding
--- | --- | --- | --- | --- | --- | --- | --- | --- | ---
xargs1 | 4 227 | 3631 | 85.90 % | 1 ms | 1 ms | 3 584 | 84.79 % | 2 ms | 1 ms
cp.html | 24 603 | 17 228 | 70.02 % | 40 ms | 20 ms | 14 948 | 60.76 % | 52 ms | 20 ms
alice29.txt | 152 089 | 88 717 | 58.33 % | 26 ms | 19 ms | 70 148 | 46.12 % | 154 ms | 25 ms
ptt5 | 513 216 | 107 580 | 20.96 % | 24 ms | 19 ms | 70 116 | 13.66 % | 2367 ms | 24 ms
bible.txt | 4 047 392 | 2 219 479 | 54.84 % | 233 ms | 145 ms | 1 501 028 | 37.09 % | 1361 ms | 185 ms
E.coli | 4 638 690 | 1 160 702 | 25.02 % | 124 ms | 85 ms | 1 342 576 | 28.94 % | 602 ms | 147 ms
