package fscbook;

//tree node class 
public class FSCstudent {

	private int ID;
	private String firstName;
	private String lastName;
	private String department;
	private int numFriends;
	private FSCfriends myFriends;
	private FSCstudent right;
	private FSCstudent left;
	
	
	 // CONSTRUCTORS
	public FSCstudent(int ID, String firstName, String lastName, String department) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		left = right = null;
	}

	public FSCstudent(int ID, String firstName, String lastName, String department, FSCstudent right, FSCstudent left) {
		this.ID = ID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.department = department;
		this.right = right;
		this.left = left;
	}
    
	


	//getters and setters
	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public int getNumFriends() {
		return numFriends;
	}

	public void setNumFriends(int numFriends) {
		this.numFriends = numFriends;
	}

	public FSCfriends getMyFriends() {
		return myFriends;
	}

	public void setMyFriends(FSCfriends myFriends) {
		this.myFriends = myFriends;
	}

	public FSCstudent getRight() {
		return right;
	}

	public void setRight(FSCstudent right) {
		this.right = right;
	}

	public FSCstudent getLeft() {
		return left;
	}

	public void setLeft(FSCstudent left) {
		this.left = left;
	}
}
