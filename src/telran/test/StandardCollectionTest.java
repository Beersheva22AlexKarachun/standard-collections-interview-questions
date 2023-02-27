package telran.test;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;

class StandardCollectionTest {

	@BeforeEach
	void setUp() throws Exception {
	}

	@Test
	@Disabled
	void SubListtest() {
		List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 70, -20));
		list.add(5);
		List<Integer> listSub = list.subList(6, 9);

		System.out.println(listSub);
		listSub.add(1, -2);
		listSub.sort(Integer::compare);
		listSub.clear();
		System.out.println(list);

	}

	@Test
	void displayOccurrencesCount() {
		String[] strings = { "lmn", "abc", "abc", "lmn", "a", "lmn" };
		Arrays.stream(strings).collect(Collectors.groupingBy(s -> s, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%s: %d\n", e.getKey(), e.getValue()));
	}

	@Test
	void displayDigitStatistics() {
		// Generate 1000000 random numbers [1-Integer.MAX_VALUE)
		// Display digits and counts of their occurrences in descending order of the
		// counts
		// consider using flatMap for getting many from one
		new Random().ints(1_000_000, 1, Integer.MAX_VALUE).flatMap(n -> Integer.toString(n).chars()).boxed()
				.collect(Collectors.groupingBy(digit -> digit, Collectors.counting())).entrySet().stream()
				.sorted((e1, e2) -> Long.compare(e2.getValue(), e1.getValue()))
				.forEach(e -> System.out.printf("%d: %d\n", e.getKey() - '0', e.getValue()));
		;
	}

	@Test
	void maxNumberWithNegativeImageTest() {
		int[] ar = { 10000000, 3, -2, -200, 200, -3, 2 };
		int[] ar1 = { 1000000, -1000000000, 3, -4 };
		int[] ar2 = { 0, 0 };
		assertEquals(200, maxNumberWithNegativeImage(ar));
		assertEquals(-1, maxNumberWithNegativeImage(ar1));
		assertEquals(0, maxNumberWithNegativeImage(ar2));
	}

	int maxNumberWithNegativeImage(int[] array) {
		HashSet<Integer> helper = new HashSet<>();
		int max = -1;
		for (int number : array) {
			helper.add(number);
			if (Math.abs(number) > max && helper.contains(-number)) {
				max = Math.abs(number);
			}
		}
		return max;
	}

	@Test
	void treeIteratingTest() {
		Integer[] array = { 1, 11, 111, 32, 9, 1234, 99, 992 };
		Integer[] array1 = { 1111, 23, 33, 44, 18, 29, 93 };
		assertArrayEquals(array, createAndIterateTreeInOrder(array));
		assertArrayEquals(array1, createAndIterateTreeInOrder(array1));
	}

	private Integer[] createAndIterateTreeInOrder(Integer[] array) {
		TreeSet<Integer> tree = new TreeSet<>((x, y) -> {
			ToIntFunction<Integer> sumOfDigits = num -> Arrays.stream(Integer.toString(num).split(""))
					.mapToInt(Integer::valueOf).sum();

			return Integer.compare(sumOfDigits.applyAsInt(x), sumOfDigits.applyAsInt(y));
		});

		tree.addAll(Arrays.asList(array));
		return tree.toArray(new Integer[0]);
	}
}
