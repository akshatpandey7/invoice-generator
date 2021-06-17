package org.fortinet.validations;

import org.fortinet.InvoiceGeneratorConstants;
import org.fortinet.exception.OrderAmountInvalidException;
import org.fortinet.exception.OrderDetailsEmptyException;
import org.fortinet.model.Invoice;
import org.fortinet.model.Item;
import org.fortinet.model.OrderDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.List;

@Component
public class OrderDetailsValidator {

    public void isOrderDetailsEmpty(Invoice invoice) throws OrderDetailsEmptyException {
        try{
            if(ObjectUtils.isEmpty(invoice.getOrderDetails())){
                throw new OrderDetailsEmptyException(InvoiceGeneratorConstants.ORDER_DETAILS_EMPTY_ERROR_MSG);
            }
        }

        catch (OrderDetailsEmptyException orderDetailsEmptyException) {
            throw orderDetailsEmptyException;
        }
    }

    public void validateOrderAmount(OrderDetails orderDetails) throws OrderAmountInvalidException {
        try{
            List<Item> itemList = orderDetails.getItems();
            double costOfItems = 0;
            for(Item item: itemList){
                costOfItems += item.getItemCost() * item.getItemQuantity();
            }
            if(orderDetails.getOrderAmount()!=costOfItems){
                throw new OrderAmountInvalidException(InvoiceGeneratorConstants.ORDER_AMOUNT_INVALID_ERROR_MSG);
            }
        }
        catch (OrderAmountInvalidException orderAmountInvalidException){
            throw orderAmountInvalidException;
        }
    }
}
