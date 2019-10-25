package kompressori.datastructures;

import kompressori.huffman.HuffmanNode;

public class PriorityQueue {
    private HuffmanNode[] heap;
    private int size;

    public PriorityQueue(int initialSize) {
        this.size = 0;
        this.heap = new HuffmanNode[initialSize];
    }

    /**
     * Return lowest frequency node from queue
     * @return
     */
    public HuffmanNode poll() {
        if (size < 1) { 
            return null;
        }

        HuffmanNode firstNode = this.heap[1];
        this.heap[1] = heap[this.size];
        this.size--;

        if (this.size > 0) {
            this.heapify(1);
        }
        
        return firstNode;
    }

    /**
     * Add new node to PriorityQueue
     * @param node
     */
    public void add(HuffmanNode node) {
        this.size++;
        this.heap[this.size] = node;
        int index = this.size;
        while (index > 1 && this.heap[index].getFrequency() < this.heap[parent(index)].getFrequency()) {
            swap(parent(index), index);
            index = parent(index);
        } 
    }

    /**
     * Add list of new nodes to PriorityQueue
     * @param list
     */
    public void addAll(HuffmanNode[] list) {
        for (HuffmanNode node : list) {
            this.add(node);
        }
    }

    /**
     * Heapify node at position
     * @param position
     */
    public void heapify(int position) {
        if (position > this.size) {
            return;
        }

        int left = leftChild(position);
        int right = rightChild(position);
        int smallest = -1;

        smallest = left <= this.size && this.heap[left].getFrequency() < this.heap[position].getFrequency() 
            ? left 
            : position;

        smallest = right <= this.size && this.heap[right].getFrequency() < this.heap[smallest].getFrequency()
            ? right
            : smallest;
        
        if (smallest != position) {
            swap(position, smallest);
            heapify(smallest);
        }
    }

    /**
     * Swap positions between two nodes
     * @param a
     * @param b
     */
    public void swap(int a, int b) {
        if (a == b) {
            return;
        } 
        HuffmanNode temporary = this.heap[a];
        this.heap[a] = this.heap[b];
        this.heap[b] = temporary;
    }

    /**
     * Get left child node based on position
     * @param position
     * @return
     */
    public int leftChild(int position) {
        return 2 * position;
    }

    /**
     * Get right child node based on position
     * @param position
     * @return
     */
    public int rightChild(int position) {
        return 2 * position + 1;
    }

    /**
     * Get parent node based on position
     * @param position
     * @return
     */
    public int parent(int position) {
        return position / 2;
    }

    public boolean isLeaf(int position) { 
        return position >= (size / 2) && position <= size;
    } 

    /**
     * Get size of the PriorityQueue
     * @return
     */
    public int size() {
        return this.size;
    }

    public void print() {
        for (HuffmanNode n : heap) {
            System.out.println(n);
        }
    }
}