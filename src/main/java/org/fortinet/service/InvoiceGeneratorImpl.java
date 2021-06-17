package org.fortinet.service;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import org.fortinet.InvoiceGeneratorConstants;
import org.fortinet.model.Invoice;
import org.fortinet.utils.ItextPdfUtils;
import org.fortinet.validations.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;


/**
 * The Invoice generator Implementation Class.
 */
@Service
public class InvoiceGeneratorImpl implements InvoiceGenerator{

    /**
     * The invoiceDetailsFile.
     */
    @Value("${invoice.details}")
    private String invoiceDetailsFile;

    /**
     * The invoiceDetailsFile.
     */
    @Value("${invoice.password}")
    private String invoiceFilePassword;

    /**
     * The itemValidator.
     */
    @Autowired
    private ItemValidator itemValidator;

    /**
     * The orderDetailsValidator.
     */
    @Autowired
    private OrderDetailsValidator orderDetailsValidator;

    /**
     * The emailValidator.
     */
    @Autowired
    private EmailValidator emailValidator;

    /**
     * The invoiceValidator.
     */
    @Autowired
    private InvoiceValidator invoiceValidator;

    /**
     * The companyValidator.
     */
    @Autowired
    private CompanyValidator companyValidator;

    /**
     * The letterHeadValidator.
     */
    @Autowired
    private LetterHeadValidator letterHeadValidator;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceGenerator.class);

    /**
     * generate invoice.
     *
     * @param invoice the invoice
     */
    @Override
    public void generateInvoice(Invoice invoice) throws IOException, DocumentException {
        logger.info("generateInvoice() started...");
        PdfWriter pdfWriter = null;
        FileOutputStream fileOutputStream = null;
        String generatedInvoiceFileName = null;

        try{
            generatedInvoiceFileName = InvoiceGeneratorConstants.INVOICE + invoice.getInvoiceId() + InvoiceGeneratorConstants.PDF_EXTENSION;
            Document pdfDocument = new Document();
            fileOutputStream = new FileOutputStream(generatedInvoiceFileName);
            pdfWriter = PdfWriter.getInstance(pdfDocument,fileOutputStream);
            pdfDocument.open();
            ItextPdfUtils.addHeading(pdfDocument);
            ItextPdfUtils.addCompanyDetails(pdfDocument,invoice);
            ItextPdfUtils.addBillingInformation(pdfDocument,invoice);
            ItextPdfUtils.addItemTable(pdfDocument,invoice);
            ItextPdfUtils.addTotalCostDetails(pdfDocument,invoice);
            pdfDocument.close();
            encryptInvoice(generatedInvoiceFileName,InvoiceGeneratorConstants.INVOICE + invoice.getInvoiceId());
        }
        catch (IOException ioException){
            throw ioException;
        }
        catch (DocumentException documentException){
            throw documentException;
        }
        finally {
            pdfWriter.close();
            fileOutputStream.close();
        }
        logger.info("generateInvoice() ended...");
    }

    /**
     * validate the invoice.
     *
     * @param invoice the invoice
     */
    @Override
    public boolean validateInvoice(Invoice invoice){
        logger.info("validateInvoice() started...");
        try {
            invoiceValidator.validateInvoice(invoice);

            letterHeadValidator.validateLetterHead(invoice);

            companyValidator.validateCompanyDetails(invoice);

            //OrderDetails validations
            orderDetailsValidator.isOrderDetailsEmpty(invoice);

            //item validations
            itemValidator.isItemListEmpty(invoice);
            itemValidator.checkItemQuantity(invoice.getOrderDetails().getItems());
            itemValidator.isItemCostEmpty(invoice.getOrderDetails().getItems());

            orderDetailsValidator.validateOrderAmount(invoice.getOrderDetails());

            if(!ObjectUtils.isEmpty(invoice.getLetterHead().getCompany())){
                emailValidator.isValidEmail(invoice.getLetterHead().getCompany().getCompanyEmail());
            }

            if(!ObjectUtils.isEmpty(invoice.getCustomer())){
                emailValidator.isValidEmail(invoice.getCustomer().getCustomerEmail());
            }

        }
        catch (Exception e){
            logger.error("Exception occured in validateInvoice()");
            e.printStackTrace();
        }
        logger.info("validateInvoice() ended...");
        return true;
    }

    /**
     * map the input json to a POJO.
     *
     */
    @Override
    public Invoice mapJsonToObject() throws IOException {
        logger.info("mapJsonToObject() started...");
        Invoice invoice;
        try{
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(MapperFeature.ACCEPT_CASE_INSENSITIVE_PROPERTIES, true);

            invoice = objectMapper.readValue(Paths.get(invoiceDetailsFile).toFile(),Invoice.class);

            logger.info(invoice.toString());
        }
        catch (IOException ioException){
            logger.error("Exception occured in encryptInvoice()");
            throw ioException;
        }
        logger.info("mapJsonToObject() ended...");
        return invoice;
    }

    /**
     * generate invoice.
     *
     * @param invoiceFile the invoice file
     * @param invoiceFileName the invoice file Name
     */
    @Override
    public String encryptInvoice(String invoiceFile,String invoiceFileName) throws IOException, DocumentException {
        logger.info("encryptInvoice() started...");
        PdfReader pdfReader = new PdfReader(invoiceFile);
        try{
            String encryptedInvoiceFile = InvoiceGeneratorConstants.ENCRYPTED+invoiceFileName+InvoiceGeneratorConstants.PDF_EXTENSION;

            PdfStamper pdfStamper
                    = new PdfStamper(pdfReader, new FileOutputStream(encryptedInvoiceFile));
            pdfStamper.setEncryption(invoiceFilePassword.getBytes(), invoiceFilePassword.getBytes(),
                    PdfWriter.ALLOW_PRINTING, PdfWriter.ENCRYPTION_AES_128);

            pdfStamper.close();
            pdfReader.close();
        }
        catch(IOException ioException){
            logger.error("Exception occured in encryptInvoice()");
            ioException.printStackTrace();
        }
        catch(DocumentException documentException){
            logger.error("Exception occured in encryptInvoice()");
            documentException.printStackTrace();
        }
        logger.info("encryptInvoice() ended...");
        return InvoiceGeneratorConstants.SUCCESS_MSG;
    }

}
