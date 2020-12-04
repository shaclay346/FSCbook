//Shane Claycomb
//1235640
//shaclay346@gmail.com
//CSC 3280 section: 02
//Program 4: FSCchickfila
//I will practice academic and personal integrity and excellence of character and expect the same from others.
package fscbook;

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class FSCbook {

	//method to add to the BST tree
	public static void add(Scanner in, PrintWriter output, FSCbookBST tree) {
		//read in info
		int ID = in.nextInt();
		String first = in.next();
		String last = in.next();
		String department = in.next();

		output.printf("\t%s %s (ID %d), from the %s department, joined FSCbook.\n\n", first, last, ID, department);

		//create new student object and insert them into the tree
		FSCstudent temp = new FSCstudent(ID, first, last, department);

		//insert them into the tree 
		tree.insert(temp);
	}

	//findID
	public static void findID(Scanner in, PrintWriter output, FSCbookBST book) {
		int ID = in.nextInt();

		//save a temporary student y searching the BST by ID
		FSCstudent temp = book.searchbyID(ID);

		if (temp == null) {
			output.printf("\tID %d was not found in FSCbook.\n\n", ID);
		}
		else {
			output.printf("\tFound:  ID %d, %s %s (%s Department)\n\n", temp.getID(), temp.getFirstName(), temp.getLastName(), temp.getDepartment());
		}
	}

	//search the BST by name
	public static void findName(Scanner in, PrintWriter output, FSCbookBST book) {
		String firstName = in.next();
		String lastName = in.next();

		//search the tree for them and save them
		FSCstudent temp = book.searchByName(firstName, lastName);

		//error if they were not found
		if (temp == null) {
			output.printf("\t%s %s was not found in FSCbook.\n\n", firstName, lastName);
		}
		//else they were found and print them 
		else {
			output.printf("\tFound:  ID %d, %s %s (%s Department)\n\n", temp.getID(), temp.getFirstName(), temp.getLastName(), temp.getDepartment());
		}
	}

	public static void friend(Scanner in, PrintWriter output, FSCbookBST book) {
		boolean flag = true;
		//read in both names
		String firstName1 = in.next();
		String lastName1 = in.next();

		String firstName2 = in.next();
		String lastName2 = in.next();

		//search the tree for both of them
		FSCstudent temp1 = book.searchByName(firstName1, lastName1);
		FSCstudent temp2 = book.searchByName(firstName2, lastName2);

		//if either are null display an error message
		if (temp1 == null || temp2 == null) {
			flag = false;
			output.print("\tCannot Perform FRIEND Command:\n");

			if (temp1 == null) {
				output.printf("\t\t%s %s - this student is not in FSCbook.\n", firstName1, lastName1);
			}
			if (temp2 == null) {
				output.printf("\t\t%s %s - this Student is not in FSCbook.\n", firstName2, lastName2);
			}
			output.println();
		}

		if (flag) {
			//try {
			//then check to see if they are already friends by searching the LL
			if (temp1.getMyFriends().search(temp2.getID()) != null) {
				flag = false;
				output.print("\tCannot Perform FRIEND Command:\n");
				output.printf("\t\t%s %s and %s %s are already friends.\n\n", firstName1, lastName1, firstName2, lastName2);
			}
			//} catch (NullPointerException fuck) {
			//	;
			//}
		}

		if (flag) {
			try {
				output.printf("\t%s %s and %s %s are now friends.\n\n", firstName1, lastName1, firstName2, lastName2);
				temp1.getMyFriends().insert(temp2.getID());
				temp2.getMyFriends().insert(temp1.getID());

				//add to their number of friends
//				temp1.setNumFriends(temp1.getNumFriends() + 1);
//				temp2.setNumFriends(temp2.getNumFriends() + 1);
				temp1.increaseNumFriends();
				temp2.increaseNumFriends();
			} catch (NullPointerException ex) {

			}
		}
	}

	public static void unfriend(String firstName1, String lastName1, String firstName2, String lastName2, PrintWriter output, FSCbookBST book) {
		boolean flag = true;

		FSCstudent temp1 = book.searchByName(firstName1, lastName1);

		FSCstudent temp2 = book.searchByName(firstName2, lastName2);

		if (temp1 == null || temp2 == null) {
			//output.print("IN HERE");
			flag = false;
			output.print("\tCannot Perform UNFRIEND Command:\n");
			if (temp1 == null) {
				output.printf("\t\t%s %s - this student is not in FSCbook.\n", firstName1, lastName1);
			}
			if (temp2 == null) {
				output.printf("\t\t%s %s - this Student is not in FSCbook.\n", firstName2, lastName2);
			}
			output.println();
		}
		try {
			//output.print("the search: " + temp1.getMyFriends().search(temp2.getID()));
		} catch (NullPointerException ex) {

		}

		if (flag) {
			try {
				flag = false;
				//THIS MIGHT BE BECAUSE I NEVER MADE A LINKED LIST WITHIN THIS OBJECT
				//WHEN I DECLARE THE FSCSTUDENT OBJECT TRY MAYBE SETTING A NEW FSCFRIND LLS
				if (temp1.getMyFriends().search(temp2.getID()) == null) {
					output.print("\tCannot Perform UNFRIEND Command:\n");
					output.printf("\t%s %s and %s %s are not currently friends.\n\n", firstName1, lastName1, firstName2, lastName2);
				}
			} catch (NullPointerException fuck) {
				;
			}
		}

		if (flag) {
			//remove the other from both friends lists
			temp1.getMyFriends().delete(temp2.getID());
			temp2.getMyFriends().delete(temp1.getID());

			//decrement their number of friends
			//temp1.setNumFriends(temp1.getNumFriends() - 1);
			//temp2.setNumFriends(temp2.getNumFriends() - 1);
			temp1.decreaseNumFriends();
			temp2.decreaseNumFriends();
		}
	}

	public static void delete(Scanner in, PrintWriter output, FSCbookBST book) {
		//read in name first and last then loop over their linked list and from there based on that ID remove each of their friends
		//for each friends ID find the name the corresponds and call unfriend
		// use .getHead() and try looping through in here 
		String firstName = in.next();
		String lastName = in.next();

		FSCstudent stuToDelete = book.searchByName(firstName, lastName);

		if (stuToDelete == null) {
			output.print("\tCannot Perform DELETE Command:\n");
			//YAEKO SAHU was not found in FSCbook.
			output.printf("\t\t%s %s was not found in FSCbook.\n\n", firstName, lastName);
		}
		else {
			FSCfriend hp = stuToDelete.getMyFriends().getHead();
			while (hp != null) {
				FSCstudent friend = book.searchbyID(hp.getID());

				//String firstName1, String lastName1, String firstName2, String lastName2, PrintWriter output, FSCbookBST book) {
				unfriend(stuToDelete.getFirstName(), stuToDelete.getLastName(), friend.getFirstName(), friend.getLastName(), output, book);
				hp = hp.getNext();
			}
			//once all friends have been deleted, remove them from the tree 
			book.delete(stuToDelete.getID());
			output.printf("\t%s %s has been removed from FSCbook.\n\n", firstName, lastName);
		}
	}

	public static void printFriends(Scanner in, PrintWriter output, FSCbookBST book) {
		String firstName = in.next();
		String lastName = in.next();
		boolean trigger = true;

		FSCstudent temp = book.searchByName(firstName, lastName);

		if (temp == null) {
			trigger = false;
			output.print("\tCannot Perform PRINTFRIENDS Command:");
			//LEAN ELQUIST was not found in FSCbook.
			output.printf("\n\t\t%s %s was not found in FSCbook.\n\n", firstName, lastName);
		}

		if (trigger) {
			if (temp.getNumFriends() == 0) {
				trigger = false;
				//KARLEEN MCCARRELL has no friends.
				output.printf("\t%s %s has no friends.\n\n", firstName, lastName);

			}
		}

		if (trigger) {
			output.printf("\tFriends for ID %d, %s %s (%s Department):", temp.getID(), temp.getFirstName(), temp.getLastName(), temp.getDepartment());
			//get LL length
			//temp.getMyFriends().LLlength();
			output.printf("\n\t\tThere are a total of %d friends(s).", temp.getMyFriends().LLlength());
			temp.getMyFriends().printAllFriends(book, output);
		}
	}

	public static void printMembers(FSCbookBST book, PrintWriter output) {
		output.print("\tMembers of FSCbook:\n");
		book.printMembers(output);
		output.println();
	}

	public static void main(String[] args) throws FileNotFoundException {
		// OPEN FILES
		// Input File:
		File inputFile = new File("FSCbook.in");
		if (!inputFile.exists()) {
			System.out.println("Input file, " + inputFile + ", does not exist.");
			System.exit(0);
		}
		// Output File:
		File outputFile = new File("test.out");

		// Make Scanner variable to read from input file, and 
		// make Printwriter variable to print to output
		Scanner in = new Scanner(inputFile);
		PrintWriter output = new PrintWriter(outputFile);

		int k = in.nextInt();
		FSCbookBST tree = new FSCbookBST();
		//for loop to loop the correct number of days
		for (int i = 1; i < k; i++) {
			String command = in.next();
			output.println(command + " Command");

			switch (command) {
				case "ADD":
					add(in, output, tree);
					break;
				case "FINDID":
					findID(in, output, tree);
					break;
				case "FINDNAME":
					findName(in, output, tree);
					break;
				case "FRIEND":
					friend(in, output, tree);
					break;
				case "UNFRIEND":
					String firstName1 = in.next();
					String lastName1 = in.next();

					String firstName2 = in.next();
					String lastName2 = in.next();

					unfriend(firstName1, lastName1, firstName2, lastName2, output, tree);
					break;
				case "PRINTFRIENDS":
					printFriends(in, output, tree);
					break;
				case "PRINTMEMBERS":
					printMembers(tree, output);
					break;
				case "DELETE":
					delete(in, output, tree);
					break;
				default:
				//delete(in, output, tree);
			}
		}
		//close io
		in.close();
		output.close();
	}

}
