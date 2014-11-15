
public class BinaryTreeTester
{

	public static <E> void main(String[] args) throws IllegalBinaryTreeOpException
	{
		BinaryTree<String> tree = new BinaryTree<String>( "I");
		System.out.println(tree.getCurrent());
		tree.addRightChild("B");
		tree.addLeftChild("C");
//		tree.addLeftChild((E) "Booloon");
		tree.goLeft();
		tree.addRightChild( "D");
		tree.addLeftChild( "E");
		tree.start();
		tree.goRight();
		tree.addRightChild("F");
		tree.addLeftChild("R");
		tree.print();
		tree.start();
		tree.goRight();
//		tree.changeCurrent("Hat");
//		tree.print();

	}

}
