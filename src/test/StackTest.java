package test;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import ku.util.Stack;
import ku.util.StackFactory;

/**
 * JUnit 4 tests for stack interface
 * 
 * @author Vittunyuta Maeprasart
 *
 */
public class StackTest {
	private Stack<String> stack;

	@Before
	public void setUp() {
		stack = makeStack(2);
	}

	@After
	public void tearDown() {
		stack = null;
	}

	/**
	 * Test that capacity is less than zero should throw <code>IllegalArgumentException</code>.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testCreateMinusCapacity() {
		stack = makeStack(-1);
		assertNull(stack.peek());
		stack.push("minus");
		fail("initial capacity is less than 0, stack will not create");
	}

	/**
	 * Test stack should empty when creating stack with zero capacity.
	 */
	@Test
	public void testCreateZeroCapacity() {
		stack = makeStack(0);
		assertEquals(0, stack.capacity());
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test creating new stack with capacity is 2.
	 */
	@Test
	public void newStackIsEmpty() {
		assertTrue(stack.isEmpty());
		assertFalse(stack.isFull());
		assertEquals(2, stack.capacity());
		assertEquals(0, stack.size());
	}

	/**
	 * test it should throw <code>EmptyStackException</code> when popping the empty stack.
	 */
	@Test(expected = java.util.EmptyStackException.class)
	public void testPopEmptyStack() {
		assertTrue(stack.isEmpty());
		stack.pop();
	}

	/**
	 * Test <code>isFull</code> method will return true when stack if full.
	 */
	@Test
	public void testFull() {
		assertFalse(stack.isFull());
		stack.push("a");
		assertFalse(stack.isFull());
		stack.push("b");
		assertTrue(stack.isFull());
	}
	
	/**
	 * Test <code>isEmpty</code> method will return true when no item in the stack.
	 */
	@Test
	public void testEmpty() {
		assertTrue(stack.isEmpty());
		stack.push("a");
		assertFalse(stack.isEmpty());
		stack.push("b");
		assertFalse(stack.isEmpty());
	}
	
	/**
	 * Test <code>isFull</code> method will return numbers of items in the stack.
	 */
	@Test
	public void testSize() {
		assertEquals(0, stack.size());
		stack.push("a");
		assertEquals(1, stack.size());
		stack.push("b");
		assertEquals(2, stack.size());
	}

	/**
	 * Test <code>pop</code> method will return on top item and size should decrease when popping the stack.
	 */
	@Test
	public void testPop() {
		stack.push("a");
		stack.push("b");
		assertTrue(stack.isFull());
		String b = "b";
		assertSame(stack.pop(), b);
		assertSame(stack.pop(), b);
		assertFalse(stack.isFull());
		assertTrue(stack.isEmpty());
	}

	/**
	 * Test <code>peek</code> method will return on top item and size should remain the same when peeking the stack.
	 */
	@Test
	public void testPeek() {
		assertNull(stack.peek());
		stack.push("a");
		stack.push("b");
		assertTrue(stack.isFull());
		String b = "b";
		assertSame(stack.peek(), b);
		assertSame(stack.peek(), b);
		assertFalse(stack.isEmpty());
		assertTrue(stack.isFull());
	}

	/**
	 * Test pushing null item should throw <code>IllegalArgumentException</code>.
	 */
	@Test(expected = IllegalArgumentException.class)
	public void testPushNull() {
		stack.push(null);
	}

	/**
	 * Test pushing items more than stack's capacity should throw <code>IllegalStateException</code>.
	 */
	@Test(expected = IllegalStateException.class)
	public void testOverflow() {
		stack.push("1");
		stack.push("2");
		stack.push("3");
	}

	/**
	 * method for creating new stack. 
	 * @param capacity is the capacity of stack.
	 * @return stack that contain the items with String type.
	 */
	private Stack<String> makeStack(int capacity) {
		// test StackFactory
		StackFactory.setStackType(1);
		return StackFactory.makeStack(capacity);
	}
}
