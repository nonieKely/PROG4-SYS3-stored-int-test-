package com.example.demo.endpoint.rest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Random;

@RestController
public class StoredController {

    private static final String path = "/tmp/stored-int.txt";

    @GetMapping("/stored-int")
    public String getStoredInt() throws IOException {
        File file = new File(path);

        if (file.exists()) {
            String content = Files.readString(file.toPath());
            return "Stored number: " + content;
        } else {
            int randomInt = new Random().nextInt(1000);
            try (FileWriter writer = new FileWriter(file)) {
                writer.write(String.valueOf(randomInt));
            }
            return "New number generated: " + randomInt;
        }
    }
}
