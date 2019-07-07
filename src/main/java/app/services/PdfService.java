package app.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.pdf.PdfWriter;
import org.joda.time.DateTime;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Service
public class PdfService {

    private static final String PATH_TO_DIRECTORY = "src/main/resources/pdfDocuments/";

    public String generatePdfAndGetPathToFile(Object object) {
        Document document = new Document();
        String pathToFile = PATH_TO_DIRECTORY
                + object.getClass().getSimpleName()
                + "_" + DateTime.now().getMillis();
        try {
            PdfWriter.getInstance(document,
                    new FileOutputStream(pathToFile));
            document.open();
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonObject = objectMapper.writeValueAsString(object);
            Font font = FontFactory
                    .getFont(FontFactory.COURIER, 11, BaseColor.BLACK);
            Chunk chunk = new Chunk(jsonObject, font);
            document.add(chunk);
        } catch (FileNotFoundException | JsonProcessingException |
                DocumentException exception) {
            exception.printStackTrace();
        } finally {
            document.close();
        }
        return pathToFile;
    }
}
