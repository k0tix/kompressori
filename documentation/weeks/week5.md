# Week 5

I finished the LZW algorithm that can encode and decode strings. Next steps for me are to implement the lzss and compare its results to the lzw algorithm (lzss should be much better with longer inputs) and then to combine the lempel-ziv with the huffman encoding. I also made a simple file io helper class that currently uses Java serializable functionality, but I have to replace this with byte representations of the Huffman tree and the LZW array so the files can actually be more compact. Next task is to also make my own data structures for the algorithms and take away the Java premade ones.

I also briefly looked into some cli interface libraries for Java and I might use Apache Commons CLI but as the functionality and commands are fairly simple it probably won't need that.