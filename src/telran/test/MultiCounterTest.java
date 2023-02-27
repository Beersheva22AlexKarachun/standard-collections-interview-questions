package telran.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.MultiCountersMap;

class MultiCounterTest {
	MultiCountersMap map;
	String[] items = { "a", "a", "a", "b", "b", "c" };
	String[] empty = new String[0];

	@BeforeEach
	void setUp() throws Exception {
		map = new MultiCountersMap();
		for (String str : items) {
			map.addItem(str);
		}
	}

	@Test
	void addTest() {
		assertEquals(4, map.addItem("a"));
		assertEquals(3, map.addItem("b"));
		assertEquals(2, map.addItem("c"));
		assertEquals(1, map.addItem("d"));
	}

	@Test
	void getValueTest() {
		assertEquals(3, map.getValue("a"));
		assertEquals(2, map.getValue("b"));
		assertEquals(1, map.getValue("c"));
		assertNull(map.getValue("d"));
	}

	@Test
	void removeTest() {
		assertTrue(map.remove("a"));
		assertNull(map.getValue("a"));
		assertFalse(map.remove("a"));
	}

	@Test
	void getMaxItems() {
		String[] expected1 = { "a" };
		String[] expected2 = { "a", "b" };
		String[] expected3 = { "c" };
		assertArrayEquals(expected1, map.getMaxItems().toArray(empty));
		map.addItem("b");
		assertArrayEquals(expected2, map.getMaxItems().toArray(empty));
		map.remove("a");
		map.remove("b");
		assertArrayEquals(expected3, map.getMaxItems().toArray(empty));
	}
}
