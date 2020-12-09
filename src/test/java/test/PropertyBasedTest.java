package test;


import algo.BinTreeInt;
import org.junit.*;

import net.jqwik.api.*;
import org.assertj.core.api.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PropertyBasedTest {

	@Report(Reporting.GENERATED)
	@Property(shrinking = ShrinkingMode.OFF)
	boolean idempotency(@ForAll List<Integer> arr) {
		BinTreeInt tree = new BinTreeInt();
		for(Integer i : arr) {
			tree.add(i);
		}
		Collections.sort(arr);
		return arr.equals(tree.toList());

	}

}
