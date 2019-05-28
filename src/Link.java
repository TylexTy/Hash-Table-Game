
/**
 * 
 * @author Tyler Bulley
 * UWO ID: 250498520
 *
 */

public class Link {
	
	protected int count; // keeps track of number of nodes in the link
	private Node head; // reference to the first node
	
	
	public Link() {
		count = 0;
		head = null;
	}
	
	
	/**
	 * adds a node containing the configuration object to the link
	 * 
	 * @param pair Configuration object
	 */
	public void add(Configuration pair) {
		
		if (head == null) {
			head = new Node(pair);
		}
		
		Node temp = new Node(pair);
		Node current = head;
		
		while (current.hasNext()) {
			current = current.getNext();
		}
		current.setNext(temp); //adds the node containing the configuration object to the rear
		count++;	
	}
	
	/**
	 * Removes a node containing a configuration object from the link
	 * @param config string representation of the game board
	 * @return boolean of whether the configuration object was successfully removed
	 */
	public boolean remove(String config) {
		
		if (head == null) {
			return false;
		}
		Node current = head;
		Node prev = null;
		
		// we continue the loop as long as there is a next element and we haven't found a match.
		// we save the current as prev, and save the next node as current.
		while (current.hasNext() && !current.getElement().getStringConfiguration().equals(config)) {
			prev = current;
			current = current.getNext();
		}
		// if we find a match...
		if (current.getElement().getStringConfiguration().equals(config)) {
			// if it's the first entry, then we make the head equal the next element, then remove current
			if (prev == null) {
				head = current.getNext();
				current = null;
				count--;
			}
			// if it's not the first entry, we set prev's next value to current's next, effectively skipping current
			else {
				prev.setNext(current.getNext());
				current = null;
				count--;
			}
			// found a match so true
			return true;
		}
		// if the code gets here, its because we didn't find a match so return false
		else {
			return false;
		}
	}
	
	/**
	 * returns the first node in the link or null if there isn't one.
	 * @return Node representing first node in link or null
	 */
	
	public Node first() {
	
		if (count == 0) {
			return null;
		}
		else {
			return head;
		}
	}
	

}
