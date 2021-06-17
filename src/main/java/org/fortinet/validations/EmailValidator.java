package org.fortinet.validations;

import org.fortinet.InvoiceGeneratorConstants;
import org.fortinet.exception.EmailInvalidException;
import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class EmailValidator {

    public void isValidEmail(String email) throws EmailInvalidException {

        Pattern pattern = Pattern.compile(InvoiceGeneratorConstants.EMAIL_REGEX,Pattern.CASE_INSENSITIVE);

        boolean isValid = pattern.matcher(email).matches();

        try{
            if(!isValid){
                throw new EmailInvalidException(email + InvoiceGeneratorConstants.EMAIL_INVALID_ERROR_MSG);
            }
        }
        catch(EmailInvalidException emailInvalidException){
            throw emailInvalidException;
        }
    }

}
