package org.fortinet.validations;

import org.fortinet.InvoiceGeneratorConstants;
import org.fortinet.exception.InvalidQuantityException;
import org.fortinet.exception.ItemCostEmptyException;
import org.fortinet.exception.ItemListEmptyException;
import org.fortinet.model.Invoice;
import org.fortinet.model.Item;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Component
public class ItemValidator {

    public void isItemListEmpty(Invoice invoice) throws ItemListEmptyException {
        try{
            if(CollectionUtils.isEmpty(invoice.getOrderDetails().getItems())){
                throw new ItemListEmptyException(InvoiceGeneratorConstants.ITEM_LIST_EMPTY_ERROR_MSG);
            }
        }
        catch(ItemListEmptyException itemListEmptyException){
            throw itemListEmptyException;
        }
    }

    public void checkItemQuantity(List<Item> itemList) throws InvalidQuantityException {
        try {
            for(Item item : itemList){
                if(item.getItemQuantity() <= 0){
                    throw new InvalidQuantityException(InvoiceGeneratorConstants.ITEM_QUANTITY_LTE_ERROR_MSG + item.getItemName());
                }
            }
        }
        catch (InvalidQuantityException invalidQuantityException){
            throw invalidQuantityException;
        }
    }

    public void isItemCostEmpty(List<Item> itemList) throws ItemCostEmptyException{
        try{
            for (Item item: itemList){
                if(Double.valueOf(item.getItemCost()) == null){
                    throw new ItemCostEmptyException(InvoiceGeneratorConstants.ITEM_COST_EMPTY_ERROR_MSG);
                }
            }
        }
        catch (ItemCostEmptyException itemCostEmptyException){
            throw itemCostEmptyException;
        }
    }
}
