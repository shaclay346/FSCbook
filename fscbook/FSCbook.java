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

	public static void add(Scanner in, PrintWriter output, FSCbookBST tree) {
		int ID = in.nextInt();
		String first = in.next();
		String last = in.next();
		String department = in.next();

		output.printf("\t%s %s (ID %d), from the %s department, joined FSCbook\n\n", first, last, ID, department);

		//create new student object and insert them into the tree
		FSCstudent temp = new FSCstudent(ID, first, last, department);

		tree.insert(temp);
	}

	public static void findID(Scanner in, PrintWriter output, FSCbookBST book) {
		int ID = in.nextInt();

		FSCstudent temp = book.searchbyID(ID);

		if (temp == null) {
			output.printf("\tID %d was not found in FSCbook\n\n", ID);
		}
		else {
			output.printf("\tFound:  ID %d, %s %s (%s Department)\n\n", temp.getID(), temp.getFirstName(), temp.getLastName(), temp.getDepartment());
		}
	}

	public static int getID(String firstName, String lastName, FSCbookBST book) {
		FSCstudent temp = book.searchByName(firstName, lastName);

		if (temp == null) {
			return 0;
		}
		else {
			return temp.getID();
		}
	}

	public static void findName(Scanner in, PrintWriter output, FSCbookBST book) {
		String firstName = in.next();
		String lastName = in.next();

		FSCstudent temp = book.searchByName(firstName, lastName);

		if (temp == null) {
			output.printf("\t%s %s was not found in FSCbook\n\n", firstName, lastName);
		}
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

		FSCstudent temp1 = book.searchByName(firstName1, lastName1);
		FSCstudent temp2 = book.searchByName(firstName2, lastName2);
		output.print(temp1);


		//int ID1 = getID(firstName1, lastName1, book);
		//	int ID2 = getID(firstName2, lastName2, book);
		if (temp1 == null || temp2 == null) {
			flag = false;
			output.print("\n\tCannot Perform FRIEND Command:");
			if (temp1 == null) {
				output.printf("\n\t\t%s %s - this Student is not in FSCbook.", firstName1, lastName1);
			}
			if (temp2 == null) {
				output.printf("\n\t\t%s %s - this Student is not in FSCbook.", firstName1, lastName1);
			}
		}

		
		if (flag) {
			try {
				//THIS MIGHT BE BECAUSE I NEVER MADE A LINKED LIST WITHIN THIS OBJECT
				//WHEN I DECLARE THE FSCSTUDENT OBJECT TRY MAYBE SETTING A NEW FSCFRIND LLS
				if (temp1.getMyFriends().search(temp2.getID()) != null) {
					output.print("\n\tCannot Perform FRIEND Command:\n");
					output.printf("\n\t\t%s %s and %s %s are already friends\n", firstName1, lastName1, firstName2, lastName2);
				}
			} catch (NullPointerException fuck) {
				;
			}
		}

		if (flag) {
			try{
			output.printf("\n\t%s %s and %s %s are now friends\n\n", firstName1, lastName1, firstName2, lastName2);
			temp1.getMyFriends().insert(temp2.getID());
			temp2.getMyFriends().insert(temp1.getID());
			
			//add to their number of friends
			temp1.setNumFriends(temp1.getNumFriends() + 1);
			temp2.setNumFriends(temp2.getNumFriends() + 1);
			}catch(NullPointerException ex){
				
			}
		}
	}
	
	public static void unfriend(Scanner in, PrintWriter output, FSCbookBST book){
		boolean flag = true;
		//read in both names
		String firstName1 = in.next();
		String lastName1 = in.next();

		String firstName2 = in.next();
		String lastName2 = in.next();

		output.print("\n" + firstName1 + " " + lastName1 + "\n");
		
		output.print("\n" + firstName2 + " " + lastName2 + "\n");
		
		FSCstudent temp1 = book.searchByName(firstName1, lastName1);
		//output.print("HERE");
		FSCstudent temp2 = book.searchByName(firstName2, lastName2);
		//output.print("HERE");


		if (temp1 == null || temp2 == null) {
			output.print("IN HERE");
			flag = false;
			output.print("\n\tCannot Perform UNFRIEND Command:");
			if (temp1 == null) {
				output.printf("\n\t\t%s %s - this Student is not in FSCbook.", firstName1, lastName1);
			}
			if (temp2 == null) {
				output.printf("\n\t\t%s %s - this Student is not in FSCbook.", firstName1, lastName1);
			}
		}
		try{
			output.print("the search: " + temp1.getMyFriends().search(temp2.getID()) );
		}catch(NullPointerException ex){
			
		}
		
		if (flag) {
			try {
				flag = false;
				//THIS MIGHT BE BECAUSE I NEVER MADE A LINKED LIST WITHIN THIS OBJECT
				//WHEN I DECLARE THE FSCSTUDENT OBJECT TRY MAYBE SETTING A NEW FSCFRIND LLS
				if (temp1.getMyFriends().search(temp2.getID()) == null) {
					output.print("\n\tCannot Perform UNFRIEND Command:\n");
					output.printf("\n\t\t%s %s and %s %s are not currently friends.\n", firstName1, lastName1, firstName2, lastName2);
				}
			} catch (NullPointerException fuck) {
				;
			}
		}
		
		if(flag){
			output.print("\nUNADD THEM\n");
			//decrement their number of friends
			temp1.setNumFriends(temp1.getNumFriends() - 1);
			temp2.setNumFriends(temp2.getNumFriends() - 1);
		}	
	}
	
	public static void printFriends(Scanner in, PrintWriter output, FSCbookBST book){
		String firstName = in.next();
		String lastName = in.next();
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
					unfriend(in, output, tree);
					break;
				case "PRINTFRIENDS":
					printFriends(in, output, tree);
					break;
				//default:
				//	output.print("here");

			}
//			//call the add method
//			if(command.equals("ADD")){
//				add(in, output, tree);
//			}
//			else if(command)
		}
		//close io
		in.close();
		output.close();
	}

}
