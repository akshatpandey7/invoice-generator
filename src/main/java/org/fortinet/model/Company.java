package org.fortinet.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class Company {

    private String companyName;

    private String companyAddress;

    private String companyEmail;

    private String companyContactNumber;

}
