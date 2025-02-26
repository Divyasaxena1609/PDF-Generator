package com.pdfgenerator.pdfgenerator.Service;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.pdfgenerator.pdfgenerator.Models.InvoiceRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.*;



@Service
public class pdfService {
    private static final String PDF_STORAGE_DIR = "temp/pdf_invoices/";

    private final TemplateEngine templateEngine;

    // Constructor injection
    public pdfService(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
        createStorageDirectory();
    }

    private void createStorageDirectory() {
        File directory = new File(PDF_STORAGE_DIR);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        checkPermissions(PDF_STORAGE_DIR); 
    }

    public static void checkPermissions(String dirPath) {
        File directory = new File(dirPath);

        if (!directory.exists()) {
            System.out.println("Directory does not exist: " + dirPath);
            if (directory.mkdirs()) {
                System.out.println("Directory created successfully: " + dirPath);
            } else {
                System.out.println("Failed to create directory: " + dirPath);
            }
        } else {
            System.out.println("Directory exists: " + dirPath);
        }

        // Check read and write permissions
        boolean canRead = directory.canRead();
        boolean canWrite = directory.canWrite();

        System.out.println("Can read: " + canRead);
        System.out.println("Can write: " + canWrite);
    }



    public String GenerateInvoiceRequest(InvoiceRequest request) throws IOException, DocumentException{
        String fileName = generateFileName(request);
        String filePath = PDF_STORAGE_DIR + fileName;

        File file = new File(filePath);

        if(!file.exists()){
            System.out.println("File does not exist. Creating new PDF.");
            createPdf(filePath , request);
        }else {
            System.out.println("File already exists: " + filePath);
        }
    
        return filePath;
    }

    public Resource GetInvoicePdf(String seller , String buyer) throws FileNotFoundException{
        String fileName = seller + "_" + buyer + ".pdf";
        File file = new File(PDF_STORAGE_DIR + fileName);
        
        System.out.println("Looking for file at: " + file.getAbsolutePath());


        if(!file.exists()){
            throw new FileNotFoundException("Pdf Not Found With Given Data");
        }

        return new FileSystemResource(file);
    }

    private String generateFileName(InvoiceRequest request){
        return request.getSeller() + "_" + request.getBuyer() + ".pdf";
    }

    private void createPdf(String filePath , InvoiceRequest request) throws IOException, DocumentException{


        if (request.getSeller() == null || request.getBuyer() == null || request.getItems().isEmpty()) {
            throw new IllegalArgumentException("Seller, Buyer, or Items cannot be null or empty.");
        }

        System.out.println("Seller: " + request.getSeller());
        System.out.println("sellerGstin: " + request.getSellerGstin());
        System.out.println("sellerAddress: " + request.getSellerAddress());

        System.out.println("Buyer: " + request.getBuyer());
        System.out.println("buyerGstin: " + request.getBuyerGstin());
        System.out.println("buyerAddress: " + request.getBuyerAddress());

        System.out.println("Items: " + request.getItems());

    
        Context context = new Context();

        context.setVariable("seller", request.getSeller());
        context.setVariable("sellerGstin", request.getSellerGstin());
        context.setVariable("sellerAddress", request.getSellerAddress());

        context.setVariable("buyer", request.getBuyer());
        context.setVariable("buyerGstin", request.getBuyerGstin());
        context.setVariable("buyerAddress", request.getBuyerAddress());

        context.setVariable("items", request.getItems());

        context.setVariable("invoice", request);  
    
        // Generate HTML content
        String htmlContent = templateEngine.process("invoice", context);
        System.out.println("Generated HTML Content: " + htmlContent); 
        try (FileOutputStream os = new FileOutputStream(filePath)) {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, os);
            document.open();
            XMLWorkerHelper.getInstance().parseXHtml(writer, document, new StringReader(htmlContent));
            document.close();
            System.out.println("PDF created successfully.");

        }
    
    }


}
