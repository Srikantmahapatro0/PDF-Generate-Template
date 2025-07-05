package com.pdfgeneration.pdfgeneration;

import com.pdfgeneration.pdfgeneration.Models.PdfRequest;
import com.pdfgeneration.pdfgeneration.Service.PdfService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;


@SpringBootTest
public class PDFServiceTest {
	@Autowired
	private PdfService pdfService;

	@Test
	public void testGenerateAndSavePDF() throws IOException {
		PdfRequest data = new PdfRequest();
		data.setTitle("Test PDF");
		data.setContents(List.of("Item 1", "Item 2", "Item 3"));

		byte[] pdfContent = pdfService.generatePDF(data);
		pdfService.savePDF(pdfContent, "Test PDF.pdf");
		byte[] loadedPDF = pdfService.loadPDF("generated_pdfs/Test PDF.pdf");
		assertTrue(Objects.equals(new String(pdfContent), new String(loadedPDF)));
	}
}
