package kompressori;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class LZW {
    private int dictSize;

    /**
     * Encodes an input string to list of integers
     * @param input
     * @return
     */
    public int[] encode(String input) {
        this.dictSize = 256;
        Map<String, Integer> dictionary = new HashMap<>(dictSize);
        for (int i = 0; i < dictSize; i++) {
            dictionary.put("" + (char) i, i);
        }

        List<Integer> encodedInput = new ArrayList<>();
        String window = "";
        for (int i = 0; i < input.length(); i++) {
            char current = input.charAt(i);
            if (dictionary.containsKey(window + current)) {
                window = window + current;
            } else {
                encodedInput.add(dictionary.get(window));
                this.dictSize += 1;
                dictionary.put(window + current, this.dictSize);
                window = "" + current;
            }
        }

        if (!window.equals("")) {
            encodedInput.add(dictionary.get(window));
        }

        int[] result = new int[encodedInput.size()];
        for (int i = 0; i < encodedInput.size(); i++) {
            result[i] = encodedInput.get(i);
        }
        return result;
    }

    /**
     * Decodes a lzw encoded input to its original form
     * @param encodedInput
     * @return
     */
    public String decode(int[] encodedInput) {
        this.dictSize = 256;
        Map<Integer, String> dictionary = new HashMap<>(dictSize);
        for (int i = 0; i < dictSize; i++) {
            dictionary.put(i, "" + (char) i);
        }

        String window = "" + (char) encodedInput[0];
        StringBuffer decodedInput = new StringBuffer(window);

        for (int i = 1; i < encodedInput.length; i++) {
            String entry;
            if (dictionary.containsKey(encodedInput[i])) {
                entry = dictionary.get(encodedInput[i]);
            } else {
                entry = window + window.charAt(0);
            }
            
            decodedInput.append(entry);
            this.dictSize += 1;
            dictionary.put(this.dictSize, window + entry.charAt(0));
            window = entry;
        }

        return decodedInput.toString();
    }
}