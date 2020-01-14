package File;

import Dienynas.Student;
import Dienynas.Subject;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.*;
import org.apache.xmlbeans.StringEnumAbstractBase;

import java.io.FileOutputStream;



public class PdfFormat implements Format {

    @Override
    public Subject loadFromFile(String fileName,Subject subject) {
       return subject;
    }

    @Override
    public void saveToFile(String fileName, Subject[] subjects) {
        try {
            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream("C:/Users/rokas/Desktop/Java/Dienynas/" + fileName ));
            document.open();

            PdfPTable table = new PdfPTable(31);
            for(int i = 0; i < Subject.index; i++) {
                PdfPCell cell = new PdfPCell(new Phrase(subjects[i].getTitle()));
                cell.setColspan(31);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                table.addCell(cell);
                cell.setRowspan(subjects[i].getIdStud());
                for(int j = 0; j <subjects[i].getIdStud(); j++){
                    cell = new PdfPCell(new Phrase(subjects[i].getStudentName(j)));
                    table.addCell(cell);
                    for(int l = 1; l < 31; l++) {
                        cell = new PdfPCell(new Phrase(Integer.toString(l)));
                        table.addCell(cell);
                    }
                }
            }
            document.add(table);

            Paragraph paragraph = new Paragraph();
            for(int i = 0; i < Subject.index; i++){
                paragraph.add(subjects[i].getTitle() + " students are: ");
                paragraph.setSpacingAfter(15.0f);
                for(int j = 0; j < subjects[i].getIdStud(); j++) {
                    paragraph.add(subjects[i].getStudentName(j) + ", ");
                }
                paragraph.add("\n");
            }
            document.add(paragraph);
            document.close();
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
