package mp7;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Authors: Christina Vu and Livia Stein Freitas
 * 
 * A class that contains the data from each node in a blockchain, including the number of the block, 
 * the transaction amount, the previous hash, the block's nonce, and its hash.
 */

public class Block {
    private int num;
    private int amount;
    private Hash prevHash;
    private long nonce;
    private Hash hash;

    /**
     * Constructs a block. Takes in a block number (num), a transaction amount (amount),
     * and a hash preceding the block being created (prevHash). Computes the block's nonce
     * and hash based on the previous info. 
     * @param num
     * @param amount
     * @param prevHash
     */
    public Block(int num, int amount, Hash prevHash) {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        mineBlock();
    }

    /**
     * Constructs a block. Takes in a block number (num), a transaction amount (amount),
     * and a hash preceding the block being created (prevHash), and a single-use number (nonce). 
     * Computes the block's hash based on the previous info. 
     * @param num
     * @param amount
     * @param prevHash
     */
    public Block(int num, int amount, Hash prevHash, long nonce) {
        this.num = num;
        this.amount = amount;
        this.prevHash = prevHash;
        this.nonce = nonce;
        calculateHash();
    }

    /**
     * Computes a valid hash and nonce. Updates the respective values.
     */
    private void mineBlock() {
        nonce = 0;
        calculateHash(); // Initialize the hash before checking validity
        while (!isValidHash()) { // [does it need a this before isValidHash()?
            nonce++;
            calculateHash();
        }
    }
    
    /**
     * Checks if the hash is valid (starts with three zeroes).
     * @return
     */
    private boolean isValidHash() {
        return this.hash.isValid(); // [does it need a this before hash?
    }

    /**
     * Computes a valid hash. Updates the block's hash value so it's a valid hash.
     */
    private void calculateHash() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(ByteBuffer.allocate(Integer.BYTES).putInt(num).array());
            md.update(ByteBuffer.allocate(Integer.BYTES).putInt(amount).array());
            if (prevHash != null) {
                md.update(prevHash.getData());
            }
            md.update(ByteBuffer.allocate(Long.BYTES).putLong(nonce).array());

            byte[] hashBytes = md.digest();
            hash = new Hash(hashBytes);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get the blocks' number.
     * @return
     */
    public int getNum() {
        return num;
    }

    /**
     * Get the block's transaction amount.
     * @return
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Get the block's nonce.
     * @return
     */
    public long getNonce() {
        return nonce;
    }

    /**
     * Get the hash preceding the block.
     * @return
     */
    public Hash getPrevHash() {
        return prevHash;
    }

    /**
     * Get the block's hash.
     * @return
     */
    public Hash getHash() {
        return hash;
    }

    /**
     * Get the block's fields and values in the form of a string.
     */
    @Override
    public String toString() {
        return String.format("Block %d (Amount: %d, Nonce: %d, prevHash: %s, hash: %s)",
                num, amount, nonce, prevHash == null ? "null" : prevHash.toString(), hash.toString());
    }
}
