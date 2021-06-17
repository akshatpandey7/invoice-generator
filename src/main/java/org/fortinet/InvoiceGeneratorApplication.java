package org.fortinet;

import org.fortinet.model.Invoice;
import org.fortinet.service.InvoiceGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvoiceGeneratorApplication implements ApplicationRunner {

    @Autowired
    private InvoiceGenerator invoiceGenerator;

    private static final Logger logger = LoggerFactory.getLogger(InvoiceGeneratorApplication.class);

    public static void main(String[] args) {
        logger.info("main() started...");
        SpringApplication.run(InvoiceGeneratorApplication.class,args);
        logger.info("main() ended...");
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.info("run() started...");
        try{
            Invoice invoice = invoiceGenerator.mapJsonToObject();
            invoiceGenerator.validateInvoice(invoice);
            invoiceGenerator.generateInvoice(invoice);
            System.exit(0);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        logger.info("run() ended...");
    }
}
