package com.jgfanng.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BPlusTreeSearchTests {
    private BPlusTree<Integer, Integer> bptree = null;

    @BeforeEach
    private void initTree() {
        bptree = new BPlusTree<>();
        bptree.insert(1, 10);
        bptree.insert(2, 20);
        bptree.insert(3, 30);
    }

    @Test
    public void testSearchHit() {
        Assertions.assertEquals((Integer) 10, bptree.search(1));
        Assertions.assertEquals((Integer) 20, bptree.search(2));
        Assertions.assertEquals((Integer) 30, bptree.search(3));
    }

    @Test
    public void testSearchNotHit() {
        Assertions.assertNull(bptree.search(4));
    }
}
