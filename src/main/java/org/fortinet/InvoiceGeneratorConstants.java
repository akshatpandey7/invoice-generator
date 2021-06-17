package org.fortinet;

/**
 * The type Invoice generator constants.
 */
public class InvoiceGeneratorConstants {

    /**
     * The constant ITEM_LIST_EMPTY_ERROR_MSG.
     */
    public static final String ITEM_LIST_EMPTY_ERROR_MSG = "The item list cannot be empty";

    /**
     * The constant ITEM_QUANTITY_LTE_ERROR_MSG.
     */
    public static final String ITEM_QUANTITY_LTE_ERROR_MSG = "The quantity is less than or equal to zero for:";

    /**
     * The constant ORDER_DETAILS_EMPTY_ERROR_MSG.
     */
    public static final String ORDER_DETAILS_EMPTY_ERROR_MSG = "The order list cannot be empty";

    /**
     * The constant ITEM_COST_EMPTY_ERROR_MSG.
     */
    public static final String ITEM_COST_EMPTY_ERROR_MSG = "The item cost cannot be empty";

    /**
     * The constant ORDER_AMOUNT_INVALID_ERROR_MSG.
     */
    public static final String ORDER_AMOUNT_INVALID_ERROR_MSG = "The order amount does not match sum of item costs";

    /**
     * The constant EMAIL_REGEX.
     */
    public static final String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    /**
     * The constant INVOICE_LOCATION.
     */
    public static final String INVOICE_LOCATION = "C:\\Licensing\\invoice-generator";

    /**
     * The constant PDF_EXTENSION.
     */
    public static final String PDF_EXTENSION = ".pdf";

    /**
     * The constant INVOICE.
     */
    public static final String INVOICE = "Invoice";

    /**
     * The constant INVOICE_ID.
     */
    public static final String INVOICE_ID = "Invoice ID: ";

    /**
     * The constant INVOICE_DATE.
     */
    public static final String INVOICE_DATE = "Invoice Date: ";

    /**
     * The constant INVOICE_DUE_DATE.
     */
    public static final String INVOICE_DUE_DATE = "Invoice Due Date: ";

    /**
     * The constant DESCRIPTION.
     */
    public static final String DESCRIPTION = "Description";

    /**
     * The constant QUANTITY.
     */
    public static final String QUANTITY = "Quantity";

    /**
     * The constant UNIT_PRICE.
     */
    public static final String UNIT_PRICE = "Unit Price";

    /**
     * The constant TOTAL_AMOUNT.
     */
    public static final String TOTAL_AMOUNT = "Total Amount(INR)";

    /**
     * The constant BILL_TO.
     */
    public static final String BILL_TO = "BILL TO";

    /**
     * The constant LOGO_FILE_NAME.
     */
    public static final String LOGO_FILE_NAME = "src/main/resources/Fortinet.png";

    /**
     * The constant ISSUING_COMPANY_DETAILS.
     */
    public static final String ISSUING_COMPANY_DETAILS = "Issuing Company Details:";

    /**
     * The constant TOTAL_COST.
     */
    public static final String TOTAL_COST = "Total Cost: ";

    /**
     * The constant PAYMENT_STATUS.
     */
    public static final String PAYMENT_STATUS = "Payment Status: ";

    /**
     * The constant PAID.
     */
    public static final String PAID = "Paid";

    /**
     * The constant NOT_PAID.
     */
    public static final String NOT_PAID = "Not Paid";

    /**
     * The constant ZERO.
     */
    public static final String ZERO = "0";

    /**
     * The constant BALANCE_DUE.
     */
    public static final String BALANCE_DUE = "Balance Due: ";

    /**
     * The constant COMPANY_DETAILS_EMPTY_ERROR_MSG.
     */
    public static final String COMPANY_DETAILS_EMPTY_ERROR_MSG = "The Company details cannot be empty";

    /**
     * The constant LETTERHEAD_DETAILS_EMPTY_ERROR_MSG.
     */
    public static final String LETTERHEAD_DETAILS_EMPTY_ERROR_MSG = "The LetterHead details cannot be empty";

    /**
     * The constant INVOICE_EMPTY_ERROR_MSG.
     */
    public static final String INVOICE_EMPTY_ERROR_MSG = "The Invoice cannot be empty";

    /**
     * The constant EMAIL_INVALID_ERROR_MSG.
     */
    public static final String EMAIL_INVALID_ERROR_MSG = ":email is not in valid format";

    /**
     * The constant INVOICE_GENERATED_SUCCESSFULLY.
     */
    public static final String INVOICE_GENERATED_SUCCESSFULLY = "Invoice Generated Successfully!";

    /**
     * The constant ENCRYPTED.
     */
    public static final String ENCRYPTED = "encrypted";

    /**
     * The constant ITEM.
     */
    public static final String ITEM = "Item(s)";

    /**
     * The constant SUCCESS_MSG.
     */
    public static final String SUCCESS_MSG = "File created and encrypted successfully!";

}
