package org.fortinet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Item {

    private String itemId;

    private String itemName;

    private int itemQuantity;

    private double itemCost;

    private String itemDescription;

}
