package File;

import Dienynas.Student;
import Dienynas.Subject;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


import javax.swing.*;
import java.io.File;
import java.io.FileInputStream;
import java.util.Iterator;

public class ExcelFormat implements Format {

    @Override
    public Subject loadFromFile(String fileName, Subject subject) {
        try{
            File file = new File(fileName);
            FileInputStream fileInputStream = new FileInputStream(file);
            XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            Iterator<Row>rowIt = sheet.iterator();

            while (rowIt.hasNext()){
                Row row = rowIt.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                while(cellIterator.hasNext()){
                    Cell cell = cellIterator.next();
                    subject.setStudent(new Student(cell.toString() + " ", cellIterator.next().toString()));

                }
            }
            workbook.close();
            fileInputStream.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return subject;
    }


    @Override
    public void saveToFile(String fileName,Subject[] subjects) {
    }


}
