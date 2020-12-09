package algo;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinTreeInt tree = new BinTreeInt();
		tree.add(8);
		tree.add(3);
		tree.add(10);
		tree.add(1);
		tree.add(6);
		tree.add(14);
		tree.add(4);
		tree.add(7);
		tree.add(13);
		tree.add(8);
		ArrayList<Integer> lst = (ArrayList<Integer>) tree.toList();
		System.out.println("occ of 8: " + tree.root.occurance);
		System.out.println("tree size with doubl. " + tree.size(true) + " tree size without doubl. " + tree.size(false)
				+ " list size " + lst.size());
		for (Integer i : lst) {
			System.out.print(i + " ");
		}
		System.out.println();
		tree.remove(8);
		lst = (ArrayList<Integer>) tree.toList();
		System.out.println("tree size with doubl. " + tree.size(true) + " tree size without doubl. " + tree.size(false)
				+ " list size " + lst.size());
		for (Integer i : lst) {
			System.out.print(i + " ");
		}
		System.out.println();
		tree.remove(8);
		lst = (ArrayList<Integer>) tree.toList();
		System.out.println("tree size with doubl. " + tree.size(true) + " tree size without doubl. " + tree.size(false)
				+ " list size " + lst.size());
		for (Integer i : lst) {
			System.out.print(i + " ");
		}
		System.out.println();
		System.out.println("summe " + tree.sum());

	}

}
