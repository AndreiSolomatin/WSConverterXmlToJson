package com.example.test;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class XmlGenerator {

    // Генерация xml-данных для проверки работы конвертора в среде разработки
    public static String generateXml() {

        StringBuffer sb = new StringBuffer();

        sb.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n");
        sb.append("<Container>\n");

        for (int i = 1; i <= 100; i++) {
            sb.append("<WBSItem>\n")
                    .append("<IWBSItemObj/>\n")
                    .append("<IObject ")
                    .append("OBID=\"OBID").append(i).append("\" ")
                    .append("UID=\"XXXX.YY.AA.BBBB.CCC.DDD").append(i).append("\" ")
                    .append("DomainUID=\"DomainUID\" ")
                    .append("CreationDate=\"2021/03/22-08:20:00:000\" ")
                    .append("TerminationDate=\"9999/12/31-23:59:59:999\" ")
                    .append("LastUpdatedDate=\"2021/03/22-08:20:00:000\" ")
                    .append("Config=\"Config\" ")
                    .append("CreationUser=\"CreationUser\" ")
                    .append("TerminationUser=\"TerminationUser=\" ")
                    .append("UniqueKey=\"XXXX.YY.AA.BBBB.CCC.DDD").append(i).append("\" ")
                    .append("Name=\"XXXX.YY.AA.BBBB.CCC.DDD").append(i).append("\" ")
                    .append("Description=\"Прочие конструкции\" ")
                    .append("ContainerID=\"ContainerID\"")
                    .append("/>\n")
                    .append("</WBSItem>\n");
        }

        sb.append("</Container>");

        String xml = sb.toString();

        //saveGeneratedXmlToFile(xml);

        return xml;
    }

    // Метод для записи сгенерированных xml-данных в файл для проверки в SoapUI
    public static void saveGeneratedXmlToFile(String xml) {

        try {
            FileWriter writer = new FileWriter(
                    "D:\\java\\projects\\RosAtom\\TestProject\\rest\\src\\main\\resources\\dataBig.xml", true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(xml);
            bufferWriter.close();

        } catch (IOException e) {
            System.out.println("Saving xml fail!");
        }
    }
}
