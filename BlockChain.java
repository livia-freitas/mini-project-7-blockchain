package mp7;

/**
 * Authors: Christina Vu and Livia Stein Freitas
 * 
 * A linked structure pointing to the first and element of the blockchain. Contains methods to
 * mine a block based on the current state of the blockchain, get the chain's size, append
 * a block to the chain, and others.
 * 
 * Simulates a series of transactions between two users, Alexis and Blake. The first node's
 * amount represents Alexis' initial balance.
 */

public class BlockChain {
    private Node first;
    private Node last;

    /**
     * Takes in an integer amount. Creates a blockchain with one initial element.
     * @param initial
     */
    public BlockChain(int initial) {
        first = new Node(new Block(0, initial, null));
        last = first;
    }

    /**
     * Takes in an integer amount. Appends a valid block to the blockchain.
     * @param amount
     * @return
     */
    public Block mine(int amount) {
        Block newBlock = new Block(last.getData().getNum() + 1, amount, last.getData().getHash());
        append(newBlock);
        return newBlock;
    }

    /**
     * Returns the size of the blockchain.
     * @return
     */
    public int getSize() {
        int size = 0;
        Node current = first;
        while (current != null) {
            size++;
            current = current.getNext();
        }
        return size;
    }
    /**
     * Appends a block to the blockchain.
     * @param blk
     */
    public void append(Block blk) {
        if (blk.getPrevHash().equals(last.getData().getHash())) {
            Node newNode = new Node(blk);
            last.setNext(newNode);
            last = newNode;
        } else {
            throw new IllegalArgumentException("Invalid block. Cannot append to the chain.");
        }
    }

    /** 
     * Removes the last block in the blockchain.
     */
    public boolean removeLast() {
        if (first == last) {
            return false;
        }
        Node current = first;
        while (current.getNext() != last) {
            current = current.getNext();
        }
        current.setNext(null);
        last = current;
        return true;
    }

    /**
     * Returns the hash of the last block in the blockchain.
     * @return
     */
    public Hash getHash() {
        return last.getData().getHash();
    }

    /**
     * Checks if the blockchain is valid.
     * @return
     */
    public boolean isValidBlockChain() {
        Node current = first;
        while (current.getNext() != null) {
            if (!current.getData().getHash().equals(current.getNext().getData().getPrevHash())) {
                return false;
            }
            current = current.getNext();
        }
        return true;
    }

    /**
     * Prints Alexis and Blake's available balances.
     */
    public void printBalances() {
        int alexisBalance = 0;
        int blakeBalance = 0;

        Node current = first;
        while (current != null) {
            if (current.getData().getAmount() < 0) {
                alexisBalance -= current.getData().getAmount();
            } else {
                blakeBalance += current.getData().getAmount();
            }
            current = current.getNext();
        }

        System.out.println("Alexis: " + alexisBalance + ", Blake: " + blakeBalance);
    }

    /**
     * Prints the data from all the blockchain's nodes.
     */
    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        Node current = first;
        while (current != null) {
            result.append(current.getData().toString()).append("\n");
            current = current.getNext();
        }
        return result.toString();
    }

    /**
     * A class representing a node in the block chain. Contains data from a block and points
     * to the next node.
     */
    private static class Node {
        private Block data;
        private Node next;

        public Node(Block data) {
            this.data = data;
        }

        public Block getData() {
            return data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }
    }
}
