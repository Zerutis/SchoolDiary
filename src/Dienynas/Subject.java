package Dienynas;

public class Subject {
    private String title;

    private int date;
    public Student[] student;
    private int idStud = 0;

    public static int idDate = 30;
    public static int index = 0;
    public static void incIndex(){ index++; }
    public void incIdStud(){ idStud++; }
    public void decIdStud(){ idStud--; }


    public Subject(String title) {
        this.title = title;
        this.student = new Student[15];
    }

    public String getTitle() {
        return title;
    }

    public String getStudentName(int index){
        return student[index].getName_surname();
    }

    public Student[] getStudent(){ return student;}

    public int getIdStud(){
        return idStud;
    }

    public void setStudent(Student student){
        this.student[idStud] = student;
        incIdStud();
    }

    public void setAttenddance(String grade, int index){
        student[index].attenddance[index] = grade;
    }


}
