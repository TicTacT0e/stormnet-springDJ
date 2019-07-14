package app.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;

@Service
public class PdfService {

    public ByteArrayOutputStream generate(Object object) {
        ObjectMapper objectMapper = new ObjectMapper()
                .enable(SerializationFeature.INDENT_OUTPUT);
        ByteArrayOutputStream byteArrayOutputStream = null;
        try {
            String jsonObject = objectMapper.writeValueAsString(object);
            Document document = new Document();
            byteArrayOutputStream = new ByteArrayOutputStream();
            PdfWriter.getInstance(document, byteArrayOutputStream);
            document.open();
            document.add(new Paragraph(jsonObject));
            document.close();
        } catch (DocumentException | JsonProcessingException exception) {
            exception.printStackTrace();
        }
        if (byteArrayOutputStream == null) {
            throw new IllegalArgumentException();
        }
        return byteArrayOutputStream;
    }
}
