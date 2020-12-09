package test;


import algo.BinTreeInt;
import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;

import java.util.Collections;
import java.util.List;

import net.jqwik.api.constraints.Size;
import org.junit.Assume;


public class PropertyBasedTest {

	@Report(Reporting.GENERATED)
	@Property(shrinking = ShrinkingMode.OFF)
	boolean idempotency(@ForAll List<Integer> arr) {
		//[-1,0,Integer.MaxValue]
		BinTreeInt tree = new BinTreeInt();
		for(Integer i : arr) {
			tree.add(i);
		}
		Collections.sort(arr);
		return arr.equals(tree.toList());

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

	@Property(shrinking = ShrinkingMode.OFF, edgeCases = EdgeCasesMode.FIRST)
	boolean countTest(@ForAll List<Integer>arr) {
		BinTreeInt tree = new BinTreeInt();
		for(Integer i : arr) {
			tree.add(i);
		}

		return  arr.size() == tree.toList().size();

	}

}
