/**
 * 
 * 
 * @author Tyler Bulley
 * UWO ID: 250498520
 */
public class Node{
	
	private Node next;
	private Configuration element;
	
	public Node() {
		next = null;
		element = null;
	}
	
	public Node(Configuration elem) {
		next = null;
		element = elem;
	}
	
	public Node getNext() {
		return next;
	}
	
	public void setNext(Node node) {
		next = node;
	}
	
	public Configuration getElement() {
		return element;
	}
	
	public void setElement(Configuration elem) {
		element = elem;
	}
	
	public boolean hasNext() {
		
		if (next == null) {
			return false;
		}
		else {
			return true;
		}
	}
	

}
