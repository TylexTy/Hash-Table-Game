/**
 * 
 * @author Tyler Bulley
 * UWO ID: 250498520
 *
 */

public class HashDictionary implements DictionaryADT {

	int size; // size of the hash Dictionary
	private Link[] table; // initializes the array of link objects table.
	int X = 39; // used in polynomial hashcode

	/**
	 * constructor initializes the hash table
	 * 
	 * @param size size of the hash table
	 */
	
	public HashDictionary(int size) {
		
		this.size = size;
		table = new Link[size]; 
		
		// initialize the table with null entries
		
		for (int i=0; i < size; i++) {
			table[i] = null;
		}
		
	}

	/**
	 * Puts the Configuration object in the hash table if it isn't already in it.
	 * 
	 * @param pair Configuration object to put in table
	 * @throws DictionaryException is thrown when pair is already in the dictionary
	 * @return an int, 0 if it was first entry, 1 if 1 or more entries already exist
	 */
	public int put(Configuration pair) throws DictionaryException {
		
		
		char[] hashConf = pair.getStringConfiguration().toCharArray(); // changes the string into character array
		int hash = (int) hashConf[hashConf.length-1]; // starts the hashcode as the integer value of the last character
		
		// this loop evaluates the hash function using polynomial hashing
		for (int i=hashConf.length-2; i > 0; i--) {
			hash = (hash*X + (int) hashConf[i]) % size;

		}
		// if the entry in the location hash of table array is empty, create a new link object
		// then use the link classes function add to add the configuration object
		
		if (table[hash] == null) {
			table[hash] = new Link();
			table[hash].add(pair);
			return 0;
		}
		// else, create a new node which will reference the head node of the link
		else {
			Node entry = table[hash].first();
			
			// the loop continues to get next node while there is a next node and while there is no match
			while (entry.hasNext() && !entry.getElement().getStringConfiguration().equals(pair.getStringConfiguration())) {
				entry = entry.getNext();
			}
			if (!entry.hasNext()) {
				table[hash].add(pair);
				return 1; // because there was already a node in the link.
			}
			// if the code gets here without being returned, it's because there was a match, and therefore
			// the configuration object was already in the dictionary
			throw new DictionaryException(pair + " was already in the Dictionary"); 
			
		}
	}
	
	/**
	 * Attempts to remove a Configuration object given a String of the configuration
	 * 
	 * @param config the String representation of the game Board
	 * @throws DictionaryException is thrown when the Configuration object could not be found
	 */
	
	public void remove(String config) throws DictionaryException {
		
		boolean isRemoved = false;
		
		char[] hashConf = config.toCharArray();
		int hash = (int) hashConf[hashConf.length-1];
		
		for (int i=hashConf.length-2; i > 0; i--) {
			hash = (hash*X + (int) hashConf[i]) % size;

		}
		if (table[hash] != null) {
			
			isRemoved = table[hash].remove(config);
			
		}
			
		// if the function remove from the link class returns false, then nothing was removed
		if (!isRemoved) {
			throw new DictionaryException(config + "could not be found in the Dictionary");	
		}
	}
	
	
	/**
	 * Gets the score that matches the configuration string
	 * 
	 *@param config String representation of the game Board
	 *@return int the score that matches the configuration string, or -1 if not found
	 */
	public int getScore(String config) {
		
		char[] hashConf = config.toCharArray();
		int hash = (int) hashConf[hashConf.length-1];
		
		for (int i=hashConf.length-2; i > 0; i--) {
			hash = (hash*X + (int) hashConf[i]) % size;

		}
		if (table[hash] == null) {
			return -1;
		}
		Node entry = table[hash].first(); // references the head node
		
		// loop continues to get Next until entry is null or a match is found
		while (entry != null && !entry.getElement().getStringConfiguration().equals(config)) {
			 entry = entry.getNext();
		 }
		if (entry == null) {
			return -1;
		}
		// else, a match is found, return the associated score
		else {
			return entry.getElement().getScore();
		}
		
	}
		
		
}


