import java.util.Scanner;


public class GuessingGame
{

	public static void main(String[] args) throws IllegalBinaryTreeOpException
	{
		BinaryTree<String> questionTree = new BinaryTree<String>();
		
		if (args.length == 2)
		{
			
		}
		Scanner scanner = new Scanner(System.in); // for console input

		boolean done = false;
		while (!done)
		{
			System.out.print("Please enter a command (o, p, q, r): ");
			String input = scanner.nextLine();
			if (input.length() > 0) 
			{
                char choice = input.charAt(0); //strip off option character
                String remainder = "";         //will hold the remaining input

	                switch (choice) 
	                { 
	
	                case 'o':
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
	
	                case 'p':
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
	
	                case 'q':
	                	done = true;
	                    break;
	
	                case 'r':
	                	questionTree = restart(scanner, questionTree);
	                    break;
	
	                default: //ignore invalid commands
	                    System.out.println("Incorrect command.");
	                    break;
                    
                    } 
            } 
		}
	} 
	private static BinaryTree<String> restart(Scanner scanner, BinaryTree<String> tree) throws IllegalBinaryTreeOpException
	{
		String input = "";
		System.out.println("Please enter a question.");
    	input = scanner.nextLine();
    	tree = new BinaryTree<String>(input);
    	System.out.println("Please enter something that is true for that question.");
    	input = scanner.nextLine();
    	tree.addLeftChild(input);
    	System.out.println("Please enter something that is false for that question.");
    	input = scanner.nextLine();
    	tree.addRightChild(input);
    	return tree;
	}
	private static void play(Scanner scanner, BinaryTree<String> tree) throws IllegalBinaryTreeOpException
	{
		String input = "";
		boolean guessingInProgress = true;
		while (guessingInProgress)
		{
			if (tree.isLeaf())
			{
				System.out.println("I guess: " + tree.getCurrent() + ". Was I right?");
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
	private static void failedGuess(Scanner scanner, BinaryTree<String> tree) throws IllegalBinaryTreeOpException
	{
		String input = "";
		String oldAnswer = tree.getCurrent();
		System.out.println("Darn. Ok, tell me a question that is true for your answer, but false for my guess.");
		input = scanner.nextLine();
		tree.changeCurrent(input);
		System.out.println("Thanks. And what were you thinking of?");
		input = scanner.nextLine();
		tree.addLeftChild(input);
		tree.addRightChild(oldAnswer);
	}
}
