package mp7;
import java.util.Arrays;

/**
 * Authors: Christina Vu and Livia Stein Freitas
 * 
 * Wrapper class for the hash byte arrays. Contains a method to get the data,
 * check the hash's validity, convert the byte array into a string, and to
 * check if a hash equals another object.
 */
public class Hash {
    private byte[] data;

    /**
     * Constructs a hash. Takes in a byte array.
     * @param data
     */
    public Hash(byte[] data) {
        this.data = data;
    }

    /**
     * Get the hash's byte array.
     * @return
     */
    public byte[] getData() {
        return data;
    }

    /**
     * Checks if the hash is valid (starts with three zeroes.)
     * @return
     */
    public boolean isValid() {
        return data[0] == 0 && data[1] == 0 && data[2] == 0;
    }

    /**
     * Returns the string representation of a hash.
     */
    @Override
    public String toString() {
        StringBuilder hexString = new StringBuilder();
        for (byte b : data) {
            hexString.append(String.format("%02X", b));
        }
        return hexString.toString();
    }

    /**
     * Checks if one hash equals another object.
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Hash hash = (Hash) other;
        return Arrays.equals(data, hash.data);
    }
}
