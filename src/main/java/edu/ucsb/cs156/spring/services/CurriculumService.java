package edu.ucsb.cs156.spring.services;

public interface CurriculumService {
    public String getJSON(String subjectArea, String quarter, String courseLevel);
    public String getJSON(String instructor, String quarter);
    public String getJSON(String quarter);
    public String getGE(String college, String area,String quarter);
    public String getGE(String college, String area,String quarter, int startT);
    public String getGE(String college, String area,String quarter, String days);
    public String getCourse(String course, int quarter);
    public String getCSV(String subjectArea, String quarter, String courseLevel, String dept, String instructor, String course, String college, String areas);
    public String getFinalExam(String quarter, String enrollCode);
}