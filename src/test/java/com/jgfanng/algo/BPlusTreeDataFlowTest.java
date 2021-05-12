package com.jgfanng.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class BPlusTreeDataFlowTest {
    BPlusTree<Integer, String> instance;

    @Test
    void testBPlusTree() {
        instance = new BPlusTree<>();
        instance = new BPlusTree<>(16);
    }

    @Test
    public void testInsert() {
        instance = new BPlusTree<>(4);

        instance.insert(1, "_1");
        instance.insert(2, "_2");
        instance.insert(3, "_3");
        instance.insert(4, "_4");
        instance.insert(5, "_5");
        instance.insert(6, "_6");
        instance.insert(7, "_7");
        instance.insert(8, "_8");
        instance.insert(9, "_9");
        instance.insert(1, "__1");
        instance.insert(100, "special");

        final Integer _size = 10;
        final Integer size = instance.searchRange(1, BPlusTree.RangePolicy.INCLUSIVE,
                100, BPlusTree.RangePolicy.INCLUSIVE).size();
        Assertions.assertEquals(_size, size);
    }

    @Test
    public void testSearch() {
        instance = new BPlusTree<>(4);

        instance.insert(1, "_1");
        instance.insert(2, "_2");
        instance.insert(3, "_3");

        final String expect0 = "_1";
        final String result0 = instance.search(1);
        Assertions.assertEquals(expect0, result0);

        instance.insert(4, "_4");

        final String expect1 = "_4";
        final String result1 = instance.search(4);
        Assertions.assertEquals(expect1, result1);

        instance.insert(5, "_5");
        instance.insert(6, "_6");
        instance.insert(7, "_7");
        instance.insert(8, "_8");
        instance.insert(9, "_9");
        instance.insert(100, "special");

        final String expect2 = "special";
        final String result2 = instance.search(100);
        Assertions.assertEquals(expect2, result2);
    }

    @Test
    public void testSearchRange() {
        instance = new BPlusTree<>(4);

        final Object [] expect0 = new String [] {};
        final Object [] result0 = instance.searchRange(0, BPlusTree.RangePolicy.EXCLUSIVE,
                0, BPlusTree.RangePolicy.EXCLUSIVE).toArray();
        Assertions.assertArrayEquals(expect0, result0);

        instance.insert(1, "_1");
        instance.insert(1, "__1");

        final Object [] expect1 = new String [] {};
        final Object [] expect2 = new String [] {"__1"};
        final Object [] result1 = instance.searchRange(1, BPlusTree.RangePolicy.INCLUSIVE,
                1, BPlusTree.RangePolicy.EXCLUSIVE).toArray();
        final Object [] result2 = instance.searchRange(1, BPlusTree.RangePolicy.INCLUSIVE,
                1, BPlusTree.RangePolicy.INCLUSIVE).toArray();
        Assertions.assertArrayEquals(expect1, result1);
        Assertions.assertArrayEquals(expect2, result2);

        instance.insert(2, "_2");
        instance.insert(3, "_3");
        final Object [] expect3 = new String [] {"_2", "_3"};
        final Object [] result3 = instance.searchRange(2, BPlusTree.RangePolicy.INCLUSIVE,
                3, BPlusTree.RangePolicy.INCLUSIVE).toArray();
        Assertions.assertArrayEquals(expect3, result3);

        instance.insert(4, "_4");
        final Object [] expect4 = new String [] {"_2", "_3", "_4"};
        final Object [] result4 = instance.searchRange(2, BPlusTree.RangePolicy.INCLUSIVE,
                4, BPlusTree.RangePolicy.INCLUSIVE).toArray();
        Assertions.assertArrayEquals(expect4, result4);

        instance.insert(5, "_5");
        instance.insert(6, "_6");
        instance.insert(7, "_7");
        instance.insert(8, "_8");
        instance.insert(9, "_9");
        instance.insert(100, "special");
        final Object [] expect5 = new String [] {"_2", "_3", "_4", "_5", "_6"};
        final Object [] result5 = instance.searchRange(1, BPlusTree.RangePolicy.EXCLUSIVE,
                7, BPlusTree.RangePolicy.EXCLUSIVE).toArray();
        Assertions.assertArrayEquals(expect5, result5);
    }

    @Test
    public void testDelete() {
        instance = new BPlusTree<>(4);

        for (int i = 0; i < 27; ++i) {
            instance.insert(i, String.valueOf('a' + i));
        }

        final Integer expect0 = 27;
        final Integer result0 = instance.searchRange(0, BPlusTree.RangePolicy.INCLUSIVE,
                26, BPlusTree.RangePolicy.INCLUSIVE).size();
        Assertions.assertEquals(expect0, result0);

        for (int i = 0; i < 20; ++i) {
            instance.delete(i);
        }
        final Integer expect1 = 7;
        final Integer result1 = instance.searchRange(0, BPlusTree.RangePolicy.INCLUSIVE,
                26, BPlusTree.RangePolicy.INCLUSIVE).size();
        Assertions.assertEquals(expect1, result1);

        for (int i = 20; i < 27; ++i) {
            instance.delete(i);
        }
        final Integer expect2 = 0;
        final Integer result2 = instance.searchRange(0, BPlusTree.RangePolicy.INCLUSIVE,
                26, BPlusTree.RangePolicy.INCLUSIVE).size();
        Assertions.assertEquals(expect2, result2);
    }

    @Test
    public void testToString() {
        instance = new BPlusTree<>(4);

        final String expect0 = "{[]}\n";
        final String result0 = instance.toString();
        Assertions.assertEquals(expect0, result0);

        instance.insert(1, "_1");
        instance.insert(2, "_2");
        instance.insert(3, "_3");
        instance.insert(4, "_4");
        instance.insert(5, "_5");
        instance.insert(6, "_6");
        instance.insert(7, "_7");
        instance.insert(8, "_8");
        instance.insert(9, "_9");

        final String expect1 = "{[3, 5, 7]}\n{[1, 2], [3, 4], [5, 6], [7, 8, 9]}\n";
        final String result1 = instance.toString();
        Assertions.assertEquals(expect1, result1);

        instance.insert(100, "special");

        final String expect2 = "{[7]}\n{[3, 5], [9]}\n{[1, 2], [3, 4], [5, 6]}, {[7, 8], [9, 100]}\n";
        final String result2 = instance.toString();
        Assertions.assertEquals(expect2, result2);
    }
}
