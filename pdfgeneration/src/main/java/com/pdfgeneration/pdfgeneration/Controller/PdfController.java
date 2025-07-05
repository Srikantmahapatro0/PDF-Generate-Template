package com.pdfgeneration.pdfgeneration.Controller;

import com.pdfgeneration.pdfgeneration.Models.PdfRequest;
import com.pdfgeneration.pdfgeneration.Service.PdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {
    @Autowired
    private PdfService pdfService;

    @PostMapping("/generate")
    public ResponseEntity<String> generatePDF(@RequestBody PdfRequest data) throws IOException {
        String fileName = "generated_pdfs/" + data.getTitle() + ".pdf";
        byte[] pdfContent = pdfService.generatePDF(data);
        pdfService.savePDF(pdfContent, fileName);
        return ResponseEntity.ok("PDF generated and saved successfully");
    }

    @GetMapping("/download")
    public ResponseEntity<Resource> downloadPDF(@RequestParam String title) throws IOException {
        String fileName = "generated_pdfs/" + data.getTitle() + ".pdf";
        byte[] pdfContent = pdfService.loadPDF(fileName);
        if (pdfContent == null || pdfContent.length == 0) {
            return ResponseEntity.notFound().build();
        }

        ByteArrayResource resource = new ByteArrayResource(pdfContent);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + title + ".pdf\"")
                .body(resource);
    }
}
