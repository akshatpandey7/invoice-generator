package org.fortinet.validations;

import org.fortinet.InvoiceGeneratorConstants;
import org.fortinet.exception.InvoiceEmptyException;
import org.fortinet.model.Invoice;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class InvoiceValidator {

    public void validateInvoice(Invoice invoice) throws InvoiceEmptyException {
        try{
            if(ObjectUtils.isEmpty(invoice)){
                throw new InvoiceEmptyException(InvoiceGeneratorConstants.INVOICE_EMPTY_ERROR_MSG);
            }
        }
        catch (InvoiceEmptyException invoiceEmptyException){
            throw invoiceEmptyException;
        }
    }
}
