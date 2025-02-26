# Invoice PDF Generator

## Overview
This is a Spring Boot application that generates PDF invoices based on user-provided data using Thymeleaf and iText. The application exposes a REST API where users can send invoice details, and the system generates a PDF accordingly.

## Features
- Accepts invoice details via a REST API
- Generates PDFs using Thymeleaf and iText
- Stores generated PDFs in a specified directory
- Allows re-downloading of previously generated PDFs

## Technologies Used
- Java (Spring Boot)
- Thymeleaf
- iText
- REST API

## API Endpoint
### Generate Invoice
**URL:** `/api/invoice/generate`

**Method:** `POST`

**Request Body (JSON format):**
```json
{
  "seller": "ABC Corp",
  "sellerGstin": "123456789",
  "sellerAddress": "123 Street, City, Country",
  "buyer": "XYZ Inc.",
  "buyerGstin": "987654321",
  "buyerAddress": "456 Avenue, City, Country",
  "items": [
    {
      "name": "Laptop",
      "quantity": "2",
      "rate": 50000.00,
      "amount": 100000.00
    },
    {
      "name": "Mouse",
      "quantity": "5",
      "rate": 500.00,
      "amount": 2500.00
    }
  ]
}
```

**Response:**
```json
{
  "message": "Invoice generated successfully",
  "pdfPath": "temp/pdf_invoices/ABCCorp_XYZInc.pdf"
}
```

## Folder Structure
```
Invoice-PDF-Generator/
│── src/
│   ├── main/
│   │   ├── java/com/example/invoice/
│   │   │   ├── controller/
│   │   │   │   ├── InvoiceController.java
│   │   │   ├── model/
│   │   │   │   ├── InvoiceRequest.java
│   │   │   ├── service/
│   │   │   │   ├── InvoiceService.java
│   │   │   ├── utils/
│   │   │   │   ├── PdfGenerator.java
│   │   ├── resources/
│   │   │   ├── templates/
│   │   │   │   ├── invoice_template.html
│   │   │   ├── application.properties
│── temp/
│   ├── pdf_invoices/
│── pom.xml
│── README.md
```

## How to Run
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/invoice-pdf-generator.git
   cd invoice-pdf-generator
   ```
2. Build the project:
   ```sh
   mvn clean install
   ```
3. Run the application:
   ```sh
   mvn spring-boot:run
   ```
4. Send a POST request to `http://localhost:8080/api/invoice/generate` with the JSON request body as described above.

## Output
- The generated PDF will be stored in the `temp/pdf_invoices/` folder.


