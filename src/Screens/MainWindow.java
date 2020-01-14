package Screens;

import Dienynas.Student;
import File.CsvFormat;
import File.ExcelFormat;
import File.PdfFormat;
import com.itextpdf.text.DocumentException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import Dienynas.Subject;

import javax.swing.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;

public class MainWindow extends AnchorPane {
    @FXML private TextField surnameField;
    @FXML private TextField nameField;
    @FXML private TextField subjectField;
    @FXML private Button addBtn;
    @FXML private Button addCourseBtn;
    @FXML private Button addGradeBtn;
    @FXML private Button saveBtn;
    @FXML private Button delBtn;
    @FXML private ComboBoxDesign comboGrade;
    @FXML private ComboBoxDesign comboStd;
    @FXML private ComboBoxDesign comboChart;
    @FXML private ComboBoxDesign comboSelect;
    @FXML private TableView<Student> tableView;

    Subject[] subject;
    String[] defaultCourse = { "C_Sharp","Java","Python" };
    ExcelFormat loadFile;
    PdfFormat saveFile;
    CsvFormat csvFormat;
    int delete = 0;
    int whichOne = 0;

    public MainWindow() {

        nameField = new TextField();
        surnameField = new TextField();
        subjectField = new TextField();
        addBtn = new Button();
        addCourseBtn = new Button();
        saveBtn = new Button();
        delBtn = new Button();
        addGradeBtn = new Button();
        comboGrade = new ComboGrade();
        comboStd = new ComboStd();
        comboChart = new ComboChart();
        comboSelect = new ComboSelect();
        tableView = new TableView<>();

        setId("AnchorPane");
        setPrefWidth(800.0);
        setPrefHeight(640.0);

        comboChart.setLayoutX(34.0);
        comboChart.setLayoutY(200.0);
        comboChart.setPromptText("Course");
        comboChart.setOnAction(this::loadTable);
        comboSelect.setLayoutX(400.0);
        comboSelect.setLayoutY(56.0);
        comboSelect.setPromptText("Course");
        //comboSelect.setOnAction(comboSelect.chooseAct(new ActionEvent()));
        comboStd.setLayoutX((400.0));
        comboStd.setLayoutY(200.0);
        comboStd.setPromptText("Student NR.");
      // comboChart.setOnAction(this::getStdNumber);
        comboGrade.setLayoutX((400.0));
        comboGrade.setLayoutY(150.0);
        comboGrade.setPromptText("Grade");

        tableView.setLayoutX(50.0);
        tableView.setLayoutY(290.0);
        tableView.setPrefHeight(300.0);
        tableView.setPrefWidth(700.0);

        addBtn.setLayoutX(261.0);
        addBtn.setLayoutY(56.0);
        addBtn.setMnemonicParsing(false);
        addBtn.setOnAction(this::addStudent);
        addBtn.setText("Add Students");
        addCourseBtn.setLayoutX(261.0);
        addCourseBtn.setLayoutY(121.0);
        addCourseBtn.setMnemonicParsing(false);
        addCourseBtn.setOnAction(this::addCourse);
        addCourseBtn.setText("Add Course");
        saveBtn.setLayoutX(600.0);
        saveBtn.setLayoutY(56.0);
        saveBtn.setMnemonicParsing(false);
        saveBtn.setOnAction(this::saveToFile);
        saveBtn.setText("Save to file");
        delBtn.setLayoutX(600.0);
        delBtn.setLayoutY(200.0);
        delBtn.setMnemonicParsing(false);
        //delBtn.setOnAction(this::deleteStd);
        delBtn.setText("Delete Student");
        addGradeBtn.setLayoutX(600.0);
        addGradeBtn.setLayoutY(150.0);
        addGradeBtn.setMnemonicParsing(false);
        addGradeBtn.setOnAction(this::addGrade);
        addGradeBtn.setText("Add Grade");

        nameField.setLayoutX(34.0);
        nameField.setLayoutY(36.0);
        nameField.setPromptText("name");
        surnameField.setLayoutX(34.0);
        surnameField.setLayoutY(78.0);
        surnameField.setPromptText("surname");
        subjectField.setLayoutX(34.0);
        subjectField.setLayoutY(121.0);
        subjectField.setPromptText("subject");

        getChildren().addAll(nameField, surnameField, subjectField);
        getChildren().addAll(addBtn, saveBtn, addCourseBtn, delBtn, addGradeBtn);
        getChildren().addAll(comboChart, comboSelect, comboStd, comboGrade);
        getChildren().addAll(tableView);

        subject = new Subject[5];
        loadFile = new ExcelFormat();
        saveFile = new PdfFormat();
        csvFormat = new CsvFormat();
        byDefault();
    }

    @FXML void addStudent(ActionEvent event) {
        if (!comboChart.getItems().isEmpty()) {
            String value = comboSelect.getValue();
            for (int i = 0; i < Subject.index; i++) {
                if (value == subject[i].getTitle()) {
                    subject[i].setStudent(new Student(nameField.getText(), surnameField.getText()));
                    break;
                }
            }
            JOptionPane.showMessageDialog(null, "Student added");
        }
    }

    @FXML void addCourse(ActionEvent event) {
        if (!subjectField.getText().isEmpty()) {
            subject[Subject.index] = new Subject(subjectField.getText());
            comboSelect.getItems().add(subject[Subject.index].getTitle());
            comboChart.getItems().add(subject[Subject.index].getTitle());


            Subject.incIndex();
            subjectField.clear();

            JOptionPane.showMessageDialog(null, "Course added");
        }
    }

    @FXML void loadTable(ActionEvent event) {
        tableView.getItems().clear();
        tableView.getColumns().clear();
        tableView.setEditable(true);

        TableColumn<Student, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setMinWidth(150.0);
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name_surname"));
        for (int i = 0; i < Subject.index; i++) {
            if (comboChart.getValue() == subject[i].getTitle()) {
                tableView.getItems().addAll(subject[i].getStudent());
                tableView.getColumns().addAll(nameColumn);
                for (int j = 0; j < Subject.idDate; j++) {
                    TableColumn<Student, String> dateColumn = new TableColumn<>(Integer.toString(j+1));
                    dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
                    dateColumn.setEditable(true);
                  // for(int l = 0; l  <subject[i].student.length; l++)
                    //    dateColumn.
                    //    tableView.getItems().addAll(subject[i].student[l].attenddance);
                    tableView.getColumns().add(dateColumn);

                }

            }
        }
    }

    void byDefault(){
        comboGrade.getItems().addAll("1","2","3","4","5","6","7","8","9","10");
        for(int i = 0; i < defaultCourse.length; i++){
            subject[Subject.index] = new Subject(defaultCourse[i]);
            subject[Subject.index] = loadFile.loadFromFile("C:/Users/rokas/Desktop/Java/Dienynas/src/accessories/" + defaultCourse[i] + ".xlsx",subject[Subject.index]);
            comboSelect.getItems().add(subject[Subject.index].getTitle());
            comboChart.getItems().add(subject[Subject.index].getTitle());
            Subject.incIndex();
        }
    }

    @FXML void getStdNumber(ActionEvent event) {
    //    delete = Integer.parseInt(comboStd.getValue());
    }

    @FXML void deleteStd(ActionEvent event){
      //  if (delete != 0){
        //    for (int i = delete; i < subject[whichOne].getIdStud(); i++){
       //         subject[whichOne].student[i] = subject[whichOne].student[i+1];
      //      }
       // }
    }

    public void updateChart(ActionEvent event){
        loadTable(event);
    }

    @FXML void addGrade(ActionEvent event){
        String grade = comboGrade.getValue();
        subject[1].setAttenddance(grade, 7);
        updateChart(event);
    }

    @FXML void saveToFile(ActionEvent event) {
        saveFile.saveToFile("Dienynas.pdf", subject);
        csvFormat.saveToFile("Dienynas.csv",subject);
    }
}
