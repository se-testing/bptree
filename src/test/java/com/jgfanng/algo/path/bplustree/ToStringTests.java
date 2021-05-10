package com.jgfanng.algo.path.bplustree;

import com.jgfanng.algo.BPlusTree;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ToStringTests {
    private BPlusTree<Integer, Integer> bptree = null;

    @BeforeEach
    private void initTree() {
        bptree = new BPlusTree<>(3);
    }

    /*
    With just this case, we have achieved Condition/Decision coverage.
     */
    @Test
    public void testToString() {
        for (int i = 1; i < 8; i++) {
            bptree.insert(i, i * 10);
        }
        Assertions.assertEquals("{[5]}\n{[3], [7]}\n{[1, 2], [3, 4]}, {[5, 6], [7]}\n", bptree.toString());
    }
}
