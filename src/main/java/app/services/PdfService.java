package app.services;


import app.entities.Role;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class PdfService {

    public static void main(String[] args) {

        Document document = new Document();
        try {
            PdfWriter.getInstance(document, new FileOutputStream("helloWorld.pdf"));

            document.open();
//            Chunk chunk = new Chunk("Hello world");
            Role role = new Role("21", "WEwe");
            document.add(role);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }

    }

}
