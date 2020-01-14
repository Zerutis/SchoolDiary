package File;

import Dienynas.Subject;
import com.itextpdf.text.DocumentException;

import java.io.*;
import java.util.Scanner;

public class CsvFormat implements Format {

    @Override
    public Subject loadFromFile(String fileName, Subject subject) {
        File file = new File(fileName);
        try {
            Scanner inputStream = new Scanner((file));
            while (inputStream.hasNext()){
                String data = inputStream.next();
                System.out.println(data);
            }
            inputStream.close();
        }catch (FileNotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public void saveToFile(String fileName, Subject[] subjects) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufferedReader = new BufferedWriter(fileWriter);
            PrintWriter printWriter = new PrintWriter(bufferedReader);
            for(int i = 0; i < Subject.index; i++){
                printWriter.println(subjects[i].getTitle());
                for (int j = 0; j < subjects[i].getIdStud(); j++) {
                    printWriter.println(subjects[i].getStudentName(j) + ",");
                }
                printWriter.println();
            }
            printWriter.flush();
            printWriter.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
