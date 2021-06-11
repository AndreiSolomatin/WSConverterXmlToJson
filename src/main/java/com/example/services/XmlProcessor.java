package com.example.services;

import org.glassfish.jersey.client.ClientConfig;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.ByteArrayInputStream;

public class XmlProcessor {

    private static String restUri = "http://localhost:8081/rest_war_exploded/rest/save/saveToDisk";
    private static Client Client = ClientBuilder.newClient(new ClientConfig());

    // Преобразование xml-данных в json и отправка их частями в rest-сервис для записи в файл
    public static void process(String xml) throws XMLStreamException, XmlProcessException {

        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        XMLStreamReader reader = xmlInputFactory.createXMLStreamReader(new ByteArrayInputStream(xml.getBytes()));

        StringBuffer buffer = new StringBuffer();
        int eventType;
        int count = 0;

        while (reader.hasNext()) {

            eventType = reader.next();

            if (eventType == XMLEvent.START_ELEMENT) {

                switch (reader.getName().getLocalPart()) {

                    case "Container":
                        send("{\n  \"wbsItems\":[");
                        break;

                    case "IObject":
                        if (count == 1000) {
                            send(buffer.toString());
                            buffer = new StringBuffer();
                            count = 1;
                        }

                        String OBID = reader.getAttributeValue(null, "OBID");
                        String UID = reader.getAttributeValue(null, "UID");
                        String CreationDate = reader.getAttributeValue(null, "CreationDate");
                        String Name = reader.getAttributeValue(null, "Name");
                        String Description = reader.getAttributeValue(null, "Description");

                        buffer.append(count == 0 ? "\n" : ",\n")
                                .append("    { \"obid\": \"").append(OBID).append("\", ")
                                .append("\"uid\": \"").append(UID).append("\", ")
                                .append("\"creationDate\": \"").append(CreationDate).append("\", ")
                                .append("\"name\": \"").append(Name).append("\", ")
                                .append("\"description\": \"").append(Description).append("\" }");

                        count++;
                        break;
                }
            }

            if (eventType == XMLEvent.END_ELEMENT) {
                if (reader.getName().getLocalPart().equals("Container")) {
                    send(buffer.toString());
                    send("\n  ]\n}");
                }
            }
        }
    }

    private static void send(String json) throws XmlProcessException {
        Response response = Client
                .target(restUri)
                .request(MediaType.TEXT_PLAIN)
                .post(Entity.entity(json, MediaType.TEXT_PLAIN));

        if(200 != response.getStatus())
            throw new XmlProcessException("Failed to send json to rest");
    }
}
