package algo;

import java.util.ArrayList;
import java.util.List;

public class BinTreeInt{
	Node root;

	int size;
	int duplicates;

	public BinTreeInt() {
		size = duplicates = 0;
		root = null;
	}

	public BinTreeInt(int val) {
		root = new Node(val);
	}

	public void add(int val) {
		if (root == null) {
			root = new Node(val);
			size++;
		} else {
			add(val, root);
		}

	}

	private void add(int val, Node act) {
		// duplicates
		if (val == act.val) {
			act.occurance++;
			duplicates++;
		} else if (val < act.val) {
			if (act.left == null) {
				act.left = new Node(val);
				size++;
			} else {
				add(val, act.left);
			}

		} else {
			if (act.right == null) {
				act.right = new Node(val);
				size++;
			} else {
				add(val, act.right);
			}

		}

	}

	public void remove(int val) {
		if (root == null) {
			return;
		} else {
			root = remove(val, root);
		}
	}

	private Node remove(int val, Node act) {
		if (act == null) {
			return null;
		}

		if (val < act.val) {
			act.left = remove(val, act.left);
		} else if (val < act.val) {
			act.right = remove(val, act.right);
		} else {
			size--;
			if (isLeaf(act)) {
				return null;
			} else if (act.right == null) {
				return act.left;
			} else if (act.left == null) {
				return act.right;
			} else {
				// remain order
				int min = findMinimum(act.right);
				act.right = deleteMinimum(act.right);

				act = new Node(min, act.left, act.right);
			}
		}

		return act;
	}

	private boolean isLeaf(Node node) {
		return node != null && node.left == null && node.right == null;
	}

	private int findMinimum(Node node) {
		if (node.left == null) {
			return node.val;
		}

		while (node.left != null) {
			node = node.left;
		}

		return node.val;
	}

	private Node deleteMinimum(Node node) {
		if (node.left == null) {
			return node.right;
		}

		node.left = deleteMinimum(node.left);

		return node;
	}

	public int count(int val) {
		if (root == null) {
			return 0;
		} else {
			return count(val, root);
		}
	}

	private int count(int val, Node act) {
		if (act == null) {
			return 0;
		}
		if ( act.val== val) {
			return act.occurance;
		} else if (act.val - val> 0) {
			return count(val, act.left);
		} else {
			return count(val, act.right);
		}
	}

	public List<Integer> toList() {
		// inorder traversel: left root right
		List<Integer> out = new ArrayList<>();
		if (root == null) {
			return out;
		}
		out.addAll(toList(root));
		return out;

	}

	private List<Integer> toList(Node act) {
		// inorder traversel: left root right
		List<Integer> lst = new ArrayList<>();
		if (act == null) {
			return new ArrayList<>();
		}

		// go left
		if (act.left != null) {
			lst.addAll(toList(act.left));
		}

		// go root
		for (int i = 0; i <= act.occurance; i++) {
			// System.out.println("act node: " + act.val + " occ: " + act.occurance);
			lst.add(act.val);
		}

		// go right
		if (act.right != null) {
			lst.addAll(toList(act.right));

		}

		return lst;
	}

	public double sum() {
		ArrayList<Integer> lst = (ArrayList<Integer>) toList();
		
		if (root == null || lst.size() == 0) {
			return 0.0;
		} else {
			return lst.stream().reduce(0,(total, act) -> total + act);
		}

	}


	public int size(boolean countDuplicates) {
		/**
		 * This returns the size of the tree with and without same values
		 * 
		 * @param countDuplicates indicates if duplicates should be counted. True if so.
		 * @return size of tree.
		 */
		return (countDuplicates ? size + duplicates : size);
	}
}
