package algo;

import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinTreeInt tree = new BinTreeInt();
		tree.add(8);
		ArrayList<Integer> lst = (ArrayList<Integer>) tree.toList();
		System.out.println("summe " + tree.sum());
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
