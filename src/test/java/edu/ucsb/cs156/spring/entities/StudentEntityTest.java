package edu.ucsb.cs156.spring.entities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

public class StudentEntityTest {
  @Test
  public void test_default_constructor_1() throws Exception {
    StudentEntity s = new StudentEntity(); 
    assertEquals(null,s.getFirst());
    assertEquals(null,s.getLast());
    assertEquals(0,s.getPerm());
    assertEquals(0,s.getUnits());
  }

  @Test
  public void test_constructor_1() throws Exception {
    StudentEntity s = new StudentEntity(null,"Chris","Gaucho",123456,0); 
    assertEquals("Chris",s.getFirst());
    assertEquals("Gaucho",s.getLast());
    assertEquals(123456,s.getPerm());
    assertEquals(0,s.getUnits());
  }

}
