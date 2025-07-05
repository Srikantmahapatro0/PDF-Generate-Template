package com.pdfgeneration.pdfgeneration.Service;

import com.pdfgeneration.pdfgeneration.Models.PdfRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.thymeleaf.TemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PdfService {
    private final TemplateEngine templateEngine;


  Path tempDir = Files.createTempDirectory("pdf-test");
String fileName = tempDir.resolve("Test_PDF.pdf").toString();
pdfService.savePDF(pdfContent, fileName);


    public PdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    public byte[] generatePDF(PdfRequest data) throws IOException {
        Context context = new Context();
        context.setVariable("title", data.getTitle());
        context.setVariable("contents", data.getContents());
        String htmlContent = templateEngine.process("pdfTemplate", context);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        ITextRenderer renderer = new ITextRenderer();
        renderer.setDocumentFromString(htmlContent);
        renderer.layout();
        renderer.createPDF(outputStream);

        return outputStream.toByteArray();
    }

    public void savePDF(byte[] pdfContent, String fileName) throws IOException {
        Path path = Paths.get(BASE_DIRECTORY);
        Files.createDirectories(path);
        Path fullPath = path.resolve(fileName);
        Files.write(fullPath, pdfContent);
    }

    public byte[] loadPDF(String fileName) throws IOException {
        Path path = Paths.get(BASE_DIRECTORY).resolve(fileName);
        if (Files.exists(path)) {
            return Files.readAllBytes(path);
        }
        return null;
    }
}
