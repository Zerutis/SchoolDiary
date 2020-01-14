package Dienynas;

public class Student {
    private String name_surname;
    public String[] attenddance;

    public Student(String name, String surname) {
        this.name_surname = name + " " + surname;
        this.attenddance = new String[30];
    }

    public String getName_surname() {
        return name_surname;
    }
}
