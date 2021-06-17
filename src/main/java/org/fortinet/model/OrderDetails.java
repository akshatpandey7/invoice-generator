package org.fortinet.model;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown=true)
public class OrderDetails {

    private String orderId;

    @JsonProperty(value="isPaid")
    private boolean isPaid;

    private List<Item> items;

    private double orderAmount;

    private String modeOfPayment;

}
