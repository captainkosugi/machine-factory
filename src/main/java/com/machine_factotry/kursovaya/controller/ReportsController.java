package com.machine_factotry.kursovaya.controller;

import com.machine_factotry.kursovaya.service.ReportsService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.io.IOException;

@Controller
public class ReportsController {

    private final ReportsService reportsService;


    public ReportsController(ReportsService reportsService) {
        this.reportsService = reportsService;
    }

    @GetMapping("/reports")
    public String reports() {
        return "reports";
    }


    @GetMapping("/get-report")
    public ResponseEntity<ByteArrayResource> downloadFactoryReport() throws IOException {

       byte[] pdfBytes =  reportsService.generateManufacturingReport();

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=factory-report.pdf")
                .contentType(MediaType.APPLICATION_PDF)
                .contentLength(pdfBytes.length)
                .body(new ByteArrayResource(pdfBytes));
    }
}
