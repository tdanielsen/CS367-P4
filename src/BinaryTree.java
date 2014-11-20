///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  GuessingGame.java
// File:             BinaryTree.java
// Semester:         CS367 Fall 2014
//
// Author:           Tim Danielsen tdanielsen@wisc.edu
// CS Login:         danielsen
// Lecturer's Name:  J. Skrentny
// Lab Section:      N/A
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * A tree where each node has 2 or less children and can be altered and a 
 * reference node used for traversal
 *
 * <p>Bugs: none known
 *
 * @author Tim Danielsen
 */

public class BinaryTree<E>
{
	private BinaryTreenode<E> root;
	private BinaryTreenode<E> ref;
	
	/**
	 * Makes a new binary tree with an empty root node and a reference to the
	 * root node
	 */
	public BinaryTree()
	{
		root = new BinaryTreenode<E>();
		ref = root;
	}
	
	/**
	 * Makes a new binary tree with a root node that has the value of the
	 * argument
	 *
	 * @param data the item that will be in the root node of the tree
	 */
	public BinaryTree(E data)
	{
		root = new BinaryTreenode<E>(data);
		ref = root;
	}
	
	/**
	 * Sets the reference to the root node
	 */
	public void start()
	{
		ref = root;
	}
	
	/**
	 * Returns the data that is stored in the node that the reference is at
	 *
	 * @return the data in the node that the reference is at
	 */
	public E getCurrent() throws IllegalBinaryTreeOpException
	{
		if (ref.getData() == null)
		{
			throw new IllegalBinaryTreeOpException("Null node");
		}
		return ref.getData();
	}
	
	/**
	 * Moves the reference to the left child of the node that the reference is
	 * at.
	 * 
	 * @throws IllegalBinaryTreeOpException
	 */
	public void goLeft() throws IllegalBinaryTreeOpException
	{
		if (ref.getLeft() == null)
		{
			throw new IllegalBinaryTreeOpException("No left child");
		}
		ref = ref.getLeft();
	}
	
	/**
	 * Moves the reference to the right child of the node that the reference is
	 * at.
	 * 
	 * @throws IllegalBinaryTreeOpException
	 */
	public void goRight() throws IllegalBinaryTreeOpException
	{
		if (ref.getRight() == null)
		{
			throw new IllegalBinaryTreeOpException("No right child");
		}
		ref = ref.getRight();
	}
	
	/**
	 * Determines if the current node is a leaf node.
	 *
	 * @return true if the node is a leaf and false otherwise
	 */
	public boolean isLeaf()
	{
		if (ref.getLeft() == null && ref.getRight() == null)
		{
			return true;
		}
		return false;
	}
	
	/**
	 * Changes the data in the current node.
	 *
	 * @param data the new value to replace the old value in the node
	 */
	public void changeCurrent(E data)
	{
		ref.setData(data);
	}
	
	/**
	 * Adds a new right child to the current node, as long as there is no
	 * right child.
	 *
	 * @param data the node that will become the right child
	 * @throws IllegalBinaryOpException
	 */
	public void addRightChild(E data) throws IllegalBinaryTreeOpException
	{
		if (ref.getRight() != null)
		{
			throw new IllegalBinaryTreeOpException("Right child already exists");
		}
		ref.setRight(data);
	}
	
	/**
	 * Adds a new left child to the current node, as long as there is no left.
	 * child
	 *
	 * @param data the node that will become the left child
	 * @throws IllegalBinaryOpException
	 */
	public void addLeftChild(E data) throws IllegalBinaryTreeOpException
	{
		if (ref.getLeft() != null)
		{
			throw new IllegalBinaryTreeOpException("Left child already exists");
		}
		ref.setLeft(data);
	}
	
	/**
	 * Moves the reference to the root and prints out the tree.
	 */
	public void print()
	{
		start();
		print(ref, "");
	}
	
	/**
	 * Recursively goes through the tree and prints out the data in each node
	 *
	 * @param ref the reference to a node in the tree
	 * @param blank the string that holds the values from the nodes
	 */
	private void print(BinaryTreenode<E> ref, String blank)
	{
		System.out.println(ref.getData());
		blank = blank + "   ";
		if (ref.getLeft() != null && ref.getRight() != null)
		{
			System.out.print(blank);
			print(ref.getLeft(), blank);
			System.out.print(blank);
			print(ref.getRight(), blank);
		}
	}
	

}
