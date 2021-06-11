package com.example.services;

import javax.ws.rs.*;
import java.io.*;

@Path("/save")
public class RestService {

    static String path = "D:\\java\\projects\\RosAtom\\TestProject\\rest\\src\\main\\resources\\result.txt";

    @POST
    @Path("/saveToDisk")
    @Produces("text/plain")
    public static String savePostType(String jsonForm) {

        if (jsonForm.startsWith("{\n  \"wbsItems\":[")) {
            File file = new File(path);
            if (file.exists())
                file.delete();
        }

        try {
            FileWriter writer = new FileWriter(path, true);
            BufferedWriter bufferWriter = new BufferedWriter(writer);
            bufferWriter.write(jsonForm);
            bufferWriter.close();
            return "ok";

        } catch (IOException e) {
            System.out.println("Saving fail!");
            return e.getMessage();
        }
    }
}