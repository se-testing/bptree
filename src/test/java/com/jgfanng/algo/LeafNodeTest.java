package com.jgfanng.algo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static com.jgfanng.algo.BPlusTree.RangePolicy.EXCLUSIVE;
import static com.jgfanng.algo.BPlusTree.RangePolicy.INCLUSIVE;

public class LeafNodeTest {
    BPlusTree<Integer, Integer> tree = new BPlusTree<>(3);
    BPlusTree<Integer, Integer>.LeafNode node;
    /*
     * 测试LeafNode
     * 测试LeafNode正常构造
     */
    @Test
    public void testLeafNode() {
        node = tree.new LeafNode();
        Assertions.assertNotNull(node.keys);
        Assertions.assertNotNull(node.values);
    }

    /*
     * 测试getValue
     * 测试获取不存在的value
     */
    @Test
    public void testGetValueNotFound(){
        node = tree.new LeafNode();
        Assertions.assertNull(node.getValue(0));
    }

    /*
     * 测试getValue
     * 测试获取存在的value
     */
    @Test
    public void testGetValueFound(){
        node = tree.new LeafNode();
        node.insertValue(0, 0);
        Assertions.assertEquals(Integer.valueOf(0), node.getValue(0));
    }

    /*
     * 测试deleteValue
     * 测试节点不存在时不出错
     */
    @Test
    public void testDeleteValueNotFound(){
        node = tree.new LeafNode();
        node.deleteValue(0);
    }

    /*
     * 测试deleteValue
     * 测试节点存在时被正确删除
     */
    @Test
    public void testDeleteValueFound(){
        node = tree.new LeafNode();
        node.insertValue(0,0);
        node.deleteValue(0);
        Assertions.assertNull(node.getValue(0));
    }

    /*
     * 测试insertValue
     * 测试节点不存在时被正确插入
     */
    @Test
    public void testInsertValueNotExistNotOverflow(){
        node = tree.new LeafNode();
        Assertions.assertNull(node.getValue(0));
        node.insertValue(0, 0);
        Assertions.assertEquals(Integer.valueOf(0), node.getValue(0));
    }

    /*
     * 测试insertValue
     * 测试节点存在时被正确覆盖
     */
    @Test
    public void testInsertValueExistNotOverflow(){
        node = tree.new LeafNode();
        node.insertValue(0, 0);
        Assertions.assertEquals(Integer.valueOf(0), node.getValue(0));
        node.insertValue(0, 1);
        Assertions.assertEquals(Integer.valueOf(1), node.getValue(0));
    }

    /*
     * 测试insertValue
     * 测试根节点overflow且节点不存在时被正确插入
     */
    @Test
    public void testInsertValueNotExistOverflow(){
        node = tree.new LeafNode();
        for (int i = 0; i <= 3; i++) {
            tree.insert(i, 0);
        }
        node.insertValue(0, 0);
        Assertions.assertEquals(Integer.valueOf(0), node.getValue(0));
    }

    /*
     * 测试insertValue
     * 测试根节点overflow且节点存在时被正确覆盖
     */
    @Test
    public void testInsertValueExistOverflow(){
        node = tree.new LeafNode();
        for (int i = 0; i <= 3; i++) {
            tree.insert(i, 0);
        }
        node.insertValue(0, 0);
        Assertions.assertEquals(Integer.valueOf(0), node.getValue(0));
        node.insertValue(0, 1);
        Assertions.assertEquals(Integer.valueOf(1), node.getValue(0));
    }

    /*
     * 测试getFirstLeafKey
     * 测试正确获得第一个叶子的Key
     */
    @Test
    public void testGetFirstLeafKey(){
        node = tree.new LeafNode();
        node.insertValue(0, 0);
        node.insertValue(1, 0);
        Assertions.assertEquals(Integer.valueOf(0), node.getFirstLeafKey());
    }

    /*
     * 测试getRange
     * 测试树为空时正确获得值
     */
    @Test
    public void testGetRangeEmpty(){
        node = tree.new LeafNode();
        List<Integer> expected = new ArrayList<>();
        Assertions.assertEquals(expected, node.getRange(0, EXCLUSIVE, 1, EXCLUSIVE));
    }

    /*
     * 测试getRange
     * 测试树不为空且范围内存在值时正确获得值
     */
    @Test
    public void testGetRangeNotEmptyAndExist(){
        node = tree.new LeafNode();
        node.insertValue(0, 0);
        List<Integer> expected = new ArrayList<>();
        expected.add(0);
        Assertions.assertEquals(expected, node.getRange(0, INCLUSIVE, 1, INCLUSIVE));
    }

    /*
     * 测试getRange
     * 测试树不为空且范围内不存在值时正确获得值
     */
    @Test
    public void testGetRangeNotEmptyAndNotExist(){
        node = tree.new LeafNode();
        node.insertValue(0, 0);
        node.insertValue(2, 0);
        List<Integer> expected = new ArrayList<>();
        Assertions.assertEquals(expected, node.getRange(1, EXCLUSIVE, 2, EXCLUSIVE));
    }

    /*
     * 测试merge
     * 测试正确merge
     */
    @Test
    public void testMerge(){
        node = tree.new LeafNode();
        BPlusTree<Integer, Integer>.LeafNode toMerge = tree.new LeafNode();
        toMerge.insertValue(0, 0);
        node.merge(toMerge);
        Assertions.assertEquals(Integer.valueOf(0), node.getFirstLeafKey());
    }

    /*
     * 测试split
     * 测试正确split
     */
    @Test
    public void testSplit(){
        node = tree.new LeafNode();
        Assertions.assertNotNull(node.split());
    }

    /*
     * 测试isOverflow
     * 测试正确判断overflow
     */
    @Test
    public void testIsOverflow(){
        node = tree.new LeafNode();
        Assertions.assertEquals(Boolean.FALSE, node.isOverflow());
    }

    /*
     * 测试isUnderflow
     * 测试正确判断underflow
     */
    @Test
    public void testIsUnderflow(){
        node = tree.new LeafNode();
        Assertions.assertEquals(Boolean.TRUE, node.isUnderflow());
    }
}
