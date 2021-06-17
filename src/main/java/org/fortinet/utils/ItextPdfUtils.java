package org.fortinet.utils;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.draw.DottedLineSeparator;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import org.fortinet.InvoiceGeneratorConstants;
import org.fortinet.model.Invoice;
import org.fortinet.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.List;

/**
 * The type Itext pdf utils.
 */
public class ItextPdfUtils {

    private static Font headingFont = new Font(Font.FontFamily.HELVETICA, 32,
            Font.BOLD);

    private static Font billToFont = new Font(Font.FontFamily.HELVETICA, 14,
            Font.BOLD);

    private static Font totalCostFont = new Font(Font.FontFamily.HELVETICA, 12,
            Font.BOLD);

    private static final Logger logger = LoggerFactory.getLogger(ItextPdfUtils.class);

    /**
     * Add empty line.
     *
     * @param paragraph the paragraph
     * @param number    the number
     */
    public static void addEmptyLine(Paragraph paragraph, int number) {
        logger.info("addEmptyLine() started...");
        for (int i = 0; i < number; i++) {
            paragraph.add(new Paragraph(" "));
        }
        logger.info("addEmptyLine() ended...");
    }

    /**
     * Add heading.
     *
     * @param document the document
     * @throws DocumentException the document exception
     * @throws IOException       the io exception
     */
    public static void addHeading(Document document) throws DocumentException, IOException {
        logger.info("addHeading() started...");
        Paragraph logo = new Paragraph();
        Image companyLogo = Image.getInstance(InvoiceGeneratorConstants.LOGO_FILE_NAME);
        companyLogo.setAlignment(Element.ALIGN_CENTER);
        logo.add(companyLogo);
        logo.setAlignment(Paragraph.ALIGN_CENTER);


        Paragraph heading = new Paragraph(InvoiceGeneratorConstants.INVOICE,headingFont);
        ItextPdfUtils.addEmptyLine(heading,2);
        heading.setAlignment(Paragraph.ALIGN_CENTER);
        document.add(logo);
        document.add(heading);
        logger.info("addHeading() ended...");
    }

    /**
     * Add company details.
     *
     * @param document the document
     * @param invoice  the invoice
     * @throws DocumentException the document exception
     * @throws IOException       the io exception
     */
    public static void addCompanyDetails(Document document, Invoice invoice) throws DocumentException, IOException {
        logger.info("addCompanyDetails() started...");
        Paragraph issuingAuthority = new Paragraph(InvoiceGeneratorConstants.ISSUING_COMPANY_DETAILS,billToFont);
        issuingAuthority.setAlignment(Paragraph.ALIGN_LEFT);
        Chunk linebreak = new Chunk(new DottedLineSeparator());
        Paragraph companyName = new Paragraph(invoice.getLetterHead().getCompany().getCompanyName());
        companyName.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph companyAddress = new Paragraph(invoice.getLetterHead().getCompany().getCompanyAddress());
        companyAddress.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph companyEmail= new Paragraph(invoice.getLetterHead().getCompany().getCompanyEmail());
        companyEmail.setAlignment(Paragraph.ALIGN_LEFT);
        Paragraph companyContact= new Paragraph(invoice.getLetterHead().getCompany().getCompanyContactNumber());
        companyContact.setAlignment(Paragraph.ALIGN_LEFT);
        addEmptyLine(companyContact,2);
        document.add(issuingAuthority);
        document.add(linebreak);
        document.add(companyName);
        document.add(companyAddress);
        document.add(companyEmail);
        document.add(companyContact);
        logger.info("addCompanyDetails() ended...");
    }

    /**
     * Add billing information.
     *
     * @param document the document
     * @param invoice  the invoice
     * @throws DocumentException the document exception
     */
    public static void addBillingInformation(Document document,Invoice invoice) throws DocumentException{
        logger.info("addBillingInformation() started...");
        Paragraph billTo = new Paragraph(InvoiceGeneratorConstants.BILL_TO,billToFont);
        billTo.setAlignment(Paragraph.ALIGN_LEFT);


        Chunk chunk = new Chunk(new VerticalPositionMark());
        Chunk linebreak = new Chunk(new DottedLineSeparator());

        Paragraph customerName = new Paragraph(invoice.getCustomer().getCustomerName());
        customerName.add(new Chunk(chunk));
        customerName.add(InvoiceGeneratorConstants.INVOICE_ID+invoice.getInvoiceId());

        Paragraph customerAddress = new Paragraph(invoice.getCustomer().getCustomerAddress());
        customerAddress.add(new Chunk(chunk));
        customerAddress.add(InvoiceGeneratorConstants.INVOICE_DATE+invoice.getInvoiceDate());

        Paragraph customerEmail = new Paragraph(invoice.getCustomer().getCustomerEmail());
        customerEmail.add(new Chunk(chunk));
        if(!StringUtils.isEmpty(invoice.getDueDate())){
            customerEmail.add(InvoiceGeneratorConstants.INVOICE_DUE_DATE+invoice.getDueDate());
        }

        Paragraph customerContact = new Paragraph(invoice.getCustomer().getCustomerContactNumber());

        ItextPdfUtils.addEmptyLine(customerContact,2);

        document.add(billTo);
        document.add(linebreak);
        document.add(customerName);
        document.add(customerAddress);
        document.add(customerEmail);
        document.add(customerContact);
        logger.info("addBillingInformation() ended...");
    }

    /**
     * Add item table.
     *
     * @param document the document
     * @param invoice  the invoice
     * @throws DocumentException the document exception
     */
    public static void addItemTable(Document document, Invoice invoice) throws DocumentException {
        logger.info("addItemTable() started...");
        PdfPTable table = new PdfPTable(4);
        table.setHorizontalAlignment(Element.ALIGN_LEFT);

        PdfPCell c1 = new PdfPCell(new Phrase(InvoiceGeneratorConstants.ITEM));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(InvoiceGeneratorConstants.QUANTITY));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(InvoiceGeneratorConstants.UNIT_PRICE));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);

        c1 = new PdfPCell(new Phrase(InvoiceGeneratorConstants.TOTAL_AMOUNT));
        c1.setHorizontalAlignment(Element.ALIGN_CENTER);
        table.addCell(c1);
        table.setHeaderRows(1);


        List<Item> itemList = invoice.getOrderDetails().getItems();

        for(Item item: itemList){
            double costOfCurrentItem = item.getItemCost() * item.getItemQuantity();
            table.addCell(item.getItemName());
            table.addCell(Integer.toString(item.getItemQuantity()));
            table.addCell(Double.toString(item.getItemCost()));
            table.addCell(Double.toString(costOfCurrentItem));
        }

        document.add(table);
        logger.info("addItemTable() ended...");
    }

    /**
     * Add total cost details.
     *
     * @param document the document
     * @param invoice  the invoice
     * @throws DocumentException the document exception
     */
    public static void addTotalCostDetails(Document document, Invoice invoice) throws DocumentException {
        logger.info("addTotalCostDetails() started...");
        Paragraph totalCost = new Paragraph(InvoiceGeneratorConstants.TOTAL_COST+ invoice.getOrderDetails().getOrderAmount(),totalCostFont);
        totalCost.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(totalCost);


        Paragraph paymentStatus;
        Paragraph balanceDue;
        if(invoice.getOrderDetails().isPaid()){
            paymentStatus = new Paragraph(InvoiceGeneratorConstants.PAYMENT_STATUS + InvoiceGeneratorConstants.PAID, totalCostFont);
            balanceDue = new Paragraph(InvoiceGeneratorConstants.BALANCE_DUE + InvoiceGeneratorConstants.ZERO, totalCostFont);
        }
        else{
            paymentStatus = new Paragraph(InvoiceGeneratorConstants.PAYMENT_STATUS + InvoiceGeneratorConstants.NOT_PAID, totalCostFont);
            balanceDue = new Paragraph(InvoiceGeneratorConstants.BALANCE_DUE + invoice.getOrderDetails().getOrderAmount(), totalCostFont);
        }
        paymentStatus.setAlignment(Paragraph.ALIGN_RIGHT);
        balanceDue.setAlignment(Paragraph.ALIGN_RIGHT);
        document.add(paymentStatus);
        document.add(balanceDue);
        logger.info("addTotalCostDetails() ended...");
    }
}
