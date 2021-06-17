package org.fortinet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Customer {

    private String customerName;

    private String customerAddress;

    private String customerEmail;

    private String customerContactNumber;


}
