package kompressori.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

/**
 * Class for simple huffman encoding and decoding
 */
public class Huffman{
    
    /**
     * Create a frequency map from a string
     * @param input
     * @return 
     */
    public Map<Character, Integer> frequencyMap(String input) {
        Map<Character, Integer> frequencies = new HashMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            frequencies.putIfAbsent(c, 0);
            frequencies.put(c, frequencies.get(c) + 1);
        }

        return frequencies;
    }

    /**
     * Build a huffman tree from the input string
     * @param input
     * @return
     */
    public HuffmanNode createHuffmanTree(String input) {
        PriorityQueue<HuffmanNode> queue = new PriorityQueue<>();
        Map<Character, Integer> frequencyMap = this.frequencyMap(input);

        for (Character c : frequencyMap.keySet()) {
            queue.add(new HuffmanNode(c, frequencyMap.get(c), null, null));
        }

        while( queue.size() > 1) {
            HuffmanNode right = queue.poll();
            HuffmanNode left = queue.poll();

            HuffmanNode parentNode = new HuffmanNode(null, left.getFrequency() + right.getFrequency(), left, right);
            queue.add(parentNode);
        }

        return queue.poll();
    }

    /**
     * Encode string input with huffman encoding
     * @param input
     * @return
     */
    public HuffmanWrapper encode(String input) {
        HuffmanNode tree = this.createHuffmanTree(input);
        Map<Character, String> codeMap = this.getCodeMap(tree);

        StringBuilder encodedString = new StringBuilder();

        for (int i = 0; i < input.length(); i++) {
            encodedString.append(codeMap.get(input.charAt(i)));
        }

        return new HuffmanWrapper(tree, encodedString.toString());
    }

    /**
     * Decode the encoded data to its original form
     * @param data
     * @return
     */
    public String decode(HuffmanWrapper data) {
        StringBuilder result = new StringBuilder("");
        
        String encodedString = data.getEncodedString();
        HuffmanNode node = data.getTree();

        for (int i = 0; i < data.getEncodedString().length(); i++) {
            if (encodedString.charAt(i) == '0') {
                node = node.getLeft();
            } else {
                node = node.getRight();
            }

            if (node.getCharacter() != null) {
                result.append(node.getCharacter());
                node = data.getTree();
            }
        }

        return result.toString();
    }

    /**
     * Helper method for the code map creation
     * @param node
     * @param code
     * @param codeMap
     */
    private void walk(HuffmanNode node, StringBuilder code, Map<Character, String> codeMap) {
        if (node == null) {
            return;
        } else if (node.getCharacter() == null) {
            walk(node.getLeft(), code.append("0"), codeMap);
            code.deleteCharAt(code.length() - 1);

            walk(node.getRight(), code.append("1"), codeMap);
            code.deleteCharAt(code.length() - 1);
        } else {
            codeMap.put(node.getCharacter(), code.toString());
        }
    }

    /**
     * Returns the characted and their assigned huffman code
     * @param root
     * @return
     */
    public Map<Character, String> getCodeMap(HuffmanNode root) {
        Map<Character, String> codeMap = new HashMap<>();
        walk(root.getLeft(), new StringBuilder("0"), codeMap);
        walk(root.getRight(), new StringBuilder("1"), codeMap);

        return codeMap;
    }
}