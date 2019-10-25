# Usage

The program can be run from commanline. Just take the released jar-file and run in on your computer.
You have to have Java installed for this to work.

```
java -jar kompressori-1.0.jar
```

The ui presents 3 different options:
```
Welcome to kompressori

Encode or decode or compare?
1 - Encode
2 - Decode
3 - Compare
Any other input halts the process
: 
```

## Encode

From the encode menu you can encode a file with either Huffman encoding or Lempel-Ziv-Welch:
```
Which algorithm would you like to use?
1 -- Huffman
2 -- LZW
: 
```

```
What is the filepath to the file you want to encode?
: /filepath/asd.txt
```

After this a /filepath/asd.txt.[encoding] will be created to the same path as asd.txt

## Decode

Decode menu allows you to decode an encoded file
```
What file do you want to decode?
: asd.txt.lzw
```

```
Where do you want to save the decoded file?
: /filepath/filename.txt
```

After this a /filepath/filename.txt is created and contains the decoded data

## Compare

The comparison menu allows you to compare the two algorithms with either manual input or a file input
```
Manual (1) data or file input (2)? 
: 2
What is the filepath of the input file?
: /your/filepath/here.txt

```

And it prints out the results:
```
Initial size: 152089 bytes

....................

Lempel-Ziv-Welch: 
Size: 70148 bytes
Ratio: 46.12 %
Encode time: 129 ms
Decode time: 20 ms

....................

Huffman: 
Size: 88717 bytes
Ratio: 58.33 %
Encode time: 21 ms
Decode time: 18 ms

....................

```