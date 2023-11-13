# mini-project-7-blockchain

Authors: Livia Stein Freitas and Christina Vu

-> A Java implementation of a blockchain simuating a series of transactions between two users, Alexis and Blake. 
-> The project consists of 4 files:
	* Hash.java: wrapper class for the hash byte array
	* Block.java: contains the information of each node in the chain
	* BlockChain.java: a linked structure that points to the first and last nodes in the chain. 
	* BlockChainDriver: user interface with commands like 'mine', 'append', 'help', etc.

Acknowledgements:

Sam Rebelsky helped us figure out how to implement isValid().
StackOverFlow helped us with the implementation of calculateHash(), the Node private class, isValidBlockChain(), toString(), and potentially other minor things.
