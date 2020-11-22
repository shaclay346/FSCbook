package fscbook;

//LLnode class 
public class FSCfriend {

	private int ID;
	private FSCfriend next;

	public FSCfriend(int ID) {
		this.ID = ID;
		this.next = null;
	}

	public FSCfriend(int ID, FSCfriend next) {
		this.ID = ID;
		this.next = next;
	}
	
	

	
	
	//getters and setters
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public FSCfriend getNext() {
		return next;
	}

	public void setNext(FSCfriend next) {
		this.next = next;
	}
}
