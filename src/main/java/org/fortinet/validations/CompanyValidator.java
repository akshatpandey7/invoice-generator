package org.fortinet.validations;

import org.fortinet.InvoiceGeneratorConstants;
import org.fortinet.exception.CompanyDetailsEmptyException;
import org.fortinet.exception.OrderDetailsEmptyException;
import org.fortinet.model.Invoice;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

@Component
public class CompanyValidator {

    public void validateCompanyDetails(Invoice invoice) throws CompanyDetailsEmptyException{

        try{
            if(ObjectUtils.isEmpty(invoice.getLetterHead().getCompany())){
                throw new CompanyDetailsEmptyException(InvoiceGeneratorConstants.COMPANY_DETAILS_EMPTY_ERROR_MSG);
            }
        }

        catch (CompanyDetailsEmptyException companyDetailsEmptyException) {
            throw companyDetailsEmptyException;
        }

    }
}
