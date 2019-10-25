# Usage

The program can be run from commanline. Just take the released jar-file and run in on your computer.
You have to have Java installed for this to work.

```
java -jar Kompressori.jar
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