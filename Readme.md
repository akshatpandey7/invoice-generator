# Invoice Generator

 

A sample application to generate an Invoice from JSON data to a password protected PDF.

 

## Requirements

 

For building and running the application you need:

 

- [JDK 11](https://www.oracle.com/in/java/technologies/javase-jdk11-downloads.html)
- [Maven 3](https://maven.apache.org)

 

## Running the application locally:
You can use the command line 

 

```shell
java -jar target\invoice-generator-1.0-SNAPSHOT.jar --invoice.details=invoice.json --invoice.password=root123
```

 

The command line arguments consist of the following:

 

- invoice.details = Name of the JSON file in the root directory
- invoice.password = Password with which you want to protect the generated file

 

#### Input Json File Format ####

 


```json
{
    "letterHead":{
        "company":{
            "companyName":"Fortinet Inc.",
            "companyAddress":"Pune",
            "companyEmail": "customersupport@fortinet.com",
            "companyContactNumber": "1234567890"
        }
    },
    "customer":{
        "customerName":"Akshat Pandey",
        "customerAddress":"Doddanekundi, Bangalore",
        "customerEmail": "akshatpandey0@gmail.com",
        "customerContactNumber": "9874563210"
        
    },
    "orderDetails":{
        "orderId":"A12B3C",
        "isPaid": "true",
        "items":[
            {
            "itemId":"A12B3C",
            "itemName":"NGFW",
            "itemDescription": "Next-Generation Firewall",
            "itemQuantity": 2,
            "itemCost": 100000.00
            },
            {
                "itemId":"12AJKSBNWK",
                "itemName":"Secure SD-WAN",
                "itemDescription": "SD-WAN solutions enable digital transformation for organizations by leveraging corporate WAN to a deliver better application experience, reduce costs, and simplify operations at the WAN edge",
                "itemQuantity": 1,
                "itemCost": 90000.00
            }
            

        ],
        "orderAmount":290000.00,
        "modeOfPayment": "Card"
    },
    "invoiceId": "DA123AQF",
    "invoiceDate": "17/6/2021",
    "dueDate": ""
}

 

```
#### Output ####

 

The output will be a password protected PDF file which can be found in the root directory of the project.
