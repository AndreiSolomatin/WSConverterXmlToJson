package com.example.services;

import org.glassfish.jersey.client.ClientConfig;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.xml.stream.XMLStreamException;

@WebService
@SOAPBinding(style = Style.RPC)
public class SoapService {

    @WebMethod
    public String SendWBS(String xml) {

        try {
            XmlProcessor.process(xml);
        } catch (XMLStreamException e) {
            System.out.println("XMLStreamException");
            return e.getMessage();
        } catch (XmlProcessException e) {
            System.out.println("XmlProcessException " + e.GetMessage());
            return e.getMessage();
        }

        return "OK";
    }

    //? Можно обойтись без этого метода - wsdl можно скачать напрямую
    @WebMethod
    public String GetInfo() {
        String url = "http://localhost:8081/rest_war_exploded/ws/soap?wsdl";
        Client client = ClientBuilder.newClient(new ClientConfig());
        String wsdl = client
                .target(url)
                .request(MediaType.TEXT_XML).get()
                .readEntity(String.class);
        return wsdl;
    }
}