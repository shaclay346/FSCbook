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
	public static void add(Scanner in, PrintWriter output, FSCbookBST tree){
		int ID = in.nextInt();
		String first = in.next();
		String last = in.next();
		String department = in.next();
		
		output.printf("\t%s %s (ID %d), from the %s department, joined FSCbook\n\n", first, last, ID, department);
		
		//create new student object and insert them into the tree
		FSCstudent temp = new FSCstudent(ID, first, last, department);
		
		tree.insert(temp);	
	}
	
	public static void findID(Scanner in, PrintWriter output, FSCbookBST book){
		int ID = in.nextInt();
		
		FSCstudent temp = book.searchbyID(ID);
		
		
		if(temp == null)
			output.printf("ID %d was not found in FSCbook", ID);
		else
			output.printf("Found:  ID %d, %s %s (%s Department)", temp.getID(), temp.getFirstName(), temp.getLastName(), temp.getDepartment());
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
			
			
			//call the add method
			if(command.equals("ADD")){
				add(in, output, tree);
			}
		}
		//close io
		in.close();
		output.close();
	}
	
}
