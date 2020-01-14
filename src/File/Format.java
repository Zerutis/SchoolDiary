package File;

import Dienynas.Subject;
import com.itextpdf.text.DocumentException;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Format {
    Subject loadFromFile(String fileName, Subject subject) throws IOException;

    void saveToFile(String fileName, Subject[] subjects) throws FileNotFoundException, DocumentException;

}
