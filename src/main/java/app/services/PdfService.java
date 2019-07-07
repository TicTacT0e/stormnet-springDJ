package app.services;


import app.entities.Role;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class PdfService {

    private static final String PATH_TO_DIRECTORY = "src/main/resources/pdfDocuments/";

    public static void main(String[] args) throws IOException {

        Document document = new Document();
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream(PATH_TO_DIRECTORY + "helloWorld.pdf"));

            document.open();
//            Chunk chunk = new Chunk("Hello world");
            Role role = new Role("21", "WEwe");
            ObjectMapper objectMapper = new ObjectMapper();

            String objectInJsonString = objectMapper.writeValueAsString(role);
            System.out.println(objectInJsonString);
            Font font = FontFactory.getFont(FontFactory.COURIER, 11, BaseColor.BLACK);
            Chunk chunk = new Chunk(objectInJsonString, font);
            document.add(chunk);
        } catch (DocumentException | FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            document.close();
        }
        

    }

}
