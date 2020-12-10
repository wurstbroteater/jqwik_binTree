package test;


import algo.BinTreeInt;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.jqwik.api.constraints.Size;



public class PropertyBasedTest {

	@Report(Reporting.GENERATED)
	@Property( shrinking = ShrinkingMode.OFF)
	boolean idempotency(@ForAll List<Integer> arr) {
		//[-1,0,Integer.MaxValue]
		BinTreeInt tree1 = initTree(arr);
		BinTreeInt tree2 = new BinTreeInt();
		for(Integer i : tree1.toList()) {
			tree2.add(i);
		}
		return tree2.toList().equals(tree1.toList());

	}

	@Property
	boolean isSorted(@ForAll List<Integer> arr) {
		BinTreeInt tree = initTree(arr);
		Collections.sort(arr);
		return tree.toList().equals(arr);
	}


	@Property(shrinking = ShrinkingMode.OFF)
	boolean sumTest(@ForAll @Size(max= 10000)List<@IntRange(max = 10000000) Integer> arr) {
		BinTreeInt tree = new BinTreeInt();
		int sum = 0;
		for(Integer i : arr) {
			tree.add(i);
			sum += i;
		}

		return sum ==  tree.sum();

	}

	@Property(shrinking = ShrinkingMode.OFF)
	boolean sumTestAddDelete(@ForAll @Size(max= 10000)List<@IntRange(max = 10000000) Integer> arr) {
		BinTreeInt tree = new BinTreeInt();
		int sum = 0;
		for(Integer i : arr) {
			tree.add(i);
			sum += i;
		}

		ArrayList<Integer> lst = (ArrayList<Integer>) tree.toList();
		Random rand = new Random();
		int element =  lst.get(rand.nextInt(lst.size()));




		return Collections.frequency(arr,element) == tree.count(element);

	}

	@Property(shrinking = ShrinkingMode.OFF, edgeCases = EdgeCasesMode.FIRST)
	boolean countTest(@ForAll List<Integer>arr) {
		BinTreeInt tree = initTree(arr);

		ArrayList<Integer> lst = (ArrayList<Integer>) tree.toList();
		if(arr.size() >= 1) {
			Random rand = new Random();
			int element =  lst.get(rand.nextInt(lst.size())); // nextInt(max - min) + min

			//+1 because if the node exists in the tree, it has an occurrence of 0
			//if the node occurs a second time the occurrence is 1
			return Collections.frequency(arr,element) == tree.count(element) +1 ;
		}
		else {
			return true;
		}


	}

	private BinTreeInt initTree(List<Integer> arr) {
		BinTreeInt tree = new BinTreeInt();
		for(Integer i : arr) {
			tree.add(i);
		}

		return tree;
	}

}
