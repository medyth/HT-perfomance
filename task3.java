package org.example.task3;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Task3 {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Usage: java -cp path/to/gson.jar:. org.example.task3.Task3 <tests.json> <values.json>");
            return;
        }

        String testsJsonFile = args[0];
        String valuesJsonFile = args[1];

        try {
            Gson gson = new Gson();
            TestStructure[] tests = gson.fromJson(new FileReader(testsJsonFile), TestStructure[].class);
            Map<Integer, String> valuesMap = gson.fromJson(new FileReader(valuesJsonFile), HashMap.class);

            for (TestStructure test : tests) {
                String value = valuesMap.get(test.getId());
                if (value != null) {
                    test.setValue(value);
                }
            }

            Gson prettyGson = new GsonBuilder().setPrettyPrinting().create();
            try (FileWriter writer = new FileWriter("report.json")) {
                prettyGson.toJson(tests, writer);
            }

            System.out.println("Report generated successfully in report.json");
        } catch (IOException e) {
            System.out.println("Error while processing files: " + e.getMessage());
        }
    }

    private static class TestStructure {
        private int id;
        private String title;
        private String value;
        private TestStructure[] values;

        public int getId() {
            return id;
        }

        public void setValue(String value) {
            this.value = value;
            if (values != null) {
                for (TestStructure nestedTest : values) {
                    nestedTest.setValue(value);
                }
            }
        }
    }
}
