# Specification

The goal of this project is to implement different compression algorithms (Huffman, LZ and DEFLATE) using Java and compare them.
The program should also be able to decompress any file and return it to its former glory without any data loss.

## Algorithms and data structures

DEFLATE is a combination of the LZSS and the Huffman coding which are run one after the other.

Huffman coding uses a binary tree and LZ uses a trie as their dictionaries. The project also needs implementations for queues and dynamic arrays
## I/O

The input is a file to be compressed and the output is a compressed file

## Sources

* [https://en.wikipedia.org/wiki/Huffman_coding](https://en.wikipedia.org/wiki/Huffman_coding)
* [https://en.wikipedia.org/wiki/LZ77_and_LZ78](https://en.wikipedia.org/wiki/LZ77_and_LZ78)
* [https://en.wikipedia.org/wiki/DEFLATE](https://en.wikipedia.org/wiki/DEFLATE)
* [Deflate specification](https://tools.ietf.org/html/rfc1951)