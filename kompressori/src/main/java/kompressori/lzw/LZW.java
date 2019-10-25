package kompressori.lzw;

import kompressori.datastructures.ByteArray;

public class LZW {
    private int dictionarySize;

    public LZW(int dictionarySize) {
        this.dictionarySize = dictionarySize;
    }

    public LZW() {
        this(65536);
    }

    public byte[] encode(byte[] input) {
        EncodeDictionary dict = new EncodeDictionary();
        ByteArray prefix = new ByteArray(1024);
        ByteArray encodedInput = new ByteArray(1024);

        for (int i = 0; i < input.length; i++) {
            if (dict.has(prefix, input[i])) {
                prefix.add(input[i]);
            } else {
                encodedInput.addLZWindex(dict.getIndex(prefix));
                dict.add(prefix, input[i]);
                prefix.freeArray();
                prefix.add(input[i]);
            }

            if (dict.size() == this.dictionarySize) {
                dict = new EncodeDictionary();
            }
        }

        encodedInput.addLZWindex(dict.getIndex(prefix));
        return encodedInput.array();
    }   

    public byte[] decode(byte[] encodedInput) {
        DecodeDictionary dictionary = new DecodeDictionary();
        ByteArray decodedInput = new ByteArray(16);

        int index = bytesToInt(encodedInput, 0);
        int lastIndex = index;
        byte next;

        decodedInput.addAll(dictionary.get(index).array());

        for (int i = 2; i < encodedInput.length; i += 2) {
            index = this.bytesToInt(encodedInput, i);
            if (dictionary.size() > index) {
                decodedInput.addAll(dictionary.get(index).array());
                next = dictionary.get(index).get(0);

                ByteArray bytes = dictionary.get(lastIndex).concat(next);
                if (bytes == null) {
                    dictionary.reset();
                } else {
                    dictionary.add(bytes);
                }
            } else {
                next = dictionary.get(lastIndex).get(0);
                ByteArray bytes = dictionary.get(lastIndex).concat(next);
                dictionary.add(bytes);
                decodedInput.addAll(dictionary.get(index).array());
            }

            lastIndex = index;
            if (dictionary.size() >= this.dictionarySize) {
                dictionary.reset();
            }
        }

        return decodedInput.array();
    }

    private int bytesToInt(byte[] encodedInput, int index) {
        return (0xff & encodedInput[index]) << 8 | (0xff & encodedInput[index + 1]);
    }
}