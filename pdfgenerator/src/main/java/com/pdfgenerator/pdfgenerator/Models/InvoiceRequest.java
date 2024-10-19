package com.pdfgenerator.pdfgenerator.Models;

import java.util.List;

import lombok.Data;

@Data
public class InvoiceRequest {
     private String seller;
     private String sellerGstin;
     private String sellerAddress;
     private String buyer;
     private String buyerGstin;
     private String buyerAddress;
     private List<Item> items;

     @Data
     private static class Item{
        private String name;
        private String quantity;
        private Double rate;
        private Double amount;
     }
}
