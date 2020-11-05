package edu.ucsb.cs156.student;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class StudentTest {

    @Test
    public void test_getFirst1() {
        Student s = new Student();
        assertEquals("Sample", s.getFirst());
    }

    @Test
    public void test_getLast1() {
        Student s = new Student();
        assertEquals("Student", s.getLast());
    }

    @Test
    public void test_getFirst2() {
        Student s = new Student("Chris", "Gaucho", 1234566, 0);
        assertEquals("Chris", s.getFirst());
    }

    @Test
    public void test_getLast2() {
        Student s = new Student("Chris", "Gaucho", 1234566, 0);
        assertEquals("Gaucho", s.getLast());
    }

    @Test
    public void test_getPerm1() {
        Student s = new Student();
        assertEquals(999999, s.getPerm());
    }

    @Test
    public void test_getUnits1() {
        Student s = new Student();
        assertEquals(0, s.getUnits());
    }

    @Test
    public void test_toString1() {
        Student s = new Student();
        String expected = "[first: Sample, last: Student, perm: 999999, units: 0]";
        assertEquals(expected, s.toString());
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_zeroPerm() {
        Student s = new Student("Test","Last", 0,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_negPerm() {
        Student s = new Student("Test","Last", -1,0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void test_constructor_tooBigPerm() {
        Student s = new Student("Test","Last", 10000000,0);
    }

    @Test
    public void test_constructor_perm_1_ok() {
        Student s = new Student("Test","Last", 1,0);
        assertEquals(1, s.getPerm());
    }

    @Test
    public void test_constructor_perm_9999996_ok() {
        Student s = new Student("Test", "Last",9999996,0);
        assertEquals(9999996, s.getPerm());
    }

    @Test
    public void test_validPerm_1111111() {
        assertTrue(Student.validPerm(1111111));
    }

    @Test
    public void test_validPerm_neg1_false() {
        assertFalse(Student.validPerm(-1));
    }

    @Test
    public void test_validPerm_0_false() {
        assertFalse(Student.validPerm(0));
    }

    @Test
    public void test_validPerm_1() {
        assertTrue(Student.validPerm(1));
    }

    @Test
    public void test_validPerm_9999996() {
        assertTrue(Student.validPerm(9999996));
    }

    @Test
    public void test_validPerm_999999() {
        assertTrue(Student.validPerm(999999));
    }

    @Test
    public void test_validPerm_1111119() {
        assertFalse(Student.validPerm(1111119));
    }

    @Test
    public void test_fromCSV_1() throws Exception {
        String csv = "Chris,Gaucho,1111111,0";
        Student s = null;
        s = Student.fromCSV(csv);
        assertEquals(s.getFirst(), "Chris");
        assertEquals(s.getLast(), "Gaucho");
        assertEquals(s.getPerm(), 1111111);
        assertEquals(s.getUnits(), 0);
    }

    @Test(expected=Student.InvalidCSVLineException.class)
    public void test_fromCSV_3() throws Student.InvalidCSVLineException, Student.InvalidPermException {
        String csv = "";
        Student s = null;
        s = Student.fromCSV(csv);
    }

    @Test(expected=Student.InvalidPermException.class)
    public void test_fromCSV_4() throws Student.InvalidCSVLineException, Student.InvalidPermException {
        String csv = "Chris,Gaucho,not-an-integer,0";
        Student s = null;
        s = Student.fromCSV(csv);
    }

    @Test(expected=Student.InvalidPermException.class)
    public void test_fromCSV_5() throws Student.InvalidCSVLineException, Student.InvalidPermException {
        String csv = "Chris,Gaucho,1111119,0";
        Student s = null;
        s = Student.fromCSV(csv);
    }

    @Test
    public void test_compareTo_1() {
        Student s1 = new Student("Test","Last",111111,0);
        Student s2 = new Student("Test","Last",222222,0);
        assertTrue(s1.compareTo(s2)<0);
    }

}
