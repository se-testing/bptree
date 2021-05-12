package com.jgfanng.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BPlusTreeDeleteTests {
    @Test
    public void testDelete() {
        var bptree = new BPlusTree<Integer, Integer>();
        bptree.insert(1, 10);
        bptree.insert(2, 20);
        bptree.insert(3, 30);
        bptree.delete(3);
        Assertions.assertNull(bptree.search(3));
        Assertions.assertEquals((Integer) 20, bptree.search(2));
        Assertions.assertEquals((Integer) 10, bptree.search(1));
    }

    @Test
    public void testDeleteWhenLeafEmpty() {
        var bptree = new BPlusTree<Integer, Integer>(3);
        bptree.insert(1, 10);
        bptree.insert(2, 20);
        bptree.insert(3, 30);
        bptree.delete(3);
        Assertions.assertNull(bptree.search(3));
        Assertions.assertEquals((Integer) 20, bptree.search(2));
        Assertions.assertEquals((Integer) 10, bptree.search(1));
    }
}
