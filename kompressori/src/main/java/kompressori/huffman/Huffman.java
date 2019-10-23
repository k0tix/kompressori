package kompressori.huffman;

import kompressori.datastructures.BitSet;
import kompressori.datastructures.ByteArray;
import kompressori.datastructures.PriorityQueue;

/**
 * Class for simple huffman encoding and decoding
 */
public class Huffman {
    
    /**
     * Create a frequency map from byte input
     * @param input
     * @return 
     */
    public int[] getFrequencies(byte[] input) {
        int[] freq = new int[256];

        for (int i = 0; i < input.length; i++) {
            freq[0xff & input[i]] += 1;
        }

        return freq;
    }

    /**
     * Build a huffman tree from the input string
     * @param input
     * @return
     */
    public HuffmanNode createHuffmanTree(int[] frequencies) {
        PriorityQueue queue = new PriorityQueue(256);

        for (int i = 0; i < frequencies.length; i++) {
            if (frequencies[i] > 0) {
                queue.add(new HuffmanNode((byte) i, frequencies[i], null, null));
            }
        }
    
        while (queue.size() > 1) {
            HuffmanNode right = queue.poll();
            HuffmanNode left = queue.poll();

            HuffmanNode parentNode = new HuffmanNode((byte) 0, left.getFrequency() + right.getFrequency(), left, right);
            queue.add(parentNode);
        }

        return queue.poll();
    }

    /**
     * Encode byte array input with huffman encoding
     * @param byteInput
     * @param frequencies
     * @return
     */
    public byte[] encode(byte[] byteInput, int[] frequencies) {
        HuffmanNode tree = this.createHuffmanTree(frequencies);
        BitSet[] codeMap = this.getCodeMap(tree);

        ByteArray encodedInput = new ByteArray(16);
        BitSet code;
        short offset = 0;
        byte nextByte = 0;

        encodedInput.add((byte) offset);

        for (int i = 0; i < byteInput.length; i++) {
            code = codeMap[0xff & byteInput[i]];
            for (int j = 0; j < code.size(); j++) {
                if (offset < 8) {
                    if (code.get(j)) {
                        nextByte |= 1 << offset;
                    }
                    offset += 1;
                } else {
                    encodedInput.add(nextByte);
                    nextByte = 0;
                    if (code.get(j)) {
                        nextByte = 1;
                    }
                    offset = 1;
                }
            }
        }

        encodedInput.add(nextByte);
        encodedInput.set(0, (byte) offset);

        return encodedInput.array();
    }

    /**
     * Encode byte array input with huffman encoding
     * @param input
     * @return
     */
    public byte[] encode(byte[] byteInput) {
        return this.encode(byteInput, this.getFrequencies(byteInput));
    }

    /**
     * Decode the encoded data to its original form
     * @param data
     * @return
     */
    public byte[] decode(byte[] encodedInput, HuffmanNode tree) {
        HuffmanNode node = tree;
        ByteArray result = new ByteArray(16);

        int bits = 8;
        for (int i = 1; i < encodedInput.length; i++) {
            bits = encodedInput.length - 1 == i ? encodedInput[0] : bits;
            for (int j = 0; j < bits; j++) {
                node = ((1 << j) & encodedInput[i]) == 0 ? node.getLeft() : node.getRight();
                if (node.isLeafNode()) {
                    result.add(node.getCharacter());
                    node = tree;
                }
            }
        }

        return result.array();
    }

    /**
     * Helper method for the code map creation
     * @param node
     * @param code
     * @param codeMap
     */
    private void walk(HuffmanNode node, BitSet code, BitSet[] codeMap) {
        if (node.isLeafNode()) {
            codeMap[0xff & node.getCharacter()] = code.clone();
        } else {
            int index = code.size();
            code.set(index, false);
            walk(node.getLeft(), code, codeMap);
            code.set(index, true);
            walk(node.getRight(), code, codeMap);
            code.clear(index);
        }
    }

    /**
     * Returns the characted and their assigned huffman code
     * @param root
     * @return
     */
    public BitSet[] getCodeMap(HuffmanNode root) {
        BitSet[] codeMap = new BitSet[256];
        BitSet code = new BitSet();

        code.set(0, false);
        walk(root.getLeft(), code, codeMap);
        code.set(0, true);
        walk(root.getRight(), code, codeMap);

        return codeMap;
    }
}