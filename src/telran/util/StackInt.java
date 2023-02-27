package telran.util;

import java.util.LinkedList;

public class StackInt {
	private LinkedList<Integer> list = new LinkedList<>();
	private LinkedList<Integer> maxList = new LinkedList<>();

	public void push(int num) {
		list.add(num);
		Integer max = maxList.peekLast();
		maxList.add((max != null && max > num) ? max : num);
	}

	public int pop() {
		maxList.removeLast();
		return list.removeLast();
	}

	public boolean isEmpty() {
		return list.isEmpty();
	}

	public int getMax() {
		// returns maximal value of the stack or throws NoSuchElementException
		// if the stack is empty
		return maxList.getLast();
	}
}
