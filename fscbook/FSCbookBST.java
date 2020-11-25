package fscbook;

//Binary Search Tree class
public class FSCbookBST {

	private FSCstudent root;

	// CONSTRUCTORS
	public FSCbookBST() {
		root = null;
	}

	/* Below are MANY methods that are used on Binary Search Trees.
	 * 
	 * Examples:
	 * search, insert, delete, isEmpty, minVal, maxVal, inorder, sumNodes, and many more
	 */
	//
	// boolean | isEmpty()
	//
	public boolean isEmpty() {
		return root == null;
	}

	//
	// boolean | search(int)
	//
	public FSCstudent searchbyID(int ID) {
		return searchbyID(root, ID);
	}

	//
	// boolean | search(BSTnode, int)
	//
	private FSCstudent searchbyID(FSCstudent p, int ID) {
		if (p == null) {
			return null;
		}
		else {
			// if the data we are searching for is found at p (at the current root)
			if (ID == p.getID()) {
				return p;
			}
			else if (ID < p.getID()) {
				return searchbyID(p.getLeft(), ID);
			}
			else {
				return searchbyID(p.getRight(), ID);
			}
		}
	}

	public FSCstudent searchByName(String firstName, String lastName) {
		String name = lastName + firstName;
		return searchByName(root, name);
	}

	//
	// boolean | search(BSTnode, int)
	//
	private FSCstudent searchByName(FSCstudent p, String name) {
		//if(p != null){
			//String s2 = p.getFirstName() + p.getLastName();
		//}
		String s1 = name;
		
		if (p == null) {
			return null;
		}
		else {
			String s2 = p.getLastName() + p.getFirstName();
			// if the data we are searching for is found at p (at the current root)
			//could probaby just be if s1.equeals(s2)
			if (s1.equals(s2)) {
				return p;
			}
			else if (s1.compareTo(s2) < 0) {
				return searchByName(p.getLeft(), s1);
			}
			else { //compareTo > 0
				return searchByName(p.getRight(), s1);
			}
		}
	}

	//
	// void | insert(int)
	//
	public void insert(FSCstudent newNode) {
		//FSCstudent newNode = new FSCstudent(data);
		root = insert(root, newNode);
	}

	//
	// BSTnode | insert(BSTnode, BSTnode)
	//
	private FSCstudent insert(FSCstudent p, FSCstudent newNode) {
		// IF there is no tree, newNode will be the root, so just return it
		if (p == null) {
			return newNode;
		}

		// ELSE, we have a tree. Insert to the right or the left
		else {
			// Insert to the RIGHT of root
			if (newNode.getID() > p.getID()) {
				// Recursively insert into the right subtree
				// The result of insertion is an updated root of the right subtree
				FSCstudent temp = insert(p.getRight(), newNode);
				// Save this newly updated root of right subtree into p.right
				p.setRight(temp);
			}
			// Insert to the LEFT of root
			else {
				// Recursively insert into the left subtree
				// The result of insertion is an updated root of the left subtree
				FSCstudent temp = insert(p.getLeft(), newNode);
				// Save this newly updated root of left subtree into p.left
				p.setLeft(temp);
			}
		}
		// Return root of updated tree
		return p;
	}

//	//
//	// void | inorder()
//	//
//	public void inorder() {
//		inorder(root);
//	}
//
//	//
//	// void | inorder(BSTnode)
//	//
//	private void inorder(FSCstudent p) {
//		if (p != null) {
//			inorder(p.getLeft());
//			System.out.print(p.getData() + ", ");
//			inorder(p.getRight());
//		}
//	}
//
//	//
//	// int | sumNodes()
//	//
//	public int sumNodes() {
//		return sumNodes(root);
//	}
//
//	//
//	// int | sumNodes(BSTnode)
//	//
//	private int sumNodes(BSTnode p) {
//		if (p != null) {
//			return p.getData() + sumNodes(p.getLeft()) + sumNodes(p.getRight());
//		}
//		else {
//			return 0;
//		}
//	}
	//
	// void | delete(int)
	//
	public void delete(int ID) {
		root = delete(root, ID);
	}

	//
	// BSTnode | delete(BSTnode, int)
	//
	private FSCstudent delete(FSCstudent p, int ID) {
		FSCstudent node2delete, newnode2delete, node2save, parent;
		int saveID;
		String saveFirst;
		String saveLast;
		String saveDepart;
		//String saveLeft;

		// Step 1: Find the node we want to delete
		node2delete = findNode(p, ID);
		// If node is not found (does not exist in tree), we clearly cannot delete it.
		if (node2delete == null) {
			return root;
		}

		// Step 2: Find the parent of the node we want to delete
		parent = parent(p, node2delete);

		// Step 3: Perform Deletion based on three possibilities
		// **1** :  node2delete is a leaf node
		if (isLeaf(node2delete)) {
			// if parent is null, this means that node2delete is the ONLY node in the tree
			if (parent == null) {
				return null; // we return null as the updated root of the tree
			}
			// Delete node if it is a left child
			if (ID < parent.getID()) {
				parent.setLeft(null);
			}
			// Delete node if it is a right child
			else {
				parent.setRight(null);
			}

			// Finally, return the root of the tree (in case the root got updated)
			return p;
		}

		// **2** : node2delete has only a left child
		if (hasOnlyLeftChild(node2delete)) {
			// if node2delete is the root
			if (parent == null) {
				return node2delete.getLeft();
			}

			// If node2delete is not the root,
			// it must the left OR the right child of some node
			// IF it is the left child of some node
			if (ID < parent.getID()) {
				parent.setLeft(parent.getLeft().getLeft());
			}
			// ELSE it is the right child of some node
			else {
				parent.setRight(parent.getRight().getLeft());
			}

			// Finally, return the root of the tree (in case the root got updated)
			return p;
		}

		// **3** :  node2delete has only a right child
		if (hasOnlyRightChild(node2delete)) {
			// if node2delete is the root
			if (parent == null) {
				return node2delete.getRight();
			}

			// If node2delete is not the root,
			// it must the left OR the right child of some node
			// IF it is the left child of some node
			if (ID < parent.getID()) {
				parent.setLeft(parent.getLeft().getRight());
			}
			// ELSE it is the right child of some node
			else {
				parent.setRight(parent.getRight().getRight());
			}

			// Finally, return the root of the tree (in case the root got updated)
			return p;
		}

		// **4** :  node2delete has TWO children.
		// Remember, we have two choices: the minVal from the right subtree (of the deleted node)
		// or the maxVal from the left subtree (of the deleted node)
		// We choose to find the minVal from the right subtree.
		newnode2delete = minNode(node2delete.getRight());
		// Now we need to temporarily save the data value(s) from this node
		saveID = newnode2delete.getID();
		saveFirst = newnode2delete.getFirstName();
		saveLast = newnode2delete.getLastName();
		saveDepart = newnode2delete.getDepartment();

		// Now, we recursively call our delete method to actually delete this node that we just copied the data from
		//IF THERE IS a mess up it is here
		p = delete(p, ID);

		// Now, put the saved data (in saveValue) into the correct place (the original node we wanted to delete)
		node2delete.setID(saveID);
		node2delete.setFirstName(saveFirst);
		node2delete.setLastName(saveLast);
		node2delete.setDepartment(saveDepart);

		// Finally, return the root of the tree (in case the root got updated)
		return p;
	}

	//
	// BSTnode | minNode(BSTnode)
	//
	public FSCstudent minNode(FSCstudent root) {
		if (root == null) {
			return null;
		}
		else {
			if (root.getLeft() == null) {
				return root;
			}
			else {
				return minNode(root.getLeft());
			}
		}
	}

	//
	// BSTnode | maxNode(BSTnode)
	//
	public FSCstudent maxNode(FSCstudent root) {
		if (root == null) {
			return null;
		}
		else {
			if (root.getRight() == null) {
				return root;
			}
			else {
				return maxNode(root.getRight());
			}
		}
	}

	//
	// BSTnode | findNode(int)
	//
	public FSCstudent findNode(int data) {
		return findNode(root, data);
	}

	//
	// BSTnode | findNode(BSTnode, int)
	//
	private FSCstudent findNode(FSCstudent p, int data) {
		if (p == null) {
			return null;
		}
		else {
			// if the data we are searching for is found at p (at the current root)
			if (data == p.getID()) {
				return p;
			}
			else if (data < p.getID()) {
				return findNode(p.getLeft(), data);
			}
			else {
				return findNode(p.getRight(), data);
			}
		}
	}

	//
	// BSTnode | parent(BSTnode)
	//
	public FSCstudent parent(FSCstudent p) {
		return parent(root, p);
	}

	//
	// BSTnode | parent(BSTnode, BSTnode)
	//
	private FSCstudent parent(FSCstudent root, FSCstudent p) {
		// Take care of NULL cases
		if (root == null || root == p) {
			return null; // because there is on parent
		}
		// If root is the actual parent of node p
		if (root.getLeft() == p || root.getRight() == p) {
			return root; // because root is the parent of p
		}
		// Look for p's parent in the left side of root
		if (p.getID() < root.getID()) {
			return parent(root.getLeft(), p);
		}

		// Look for p's parent in the right side of root
		else if (p.getID() > root.getID()) {
			return parent(root.getRight(), p);
		}

		// Take care of any other cases
		else {
			return null;
		}
	}

	//
	// boolean | isLeaf(BSTnode)
	//
	public boolean isLeaf(FSCstudent p) {
		return (p.getLeft() == null && p.getRight() == null);
	}

	//
	// boolean | hasOnlyLeftChild(BSTnode)
	//
	public boolean hasOnlyLeftChild(FSCstudent p) {
		return (p.getLeft() != null && p.getRight() == null);
	}

	//
	// boolean | hasOnlyRightChild(BSTnode)
	//
	public boolean hasOnlyRightChild(FSCstudent p) {
		return (p.getLeft() == null && p.getRight() != null);
	}
}
