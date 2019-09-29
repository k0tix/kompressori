# Week 4

On the fourth week I implemented a basic LZW algorithm that can encode and decode strings. Next steps for me are to implement the lzss and compare its results to the lzw algorithm (lzss should be much better with longer inputs) and then to combine the lempel-ziv with the huffman encoding (DEFLATE).

Also one task is to make an interface (first only a cli-interface) where the user can choose and input file and the program creates a compressed version with some filename extension or uncompresses a compressed file. I might also make a simple gui if I have time for that. I also have to create custom data structures to replace the java ones.