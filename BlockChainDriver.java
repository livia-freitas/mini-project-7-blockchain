package mp7;
import java.util.Scanner;

/**
 * Authors: Christina Vu and Livia Stein Freitas
 * 
 * A class that handles user interaction with the blockchain. Implements commands to:
 * mine an amount, append a block, remove a node, check blockchain validity, print balances,
 * print available commands, and quit. 
 */
public class BlockChainDriver {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java BlockChainDriver <initial_amount>");
            System.exit(1);
        }

        int initialAmount = Integer.parseInt(args[0]); 
        BlockChain blockChain = new BlockChain(initialAmount); //creates blockchain with inputted inicial balance

        Scanner scanner = new Scanner(System.in);

        while (true) { //keeps the program running so user can interact with the blockchain
            System.out.println(blockChain.toString());
            System.out.print("Command? ");
            String command = scanner.next();

            switch (command) {
                case "mine": //mines based on an inputted amount
                    System.out.print("Transaction amount: ");
                    int mineAmount = scanner.nextInt();
                    blockChain.mine(mineAmount);
                    break;

                case "append": //adds a transaction to the blockchain
                    System.out.print("Transaction amount: ");
                    int appendAmount = scanner.nextInt();
                    blockChain.append(new Block(blockChain.getSize(), appendAmount, blockChain.getHash()));
                    break;

                case "remove": //removes last block in the blockchani
                    blockChain.removeLast();
                    break;

                case "check": //checks if blockchain is valid
                    System.out.println("Is valid: " + blockChain.isValidBlockChain());
                    break;

                case "report": //prints Alexis' and Blakes's balances
                    blockChain.printBalances();
                    break;

                case "help": //shows available commands
                    System.out.println("Valid commands:\n" +
                            "mine: discovers the nonce for a given transaction\n" +
                            "append: appends a new block onto the end of the chain\n" +
                            "remove: removes the last block from the end of the chain\n" +
                            "check: checks that the block chain is valid\n" +
                            "report: reports the balances of Alexis and Blake\n" +
                            "help: prints this list of commands\n" +
                            "quit: quits the program");
                    break;

                case "quit": //quits program
                    System.exit(0);

                default: //handles invalid commands
                    System.out.println("Invalid command. Type 'help' for a list of commands.");
            }
        }
    }
}
