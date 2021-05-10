package com.jgfanng.algo;

import com.jgfanng.algo.BPlusTree.RangePolicy;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BPlusTreeTest {
	@Test
	public void test() {
		BPlusTree<Integer, String> bpt = new BPlusTree<Integer, String>(4);
		bpt.insert(0, "a");
		bpt.insert(1, "b");
		bpt.insert(2, "c");
		bpt.insert(3, "d");
		bpt.insert(4, "e");
		bpt.insert(5, "f");
		bpt.insert(6, "g");
		bpt.insert(7, "h");
		bpt.insert(8, "i");
		bpt.insert(9, "j");
		bpt.delete(1);
		bpt.delete(3);
		bpt.delete(5);
		bpt.delete(7);
		bpt.delete(9);
		assertEquals(bpt.search(0), "a");
		assertEquals(bpt.search(1), null);
		assertEquals(bpt.search(2), "c");
		assertEquals(bpt.search(3), null);
		assertEquals(bpt.search(4), "e");
		assertEquals(bpt.search(5), null);
		assertEquals(bpt.search(6), "g");
		assertEquals(bpt.search(7), null);
		assertEquals(bpt.search(8), "i");
		assertEquals(bpt.search(9), null);
	}

	@Test
	public void testSearchRange() {
		BPlusTree<Integer, String> bpt = new BPlusTree<Integer, String>(4);
		bpt.insert(0, "a");
		bpt.insert(1, "b");
		bpt.insert(2, "c");
		bpt.insert(3, "d");
		bpt.insert(4, "e");
		bpt.insert(5, "f");
		bpt.insert(6, "g");
		bpt.insert(7, "h");
		bpt.insert(8, "i");
		bpt.insert(9, "j");
		assertArrayEquals(
				bpt.searchRange(3, RangePolicy.EXCLUSIVE, 7,
						RangePolicy.EXCLUSIVE).toArray(), new String[]{"e",
						"f", "g"});
		assertArrayEquals(
				bpt.searchRange(3, RangePolicy.INCLUSIVE, 7,
						RangePolicy.EXCLUSIVE).toArray(), new String[]{"d",
						"e", "f", "g"});
		assertArrayEquals(
				bpt.searchRange(3, RangePolicy.EXCLUSIVE, 7,
						RangePolicy.INCLUSIVE).toArray(), new String[]{"e",
						"f", "g", "h"});
		assertArrayEquals(
				bpt.searchRange(3, RangePolicy.INCLUSIVE, 7,
						RangePolicy.INCLUSIVE).toArray(), new String[]{"d",
						"e", "f", "g", "h"});
	}

	@Test
	public void testInternalNodeGetChild() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child0 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child1 =
				new BPlusTree<Integer, String>(4).new InternalNode();

		node.children.add(0, null);
		node.keys.add(0, 1);
		node.children.add(1, child0);
		assertEquals(child0, node.getChild(1));

		node.keys.add(1, 3);
		node.children.add(2, child1);
		assertEquals(child0, node.getChild(1));
		assertEquals(child1, node.getChild(3));
	}

	@Test
	public void testInternalNodeInsertChild1() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child0 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child1 =
				new BPlusTree<Integer, String>(4).new InternalNode();

		node.children.add(0, null);
		node.insertChild(1, child0);
		assertEquals(child0, node.getChild(1));

		node.insertChild(3, child1);
		assertEquals(child0, node.getChild(1));
		assertEquals(child1, node.getChild(3));
	}

	@Test
	public void testInternalNodeInsertChild2() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child0 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child1 =
				new BPlusTree<Integer, String>(4).new InternalNode();

		node.children.add(0, null);
		node.insertChild(1, child0);
		assertEquals(child0, node.getChild(1));

		node.insertChild(1, child1);
		assertEquals(child1, node.getChild(1));
	}

	@Test
	public void testInternalNodeDeleteChild1() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child0 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child1 =
				new BPlusTree<Integer, String>(4).new InternalNode();

		node.children.add(0, null);
		node.insertChild(1, child0);
		node.insertChild(3, child1);
		assertEquals(child0, node.getChild(1));
		assertEquals(child1, node.getChild(3));

		node.deleteChild(1);
		assertNull(node.getChild(1));
		assertEquals(child1, node.getChild(3));
	}

	@Test
	public void testInternalNodeDeleteChild2() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child0 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child1 =
				new BPlusTree<Integer, String>(4).new InternalNode();

		node.children.add(0, null);
		node.insertChild(1, child0);
		node.insertChild(3, child1);
		assertEquals(child0, node.getChild(1));
		assertEquals(child1, node.getChild(3));

		node.deleteChild(5);
		assertEquals(child0, node.getChild(1));
		assertEquals(child1, node.getChild(3));
	}

	@Test
	public void testGetChildLeftSibling1() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child0 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child1 =
				new BPlusTree<Integer, String>(4).new InternalNode();

		node.children.add(0, null);
		node.insertChild(1, child0);
		node.insertChild(3, child1);
		assertEquals(child0, node.getChildLeftSibling(3));
	}

	@Test
	public void testGetChildLeftSibling2() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child0 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child1 =
				new BPlusTree<Integer, String>(4).new InternalNode();

		node.children.add(0, null);
		node.insertChild(1, child0);
		node.insertChild(3, child1);
		assertNull(node.getChildLeftSibling(0));
	}

	@Test
	public void testGetChildRightSibling1() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child0 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child1 =
				new BPlusTree<Integer, String>(4).new InternalNode();

		node.children.add(0, null);
		node.insertChild(1, child0);
		node.insertChild(3, child1);
		assertEquals(child1, node.getChildRightSibling(1));
	}

	@Test
	public void testGetChildRightSibling2() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child0 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child1 =
				new BPlusTree<Integer, String>(4).new InternalNode();

		node.children.add(0, null);
		node.insertChild(1, child0);
		node.insertChild(3, child1);
		assertNull(node.getChildRightSibling(3));
	}

	@Test
	public void testIsOverflow() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child0 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child1 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child2 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child3 =
				new BPlusTree<Integer, String>(4).new InternalNode();

		node.children.add(0, null);
		node.insertChild(1, child0);
		node.insertChild(3, child1);
		node.insertChild(5, child2);
		assertFalse(node.isOverflow());

		node.insertChild(7, child3);
		assertTrue(node.isOverflow());
	}

	@Test
	public void testIsUnderflow() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode child0 =
				new BPlusTree<Integer, String>(4).new InternalNode();

		node.children.add(0, null);
		assertTrue(node.isUnderflow());

		node.insertChild(1, child0);
		assertFalse(node.isUnderflow());
	}

	@Test
	public void testInternalNodeGetValue() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.LeafNode leaf =
				new BPlusTree<Integer, String>(4).new LeafNode();

		node.children.add(0, null);
		leaf.insertValue(1, "abc");
		node.insertChild(1, leaf);
		assertEquals("abc", node.getValue(1));
	}

	@Test
	public void testInternalNodeInsertValue1() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.LeafNode child =
				new BPlusTree<Integer, String>(4).new LeafNode();

		node.children.add(0, null);
		node.insertChild(1, child);
		node.insertValue(1, "abc");
		assertEquals("abc", node.getValue(1));
	}

	@Test
	public void testInternalNodeInsertValue2() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.LeafNode child0 =
				new BPlusTree<Integer, String>(4).new LeafNode();
		BPlusTree<Integer, String>.LeafNode child1 =
				new BPlusTree<Integer, String>(4).new LeafNode();
		BPlusTree<Integer, String>.LeafNode child2 =
				new BPlusTree<Integer, String>(4).new LeafNode();
		BPlusTree<Integer, String>.LeafNode child3 =
				new BPlusTree<Integer, String>(4).new LeafNode();

		node.children.add(0, null);
		node.insertChild(1, child0);
		node.insertChild(2, child1);
		node.insertChild(3, child2);
		node.insertChild(4, child3);
		node.insertValue(1, "abc");
		node.insertValue(2, "bcd");
		node.insertValue(3, "cde");
		node.insertValue(4, "def");

		assertEquals("abc", node.getValue(1));
		assertEquals("bcd", node.getValue(2));
		assertEquals("cde", node.getValue(3));
		assertEquals("def", node.getValue(4));
	}

	@Test
	public void testInternalNodeDeleteValue1() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.LeafNode child0 =
				new BPlusTree<Integer, String>(4).new LeafNode();
		BPlusTree<Integer, String>.LeafNode child1 =
				new BPlusTree<Integer, String>(4).new LeafNode();

		child0.insertValue(1, "a");
		child0.insertValue(2, "b");
		child0.insertValue(3, "c");
		child1.insertValue(4, "d");
		child1.insertValue(5, "e");
		child1.insertValue(6, "f");

		node.children.add(0, child0);
		node.insertChild(4, child1);

		node.deleteValue(5);
		assertNull(node.getValue(5));
	}

	@Test
	public void testInternalNodeDeleteValue2() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.LeafNode child0 =
				new BPlusTree<Integer, String>(4).new LeafNode();
		BPlusTree<Integer, String>.LeafNode child1 =
				new BPlusTree<Integer, String>(4).new LeafNode();

		child0.insertValue(1, "a");
		child0.insertValue(2, "b");
		child0.insertValue(3, "c");
		child1.insertValue(5, "e");
		child1.insertValue(6, "f");

		node.children.add(0, child0);
		node.insertChild(4, child1);

		node.deleteValue(5);
		assertNull(node.getValue(5));
	}

	@Test
	public void testInternalNodeGetFirstLeafKey() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.LeafNode child0 =
				new BPlusTree<Integer, String>(4).new LeafNode();

		child0.keys.add(1);
		node.children.add(child0);

		assertEquals(Integer.valueOf(1), node.getFirstLeafKey());
	}

	@Test
	public void testInternalNodeGetRange() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.LeafNode child =
				new BPlusTree<Integer, String>(4).new LeafNode();
		node.children.add(child);
		node.insertChild(1, child);
		node.insertValue(1, "a");
		node.insertValue(2, "b");
		node.insertValue(3, "c");
		node.insertValue(4, "d");

		List<String> out = new ArrayList<>();
		out.add("b");
		out.add("c");
		assertEquals(out, node.getRange(2, RangePolicy.INCLUSIVE, 4, RangePolicy.EXCLUSIVE));
	}

	@Test
	public void testInternalNodeMerge() {
		BPlusTree<Integer, String>.InternalNode node0 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.InternalNode node1 =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.LeafNode child0 =
				new BPlusTree<Integer, String>(4).new LeafNode();
		BPlusTree<Integer, String>.LeafNode child1 =
				new BPlusTree<Integer, String>(4).new LeafNode();
		BPlusTree<Integer, String>.LeafNode child2 =
				new BPlusTree<Integer, String>(4).new LeafNode();

		child0.keys.add(1);
		node1.children.add(child0);
		node1.insertChild(3, child1);
		node1.insertChild(5, child2);
		node0.merge(node1);

		assertEquals(3, node0.keys.size());
		assertEquals(3, node0.children.size());
	}

	@Test
	public void testInternalNodeSplit() {
		BPlusTree<Integer, String>.InternalNode node =
				new BPlusTree<Integer, String>(4).new InternalNode();
		BPlusTree<Integer, String>.LeafNode child0 =
				new BPlusTree<Integer, String>(4).new LeafNode();
		BPlusTree<Integer, String>.LeafNode child1 =
				new BPlusTree<Integer, String>(4).new LeafNode();
		BPlusTree<Integer, String>.LeafNode child2 =
				new BPlusTree<Integer, String>(4).new LeafNode();
		BPlusTree<Integer, String>.LeafNode child3 =
				new BPlusTree<Integer, String>(4).new LeafNode();

		node.children.add(child0);
		node.insertChild(1, child1);
		node.insertChild(2, child2);
		node.insertChild(3, child3);
		BPlusTree<Integer, String>.InternalNode sibling =
				(BPlusTree<Integer, String>.InternalNode) node.split();

		assertEquals(1, sibling.keys.size());
		assertEquals(2, sibling.children.size());
		assertEquals(1, node.keys.size());
		assertEquals(2, node.children.size());
	}
}
