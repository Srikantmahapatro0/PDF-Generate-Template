package com.pdfgeneration.pdfgeneration.Service;

import com.pdfgeneration.pdfgeneration.Models.PdfRequest;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class PdfService {
    private static final String BASE_DIRECTORY = "generated_pdfs";

    private final TemplateEngine templateEngine;

    public PdfService(TemplateEngine templateEngine) throws IOException {
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

    public void savePDF(byte[] content, String fileName) throws IOException {
        Path dir = Paths.get(BASE_DIRECTORY);
        Files.createDirectories(dir);
        Path fullPath = dir.resolve(fileName);
        Files.write(fullPath, content);
    }

    public byte[] loadPDF(String fileName) throws IOException {
        Path path = Paths.get(BASE_DIRECTORY).resolve(fileName);
        if (Files.exists(path)) {
            return Files.readAllBytes(path);
        }
        return null;
    }
}
