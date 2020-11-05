package edu.ucsb.cs156.spring.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import edu.ucsb.cs156.student.Student;

import java.util.Objects;

/** Object for storing a single Student into a table
 * of Students in a relational database (SQL)
 */

@Entity
public class StudentEntity {

    /* generated id value; always use this */
    /* Wrapper class Long wraps long */

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    /* fields to store in database */

    @Column(nullable = false)
    private String first;

    @Column(nullable = false)
    private String last;

    @Column(nullable = false)
    private int perm;

    @Column(nullable = false)
    private int units;

    public StudentEntity() {
    }

    public StudentEntity(Long id, String first, String last, int perm, int units) {
        this.id = id;
        this.first = first;
        this.last = last;
        this.perm = perm;
        this.units = units;
    }

    public StudentEntity(Student s) {
        this.first = s.getFirst();
        this.last = s.getLast();
        this.perm = s.getPerm();
        this.units = s.getUnits();
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public StudentEntity id(Long id) {
        this.id = id;
        return this;
    }

    public StudentEntity first(String first) {
        this.first = first;
        return this;
    }

    public StudentEntity last(String last) {
        this.last = last;
        return this;
    }

    public StudentEntity perm(int perm) {
        this.perm = perm;
        return this;
    }

    public StudentEntity units(int units) {
        this.units = units;
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof StudentEntity)) {
            return false;
        }
        StudentEntity studentEntity = (StudentEntity) o;
        return Objects.equals(id, studentEntity.id) && Objects.equals(first, studentEntity.first)
                && Objects.equals(last, studentEntity.last) && perm == studentEntity.perm
                && units == studentEntity.units;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, first, last, perm, units);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", first='" + getFirst() + "'" + ", last='" + getLast() + "'"
                + ", perm='" + getPerm() + "'" + ", units='" + getUnits() + "'" + "}";
    }

}