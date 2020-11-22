
package fscbook;

//LL class 
public class FSCfriends {
	private FSCfriend head;
	
	//constructor
	public FSCfriends(){
		this.head = null;
	}


	
	
	//insert method
	public void insert(String fName, String lName, int id, String number, double balance) {
		head = insert(head, id);
	}
	//method to insert the Student node at the correct postition of the Linked List
	private FSCfriend insert(FSCfriend head, int id) {
		// IF there is no list, newNode will be the first node, so just return it
		if (head == null || head.getID() > id) {
			//int ID, String firstName, String lastName, String phoneNumber, double balance, Student next
			head = new FSCfriend(id, head);
			return head;
		}
		// ELSE, we have a list. Insert the new node at the correct location
		else {
			// We need to traverse to the correct insertion location...so we need a help ptr
			FSCfriend helpPtr = head;
			// Traverse to correct insertion point
			while (helpPtr.getNext() != null) {
				if (helpPtr.getNext().getID() > id) {
					break; // we found our spot and should break out of the while loop
				}
				helpPtr = helpPtr.getNext();
			}
			// Now make the new node. Set its next to point to the successor node.
			// And then make the predecessor node point to the new node
			FSCfriend newNode = new FSCfriend(id, helpPtr.getNext());
			helpPtr.setNext(newNode);
		}
		// Return head
		return head;
	}


	//method to search the Linked list based on ID
	public FSCfriend search(int id) {
		return search(head, id);
	}
	
	private FSCfriend search(FSCfriend head, int id) {
		//create help Pointer 
		FSCfriend helpPointer = head;
		//if the Student was found in the Linked List then return that node
		while (helpPointer != null) {
			if (helpPointer.getID() == id) {
				return helpPointer;
			}
			helpPointer = helpPointer.getNext();
		}//if no student with that ID is found then return null
		return null;
	}

	//method to delete an object from the LL
	public void delete(int id) {
		head = delete(head, id);
	}

	private FSCfriend delete(FSCfriend head, int id) {
		FSCfriend hp = head;

		//empty set 
		if (head == null) {
			//if list is empty
			return null;
		}
		else { //list is not empty
			//if head is what we are deleting
			if (head.getID() == id) {
				head = head.getNext();
			}
			else {//else delete in the middle of the LL
				while (hp.getNext() != null) {
					if (hp.getNext().getID() == id) {
						//traverse to predacessor node and point it to successor of what we are deleting
						hp.setNext(hp.getNext().getNext());
						break;
					}
					hp = hp.getNext();
				}
			}
		}
		return head;
	}
	
	
	//getters and setters
	public FSCfriend getHead() {
		return head;
	}
	public void setHead(FSCfriend head) {
		this.head = head;
	}
}
