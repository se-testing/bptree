package com.jgfanng.algo.path.bplustree;

import com.jgfanng.algo.BPlusTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class TrivialTests {
    @Test
    public void testConstructTree() {
        var bptree = new BPlusTree<Integer, Integer>();
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            var illegalBPtree = new BPlusTree<Integer, Integer>(1);
        });
    }
}
