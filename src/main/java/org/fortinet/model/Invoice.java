package org.fortinet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Invoice {

    private LetterHead letterHead;

    private Customer customer;

    private OrderDetails orderDetails;

    private String invoiceId;

    private String invoiceDate; // rethink about Date format

    private String dueDate;

}
