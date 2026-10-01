package org.example;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class CsvReader {

    public CsvReader() {

    }

    List<String[]> products = new ArrayList<>();

    public List<String[]> getLines() throws IOException {
        List<String> lines = Files.readAllLines(Path.of("products_100.csv"));

        for (int i = 1; i < lines.size(); i++) {
            String[] parts = lines.get(i).split(",");
            products.add(parts);
        }
        return products;
    }
}
