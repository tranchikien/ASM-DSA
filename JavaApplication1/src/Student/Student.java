package Student;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

class Student {

    private int id;
    private String name;
    private double mark;

    public Student(int id, String name, double mark) {
        this.id = id;
        this.name = name;
        this.mark = mark;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getMark() {
        return mark;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public String getRank() {
        if (mark < 5.0) {
            return "Fail";
        } else if (mark < 6.5) {
            return "Medium";
        } else if (mark < 7.5) {
            return "Good";
        } else if (mark < 9.0) {
            return "Very Good";
        } else {
            return "Excellent";
        }
    }

    @Override
    public String toString() {
        return "ID: " + id + ", Name: " + name + ", Mark: " + mark + ", Rank: " + getRank();
    }
}
