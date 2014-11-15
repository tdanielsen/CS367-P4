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
	                	if (questionTree.getCurrent() == null)
	                	{
	                		throw new IllegalBinaryTreeOpException("Empty Tree");
	                	}
	                	questionTree.print();
	                    break;
	
	                case 'p':
	                    // TODO *** implement this option ***
	                    break;
	
	                case 'q':
	                	done = true;
	                    break;
	
	                case 'r':
	                	System.out.println("Please enter a question.");
	                	input = scanner.nextLine();
	                	questionTree = new BinaryTree<String>(input);
	                	System.out.println("Please enter something that is true for that question.");
	                	input = scanner.nextLine();
	                	questionTree.addLeftChild(input);
	                	System.out.println("Please enter something that is false for that question.");
	                	input = scanner.nextLine();
	                	questionTree.addRightChild(input);
	                    break;
	
	                default: //ignore invalid commands
	                    System.out.println("Incorrect command.");
	                    break;
                    
                    } 
            } 
		}
	} 
}
