package com.epam.mjc.nio;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;


public class FileReader {

    public Profile getDataFromFile(File file) {
        try {
            Path filePath = file.toPath();
            List<String> lines;
            try (BufferedReader reader = Files.newBufferedReader(filePath)) {
                lines = Files.readAllLines(filePath);
            }
            String name = null;
            Integer age = null;
            String email = null;
            Long phone = null;
            for (String line : lines) {
                String[] parts = line.split(":");
                if (parts.length == 2) {
                    String key = parts[0].trim();
                    String value = parts[1].trim();
                    switch (key) {
                        case "Name":
                            name = value;
                            break;
                        case "Age":
                            age = Integer.parseInt(value);
                            break;
                        case "Email":
                            email = value;
                            break;
                        case "Phone":
                            phone = Long.parseLong(value);
                            break;
                        default:
                            break;
                    }
                }
            }
            return new Profile(name, age, email, phone);
        } catch (IOException e) {
            throw new RuntimeException("Error during reading data from file", e);
        }
    }
}

