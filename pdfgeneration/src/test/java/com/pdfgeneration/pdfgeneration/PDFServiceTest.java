import com.pdfgeneration.pdfgeneration.Models.PdfRequest;
import com.pdfgeneration.pdfgeneration.Service.PdfService;
import org.junit.jupiter.api.Test;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.*;

public class PDFServiceTest {

    // Mocked TemplateEngine that doesn't need an actual template file
    class DummyTemplateEngine extends TemplateEngine {
        @Override
        public String process(String template, Context context) {
            return "<html><body><h1>" + context.getVariable("title") + "</h1><p>" +
                    context.getVariable("contents") + "</p></body></html>";
        }
    }

    @Test
    public void testGenerateAndSavePDF() throws Exception {
        PdfService pdfService = new PdfService(new DummyTemplateEngine());

        PdfRequest request = new PdfRequest();
        request.setTitle("Test PDF");
        request.setContents("This is a test PDF file.");

        byte[] bytes = pdfService.generatePDF(request);
        assertNotNull(bytes); // ✅ Prevent NPE

        Path outputFile = Paths.get("generated_pdfs/Test PDF.pdf");
        pdfService.savePDF(bytes, "Test PDF.pdf");

        assertTrue(Files.exists(outputFile));
        assertTrue(Files.size(outputFile) > 0);
    }
}
