package com.jgfanng.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BPlusTreeTrivialTests {
    @Test
    public void testConstructTree() {
        var bptree = new BPlusTree<Integer, Integer>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var illegalBPtree = new BPlusTree<Integer, Integer>(1);
        });
    }
}
