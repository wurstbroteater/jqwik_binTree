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
    @Property(shrinking = ShrinkingMode.OFF)
    boolean idempotency(@ForAll List<Integer> arr) {
        //[-1,0,Integer.MaxValue]
        BinTreeInt tree1 = initTree(arr);
        BinTreeInt tree2 = new BinTreeInt();
        for (Integer i : tree1.toList()) {
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
    boolean sumTest(@ForAll @Size(max = 10000) List<@IntRange(max = 10000000) Integer> arr) {
        BinTreeInt tree = new BinTreeInt();
        int sum = 0;
        for (Integer i : arr) {
            tree.add(i);
            sum += i;
        }

        return sum == tree.sum();

    }

    @Property(shrinking = ShrinkingMode.FULL)
    boolean sumTestAddDelete(@ForAll @Size(min= 1,max = 2) List<@IntRange(max = 10000000) Integer> arr) {
        //min size of 1 otherwise Random could generate inaccessible indices
        BinTreeInt tree = new BinTreeInt();
        int sum = 0;
        for (Integer i : arr) {
            tree.add(i);
            sum += i;
        }
        BinTreeInt cpyTree = (BinTreeInt) deepCopy(tree);
        ArrayList<Integer> cpyArr = (ArrayList) deepCopy(arr);

        //remove random
        for(int i= 0; i < tree.toList().size(); i++) {
            if(Math.random() >= 0.5) {
                sum -= arr.get(i);
                cpyTree.remove(arr.get(i));
                cpyArr.remove(arr.get(i));
            }
        }

        return sum == cpyTree.sum();

    }

    @Property(shrinking = ShrinkingMode.OFF, edgeCases = EdgeCasesMode.FIRST)
    boolean countTest(@ForAll @Size(min = 1) List<Integer> arr) {
        //min size of 1 otherwise Random could generate inaccessible indices
        BinTreeInt tree = initTree(arr);

        ArrayList<Integer> lst = (ArrayList<Integer>) tree.toList();

        Random rand = new Random();
        int element = lst.get(rand.nextInt(lst.size())); // nextInt(max - min) + min

        //+1 because if the node exists in the tree, it has an occurrence of 0
        //if the node occurs a second time the occurrence is 1
        return Collections.frequency(arr, element) == tree.count(element) + 1;


    }

    private Object deepCopy(Object obj) {
        if(obj instanceof  BinTreeInt) {
            BinTreeInt tree = (BinTreeInt) obj;
            BinTreeInt out = new BinTreeInt();
            for(Integer i : tree.toList()) {
                out.add(i);
            }
            return out;
        } else if(obj instanceof List) {
            List<Integer> lst = (List<Integer>) obj;
            return  new ArrayList<>(lst);
        } else {
            return null;
        }
    }
    private BinTreeInt initTree(List<Integer> arr) {
        BinTreeInt tree = new BinTreeInt();
        for (Integer i : arr) {
            tree.add(i);
        }

        return tree;
    }

}
