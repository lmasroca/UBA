package org.autotest;

import org.junit.FixMethodOrder;
import org.junit.jupiter.api.Test;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class RegressionTest4 {

    public static boolean debug = false;

    @Test
    public void test0201() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0201");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) (short) 10);
        boolean boolean2 = stackAr1.isFull();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj3 = stackAr1.pop();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
    }

    @Test
    public void test0202() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0202");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr(1);
        boolean boolean2 = stackAr1.isFull();
        org.autotest.StackAr stackAr4 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr6 = new org.autotest.StackAr((int) '4');
        java.lang.String str7 = stackAr6.toString();
        boolean boolean8 = stackAr4.equals((java.lang.Object) stackAr6);
        org.autotest.StackAr stackAr10 = new org.autotest.StackAr((int) '4');
        stackAr10.push((java.lang.Object) (-1L));
        java.lang.Object obj13 = stackAr10.pop();
        stackAr4.push((java.lang.Object) stackAr10);
        stackAr1.push((java.lang.Object) stackAr10);
        org.autotest.StackAr stackAr17 = new org.autotest.StackAr((int) 'a');
        boolean boolean19 = stackAr17.equals((java.lang.Object) (-1.0f));
        org.autotest.StackAr stackAr21 = new org.autotest.StackAr((int) '4');
        int int22 = stackAr21.size();
        java.lang.String str23 = stackAr21.toString();
        boolean boolean24 = stackAr17.equals((java.lang.Object) str23);
        // The following exception was thrown during execution in test generation
        try {
            stackAr1.push((java.lang.Object) boolean24);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "[]" + "'", str7, "[]");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + true + "'", boolean8 == true);
        org.junit.Assert.assertEquals("'" + obj13 + "' != '" + (-1L) + "'", obj13, (-1L));
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertTrue("'" + int22 + "' != '" + 0 + "'", int22 == 0);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "[]" + "'", str23, "[]");
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
    }

    @Test
    public void test0203() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0203");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        java.lang.String str4 = stackAr0.toString();
        boolean boolean6 = stackAr0.equals((java.lang.Object) 10L);
        org.autotest.StackAr stackAr7 = new org.autotest.StackAr();
        boolean boolean8 = stackAr7.isFull();
        int int9 = stackAr7.size();
        boolean boolean10 = stackAr7.isEmpty();
        stackAr0.push((java.lang.Object) stackAr7);
        java.lang.Class<?> wildcardClass12 = stackAr7.getClass();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertNotNull(wildcardClass12);
    }

    @Test
    public void test0204() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0204");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) (short) 0);
        boolean boolean2 = stackAr1.isFull();
        java.lang.String str3 = stackAr1.toString();
        org.autotest.StackAr stackAr4 = new org.autotest.StackAr();
        boolean boolean5 = stackAr4.isFull();
        boolean boolean7 = stackAr4.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr9 = new org.autotest.StackAr((int) '4');
        boolean boolean10 = stackAr4.equals((java.lang.Object) '4');
        org.autotest.StackAr stackAr12 = new org.autotest.StackAr((int) 'a');
        boolean boolean14 = stackAr12.equals((java.lang.Object) (-1.0f));
        boolean boolean16 = stackAr12.equals((java.lang.Object) "");
        boolean boolean17 = stackAr4.equals((java.lang.Object) "");
        boolean boolean18 = stackAr4.isFull();
        boolean boolean19 = stackAr4.isFull();
        int int20 = stackAr4.size();
        // The following exception was thrown during execution in test generation
        try {
            stackAr1.push((java.lang.Object) int20);
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[]" + "'", str3, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 0 + "'", int20 == 0);
    }

    @Test
    public void test0205() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0205");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr3 = new org.autotest.StackAr((int) '4');
        java.lang.String str4 = stackAr3.toString();
        boolean boolean5 = stackAr1.equals((java.lang.Object) stackAr3);
        org.autotest.StackAr stackAr7 = new org.autotest.StackAr((int) '4');
        stackAr7.push((java.lang.Object) (-1L));
        java.lang.Object obj10 = stackAr7.pop();
        stackAr1.push((java.lang.Object) stackAr7);
        boolean boolean12 = stackAr7.isEmpty();
        org.autotest.StackAr stackAr14 = new org.autotest.StackAr((int) '4');
        java.lang.String str15 = stackAr14.toString();
        java.lang.String str16 = stackAr14.toString();
        org.autotest.StackAr stackAr17 = new org.autotest.StackAr();
        boolean boolean18 = stackAr17.isFull();
        boolean boolean20 = stackAr17.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr22 = new org.autotest.StackAr((int) '4');
        boolean boolean23 = stackAr17.equals((java.lang.Object) '4');
        org.autotest.StackAr stackAr25 = new org.autotest.StackAr((int) 'a');
        boolean boolean27 = stackAr25.equals((java.lang.Object) (-1.0f));
        boolean boolean29 = stackAr25.equals((java.lang.Object) "");
        boolean boolean30 = stackAr17.equals((java.lang.Object) "");
        boolean boolean31 = stackAr17.isFull();
        boolean boolean32 = stackAr14.equals((java.lang.Object) stackAr17);
        int int33 = stackAr17.size();
        boolean boolean34 = stackAr17.isEmpty();
        boolean boolean35 = stackAr17.isEmpty();
        org.autotest.StackAr stackAr37 = new org.autotest.StackAr((int) '4');
        int int38 = stackAr37.size();
        stackAr37.push((java.lang.Object) 0);
        boolean boolean41 = stackAr37.isFull();
        stackAr17.push((java.lang.Object) stackAr37);
        org.autotest.StackAr stackAr44 = new org.autotest.StackAr((int) '4');
        stackAr44.push((java.lang.Object) (-1L));
        java.lang.Object obj47 = stackAr44.pop();
        stackAr44.push((java.lang.Object) (-1));
        boolean boolean50 = stackAr44.isEmpty();
        org.autotest.StackAr stackAr52 = new org.autotest.StackAr((int) 'a');
        boolean boolean54 = stackAr52.equals((java.lang.Object) (-1.0f));
        boolean boolean56 = stackAr52.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr57 = new org.autotest.StackAr();
        boolean boolean58 = stackAr57.isFull();
        stackAr52.push((java.lang.Object) stackAr57);
        boolean boolean60 = stackAr52.isFull();
        java.lang.Object obj61 = stackAr52.pop();
        boolean boolean62 = stackAr44.equals(obj61);
        stackAr17.push(obj61);
        boolean boolean64 = stackAr7.equals(obj61);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj65 = stackAr7.top();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertEquals("'" + obj10 + "' != '" + (-1L) + "'", obj10, (-1L));
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + true + "'", boolean12 == true);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "[]" + "'", str15, "[]");
        org.junit.Assert.assertEquals("'" + str16 + "' != '" + "[]" + "'", str16, "[]");
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertTrue("'" + int33 + "' != '" + 0 + "'", int33 == 0);
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + true + "'", boolean34 == true);
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + true + "'", boolean35 == true);
        org.junit.Assert.assertTrue("'" + int38 + "' != '" + 0 + "'", int38 == 0);
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + false + "'", boolean41 == false);
        org.junit.Assert.assertEquals("'" + obj47 + "' != '" + (-1L) + "'", obj47, (-1L));
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + false + "'", boolean50 == false);
        org.junit.Assert.assertTrue("'" + boolean54 + "' != '" + false + "'", boolean54 == false);
        org.junit.Assert.assertTrue("'" + boolean56 + "' != '" + false + "'", boolean56 == false);
        org.junit.Assert.assertTrue("'" + boolean58 + "' != '" + false + "'", boolean58 == false);
        org.junit.Assert.assertTrue("'" + boolean60 + "' != '" + false + "'", boolean60 == false);
        org.junit.Assert.assertNotNull(obj61);
        org.junit.Assert.assertEquals(obj61.toString(), "[]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj61), "[]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj61), "[]");
        org.junit.Assert.assertTrue("'" + boolean62 + "' != '" + false + "'", boolean62 == false);
        org.junit.Assert.assertTrue("'" + boolean64 + "' != '" + false + "'", boolean64 == false);
    }

    @Test
    public void test0206() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0206");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr5 = new org.autotest.StackAr((int) '4');
        boolean boolean6 = stackAr0.equals((java.lang.Object) '4');
        org.autotest.StackAr stackAr8 = new org.autotest.StackAr((int) 'a');
        boolean boolean10 = stackAr8.equals((java.lang.Object) (-1.0f));
        boolean boolean12 = stackAr8.equals((java.lang.Object) "");
        boolean boolean13 = stackAr0.equals((java.lang.Object) "");
        boolean boolean14 = stackAr0.isFull();
        org.autotest.StackAr stackAr16 = new org.autotest.StackAr((int) '4');
        java.lang.String str17 = stackAr16.toString();
        java.lang.String str18 = stackAr16.toString();
        org.autotest.StackAr stackAr19 = new org.autotest.StackAr();
        boolean boolean20 = stackAr19.isFull();
        boolean boolean22 = stackAr19.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr24 = new org.autotest.StackAr((int) '4');
        boolean boolean25 = stackAr19.equals((java.lang.Object) '4');
        org.autotest.StackAr stackAr27 = new org.autotest.StackAr((int) 'a');
        boolean boolean29 = stackAr27.equals((java.lang.Object) (-1.0f));
        boolean boolean31 = stackAr27.equals((java.lang.Object) "");
        boolean boolean32 = stackAr19.equals((java.lang.Object) "");
        boolean boolean33 = stackAr19.isFull();
        boolean boolean34 = stackAr16.equals((java.lang.Object) stackAr19);
        java.lang.Object obj35 = null;
        stackAr19.push(obj35);
        java.lang.Object obj37 = stackAr19.top();
        java.lang.String str38 = stackAr19.toString();
        boolean boolean39 = stackAr0.equals((java.lang.Object) stackAr19);
        org.autotest.StackAr stackAr41 = new org.autotest.StackAr((int) 'a');
        java.lang.String str42 = stackAr41.toString();
        boolean boolean43 = stackAr41.isEmpty();
        boolean boolean44 = stackAr0.equals((java.lang.Object) stackAr41);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj45 = stackAr0.pop();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "[]" + "'", str17, "[]");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "[]" + "'", str18, "[]");
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
        org.junit.Assert.assertNull(obj37);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "[null]" + "'", str38, "[null]");
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertEquals("'" + str42 + "' != '" + "[]" + "'", str42, "[]");
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + true + "'", boolean43 == true);
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + false + "'", boolean44 == false);
    }

    @Test
    public void test0207() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0207");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        int int2 = stackAr1.size();
        stackAr1.push((java.lang.Object) 0);
        boolean boolean5 = stackAr1.isFull();
        java.lang.Object obj6 = stackAr1.top();
        java.lang.Object obj7 = stackAr1.pop();
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertEquals("'" + obj6 + "' != '" + 0 + "'", obj6, 0);
        org.junit.Assert.assertEquals("'" + obj7 + "' != '" + 0 + "'", obj7, 0);
    }

    @Test
    public void test0208() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0208");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr5 = new org.autotest.StackAr((int) '4');
        boolean boolean6 = stackAr0.equals((java.lang.Object) '4');
        org.autotest.StackAr stackAr8 = new org.autotest.StackAr((int) 'a');
        boolean boolean10 = stackAr8.equals((java.lang.Object) (-1.0f));
        boolean boolean12 = stackAr8.equals((java.lang.Object) "");
        boolean boolean13 = stackAr0.equals((java.lang.Object) "");
        boolean boolean14 = stackAr0.isFull();
        org.autotest.StackAr stackAr16 = new org.autotest.StackAr((int) '4');
        java.lang.String str17 = stackAr16.toString();
        java.lang.String str18 = stackAr16.toString();
        org.autotest.StackAr stackAr19 = new org.autotest.StackAr();
        boolean boolean20 = stackAr19.isFull();
        boolean boolean22 = stackAr19.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr24 = new org.autotest.StackAr((int) '4');
        boolean boolean25 = stackAr19.equals((java.lang.Object) '4');
        org.autotest.StackAr stackAr27 = new org.autotest.StackAr((int) 'a');
        boolean boolean29 = stackAr27.equals((java.lang.Object) (-1.0f));
        boolean boolean31 = stackAr27.equals((java.lang.Object) "");
        boolean boolean32 = stackAr19.equals((java.lang.Object) "");
        boolean boolean33 = stackAr19.isFull();
        boolean boolean34 = stackAr16.equals((java.lang.Object) stackAr19);
        java.lang.Object obj35 = null;
        stackAr19.push(obj35);
        java.lang.Object obj37 = stackAr19.top();
        java.lang.String str38 = stackAr19.toString();
        boolean boolean39 = stackAr0.equals((java.lang.Object) stackAr19);
        org.autotest.StackAr stackAr41 = new org.autotest.StackAr((int) 'a');
        java.lang.String str42 = stackAr41.toString();
        boolean boolean43 = stackAr41.isEmpty();
        boolean boolean44 = stackAr0.equals((java.lang.Object) stackAr41);
        org.autotest.StackAr stackAr46 = new org.autotest.StackAr((int) 'a');
        boolean boolean48 = stackAr46.equals((java.lang.Object) (-1.0f));
        boolean boolean50 = stackAr46.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr51 = new org.autotest.StackAr();
        boolean boolean52 = stackAr51.isFull();
        stackAr46.push((java.lang.Object) stackAr51);
        boolean boolean54 = stackAr46.isFull();
        java.lang.Object obj55 = stackAr46.pop();
        stackAr41.push(obj55);
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertEquals("'" + str17 + "' != '" + "[]" + "'", str17, "[]");
        org.junit.Assert.assertEquals("'" + str18 + "' != '" + "[]" + "'", str18, "[]");
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
        org.junit.Assert.assertNull(obj37);
        org.junit.Assert.assertEquals("'" + str38 + "' != '" + "[null]" + "'", str38, "[null]");
        org.junit.Assert.assertTrue("'" + boolean39 + "' != '" + false + "'", boolean39 == false);
        org.junit.Assert.assertEquals("'" + str42 + "' != '" + "[]" + "'", str42, "[]");
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + true + "'", boolean43 == true);
        org.junit.Assert.assertTrue("'" + boolean44 + "' != '" + false + "'", boolean44 == false);
        org.junit.Assert.assertTrue("'" + boolean48 + "' != '" + false + "'", boolean48 == false);
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + false + "'", boolean50 == false);
        org.junit.Assert.assertTrue("'" + boolean52 + "' != '" + false + "'", boolean52 == false);
        org.junit.Assert.assertTrue("'" + boolean54 + "' != '" + false + "'", boolean54 == false);
        org.junit.Assert.assertNotNull(obj55);
        org.junit.Assert.assertEquals(obj55.toString(), "[]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj55), "[]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj55), "[]");
    }

    @Test
    public void test0209() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0209");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        org.autotest.StackAr stackAr3 = new org.autotest.StackAr();
        boolean boolean4 = stackAr3.isFull();
        boolean boolean6 = stackAr3.equals((java.lang.Object) (byte) -1);
        stackAr1.push((java.lang.Object) boolean6);
        boolean boolean8 = stackAr1.isEmpty();
        java.lang.Object obj9 = stackAr1.top();
        java.lang.Object obj10 = stackAr1.top();
        java.lang.String str11 = stackAr1.toString();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertEquals("'" + obj9 + "' != '" + false + "'", obj9, false);
        org.junit.Assert.assertEquals("'" + obj10 + "' != '" + false + "'", obj10, false);
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "[false]" + "'", str11, "[false]");
    }

    @Test
    public void test0210() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0210");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        java.lang.String str2 = stackAr1.toString();
        stackAr1.push((java.lang.Object) 0);
        java.lang.String str5 = stackAr1.toString();
        org.autotest.StackAr stackAr7 = new org.autotest.StackAr((int) '4');
        java.lang.String str8 = stackAr7.toString();
        stackAr7.push((java.lang.Object) 0);
        java.lang.String str11 = stackAr7.toString();
        java.lang.Object obj12 = stackAr7.top();
        stackAr1.push(obj12);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "[]" + "'", str2, "[]");
        org.junit.Assert.assertEquals("'" + str5 + "' != '" + "[0]" + "'", str5, "[0]");
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "[]" + "'", str8, "[]");
        org.junit.Assert.assertEquals("'" + str11 + "' != '" + "[0]" + "'", str11, "[0]");
        org.junit.Assert.assertEquals("'" + obj12 + "' != '" + 0 + "'", obj12, 0);
    }

    @Test
    public void test0211() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0211");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) (byte) 0);
        org.autotest.StackAr stackAr3 = new org.autotest.StackAr((int) 'a');
        boolean boolean5 = stackAr3.equals((java.lang.Object) (-1.0f));
        boolean boolean7 = stackAr3.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr8 = new org.autotest.StackAr();
        boolean boolean9 = stackAr8.isFull();
        stackAr3.push((java.lang.Object) stackAr8);
        org.autotest.StackAr stackAr12 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr14 = new org.autotest.StackAr((int) '4');
        java.lang.String str15 = stackAr14.toString();
        boolean boolean16 = stackAr12.equals((java.lang.Object) stackAr14);
        org.autotest.StackAr stackAr17 = new org.autotest.StackAr();
        boolean boolean18 = stackAr17.isFull();
        boolean boolean20 = stackAr17.equals((java.lang.Object) (byte) -1);
        java.lang.String str21 = stackAr17.toString();
        boolean boolean23 = stackAr17.equals((java.lang.Object) 10L);
        java.lang.String str24 = stackAr17.toString();
        stackAr14.push((java.lang.Object) stackAr17);
        stackAr3.push((java.lang.Object) stackAr17);
        java.lang.Object obj27 = stackAr3.pop();
        boolean boolean28 = stackAr1.equals((java.lang.Object) stackAr3);
        java.lang.Class<?> wildcardClass29 = stackAr3.getClass();
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertEquals("'" + str15 + "' != '" + "[]" + "'", str15, "[]");
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + true + "'", boolean16 == true);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "[]" + "'", str21, "[]");
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "[]" + "'", str24, "[]");
        org.junit.Assert.assertNotNull(obj27);
        org.junit.Assert.assertEquals(obj27.toString(), "[]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj27), "[]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj27), "[]");
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertNotNull(wildcardClass29);
    }

    @Test
    public void test0212() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0212");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr3 = new org.autotest.StackAr((int) '4');
        java.lang.String str4 = stackAr3.toString();
        boolean boolean5 = stackAr1.equals((java.lang.Object) stackAr3);
        boolean boolean7 = stackAr3.equals((java.lang.Object) "[]");
        boolean boolean8 = stackAr3.isFull();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj9 = stackAr3.pop();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0213() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0213");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) (short) 0);
        boolean boolean2 = stackAr1.isEmpty();
        boolean boolean3 = stackAr1.isFull();
        org.autotest.StackAr stackAr5 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr7 = new org.autotest.StackAr((int) '4');
        java.lang.String str8 = stackAr7.toString();
        boolean boolean9 = stackAr5.equals((java.lang.Object) stackAr7);
        org.autotest.StackAr stackAr11 = new org.autotest.StackAr((int) '4');
        stackAr11.push((java.lang.Object) (-1L));
        java.lang.Object obj14 = stackAr11.pop();
        stackAr5.push((java.lang.Object) stackAr11);
        boolean boolean16 = stackAr1.equals((java.lang.Object) stackAr5);
        java.lang.Object obj17 = stackAr5.pop();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj18 = stackAr5.pop();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "[]" + "'", str8, "[]");
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertEquals("'" + obj14 + "' != '" + (-1L) + "'", obj14, (-1L));
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertNotNull(obj17);
        org.junit.Assert.assertEquals(obj17.toString(), "[]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj17), "[]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj17), "[]");
    }

    @Test
    public void test0214() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0214");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) 'a');
        boolean boolean3 = stackAr1.equals((java.lang.Object) (-1.0f));
        boolean boolean5 = stackAr1.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr6 = new org.autotest.StackAr();
        boolean boolean7 = stackAr6.isFull();
        stackAr1.push((java.lang.Object) stackAr6);
        boolean boolean9 = stackAr1.isFull();
        java.lang.Object obj10 = stackAr1.pop();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj11 = stackAr1.top();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(obj10);
        org.junit.Assert.assertEquals(obj10.toString(), "[]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj10), "[]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj10), "[]");
    }

    @Test
    public void test0215() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0215");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        java.lang.String str4 = stackAr0.toString();
        boolean boolean6 = stackAr0.equals((java.lang.Object) 10L);
        org.autotest.StackAr stackAr7 = new org.autotest.StackAr();
        boolean boolean8 = stackAr7.isFull();
        int int9 = stackAr7.size();
        boolean boolean10 = stackAr7.isEmpty();
        stackAr0.push((java.lang.Object) stackAr7);
        int int12 = stackAr0.size();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 1 + "'", int12 == 1);
    }

    @Test
    public void test0216() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0216");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        int int2 = stackAr1.size();
        java.lang.String str3 = stackAr1.toString();
        int int4 = stackAr1.size();
        boolean boolean5 = stackAr1.isEmpty();
        java.lang.String str6 = stackAr1.toString();
        boolean boolean7 = stackAr1.isEmpty();
        boolean boolean8 = stackAr1.isFull();
        java.lang.Class<?> wildcardClass9 = stackAr1.getClass();
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[]" + "'", str3, "[]");
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertEquals("'" + str6 + "' != '" + "[]" + "'", str6, "[]");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + true + "'", boolean7 == true);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertNotNull(wildcardClass9);
    }

    @Test
    public void test0217() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0217");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr3 = new org.autotest.StackAr((int) '4');
        java.lang.String str4 = stackAr3.toString();
        boolean boolean5 = stackAr1.equals((java.lang.Object) stackAr3);
        boolean boolean6 = stackAr1.isEmpty();
        boolean boolean7 = stackAr1.isFull();
        java.lang.Class<?> wildcardClass8 = stackAr1.getClass();
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + true + "'", boolean6 == true);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertNotNull(wildcardClass8);
    }

    @Test
    public void test0218() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0218");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        java.lang.String str4 = stackAr0.toString();
        boolean boolean6 = stackAr0.equals((java.lang.Object) 10L);
        java.lang.String str7 = stackAr0.toString();
        boolean boolean8 = stackAr0.isFull();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj9 = stackAr0.top();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "[]" + "'", str7, "[]");
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
    }

    @Test
    public void test0219() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0219");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        java.lang.String str4 = stackAr0.toString();
        boolean boolean5 = stackAr0.isFull();
        java.lang.Class<?> wildcardClass6 = stackAr0.getClass();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test0220() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0220");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        int int2 = stackAr1.size();
        int int3 = stackAr1.size();
        boolean boolean4 = stackAr1.isEmpty();
        stackAr1.push((java.lang.Object) "[[],true]");
        java.lang.String str7 = stackAr1.toString();
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + true + "'", boolean4 == true);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "[[[],true]]" + "'", str7, "[[[],true]]");
    }

    @Test
    public void test0221() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0221");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) false);
        java.lang.String str4 = stackAr0.toString();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj5 = stackAr0.pop();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
    }

    @Test
    public void test0222() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0222");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) 'a');
        boolean boolean3 = stackAr1.equals((java.lang.Object) (-1.0f));
        boolean boolean5 = stackAr1.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr6 = new org.autotest.StackAr();
        boolean boolean7 = stackAr6.isFull();
        stackAr1.push((java.lang.Object) stackAr6);
        org.autotest.StackAr stackAr10 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr12 = new org.autotest.StackAr((int) '4');
        java.lang.String str13 = stackAr12.toString();
        boolean boolean14 = stackAr10.equals((java.lang.Object) stackAr12);
        org.autotest.StackAr stackAr15 = new org.autotest.StackAr();
        boolean boolean16 = stackAr15.isFull();
        boolean boolean18 = stackAr15.equals((java.lang.Object) (byte) -1);
        java.lang.String str19 = stackAr15.toString();
        boolean boolean21 = stackAr15.equals((java.lang.Object) 10L);
        java.lang.String str22 = stackAr15.toString();
        stackAr12.push((java.lang.Object) stackAr15);
        stackAr1.push((java.lang.Object) stackAr15);
        boolean boolean25 = stackAr15.isFull();
        boolean boolean27 = stackAr15.equals((java.lang.Object) (short) 10);
        boolean boolean28 = stackAr15.isEmpty();
        boolean boolean29 = stackAr15.isFull();
        org.autotest.StackAr stackAr31 = new org.autotest.StackAr((int) '4');
        stackAr31.push((java.lang.Object) (-1L));
        java.lang.Object obj34 = stackAr31.pop();
        java.lang.String str35 = stackAr31.toString();
        boolean boolean36 = stackAr31.isEmpty();
        stackAr15.push((java.lang.Object) stackAr31);
        java.lang.Class<?> wildcardClass38 = stackAr31.getClass();
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "[]" + "'", str13, "[]");
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "[]" + "'", str19, "[]");
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "[]" + "'", str22, "[]");
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + false + "'", boolean27 == false);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + true + "'", boolean28 == true);
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertEquals("'" + obj34 + "' != '" + (-1L) + "'", obj34, (-1L));
        org.junit.Assert.assertEquals("'" + str35 + "' != '" + "[]" + "'", str35, "[]");
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + true + "'", boolean36 == true);
        org.junit.Assert.assertNotNull(wildcardClass38);
    }

    @Test
    public void test0223() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0223");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr3 = new org.autotest.StackAr((int) '4');
        java.lang.String str4 = stackAr3.toString();
        boolean boolean5 = stackAr1.equals((java.lang.Object) stackAr3);
        org.autotest.StackAr stackAr7 = new org.autotest.StackAr((int) '4');
        stackAr7.push((java.lang.Object) (-1L));
        java.lang.Object obj10 = stackAr7.pop();
        stackAr1.push((java.lang.Object) stackAr7);
        boolean boolean12 = stackAr1.isEmpty();
        java.lang.Class<?> wildcardClass13 = stackAr1.getClass();
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertEquals("'" + obj10 + "' != '" + (-1L) + "'", obj10, (-1L));
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertNotNull(wildcardClass13);
    }

    @Test
    public void test0224() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0224");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        stackAr1.push((java.lang.Object) (-1L));
        java.lang.Object obj4 = stackAr1.pop();
        stackAr1.push((java.lang.Object) (-1));
        java.lang.Object obj7 = stackAr1.top();
        org.junit.Assert.assertEquals("'" + obj4 + "' != '" + (-1L) + "'", obj4, (-1L));
        org.junit.Assert.assertEquals("'" + obj7 + "' != '" + (-1) + "'", obj7, (-1));
    }

    @Test
    public void test0225() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0225");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        java.lang.String str2 = stackAr1.toString();
        java.lang.String str3 = stackAr1.toString();
        org.autotest.StackAr stackAr4 = new org.autotest.StackAr();
        boolean boolean5 = stackAr4.isFull();
        boolean boolean7 = stackAr4.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr9 = new org.autotest.StackAr((int) '4');
        boolean boolean10 = stackAr4.equals((java.lang.Object) '4');
        org.autotest.StackAr stackAr12 = new org.autotest.StackAr((int) 'a');
        boolean boolean14 = stackAr12.equals((java.lang.Object) (-1.0f));
        boolean boolean16 = stackAr12.equals((java.lang.Object) "");
        boolean boolean17 = stackAr4.equals((java.lang.Object) "");
        boolean boolean18 = stackAr4.isFull();
        boolean boolean19 = stackAr1.equals((java.lang.Object) stackAr4);
        java.lang.Object obj20 = null;
        stackAr4.push(obj20);
        java.lang.Object obj22 = stackAr4.top();
        boolean boolean23 = stackAr4.isEmpty();
        java.lang.Class<?> wildcardClass24 = stackAr4.getClass();
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "[]" + "'", str2, "[]");
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[]" + "'", str3, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNull(obj22);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(wildcardClass24);
    }

    @Test
    public void test0226() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0226");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        java.lang.String str4 = stackAr0.toString();
        boolean boolean5 = stackAr0.isEmpty();
        org.autotest.StackAr stackAr7 = new org.autotest.StackAr((int) 'a');
        boolean boolean9 = stackAr7.equals((java.lang.Object) (-1.0f));
        boolean boolean10 = stackAr7.isEmpty();
        boolean boolean11 = stackAr0.equals((java.lang.Object) stackAr7);
        boolean boolean12 = stackAr7.isFull();
        boolean boolean13 = stackAr7.isFull();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj14 = stackAr7.top();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
    }

    @Test
    public void test0227() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0227");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        java.lang.String str4 = stackAr0.toString();
        boolean boolean6 = stackAr0.equals((java.lang.Object) 10L);
        java.lang.String str7 = stackAr0.toString();
        org.autotest.StackAr stackAr9 = new org.autotest.StackAr((int) 'a');
        boolean boolean11 = stackAr9.equals((java.lang.Object) (-1.0f));
        boolean boolean13 = stackAr9.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr14 = new org.autotest.StackAr();
        boolean boolean15 = stackAr14.isFull();
        stackAr9.push((java.lang.Object) stackAr14);
        org.autotest.StackAr stackAr18 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr20 = new org.autotest.StackAr((int) '4');
        java.lang.String str21 = stackAr20.toString();
        boolean boolean22 = stackAr18.equals((java.lang.Object) stackAr20);
        org.autotest.StackAr stackAr23 = new org.autotest.StackAr();
        boolean boolean24 = stackAr23.isFull();
        boolean boolean26 = stackAr23.equals((java.lang.Object) (byte) -1);
        java.lang.String str27 = stackAr23.toString();
        boolean boolean29 = stackAr23.equals((java.lang.Object) 10L);
        java.lang.String str30 = stackAr23.toString();
        stackAr20.push((java.lang.Object) stackAr23);
        stackAr9.push((java.lang.Object) stackAr23);
        boolean boolean33 = stackAr0.equals((java.lang.Object) stackAr23);
        org.autotest.StackAr stackAr35 = new org.autotest.StackAr((int) '4');
        int int36 = stackAr35.size();
        boolean boolean37 = stackAr35.isFull();
        boolean boolean38 = stackAr0.equals((java.lang.Object) boolean37);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj39 = stackAr0.pop();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "[]" + "'", str7, "[]");
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "[]" + "'", str21, "[]");
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "[]" + "'", str27, "[]");
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "[]" + "'", str30, "[]");
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + true + "'", boolean33 == true);
        org.junit.Assert.assertTrue("'" + int36 + "' != '" + 0 + "'", int36 == 0);
        org.junit.Assert.assertTrue("'" + boolean37 + "' != '" + false + "'", boolean37 == false);
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
    }

    @Test
    public void test0228() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0228");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        java.lang.String str4 = stackAr0.toString();
        boolean boolean5 = stackAr0.isEmpty();
        org.autotest.StackAr stackAr7 = new org.autotest.StackAr((int) 'a');
        boolean boolean9 = stackAr7.equals((java.lang.Object) (-1.0f));
        boolean boolean10 = stackAr7.isEmpty();
        boolean boolean11 = stackAr0.equals((java.lang.Object) stackAr7);
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj12 = stackAr0.pop();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
    }

    @Test
    public void test0229() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0229");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) (short) 10);
        boolean boolean2 = stackAr1.isFull();
        stackAr1.push((java.lang.Object) 'a');
        java.lang.Object obj5 = stackAr1.pop();
        java.lang.Class<?> wildcardClass6 = stackAr1.getClass();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertEquals("'" + obj5 + "' != '" + 'a' + "'", obj5, 'a');
        org.junit.Assert.assertNotNull(wildcardClass6);
    }

    @Test
    public void test0230() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0230");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        int int2 = stackAr1.size();
        java.lang.String str3 = stackAr1.toString();
        int int4 = stackAr1.size();
        boolean boolean5 = stackAr1.isFull();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj6 = stackAr1.top();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[]" + "'", str3, "[]");
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
    }

    @Test
    public void test0231() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0231");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) (short) 0);
        boolean boolean2 = stackAr1.isEmpty();
        boolean boolean3 = stackAr1.isFull();
        org.autotest.StackAr stackAr5 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr7 = new org.autotest.StackAr((int) '4');
        java.lang.String str8 = stackAr7.toString();
        boolean boolean9 = stackAr5.equals((java.lang.Object) stackAr7);
        org.autotest.StackAr stackAr11 = new org.autotest.StackAr((int) '4');
        stackAr11.push((java.lang.Object) (-1L));
        java.lang.Object obj14 = stackAr11.pop();
        stackAr5.push((java.lang.Object) stackAr11);
        boolean boolean16 = stackAr1.equals((java.lang.Object) stackAr5);
        org.autotest.StackAr stackAr18 = new org.autotest.StackAr((int) '4');
        int int19 = stackAr18.size();
        java.lang.String str20 = stackAr18.toString();
        int int21 = stackAr18.size();
        boolean boolean22 = stackAr18.isEmpty();
        boolean boolean23 = stackAr5.equals((java.lang.Object) boolean22);
        java.lang.String str24 = stackAr5.toString();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
        org.junit.Assert.assertEquals("'" + str8 + "' != '" + "[]" + "'", str8, "[]");
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + true + "'", boolean9 == true);
        org.junit.Assert.assertEquals("'" + obj14 + "' != '" + (-1L) + "'", obj14, (-1L));
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + int19 + "' != '" + 0 + "'", int19 == 0);
        org.junit.Assert.assertEquals("'" + str20 + "' != '" + "[]" + "'", str20, "[]");
        org.junit.Assert.assertTrue("'" + int21 + "' != '" + 0 + "'", int21 == 0);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertEquals("'" + str24 + "' != '" + "[[]]" + "'", str24, "[[]]");
    }

    @Test
    public void test0232() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0232");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        java.lang.String str4 = stackAr0.toString();
        boolean boolean6 = stackAr0.equals((java.lang.Object) 10L);
        java.lang.String str7 = stackAr0.toString();
        boolean boolean9 = stackAr0.equals((java.lang.Object) (byte) 0);
        org.autotest.StackAr stackAr11 = new org.autotest.StackAr((int) 'a');
        boolean boolean13 = stackAr11.equals((java.lang.Object) (-1.0f));
        boolean boolean15 = stackAr11.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr16 = new org.autotest.StackAr();
        boolean boolean17 = stackAr16.isFull();
        stackAr11.push((java.lang.Object) stackAr16);
        org.autotest.StackAr stackAr20 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr22 = new org.autotest.StackAr((int) '4');
        java.lang.String str23 = stackAr22.toString();
        boolean boolean24 = stackAr20.equals((java.lang.Object) stackAr22);
        org.autotest.StackAr stackAr25 = new org.autotest.StackAr();
        boolean boolean26 = stackAr25.isFull();
        boolean boolean28 = stackAr25.equals((java.lang.Object) (byte) -1);
        java.lang.String str29 = stackAr25.toString();
        boolean boolean31 = stackAr25.equals((java.lang.Object) 10L);
        java.lang.String str32 = stackAr25.toString();
        stackAr22.push((java.lang.Object) stackAr25);
        stackAr11.push((java.lang.Object) stackAr25);
        boolean boolean35 = stackAr11.isEmpty();
        boolean boolean36 = stackAr0.equals((java.lang.Object) stackAr11);
        int int37 = stackAr0.size();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj38 = stackAr0.pop();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "[]" + "'", str7, "[]");
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "[]" + "'", str23, "[]");
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + true + "'", boolean24 == true);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertEquals("'" + str29 + "' != '" + "[]" + "'", str29, "[]");
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertEquals("'" + str32 + "' != '" + "[]" + "'", str32, "[]");
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + false + "'", boolean35 == false);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
        org.junit.Assert.assertTrue("'" + int37 + "' != '" + 0 + "'", int37 == 0);
    }

    @Test
    public void test0233() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0233");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr3 = new org.autotest.StackAr((int) '4');
        java.lang.String str4 = stackAr3.toString();
        boolean boolean5 = stackAr1.equals((java.lang.Object) stackAr3);
        org.autotest.StackAr stackAr6 = new org.autotest.StackAr();
        boolean boolean7 = stackAr6.isFull();
        boolean boolean9 = stackAr6.equals((java.lang.Object) (byte) -1);
        java.lang.String str10 = stackAr6.toString();
        boolean boolean12 = stackAr6.equals((java.lang.Object) 10L);
        java.lang.String str13 = stackAr6.toString();
        stackAr3.push((java.lang.Object) stackAr6);
        org.autotest.StackAr stackAr16 = new org.autotest.StackAr((int) 'a');
        boolean boolean18 = stackAr16.equals((java.lang.Object) (-1.0f));
        boolean boolean20 = stackAr16.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr21 = new org.autotest.StackAr();
        boolean boolean22 = stackAr21.isFull();
        stackAr16.push((java.lang.Object) stackAr21);
        org.autotest.StackAr stackAr25 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr27 = new org.autotest.StackAr((int) '4');
        java.lang.String str28 = stackAr27.toString();
        boolean boolean29 = stackAr25.equals((java.lang.Object) stackAr27);
        org.autotest.StackAr stackAr30 = new org.autotest.StackAr();
        boolean boolean31 = stackAr30.isFull();
        boolean boolean33 = stackAr30.equals((java.lang.Object) (byte) -1);
        java.lang.String str34 = stackAr30.toString();
        boolean boolean36 = stackAr30.equals((java.lang.Object) 10L);
        java.lang.String str37 = stackAr30.toString();
        stackAr27.push((java.lang.Object) stackAr30);
        stackAr16.push((java.lang.Object) stackAr30);
        org.autotest.StackAr stackAr41 = new org.autotest.StackAr((int) (short) 0);
        boolean boolean42 = stackAr41.isEmpty();
        boolean boolean43 = stackAr41.isFull();
        org.autotest.StackAr stackAr45 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr47 = new org.autotest.StackAr((int) '4');
        java.lang.String str48 = stackAr47.toString();
        boolean boolean49 = stackAr45.equals((java.lang.Object) stackAr47);
        org.autotest.StackAr stackAr51 = new org.autotest.StackAr((int) '4');
        stackAr51.push((java.lang.Object) (-1L));
        java.lang.Object obj54 = stackAr51.pop();
        stackAr45.push((java.lang.Object) stackAr51);
        boolean boolean56 = stackAr41.equals((java.lang.Object) stackAr45);
        stackAr30.push((java.lang.Object) stackAr45);
        org.autotest.StackAr stackAr59 = new org.autotest.StackAr((int) (short) 0);
        boolean boolean60 = stackAr59.isFull();
        int int61 = stackAr59.size();
        int int62 = stackAr59.size();
        stackAr45.push((java.lang.Object) stackAr59);
        org.autotest.StackAr stackAr64 = new org.autotest.StackAr();
        boolean boolean65 = stackAr64.isEmpty();
        boolean boolean66 = stackAr45.equals((java.lang.Object) stackAr64);
        boolean boolean67 = stackAr45.isEmpty();
        stackAr3.push((java.lang.Object) boolean67);
        org.autotest.StackAr stackAr70 = new org.autotest.StackAr((int) (short) 100);
        stackAr3.push((java.lang.Object) stackAr70);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertEquals("'" + str10 + "' != '" + "[]" + "'", str10, "[]");
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "[]" + "'", str13, "[]");
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + false + "'", boolean22 == false);
        org.junit.Assert.assertEquals("'" + str28 + "' != '" + "[]" + "'", str28, "[]");
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + true + "'", boolean29 == true);
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + false + "'", boolean31 == false);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertEquals("'" + str34 + "' != '" + "[]" + "'", str34, "[]");
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
        org.junit.Assert.assertEquals("'" + str37 + "' != '" + "[]" + "'", str37, "[]");
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + true + "'", boolean42 == true);
        org.junit.Assert.assertTrue("'" + boolean43 + "' != '" + true + "'", boolean43 == true);
        org.junit.Assert.assertEquals("'" + str48 + "' != '" + "[]" + "'", str48, "[]");
        org.junit.Assert.assertTrue("'" + boolean49 + "' != '" + true + "'", boolean49 == true);
        org.junit.Assert.assertEquals("'" + obj54 + "' != '" + (-1L) + "'", obj54, (-1L));
        org.junit.Assert.assertTrue("'" + boolean56 + "' != '" + false + "'", boolean56 == false);
        org.junit.Assert.assertTrue("'" + boolean60 + "' != '" + true + "'", boolean60 == true);
        org.junit.Assert.assertTrue("'" + int61 + "' != '" + 0 + "'", int61 == 0);
        org.junit.Assert.assertTrue("'" + int62 + "' != '" + 0 + "'", int62 == 0);
        org.junit.Assert.assertTrue("'" + boolean65 + "' != '" + true + "'", boolean65 == true);
        org.junit.Assert.assertTrue("'" + boolean66 + "' != '" + false + "'", boolean66 == false);
        org.junit.Assert.assertTrue("'" + boolean67 + "' != '" + false + "'", boolean67 == false);
    }

    @Test
    public void test0234() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0234");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) 'a');
        boolean boolean3 = stackAr1.equals((java.lang.Object) (-1.0f));
        boolean boolean5 = stackAr1.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr6 = new org.autotest.StackAr();
        boolean boolean7 = stackAr6.isFull();
        stackAr1.push((java.lang.Object) stackAr6);
        org.autotest.StackAr stackAr10 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr12 = new org.autotest.StackAr((int) '4');
        java.lang.String str13 = stackAr12.toString();
        boolean boolean14 = stackAr10.equals((java.lang.Object) stackAr12);
        org.autotest.StackAr stackAr15 = new org.autotest.StackAr();
        boolean boolean16 = stackAr15.isFull();
        boolean boolean18 = stackAr15.equals((java.lang.Object) (byte) -1);
        java.lang.String str19 = stackAr15.toString();
        boolean boolean21 = stackAr15.equals((java.lang.Object) 10L);
        java.lang.String str22 = stackAr15.toString();
        stackAr12.push((java.lang.Object) stackAr15);
        stackAr1.push((java.lang.Object) stackAr15);
        org.autotest.StackAr stackAr26 = new org.autotest.StackAr((int) (short) 0);
        boolean boolean27 = stackAr26.isEmpty();
        boolean boolean28 = stackAr26.isFull();
        org.autotest.StackAr stackAr30 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr32 = new org.autotest.StackAr((int) '4');
        java.lang.String str33 = stackAr32.toString();
        boolean boolean34 = stackAr30.equals((java.lang.Object) stackAr32);
        org.autotest.StackAr stackAr36 = new org.autotest.StackAr((int) '4');
        stackAr36.push((java.lang.Object) (-1L));
        java.lang.Object obj39 = stackAr36.pop();
        stackAr30.push((java.lang.Object) stackAr36);
        boolean boolean41 = stackAr26.equals((java.lang.Object) stackAr30);
        stackAr15.push((java.lang.Object) stackAr30);
        org.autotest.StackAr stackAr44 = new org.autotest.StackAr((int) (short) 0);
        boolean boolean45 = stackAr44.isFull();
        int int46 = stackAr44.size();
        int int47 = stackAr44.size();
        stackAr30.push((java.lang.Object) stackAr44);
        org.autotest.StackAr stackAr49 = new org.autotest.StackAr();
        boolean boolean50 = stackAr49.isEmpty();
        boolean boolean51 = stackAr30.equals((java.lang.Object) stackAr49);
        org.autotest.StackAr stackAr53 = new org.autotest.StackAr((int) 'a');
        boolean boolean55 = stackAr53.equals((java.lang.Object) (-1.0f));
        boolean boolean57 = stackAr53.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr58 = new org.autotest.StackAr();
        boolean boolean59 = stackAr58.isFull();
        stackAr53.push((java.lang.Object) stackAr58);
        org.autotest.StackAr stackAr62 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr64 = new org.autotest.StackAr((int) '4');
        java.lang.String str65 = stackAr64.toString();
        boolean boolean66 = stackAr62.equals((java.lang.Object) stackAr64);
        org.autotest.StackAr stackAr67 = new org.autotest.StackAr();
        boolean boolean68 = stackAr67.isFull();
        boolean boolean70 = stackAr67.equals((java.lang.Object) (byte) -1);
        java.lang.String str71 = stackAr67.toString();
        boolean boolean73 = stackAr67.equals((java.lang.Object) 10L);
        java.lang.String str74 = stackAr67.toString();
        stackAr64.push((java.lang.Object) stackAr67);
        stackAr53.push((java.lang.Object) stackAr67);
        java.lang.Object obj77 = stackAr53.pop();
        boolean boolean78 = stackAr49.equals((java.lang.Object) stackAr53);
        boolean boolean79 = stackAr49.isEmpty();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj80 = stackAr49.top();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "[]" + "'", str13, "[]");
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + true + "'", boolean14 == true);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertEquals("'" + str19 + "' != '" + "[]" + "'", str19, "[]");
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + false + "'", boolean21 == false);
        org.junit.Assert.assertEquals("'" + str22 + "' != '" + "[]" + "'", str22, "[]");
        org.junit.Assert.assertTrue("'" + boolean27 + "' != '" + true + "'", boolean27 == true);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + true + "'", boolean28 == true);
        org.junit.Assert.assertEquals("'" + str33 + "' != '" + "[]" + "'", str33, "[]");
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + true + "'", boolean34 == true);
        org.junit.Assert.assertEquals("'" + obj39 + "' != '" + (-1L) + "'", obj39, (-1L));
        org.junit.Assert.assertTrue("'" + boolean41 + "' != '" + false + "'", boolean41 == false);
        org.junit.Assert.assertTrue("'" + boolean45 + "' != '" + true + "'", boolean45 == true);
        org.junit.Assert.assertTrue("'" + int46 + "' != '" + 0 + "'", int46 == 0);
        org.junit.Assert.assertTrue("'" + int47 + "' != '" + 0 + "'", int47 == 0);
        org.junit.Assert.assertTrue("'" + boolean50 + "' != '" + true + "'", boolean50 == true);
        org.junit.Assert.assertTrue("'" + boolean51 + "' != '" + false + "'", boolean51 == false);
        org.junit.Assert.assertTrue("'" + boolean55 + "' != '" + false + "'", boolean55 == false);
        org.junit.Assert.assertTrue("'" + boolean57 + "' != '" + false + "'", boolean57 == false);
        org.junit.Assert.assertTrue("'" + boolean59 + "' != '" + false + "'", boolean59 == false);
        org.junit.Assert.assertEquals("'" + str65 + "' != '" + "[]" + "'", str65, "[]");
        org.junit.Assert.assertTrue("'" + boolean66 + "' != '" + true + "'", boolean66 == true);
        org.junit.Assert.assertTrue("'" + boolean68 + "' != '" + false + "'", boolean68 == false);
        org.junit.Assert.assertTrue("'" + boolean70 + "' != '" + false + "'", boolean70 == false);
        org.junit.Assert.assertEquals("'" + str71 + "' != '" + "[]" + "'", str71, "[]");
        org.junit.Assert.assertTrue("'" + boolean73 + "' != '" + false + "'", boolean73 == false);
        org.junit.Assert.assertEquals("'" + str74 + "' != '" + "[]" + "'", str74, "[]");
        org.junit.Assert.assertNotNull(obj77);
        org.junit.Assert.assertEquals(obj77.toString(), "[]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj77), "[]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj77), "[]");
        org.junit.Assert.assertTrue("'" + boolean78 + "' != '" + false + "'", boolean78 == false);
        org.junit.Assert.assertTrue("'" + boolean79 + "' != '" + true + "'", boolean79 == true);
    }

    @Test
    public void test0235() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0235");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        java.lang.String str4 = stackAr0.toString();
        boolean boolean5 = stackAr0.isEmpty();
        org.autotest.StackAr stackAr7 = new org.autotest.StackAr((int) 'a');
        boolean boolean9 = stackAr7.equals((java.lang.Object) (-1.0f));
        boolean boolean10 = stackAr7.isEmpty();
        boolean boolean11 = stackAr0.equals((java.lang.Object) stackAr7);
        boolean boolean12 = stackAr7.isFull();
        java.lang.String str13 = stackAr7.toString();
        java.lang.Class<?> wildcardClass14 = stackAr7.getClass();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + true + "'", boolean10 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean12 + "' != '" + false + "'", boolean12 == false);
        org.junit.Assert.assertEquals("'" + str13 + "' != '" + "[]" + "'", str13, "[]");
        org.junit.Assert.assertNotNull(wildcardClass14);
    }

    @Test
    public void test0236() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0236");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        org.autotest.StackAr stackAr3 = new org.autotest.StackAr();
        boolean boolean4 = stackAr3.isFull();
        boolean boolean6 = stackAr3.equals((java.lang.Object) (byte) -1);
        stackAr1.push((java.lang.Object) boolean6);
        org.autotest.StackAr stackAr9 = new org.autotest.StackAr((int) 'a');
        boolean boolean10 = stackAr9.isFull();
        boolean boolean11 = stackAr1.equals((java.lang.Object) boolean10);
        java.lang.Object obj12 = stackAr1.pop();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj13 = stackAr1.top();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertEquals("'" + obj12 + "' != '" + false + "'", obj12, false);
    }

    @Test
    public void test0237() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0237");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr3 = new org.autotest.StackAr((int) '4');
        java.lang.String str4 = stackAr3.toString();
        boolean boolean5 = stackAr1.equals((java.lang.Object) stackAr3);
        org.autotest.StackAr stackAr7 = new org.autotest.StackAr((int) (byte) 0);
        org.autotest.StackAr stackAr9 = new org.autotest.StackAr((int) 'a');
        boolean boolean11 = stackAr9.equals((java.lang.Object) (-1.0f));
        boolean boolean13 = stackAr9.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr14 = new org.autotest.StackAr();
        boolean boolean15 = stackAr14.isFull();
        stackAr9.push((java.lang.Object) stackAr14);
        org.autotest.StackAr stackAr18 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr20 = new org.autotest.StackAr((int) '4');
        java.lang.String str21 = stackAr20.toString();
        boolean boolean22 = stackAr18.equals((java.lang.Object) stackAr20);
        org.autotest.StackAr stackAr23 = new org.autotest.StackAr();
        boolean boolean24 = stackAr23.isFull();
        boolean boolean26 = stackAr23.equals((java.lang.Object) (byte) -1);
        java.lang.String str27 = stackAr23.toString();
        boolean boolean29 = stackAr23.equals((java.lang.Object) 10L);
        java.lang.String str30 = stackAr23.toString();
        stackAr20.push((java.lang.Object) stackAr23);
        stackAr9.push((java.lang.Object) stackAr23);
        java.lang.Object obj33 = stackAr9.pop();
        boolean boolean34 = stackAr7.equals((java.lang.Object) stackAr9);
        stackAr1.push((java.lang.Object) stackAr9);
        java.lang.Object obj36 = stackAr1.top();
        java.lang.Class<?> wildcardClass37 = stackAr1.getClass();
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + true + "'", boolean5 == true);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "[]" + "'", str21, "[]");
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertTrue("'" + boolean26 + "' != '" + false + "'", boolean26 == false);
        org.junit.Assert.assertEquals("'" + str27 + "' != '" + "[]" + "'", str27, "[]");
        org.junit.Assert.assertTrue("'" + boolean29 + "' != '" + false + "'", boolean29 == false);
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "[]" + "'", str30, "[]");
        org.junit.Assert.assertNotNull(obj33);
        org.junit.Assert.assertEquals(obj33.toString(), "[]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj33), "[]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj33), "[]");
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
        org.junit.Assert.assertNotNull(obj36);
        org.junit.Assert.assertEquals(obj36.toString(), "[[]]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj36), "[[]]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj36), "[[]]");
        org.junit.Assert.assertNotNull(wildcardClass37);
    }

    @Test
    public void test0238() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0238");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        java.lang.String str2 = stackAr1.toString();
        java.lang.String str3 = stackAr1.toString();
        org.autotest.StackAr stackAr4 = new org.autotest.StackAr();
        boolean boolean5 = stackAr4.isFull();
        boolean boolean7 = stackAr4.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr9 = new org.autotest.StackAr((int) '4');
        boolean boolean10 = stackAr4.equals((java.lang.Object) '4');
        org.autotest.StackAr stackAr12 = new org.autotest.StackAr((int) 'a');
        boolean boolean14 = stackAr12.equals((java.lang.Object) (-1.0f));
        boolean boolean16 = stackAr12.equals((java.lang.Object) "");
        boolean boolean17 = stackAr4.equals((java.lang.Object) "");
        boolean boolean18 = stackAr4.isFull();
        boolean boolean19 = stackAr1.equals((java.lang.Object) stackAr4);
        java.lang.Object obj20 = null;
        stackAr4.push(obj20);
        java.lang.Object obj22 = stackAr4.top();
        java.lang.String str23 = stackAr4.toString();
        int int24 = stackAr4.size();
        boolean boolean25 = stackAr4.isEmpty();
        org.autotest.StackAr stackAr27 = new org.autotest.StackAr((int) 'a');
        boolean boolean28 = stackAr27.isFull();
        org.autotest.StackAr stackAr29 = new org.autotest.StackAr();
        boolean boolean30 = stackAr29.isFull();
        boolean boolean32 = stackAr29.equals((java.lang.Object) (byte) -1);
        stackAr27.push((java.lang.Object) boolean32);
        boolean boolean34 = stackAr27.isEmpty();
        java.lang.Object obj35 = stackAr27.top();
        boolean boolean36 = stackAr27.isEmpty();
        stackAr4.push((java.lang.Object) boolean36);
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "[]" + "'", str2, "[]");
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[]" + "'", str3, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertNull(obj22);
        org.junit.Assert.assertEquals("'" + str23 + "' != '" + "[null]" + "'", str23, "[null]");
        org.junit.Assert.assertTrue("'" + int24 + "' != '" + 1 + "'", int24 == 1);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertTrue("'" + boolean28 + "' != '" + false + "'", boolean28 == false);
        org.junit.Assert.assertTrue("'" + boolean30 + "' != '" + false + "'", boolean30 == false);
        org.junit.Assert.assertTrue("'" + boolean32 + "' != '" + false + "'", boolean32 == false);
        org.junit.Assert.assertTrue("'" + boolean34 + "' != '" + false + "'", boolean34 == false);
        org.junit.Assert.assertEquals("'" + obj35 + "' != '" + false + "'", obj35, false);
        org.junit.Assert.assertTrue("'" + boolean36 + "' != '" + false + "'", boolean36 == false);
    }

    @Test
    public void test0239() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0239");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        java.lang.String str2 = stackAr1.toString();
        java.lang.String str3 = stackAr1.toString();
        org.autotest.StackAr stackAr4 = new org.autotest.StackAr();
        boolean boolean5 = stackAr4.isFull();
        boolean boolean7 = stackAr4.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr9 = new org.autotest.StackAr((int) '4');
        boolean boolean10 = stackAr4.equals((java.lang.Object) '4');
        org.autotest.StackAr stackAr12 = new org.autotest.StackAr((int) 'a');
        boolean boolean14 = stackAr12.equals((java.lang.Object) (-1.0f));
        boolean boolean16 = stackAr12.equals((java.lang.Object) "");
        boolean boolean17 = stackAr4.equals((java.lang.Object) "");
        boolean boolean18 = stackAr4.isFull();
        boolean boolean19 = stackAr1.equals((java.lang.Object) stackAr4);
        java.lang.Object obj20 = null;
        stackAr4.push(obj20);
        org.autotest.StackAr stackAr23 = new org.autotest.StackAr(0);
        boolean boolean24 = stackAr4.equals((java.lang.Object) 0);
        boolean boolean25 = stackAr4.isEmpty();
        org.autotest.StackAr stackAr27 = new org.autotest.StackAr((int) '4');
        org.autotest.StackAr stackAr29 = new org.autotest.StackAr((int) '4');
        java.lang.String str30 = stackAr29.toString();
        boolean boolean31 = stackAr27.equals((java.lang.Object) stackAr29);
        org.autotest.StackAr stackAr32 = new org.autotest.StackAr();
        boolean boolean33 = stackAr32.isFull();
        boolean boolean35 = stackAr32.equals((java.lang.Object) (byte) -1);
        java.lang.String str36 = stackAr32.toString();
        boolean boolean38 = stackAr32.equals((java.lang.Object) 10L);
        java.lang.String str39 = stackAr32.toString();
        stackAr29.push((java.lang.Object) stackAr32);
        stackAr4.push((java.lang.Object) stackAr32);
        boolean boolean42 = stackAr4.isEmpty();
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "[]" + "'", str2, "[]");
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[]" + "'", str3, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertTrue("'" + boolean24 + "' != '" + false + "'", boolean24 == false);
        org.junit.Assert.assertTrue("'" + boolean25 + "' != '" + false + "'", boolean25 == false);
        org.junit.Assert.assertEquals("'" + str30 + "' != '" + "[]" + "'", str30, "[]");
        org.junit.Assert.assertTrue("'" + boolean31 + "' != '" + true + "'", boolean31 == true);
        org.junit.Assert.assertTrue("'" + boolean33 + "' != '" + false + "'", boolean33 == false);
        org.junit.Assert.assertTrue("'" + boolean35 + "' != '" + false + "'", boolean35 == false);
        org.junit.Assert.assertEquals("'" + str36 + "' != '" + "[]" + "'", str36, "[]");
        org.junit.Assert.assertTrue("'" + boolean38 + "' != '" + false + "'", boolean38 == false);
        org.junit.Assert.assertEquals("'" + str39 + "' != '" + "[]" + "'", str39, "[]");
        org.junit.Assert.assertTrue("'" + boolean42 + "' != '" + false + "'", boolean42 == false);
    }

    @Test
    public void test0240() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0240");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) (short) 0);
        boolean boolean2 = stackAr1.isEmpty();
        int int3 = stackAr1.size();
        org.autotest.StackAr stackAr5 = new org.autotest.StackAr((int) 'a');
        boolean boolean7 = stackAr5.equals((java.lang.Object) (-1.0f));
        int int8 = stackAr5.size();
        boolean boolean10 = stackAr5.equals((java.lang.Object) (byte) 100);
        boolean boolean11 = stackAr5.isFull();
        int int12 = stackAr5.size();
        boolean boolean13 = stackAr1.equals((java.lang.Object) stackAr5);
        java.lang.Class<?> wildcardClass14 = stackAr5.getClass();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + int8 + "' != '" + 0 + "'", int8 == 0);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + int12 + "' != '" + 0 + "'", int12 == 0);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(wildcardClass14);
    }

    @Test
    public void test0241() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0241");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr();
        boolean boolean2 = stackAr1.isFull();
        boolean boolean4 = stackAr1.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr6 = new org.autotest.StackAr((int) '4');
        boolean boolean7 = stackAr1.equals((java.lang.Object) '4');
        boolean boolean8 = stackAr0.equals((java.lang.Object) '4');
        int int9 = stackAr0.size();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj10 = stackAr0.top();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean4 + "' != '" + false + "'", boolean4 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + int9 + "' != '" + 0 + "'", int9 == 0);
    }

    @Test
    public void test0242() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0242");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        stackAr0.push((java.lang.Object) 0.0f);
        java.lang.String str3 = stackAr0.toString();
        org.autotest.StackAr stackAr5 = new org.autotest.StackAr((int) 'a');
        boolean boolean7 = stackAr5.equals((java.lang.Object) (-1.0f));
        boolean boolean9 = stackAr5.equals((java.lang.Object) "");
        org.autotest.StackAr stackAr10 = new org.autotest.StackAr();
        boolean boolean11 = stackAr10.isFull();
        stackAr5.push((java.lang.Object) stackAr10);
        boolean boolean13 = stackAr5.isFull();
        java.lang.Object obj14 = stackAr5.pop();
        stackAr0.push((java.lang.Object) stackAr5);
        java.lang.Object obj16 = stackAr0.top();
        java.lang.Class<?> wildcardClass17 = stackAr0.getClass();
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[0.0]" + "'", str3, "[0.0]");
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean13 + "' != '" + false + "'", boolean13 == false);
        org.junit.Assert.assertNotNull(obj14);
        org.junit.Assert.assertEquals(obj14.toString(), "[]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj14), "[]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj14), "[]");
        org.junit.Assert.assertNotNull(obj16);
        org.junit.Assert.assertEquals(obj16.toString(), "[]");
        org.junit.Assert.assertEquals(java.lang.String.valueOf(obj16), "[]");
        org.junit.Assert.assertEquals(java.util.Objects.toString(obj16), "[]");
        org.junit.Assert.assertNotNull(wildcardClass17);
    }

    @Test
    public void test0243() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0243");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) 'a');
        boolean boolean2 = stackAr1.isFull();
        boolean boolean3 = stackAr1.isEmpty();
        int int4 = stackAr1.size();
        org.autotest.StackAr stackAr5 = new org.autotest.StackAr();
        boolean boolean6 = stackAr5.isFull();
        boolean boolean8 = stackAr5.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr10 = new org.autotest.StackAr((int) '4');
        boolean boolean11 = stackAr5.equals((java.lang.Object) '4');
        org.autotest.StackAr stackAr13 = new org.autotest.StackAr((int) 'a');
        boolean boolean15 = stackAr13.equals((java.lang.Object) (-1.0f));
        boolean boolean17 = stackAr13.equals((java.lang.Object) "");
        boolean boolean18 = stackAr5.equals((java.lang.Object) "");
        boolean boolean19 = stackAr5.isFull();
        boolean boolean20 = stackAr5.isFull();
        java.lang.String str21 = stackAr5.toString();
        stackAr1.push((java.lang.Object) stackAr5);
        boolean boolean23 = stackAr1.isFull();
        java.lang.Class<?> wildcardClass24 = stackAr1.getClass();
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + false + "'", boolean2 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + true + "'", boolean3 == true);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertTrue("'" + boolean8 + "' != '" + false + "'", boolean8 == false);
        org.junit.Assert.assertTrue("'" + boolean11 + "' != '" + false + "'", boolean11 == false);
        org.junit.Assert.assertTrue("'" + boolean15 + "' != '" + false + "'", boolean15 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertTrue("'" + boolean20 + "' != '" + false + "'", boolean20 == false);
        org.junit.Assert.assertEquals("'" + str21 + "' != '" + "[]" + "'", str21, "[]");
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + false + "'", boolean23 == false);
        org.junit.Assert.assertNotNull(wildcardClass24);
    }

    @Test
    public void test0244() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0244");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isEmpty();
        java.lang.Class<?> wildcardClass2 = stackAr0.getClass();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + true + "'", boolean1 == true);
        org.junit.Assert.assertNotNull(wildcardClass2);
    }

    @Test
    public void test0245() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0245");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        int int2 = stackAr1.size();
        java.lang.String str3 = stackAr1.toString();
        java.lang.String str4 = stackAr1.toString();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj5 = stackAr1.pop();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[]" + "'", str3, "[]");
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
    }

    @Test
    public void test0246() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0246");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        int int2 = stackAr1.size();
        java.lang.String str3 = stackAr1.toString();
        int int4 = stackAr1.size();
        boolean boolean5 = stackAr1.isFull();
        int int6 = stackAr1.size();
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[]" + "'", str3, "[]");
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + int6 + "' != '" + 0 + "'", int6 == 0);
    }

    @Test
    public void test0247() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0247");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) 'a');
        int int2 = stackAr1.size();
        int int3 = stackAr1.size();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj4 = stackAr1.pop();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + int2 + "' != '" + 0 + "'", int2 == 0);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
    }

    @Test
    public void test0248() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0248");
        org.autotest.StackAr stackAr0 = new org.autotest.StackAr();
        boolean boolean1 = stackAr0.isFull();
        boolean boolean3 = stackAr0.equals((java.lang.Object) (byte) -1);
        java.lang.String str4 = stackAr0.toString();
        boolean boolean6 = stackAr0.equals((java.lang.Object) 10L);
        java.lang.String str7 = stackAr0.toString();
        boolean boolean9 = stackAr0.equals((java.lang.Object) (byte) -1);
        java.lang.Class<?> wildcardClass10 = stackAr0.getClass();
        org.junit.Assert.assertTrue("'" + boolean1 + "' != '" + false + "'", boolean1 == false);
        org.junit.Assert.assertTrue("'" + boolean3 + "' != '" + false + "'", boolean3 == false);
        org.junit.Assert.assertEquals("'" + str4 + "' != '" + "[]" + "'", str4, "[]");
        org.junit.Assert.assertTrue("'" + boolean6 + "' != '" + false + "'", boolean6 == false);
        org.junit.Assert.assertEquals("'" + str7 + "' != '" + "[]" + "'", str7, "[]");
        org.junit.Assert.assertTrue("'" + boolean9 + "' != '" + false + "'", boolean9 == false);
        org.junit.Assert.assertNotNull(wildcardClass10);
    }

    @Test
    public void test0249() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0249");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) (short) 0);
        boolean boolean2 = stackAr1.isFull();
        int int3 = stackAr1.size();
        int int4 = stackAr1.size();
        int int5 = stackAr1.size();
        // The following exception was thrown during execution in test generation
        try {
            java.lang.Object obj6 = stackAr1.top();
            org.junit.Assert.fail("Expected exception of type java.lang.IllegalStateException; message: null");
        } catch (java.lang.IllegalStateException e) {
            // Expected exception.
        }
        org.junit.Assert.assertTrue("'" + boolean2 + "' != '" + true + "'", boolean2 == true);
        org.junit.Assert.assertTrue("'" + int3 + "' != '" + 0 + "'", int3 == 0);
        org.junit.Assert.assertTrue("'" + int4 + "' != '" + 0 + "'", int4 == 0);
        org.junit.Assert.assertTrue("'" + int5 + "' != '" + 0 + "'", int5 == 0);
    }

    @Test
    public void test0250() throws Throwable {
        if (debug)
            System.out.format("%n%s%n", "RegressionTest4.test0250");
        org.autotest.StackAr stackAr1 = new org.autotest.StackAr((int) '4');
        java.lang.String str2 = stackAr1.toString();
        java.lang.String str3 = stackAr1.toString();
        org.autotest.StackAr stackAr4 = new org.autotest.StackAr();
        boolean boolean5 = stackAr4.isFull();
        boolean boolean7 = stackAr4.equals((java.lang.Object) (byte) -1);
        org.autotest.StackAr stackAr9 = new org.autotest.StackAr((int) '4');
        boolean boolean10 = stackAr4.equals((java.lang.Object) '4');
        org.autotest.StackAr stackAr12 = new org.autotest.StackAr((int) 'a');
        boolean boolean14 = stackAr12.equals((java.lang.Object) (-1.0f));
        boolean boolean16 = stackAr12.equals((java.lang.Object) "");
        boolean boolean17 = stackAr4.equals((java.lang.Object) "");
        boolean boolean18 = stackAr4.isFull();
        boolean boolean19 = stackAr1.equals((java.lang.Object) stackAr4);
        int int20 = stackAr4.size();
        boolean boolean21 = stackAr4.isEmpty();
        boolean boolean22 = stackAr4.isEmpty();
        boolean boolean23 = stackAr4.isEmpty();
        java.lang.Class<?> wildcardClass24 = stackAr4.getClass();
        org.junit.Assert.assertEquals("'" + str2 + "' != '" + "[]" + "'", str2, "[]");
        org.junit.Assert.assertEquals("'" + str3 + "' != '" + "[]" + "'", str3, "[]");
        org.junit.Assert.assertTrue("'" + boolean5 + "' != '" + false + "'", boolean5 == false);
        org.junit.Assert.assertTrue("'" + boolean7 + "' != '" + false + "'", boolean7 == false);
        org.junit.Assert.assertTrue("'" + boolean10 + "' != '" + false + "'", boolean10 == false);
        org.junit.Assert.assertTrue("'" + boolean14 + "' != '" + false + "'", boolean14 == false);
        org.junit.Assert.assertTrue("'" + boolean16 + "' != '" + false + "'", boolean16 == false);
        org.junit.Assert.assertTrue("'" + boolean17 + "' != '" + false + "'", boolean17 == false);
        org.junit.Assert.assertTrue("'" + boolean18 + "' != '" + false + "'", boolean18 == false);
        org.junit.Assert.assertTrue("'" + boolean19 + "' != '" + false + "'", boolean19 == false);
        org.junit.Assert.assertTrue("'" + int20 + "' != '" + 0 + "'", int20 == 0);
        org.junit.Assert.assertTrue("'" + boolean21 + "' != '" + true + "'", boolean21 == true);
        org.junit.Assert.assertTrue("'" + boolean22 + "' != '" + true + "'", boolean22 == true);
        org.junit.Assert.assertTrue("'" + boolean23 + "' != '" + true + "'", boolean23 == true);
        org.junit.Assert.assertNotNull(wildcardClass24);
    }
}

