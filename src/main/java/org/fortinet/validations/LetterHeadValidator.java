package org.fortinet.validations;

import org.fortinet.InvoiceGeneratorConstants;
import org.fortinet.exception.LetterHeadEmptyException;
import org.fortinet.model.Invoice;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class LetterHeadValidator {

    public void validateLetterHead(Invoice invoice) throws LetterHeadEmptyException{
        try {
            if(ObjectUtils.isEmpty(invoice.getLetterHead())){
                throw new LetterHeadEmptyException(InvoiceGeneratorConstants.LETTERHEAD_DETAILS_EMPTY_ERROR_MSG);
            }
        }
        catch (LetterHeadEmptyException letterHeadEmptyException){
            throw letterHeadEmptyException;
        }
    }
}
