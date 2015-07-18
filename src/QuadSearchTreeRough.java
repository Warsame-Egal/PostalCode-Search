import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;

import postalCode.PostalCode;

/** Basic binary search tree that supports insertion and three traversal patterns. */
public class QuadSearchTreeRough {
	private QuadNode root; // nodes root
	private int maxItemsBeforeSplit;
	private int size;
	
	public QuadSearchTreeRough(int maxItemsBeforeSplit) {
		this.maxItemsBeforeSplit = maxItemsBeforeSplit;
	}
	
	//add returns true or false indicating weather a postal code came in or not
	public boolean add(PostalCode postalCode) { // dealing with refrence to postal code objects
		if (root == null) { // special case . . . empty tree . . . establish root Node
			root = new QuadNode(postalCode); //recieving refrence to postal code
			return true;
		}	else {
			return root.add(postalCode); //access add method in quadnode
			
		}
	}
	
//	private boolean add(QuadNode node, PostalCode postalCode) { // RECURSIVE ALGORITHM		
//		if (postalCode < node.postalCodeCollection) { // new value is smaller, thus pursue the left branch
//			if (node.northWest == null) { // exhausted left-branch processing, must add new Node
//				node.northWest = new QuadNode(postalCode);
//				return true; // successfully added to left branch of current node object
//			}	else
//				return add(node.northWest, postalCode); // tree is deeper, thus pursue, return result in case hit a duplicate on deeper calls
//		}else if (postalCode > node.postalCodeCollection) {// new value is larger, thus pursue the right branch
//			if (node.northEast == null) { // exhausted right-branch processing, must add new Node
//				node.northEast = new QuadNode(postalCode);
//				return true; // successfully added to right branch of current node object
//			}	else
//			return add(node.northEast, postalCode); // tree is deeper, thus pursue, return result in case hit a duplicate on deeper calls
//		} else {
//			return false; // encountered a duplicate . . . reject attempted addition
//		}
//	} // end add()
	
//  // Beautiful illustration of the IN-ORDER RECURSIVE ALGORITHM
//	public void displayInOrder() { System.out.print("\nIn-Order Traversal: "); displayInOrder(root); }
//	private void displayInOrder(QuadNode node) {
//		if (node == null)
//			return;
//		displayInOrder(node.northWest);
//		System.out.print(node);  // The ESSENCE of IN-ORDER: Act on the node AFTER finishing all LEFT branch processing, but BEFORE embarking RIGHT branch processing.
//		displayInOrder(node.northEast);
//	} // end displayInOrder()
	
  // Beautiful illustration of the PRE-ORDER RECURSIVE ALGORITHM
	public void displayPreOrder() { System.out.print("\nPre-Order Traversal: "); displayPreOrder(root); }
	private void displayPreOrder(QuadNode node) {
		if (node == null)
			return;
		System.out.print(node); // The ESSENCE of PRE-ORDER: Act on the node BEFORE embarking on any LEFT or RIGHT branch processing. print node beofre persuing left and right nodes
		displayPreOrder(node.northWest);
		displayPreOrder(node.northEast);
		displayPreOrder(node.southWest);
		displayPreOrder(node.southEast);
	} // end displayPreOrder()
	
  // Beautiful illustration of the POST-ORDER RECURSIVE ALGORITHM
	public void displayPostOrder() { System.out.print("\nPost-Order Traversal: "); displayPostOrder(root); }
	private void displayPostOrder(QuadNode node) {
		if (node == null)
			return;
		displayPostOrder(node.northWest);
		displayPostOrder(node.northEast);
		displayPreOrder(node.southWest);
		displayPreOrder(node.southEast);
		System.out.print(node); // The ESSENCE of POST-ORDER: Act on the node AFTER finishing all LEFT and RIGHT branch processing.
	} // end displayPostOrder()
	
	@Override
	public String toString() {
		return "Binary Search Tree: size:" + size;
	}
	
	// ***************************************************************************
	// ***************************************************************************
	// Start inner class Node
	// Inner class to support the storage of items to be added to the tree, in this case, "int" values 
	private class QuadNode {
		private Collection<PostalCode> postalCodeCollection; // The use of "private" is technically NOT required, because they are specified in a "private" class.
		private QuadNode northWest;  // Even though "private" the parent (containing) class has full rights of access.
		private QuadNode northEast; // Using "private" is probably "better" form, even though not required.
		private QuadNode southWest;  // Even though "private" the parent (containing) class has full rights of access.
		private QuadNode southEast; // Using "private" is probably "better" form, even though not required.

		private QuadNode() {
			postalCodeCollection = new ArrayList<PostalCode>(maxItemsBeforeSplit);
		}
		private QuadNode(PostalCode postalCode) {
			postalCodeCollection = new ArrayList<PostalCode>(maxItemsBeforeSplit); //create the quad node. null initially
			this.postalCodeCollection.add(postalCode); // add a postal code to the node
			++size; // instance field of the outer class BinarySearchTree. It is accessible because this class is "non-static." 
		}
		
		public boolean add(PostalCode postalCode) {
			// case1: already an INTERNAL node I CANNOT store postalCode here. SUGGESTS RECURSIVE CALLS
			if(postalCodeCollection == null) {// if true INTERNAL node. which does not accept PostalCode objects here.
				// recursivelly add to a child QuadNode
				// ALL four child QuadNodes MUST ALREADY exist, nothWest/northEast/southWest/southEast
				// In which QuadNode should the new PostalCode Object reside
			addToCorrectQuadrant(postalCode);
			} else { // LEAFE node because they already have an existing Collection of PostalCode Objects  	
				// case2a: LEAF node, that is currently storing PostalCode AND it has MORE room. EASY ONE.
				if (postalCodeCollection.size() < maxItemsBeforeSplit ) {
						postalCodeCollection.add(postalCode);
						return true;
				} else {
					// case 2b: LEAF node, that is currently storing PostalCode AND it has MORE NO room.
					northWest = new QuadNode();
					northEast = new QuadNode();
					southWest = new QuadNode();
					southEast = new QuadNode();
					for (PostalCode existingPostalCode : postalCodeCollection) { // existing postal code contains postalcode collection in each iteration of loop
						addToCorrectQuadrant(existingPostalCode);
					}
					addToCorrectQuadrant(postalCode);
				}
			}
			
			
			// case3: LEAF node that is currently storing PostalCode And it has NO MORE room . MORE COMPLICATING
			this.postalCodeCollection.add(postalCode);
			++size; // instance field of the outer class BinarySearchTree. It is accessible because this class is "non-static." 
			return true; // TODO must fix this
		}
		
		private void addToCorrectQuadrant(PostalCode postalCode) {
			
			if (true) // TODO test to see if the PostalCode object should reside in northWest QuadNode
			northWest.add(postalCode);
			else if (true) // TODO test to see if the PostalCode object should reside in northEast QuadNode
			northEast.add(postalCode);
			else if (true) // TODO test to see if the PostalCode object should reside in southWest QuadNode
			southWest.add(postalCode);
			else if (true) // TODO test to see if the PostalCode object should reside in southEast QuadNode
			southEast.add(postalCode);
		} 
		
		@Override
		public String toString() {
			return postalCodeCollection + " ";
		}
	} // end inner class Node
	// End inner class Node
	//***************************************************************************
	//***************************************************************************
} // end class BinarySearchTree