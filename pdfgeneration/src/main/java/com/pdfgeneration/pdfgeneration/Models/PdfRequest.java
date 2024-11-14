package com.pdfgeneration.pdfgeneration.Models;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PdfRequest {
    private String title;
    private List<String> contents;
}
