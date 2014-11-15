
public class BinaryTree<E>
{
	private BinaryTreenode<E> root;
	private BinaryTreenode<E> ref;
	public BinaryTree()
	{
		root = new BinaryTreenode<E>();
		ref = root;
	}
	public BinaryTree(E data)
	{
		root = new BinaryTreenode<E>(data);
		ref = root;
	}
	public void start()
	{
		ref = root;
	}
	public E getCurrent() throws IllegalBinaryTreeOpException
	{
		if (ref.getData() == null)
		{
			throw new IllegalBinaryTreeOpException("Null node");
		}
		return ref.getData();
	}
	public void goLeft() throws IllegalBinaryTreeOpException
	{
		if (ref.getLeft() == null)
		{
			throw new IllegalBinaryTreeOpException("No left child");
		}
		ref = ref.getLeft();
	}
	public void goRight() throws IllegalBinaryTreeOpException
	{
		if (ref.getRight() == null)
		{
			throw new IllegalBinaryTreeOpException("No right child");
		}
		ref = ref.getRight();
	}
	public boolean isLeaf()
	{
		if (ref.getLeft() == null)
		{
			return true;
		}
		return false;
	}
	public void changeCurrent(E data)
	{
		ref.setData(data);
	}
	public void addRightChild(E data) throws IllegalBinaryTreeOpException
	{
		if (ref.getRight() != null)
		{
			throw new IllegalBinaryTreeOpException("Right child already exists");
		}
		ref.setRight(data);
	}
	public void addLeftChild(E data) throws IllegalBinaryTreeOpException
	{
		if (ref.getLeft() != null)
		{
			throw new IllegalBinaryTreeOpException("Left child already exists");
		}
		ref.setLeft(data);
	}
	public void print()
	{
		start();
		print(ref, "");
	}
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
