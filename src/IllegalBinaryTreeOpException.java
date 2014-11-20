///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Main Class File:  IllegalBinaryTreeOpException.java
// File:             BinaryTree.java
// Semester:         CS367 Fall 2014
//
// Author:           Tim Danielsen tdanielsen@wisc.edu
// CS Login:         danielsen
// Lecturer's Name:  J. Skrentny
// Lab Section:      N/A
//////////////////////////// 80 columns wide //////////////////////////////////

/**
 * An exception class that handles any violations to the Binary Tree class
 *
 * <p>Bugs: none known
 *
 * @author Tim Danielsen
 */

@SuppressWarnings("serial")
class IllegalBinaryTreeOpException extends Exception
{
	/**
	 * Makes a new IllegalBinaryTreeOpException
	 */
	public IllegalBinaryTreeOpException()
	{
		
	}
	
	/**
	 * Makes a new IllegalBinaryTreeOpException that provides a message
	 *
	 * @param message a string that describes what went wrong
	 */
	public IllegalBinaryTreeOpException(String message)
	{
		super(message);
	}

}
