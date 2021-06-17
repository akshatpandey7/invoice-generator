package org.fortinet.service;

import com.itextpdf.text.DocumentException;
import org.fortinet.model.Invoice;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface InvoiceGenerator {

    void generateInvoice(Invoice invoice) throws IOException, DocumentException, InterruptedException;

    boolean validateInvoice(Invoice invoice) throws Exception;

    Invoice mapJsonToObject() throws IOException;

    String encryptInvoice(String invoiceFile,String invoiceFileName) throws IOException, DocumentException;
}
