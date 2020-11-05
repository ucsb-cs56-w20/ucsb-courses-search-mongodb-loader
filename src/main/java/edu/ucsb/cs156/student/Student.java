package edu.ucsb.cs156.student;

import java.util.Comparator;

import java.util.ArrayList;
import java.util.List;

/**
 *  POJO = Plain Old Java Object for Student
 * 
 * */

public class Student implements Comparable<Student> {
    private String first;
    private String last;
    private int perm;
    private int units;

    public int compareTo(Student o) {
        // compare this with o and return <0 ==0 >0
        return this.perm - o.perm;
    }

    public Student() {
        first = "Sample";
        last = "Student";
        perm = 999999;
        units = 0;
    }

    public Student(String first, String last, int perm, int units) {

        if (!validPerm(perm)) {
            throw new IllegalArgumentException("Unacceptable value for perm: " + perm);
        }

        this.first = first;
        this.last = last;
        this.perm = perm;
        this.units = units;
    }

    public String getFirst() {
        return this.first;
    }

    public String getLast() {
        return this.last;
    }

    public int getPerm() {
        return this.perm;
    }

    public int getUnits() {
        return this.units;
    }

    @Override
    public String toString() {
        return "[first: " + this.first +  ", last: " + this.last + ", perm: " + this.perm + ", units: " + this.units+ "]";
    }

    public static boolean validPerm(int perm) {
        if (perm < 1 || perm > 9999996) {
            return false;
        }
        if (perm <= 999999)
           return true;
        int lastDigit = perm % 10;
        int firstSix = perm / 10;
        return lastDigit == Luhn.checkDigit(firstSix);
    }

    public static class InvalidCSVLineException extends Exception {
        private static final long serialVersionUID = 1L;
        public InvalidCSVLineException(String msg) {
            super(msg);
        }
    }

    public static class InvalidPermException extends Exception {
        private static final long serialVersionUID = 1L;
        public InvalidPermException(String msg) { 
            super (msg);
        }
    }

    public static Student fromCSV(String csv) throws InvalidCSVLineException, InvalidPermException {
            String [] parts = csv.split(",");

            if (parts.length != 4) {
                throw new InvalidCSVLineException("Invalid: "+csv);
            }

            String first=parts[0];
            String last=parts[1];
            int perm = 0;

            try {
                perm=Integer.parseInt(parts[2]);
            } catch (NumberFormatException nfe) {
                throw new InvalidPermException("Invalid: " + parts[2]);
            }

            if (!validPerm(perm)) {
                throw new InvalidPermException("Invalid: " + perm);
            }

            int units = 0;
            try {
                units=Integer.parseInt(parts[3]);
            } catch (NumberFormatException nfe) {
                throw new InvalidCSVLineException("Invalid Units: " + parts[3]);
            }

            return new Student(first, last, perm, units);
    }

    /**
     * This is an inner class.
     */
    public static class FirstNameComparator implements Comparator<Student> {

        @Override
        public int compare(Student s1, Student s2) {
            return s1.first.compareToIgnoreCase(s2.first);
        }

    }

    /**
     * Print students one per line
     * @param students
     */
    public static void listStudents(List<Student> students) {
        for (Student s: students) {
            System.out.println(s);
        }
    }

    public static ArrayList<Student> linesToStudents(List<String> lines) {

        ArrayList<Student> result = new ArrayList<Student>();

        for (String line: lines) {
            try {
                Student s = Student.fromCSV(line);
                result.add(s);
            } catch (Student.InvalidCSVLineException e1) {
                System.err.println("Invalid line ignored: " + line);
            } catch (Student.InvalidPermException e2) {
                System.err.println("Line with invalid perm ignored: " + line);
            }
        }
        return result;
    }




}
