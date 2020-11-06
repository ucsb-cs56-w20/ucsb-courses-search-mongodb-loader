package edu.ucsb.cs156.spring.documents;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.mapping.Document;

import edu.ucsb.cs156.student.Student;

import java.util.Objects;

@Document(collection = "students")
public class StudentDocument {

    private String first;
    private String last;
    private int perm;
    private int units;
    
    public StudentDocument() {
    }

    public StudentDocument(String first, String last, int perm, int units) {
        this.first = first;
        this.last = last;
        this.perm = perm;
        this.units = units;
    }

    public StudentDocument(Student s) {
        this.first = s.getFirst();
        this.last = s.getLast();
        this.perm = s.getPerm();
        this.units = s.getUnits();
    }


    public String getFirst() {
        return this.first;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public String getLast() {
        return this.last;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public int getPerm() {
        return this.perm;
    }

    public void setPerm(int perm) {
        this.perm = perm;
    }

    public int getUnits() {
        return this.units;
    }

    public void setUnits(int units) {
        this.units = units;
    }

   
    public StudentDocument first(String first) {
        this.first = first;
        return this;
    }

    public StudentDocument last(String last) {
        this.last = last;
        return this;
    }

    public StudentDocument perm(int perm) {
        this.perm = perm;
        return this;
    }

    public StudentDocument units(int units) {
        this.units = units;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StudentDocument)) {
            return false;
        }
        StudentDocument studentDocument = (StudentDocument) o;
        return Objects.equals(first, studentDocument.first) && Objects.equals(last, studentDocument.last) && perm == studentDocument.perm && units == studentDocument.units;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, last, perm, units);
    }

    @Override
    public String toString() {
        return "{" +
            "first='" + getFirst() + "'" +
            ", last='" + getLast() + "'" +
            ", perm='" + getPerm() + "'" +
            ", units='" + getUnits() + "'" +
            "}";
    }


   
}