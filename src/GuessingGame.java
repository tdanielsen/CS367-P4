//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            CS-367 P4
// Files:            GuessingGame.java, BinaryTree.java, BinaryTreenode.java,
//                   IllegalBinaryTreeOpException.java
// Semester:         CS367 Fall 2014
//
// Author:           Tim Danielsen
// Email:            tdanielsen@wisc.edu
// CS Login:         danielsen
// Lecturer's Name:  J. Skrentny
// Lab Section:      N/A
//////////////////////////// 80 columns wide //////////////////////////////////

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

/**
 * Plays the classic 20 questions game, where the users enter the question and
 * answers that the program will use. It can also use an import file to act as
 * the user import.
 *
 * <p>
 * Bugs: none known
 *
 * @author Tim Danielsen
 */

public class GuessingGame
{

	/**
	 * Plays the guessing game with the user, or an import file
	 *
	 * @param args
	 *            Command line arguments
	 */
	public static void main(String[] args) throws IllegalBinaryTreeOpException,
			IOException
	{
		BinaryTree<String> questionTree = new BinaryTree<String>();
		// Used to see if the program should quit if there is an import file
		// that has q in it
		boolean endAll = true;
		if (args.length == 1)
		{
			String fileName = args[0];
			try
			{
				// Read the import file if there is one
				BufferedReader in = new BufferedReader(new FileReader(fileName));
				String line;
				// Goes through the the import file and executes each file
				while ((line = in.readLine()) != null)
				{
					if (line.equalsIgnoreCase("o"))
					{
						// Makes sure that the program won't crash if there is
						// an IllegalBinaryTreeOpException
						try
						{
							questionTree.getCurrent();
							questionTree.print();
						}
						catch (IllegalBinaryTreeOpException e)
						{
							System.out.println("Empty Tree");
						}
					}
					if (line.equalsIgnoreCase("p"))
					{
						// Makes sure that the program won't crash if there is
						// an IllegalBinaryTreeOpException
						try
						{
							questionTree.getCurrent();
							playFile(in, questionTree);
						}
						catch (IllegalBinaryTreeOpException e)
						{
							questionTree = restartFile(in, questionTree);
						}
					}
					if (line.equalsIgnoreCase("r"))
					{
						questionTree = restartFile(in, questionTree);
					}
					if (line.equalsIgnoreCase("q"))
					{
						endAll = false;
						break;
					}
				}
			}
			catch (FileNotFoundException e)
			{
				System.out.println("Cannot find the specified file");
			}
		}
		if (endAll)
		{
			Scanner scanner = new Scanner(System.in); // for console input

			boolean done = false;
			while (!done)
			{
				System.out.print("Please enter a command (o, p, q, r): ");
				String input = scanner.nextLine();
				if (input.length() > 0)
				{
					switch (input)
					{
					case "o":
						// Makes sure that the program won't crash if there is
						// an IllegalBinaryTreeOpException
						try
						{
							questionTree.getCurrent();
							questionTree.print();
						}
						catch (IllegalBinaryTreeOpException e)
						{
							System.out.println("Empty Tree");
						}

						break;

					case "p":
						// Makes sure that the program won't crash if there is
						// an IllegalBinaryTreeOpException
						try
						{
							questionTree.getCurrent();
							play(scanner, questionTree);
						}
						catch (IllegalBinaryTreeOpException e)
						{
							questionTree = restart(scanner, questionTree);
						}
						break;

					case "q":
						done = true;
						break;

					case "r":
						questionTree = restart(scanner, questionTree);
						break;

					default: // ignore invalid commands
						System.out.println("Incorrect command.");
						break;

					}
				}
			}
		}
	}

	/**
	 * Restarts the guessing tree with a new starting question and two new
	 * answers based off of it (used when using an import file)
	 *
	 * @param in
	 *            Buffered Reader that reads in the import file
	 * @param tree
	 *            The tree that will be created based on the input values
	 * @return a Binary tree
	 */
	private static BinaryTree<String> restartFile(BufferedReader in,
			BinaryTree<String> tree) throws IllegalBinaryTreeOpException,
			IOException
	{
		String input = "";
		System.out.println("Please enter a question.");
		// Goes to the next line and uses that line for input
		input = in.readLine();
		tree = new BinaryTree<String>(input);
		System.out.println
			("Please enter something that is true for that question.");
		// Goes to the next line and uses that line for input
		input = in.readLine();
		tree.addLeftChild(input);
		System.out.println
			("Please enter something that is false for that question.");
		// Goes to the next line and uses that line for input
		input = in.readLine();
		tree.addRightChild(input);
		return tree;
	}

	/**
	 * Plays the game based on the input from the imported file
	 *
	 * @param in
	 *            Buffered Reader that reads in the import file
	 * @param tree
	 *            The tree that the game will be played with
	 */
	private static void playFile(BufferedReader in, BinaryTree<String> tree)
			throws IllegalBinaryTreeOpException, IOException
	{
		String input = "";
		boolean guessingInProgress = true;
		tree.start();
		while (guessingInProgress)
		{
			if (tree.isLeaf())
			{
				System.out.println("I guess: " + tree.getCurrent()
						+ ". Was I right?");
				// Goes to the next line and uses that line for input
				input = in.readLine();
				if (input.equalsIgnoreCase("y"))
				{
					System.out.println("I win.");
				}
				if (input.equalsIgnoreCase("n"))
				{
					failedGuessFile(in, tree);
				}
				guessingInProgress = false;
			}
			else
			{
				System.out.println(tree.getCurrent());
				// Goes to the next line and uses that line for input
				input = in.readLine();
				if (input.equalsIgnoreCase("y"))
				{
					tree.goLeft();
				}
				if (input.equalsIgnoreCase("n"))
				{
					tree.goRight();
				}
			}
		}

	}

	/**
	 * Fixes the guessing tree if the program can't guess the answer
	 *
	 * @param in
	 *            Buffered Reader that reads in the import file
	 * @param tree
	 *            The tree that the game will be changed
	 */
	private static void failedGuessFile(BufferedReader in,
			BinaryTree<String> tree) throws IllegalBinaryTreeOpException,
			IOException
	{
		String input = "";
		// Saves the old answer to be put in later
		String oldAnswer = tree.getCurrent();
		System.out.println
			("Darn. Ok, tell me a question that is true for your answer, but false for my guess.");
		// Goes to the next line and uses that line for input
		input = in.readLine();
		tree.changeCurrent(input);
		System.out.println("Thanks. And what were you thinking of?");
		// Goes to the next line and uses that line for input
		input = in.readLine();
		tree.addLeftChild(input);
		tree.addRightChild(oldAnswer);

	}

	/**
	 * Restarts the guessing tree with a new starting question and two new
	 * answers based off of it
	 *
	 * @param scanner
	 *            The scanner that reads in the user input
	 * @param tree
	 *            The tree that will be created based on the input values
	 * @return a Binary tree
	 */
	private static BinaryTree<String> restart(Scanner scanner,
			BinaryTree<String> tree) throws IllegalBinaryTreeOpException
	{
		String input = "";
		System.out.println("Please enter a question.");
		input = scanner.nextLine();
		tree = new BinaryTree<String>(input);
		System.out
				.println("Please enter something that is true for that question.");
		input = scanner.nextLine();
		tree.addLeftChild(input);
		System.out
				.println("Please enter something that is false for that question.");
		input = scanner.nextLine();
		tree.addRightChild(input);
		return tree;
	}

	/**
	 * Plays the game based on the input from user
	 *
	 * @param scanner
	 *            The scanner that reads in the user input
	 * @param tree
	 *            The tree that the game will be played with
	 */
	private static void play(Scanner scanner, BinaryTree<String> tree)
			throws IllegalBinaryTreeOpException
	{
		String input = "";
		tree.start();
		boolean guessingInProgress = true;
		while (guessingInProgress)
		{
			// Checks if the programs is at the end of the guessing tree
			if (tree.isLeaf())
			{
				System.out.println("I guess: " + tree.getCurrent()
						+ ". Was I right?");
				input = scanner.nextLine();
				if (input.equalsIgnoreCase("y"))
				{
					System.out.println("I win.");
				}
				if (input.equalsIgnoreCase("n"))
				{
					failedGuess(scanner, tree);
				}
				guessingInProgress = false;
			}
			else
			{
				System.out.println(tree.getCurrent());
				input = scanner.nextLine();
				if (input.equalsIgnoreCase("y"))
				{
					tree.goLeft();
				}
				if (input.equalsIgnoreCase("n"))
				{
					tree.goRight();
				}
			}
		}
	}

	/**
	 * Fixes the guessing tree if the program can't guess the answer
	 *
	 * @param scanner
	 *            The scanner that reads in the user input
	 * @param tree
	 *            The tree that the game will be changed
	 */
	private static void failedGuess(Scanner scanner, BinaryTree<String> tree)
			throws IllegalBinaryTreeOpException
	{
		String input = "";
		// Saves the old answer to be put in later
		String oldAnswer = tree.getCurrent();
		System.out
				.println("Darn. Ok, tell me a question that is true for your answer, but false for my guess.");
		input = scanner.nextLine();
		tree.changeCurrent(input);
		System.out.println("Thanks. And what were you thinking of?");
		input = scanner.nextLine();
		tree.addLeftChild(input);
		tree.addRightChild(oldAnswer);
	}
}
