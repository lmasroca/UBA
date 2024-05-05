package org.autotest;

import static org.junit.Assert.*;

import org.junit.jupiter.api.Test;

public class RegressionTestExtended {
	
	@Test
	public void testHashcode() throws Throwable {
		StackAr stackAr0 = new StackAr();
		assertEquals(129083679, stackAr0.hashCode());
	}
	
	@Test
	public void testEqualsMethod() throws Exception {
		StackAr stack1 = new StackAr();
		assertEquals(stack1, stack1);
	}
}
