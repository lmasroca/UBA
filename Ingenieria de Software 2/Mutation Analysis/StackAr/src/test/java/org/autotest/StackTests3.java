package org.autotest;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

public class StackTests3 extends MutationAnalysisRunner {
    @Override
    protected boolean useVerboseMode() {
        return false;
    }

    public void testSizeIncreasesByOne() throws Exception {
        Stack stack = createStack();
        assertEquals(0, stack.size());
        stack.push(42);
        assertEquals(1, stack.size());
    }

    public void testDefaultConstructor() throws Exception {
        Stack stack = createStack();
        assertTrue(stack.isEmpty());
    }

    public void testConstructorWithSpecifiedCapacity() throws Exception {
        Stack stack = createStack(5);
    }

    public void testConstructorWithNegativeCapacity() {
        assertThrows(IllegalArgumentException.class, () -> {
            Stack stack = createStack(-1);
        });
    }

    public void testIsEmptyMethod() throws Exception {
        Stack stack = createStack();
        assertTrue(stack.isEmpty());
        stack.push(42);
        assertFalse(stack.isEmpty());
        stack.pop();
        assertTrue(stack.isEmpty());
    }

    public void testIsFullMethod() throws Exception {
        Stack stack = createStack(1);
        assertFalse(stack.isFull());
        stack.push(42);
        assertTrue(stack.isFull());
        stack.pop();
        assertFalse(stack.isFull());
    }

    public void testToStringMethod() throws Exception {
        Stack stack = createStack(2);
        assertEquals("[]", stack.toString());
        stack.push(42);
        assertEquals("[42]", stack.toString());
        stack.push(43);
        assertEquals("[42,43]", stack.toString());
    }

    public void testNewStackHashCode() throws Exception {
        Stack stack = createStack();
        assertEquals(129083679, stack.hashCode());
    }
	
	public void testEqualsMethod() throws Exception {
		Stack stack1 = createStack();
		Stack stack2 = createStack();
		Stack stack3 = createStack();
		stack3.push(41);
		assertEquals(stack1, stack1);
		assertEquals(stack1, stack2);
		assertNotEquals(stack1, null);
		assertNotEquals(stack1, new ArrayList<>());
		stack1.push(42);
		assertNotEquals(stack1, stack3);
		assertNotEquals(stack1, stack2);
		stack2.push(42);
		assertEquals(stack1, stack2);
		stack2.pop();
		assertNotEquals(stack1, stack2);
	}
	
	public void testStackWithNoCapacityIsFull() throws Exception {
		Stack stack = createStack(0);
		assertTrue(stack.isFull());
	}
	
	public void testPushIntoFullStackThrowsException() throws Exception {
		Stack stack = createStack(1);
		stack.push(42);
		assertThrows(IllegalStateException.class, () -> {
			stack.push(43);
		});
	}
	
	public void testPopStack() throws Exception {
		Stack stack = createStack();
		stack.push(42);
		assertEquals(42, stack.pop());
	}
	
	public void testTopEmptyStackThrowsException() throws Exception {
		Stack stack = createStack();
		assertThrows(IllegalStateException.class, () -> {
			stack.top();
		});
	}

}
