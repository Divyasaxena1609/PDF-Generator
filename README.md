# Invoice PDF Generator

## Overview
This Spring Boot application generates invoices in PDF format using Thymeleaf and iText. The application exposes a REST API that accepts invoice details as input and returns a downloadable PDF invoice.

## Features
- Accepts invoice details via REST API
- Generates PDF invoices using Thymeleaf and iText
- Stores PDFs locally for future reference
- Allows redownloading of previously generated invoices

## Technologies Used
- Java 7+
- Spring Boot
- Thymeleaf
- iText
- REST API
- Maven

## Installation
1. Clone the repository:
   
   git clone <repository-url>
   cd <project-folder>
  
2. Build the project:
   
   mvn clean install
   
3. Run the application:

   mvn spring-boot:run
   

## API Endpoints

### Generate Invoice
**Endpoint:**  
POST /api/invoice/generate

**Request Body:**
`json
{
  "seller": "ABC Corp",
  "sellerGstin": "29ABCDE1234F1Z5",
  "sellerAddress": "123 Main Street, City, State, 560001",
  "buyer": "XYZ Inc",
  "buyerGstin": "27XYZDE5678G1H3",
  "buyerAddress": "456 Market Road, City, State, 400001",
  "items": [
    {
      "name": "Product 1",
      "quantity": "2",
      "rate": 500.0,
      "amount": 1000.0
    },
    {
      "name": "Product 2",
      "quantity": "1",
      "rate": 750.0,
      "amount": 750.0
    }
  ]
}`


**Response:**
- Success: Returns a downloadable PDF invoice link
- Error: Returns an appropriate error message

## File Storage
- Generated PDF invoices are stored in the `temp/pdf_invoices/` directory.
- File names follow the format: `{seller}_{buyer}.pdf`

## Testing
You can test the API using Postman:
1. Set request type to `POST`
2. Use `http://localhost:8080/api/invoice/generate` as the URL
3. Provide the JSON request body as per the example above
4. Submit the request and download the generated PDF.
