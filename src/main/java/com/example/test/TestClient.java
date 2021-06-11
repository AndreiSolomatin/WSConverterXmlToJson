package com.example.test;

import com.sun.xml.ws.client.BindingProviderProperties;
import javax.xml.namespace.QName;
import javax.xml.ws.BindingProvider;
import javax.xml.ws.Service;
import java.net.URL;


public class TestClient {

    public static void main(String[] args) {

        try {
            String xml = XmlGenerator.generateXml();

            Service soapService = Service.create(new URL("http://localhost:8081/rest_war_exploded/ws/soap?wsdl"),
                    new QName("http://services.example.com/", "SoapServiceService"));
            SoapService soap = soapService.getPort(SoapService.class);

            ((BindingProvider) soap).getRequestContext().put(BindingProviderProperties.REQUEST_TIMEOUT, 5 * 60 * 1000);

            String wsdl = soap.getInfo();

            String responseSoap = soap.sendWBS(xml);

        } catch (Exception e) {
            System.out.println("TestClientException" + e.getMessage());
        }
    }
}
