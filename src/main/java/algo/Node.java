package algo;

public class Node {
	int val;
	Node left;
	Node right;
	int occurance;
	
	public Node(int val, Node left, Node right) {
		this.val = val;
		this.left = left;
		this.right = right;
	}
	
	public Node(int val) {
		this.val = val;
		this.left = null;
		this.right = null;
		this.occurance = 0;
	}
	

	

}
