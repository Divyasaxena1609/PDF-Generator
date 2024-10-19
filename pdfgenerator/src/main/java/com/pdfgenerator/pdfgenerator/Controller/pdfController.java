package com.pdfgenerator.pdfgenerator.Controller;

/*import java.io.FileNotFoundException;
import java.io.IOException;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
//import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.pdfgenerator.pdfgenerator.Models.InvoiceRequest;
import com.pdfgenerator.pdfgenerator.Service.pdfService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping
public class pdfController {
    
    @Autowired
    pdfService service;
    
    @PostMapping("/generate")
    public ResponseEntity<?> generatePdf(@RequestBody InvoiceRequest request) throws IOException, DocumentException{
        String filePath = service.GenerateInvoiceRequest(request);
        return ResponseEntity.ok("PDF generated and saved at : " + filePath);
    }

    /*@GetMapping("/download")
    public ResponseEntity<Resource>downloadPdf(@RequestParam String seller, @RequestParam String buyer) throws IOException {
        Resource file = service.GetInvoicePdf(seller, buyer);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/download")
public ResponseEntity<?> downloadPdf(@RequestParam String seller, @RequestParam String buyer) {
    try {
        Resource pdf = service.GetInvoicePdf(seller, buyer);
        return ResponseEntity.ok(pdf);
    } catch (FileNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PDF Not Found: " + e.getMessage());
    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
    }
}

    
}*/



import java.io.FileNotFoundException;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.itextpdf.text.DocumentException;
import com.pdfgenerator.pdfgenerator.Models.InvoiceRequest;
import com.pdfgenerator.pdfgenerator.Service.pdfService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping
public class pdfController {

    @Autowired
    private pdfService service;

    // API to generate the PDF based on request body
    @PostMapping("/generate")
    public ResponseEntity<?> generatePdf(@RequestBody InvoiceRequest request) throws IOException, DocumentException {
        String filePath = service.GenerateInvoiceRequest(request);
        return ResponseEntity.ok("PDF generated and saved at: " + filePath);
    }

    // API to download the PDF by passing seller and buyer as query params
    @GetMapping("/download")
    public ResponseEntity<?> downloadPdf(@RequestParam String seller, @RequestParam String buyer) {
        try {
            // Service method should return the PDF file as a resource
            Resource pdf = service.GetInvoicePdf(seller, buyer);

            // Set response headers to force download
            return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + pdf.getFilename() + "\"")
                .body(pdf);

        } catch (FileNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PDF Not Found: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred: " + e.getMessage());
        }
    }
}

