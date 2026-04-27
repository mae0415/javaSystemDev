package bean;

import java.io.Serializable;
import java.util.List;

public class TestListStudent implements Serializable {

    private Student student;
    private List<Test> tests;

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}