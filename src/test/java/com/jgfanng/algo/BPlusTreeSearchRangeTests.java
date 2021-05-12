package com.jgfanng.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class BPlusTreeSearchRangeTests {
    private BPlusTree<Integer, Integer> bptree = null;

    @BeforeEach
    private void initTree() {
        bptree = new BPlusTree<Integer, Integer>();
        for (int i = 1; i < 11; i++) {
            bptree.insert(i, 10 * i);
        }
    }

    @Test
    public void testSearchRangeEE() {
        Assertions.assertEquals(Arrays.asList(30, 40),
                bptree.searchRange(2, BPlusTree.RangePolicy.EXCLUSIVE, 5, BPlusTree.RangePolicy.EXCLUSIVE));
        Assertions.assertEquals(Arrays.asList(80, 90, 100),
                bptree.searchRange(7, BPlusTree.RangePolicy.EXCLUSIVE, 20, BPlusTree.RangePolicy.EXCLUSIVE));
        Assertions.assertEquals(Arrays.asList(),
                bptree.searchRange(11, BPlusTree.RangePolicy.EXCLUSIVE, 20, BPlusTree.RangePolicy.EXCLUSIVE));
        Assertions.assertEquals(Arrays.asList(),
                bptree.searchRange(5, BPlusTree.RangePolicy.EXCLUSIVE, 2, BPlusTree.RangePolicy.EXCLUSIVE));
    }

    @Test
    public void testSearchRangeII() {
        Assertions.assertEquals(Arrays.asList(20, 30, 40, 50),
                bptree.searchRange(2, BPlusTree.RangePolicy.INCLUSIVE, 5, BPlusTree.RangePolicy.INCLUSIVE));
        Assertions.assertEquals(Arrays.asList(70, 80, 90, 100),
                bptree.searchRange(7, BPlusTree.RangePolicy.INCLUSIVE, 20, BPlusTree.RangePolicy.INCLUSIVE));
        Assertions.assertEquals(Arrays.asList(),
                bptree.searchRange(11, BPlusTree.RangePolicy.INCLUSIVE, 20, BPlusTree.RangePolicy.INCLUSIVE));
        Assertions.assertEquals(Arrays.asList(),
                bptree.searchRange(5, BPlusTree.RangePolicy.INCLUSIVE, 2, BPlusTree.RangePolicy.INCLUSIVE));
    }

    @Test
    public void testSearchRangeEI() {
        Assertions.assertEquals(Arrays.asList(30, 40, 50),
                bptree.searchRange(2, BPlusTree.RangePolicy.EXCLUSIVE, 5, BPlusTree.RangePolicy.INCLUSIVE));
        Assertions.assertEquals(Arrays.asList(80, 90, 100),
                bptree.searchRange(7, BPlusTree.RangePolicy.EXCLUSIVE, 20, BPlusTree.RangePolicy.INCLUSIVE));
        Assertions.assertEquals(Arrays.asList(),
                bptree.searchRange(11, BPlusTree.RangePolicy.EXCLUSIVE, 20, BPlusTree.RangePolicy.INCLUSIVE));
        Assertions.assertEquals(Arrays.asList(),
                bptree.searchRange(5, BPlusTree.RangePolicy.EXCLUSIVE, 2, BPlusTree.RangePolicy.INCLUSIVE));
    }

    @Test
    public void testSearchRangeIE() {
        Assertions.assertEquals(Arrays.asList(20, 30, 40),
                bptree.searchRange(2, BPlusTree.RangePolicy.INCLUSIVE, 5, BPlusTree.RangePolicy.EXCLUSIVE));
        Assertions.assertEquals(Arrays.asList(70, 80, 90, 100),
                bptree.searchRange(7, BPlusTree.RangePolicy.INCLUSIVE, 20, BPlusTree.RangePolicy.EXCLUSIVE));
        Assertions.assertEquals(Arrays.asList(),
                bptree.searchRange(11, BPlusTree.RangePolicy.INCLUSIVE, 20, BPlusTree.RangePolicy.EXCLUSIVE));
        Assertions.assertEquals(Arrays.asList(),
                bptree.searchRange(5, BPlusTree.RangePolicy.INCLUSIVE, 2, BPlusTree.RangePolicy.EXCLUSIVE));
    }
}
