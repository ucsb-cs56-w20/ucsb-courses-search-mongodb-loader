package edu.ucsb.cs156.student;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class LuhnTest {

    @Test
    public void test_luhn_111111() {
        assertEquals(1, Luhn.checkDigit(111111));
    }

    @Test
    public void test_luhn_123456() {
        assertEquals(6, Luhn.checkDigit(123456));
    }

    @Test
    public void test_luhn_182734() {
        assertEquals(4, Luhn.checkDigit(182734));
    }

    @Test
    public void test_luhn_defaultConstructor() {
       Luhn l = new Luhn();
    }

}