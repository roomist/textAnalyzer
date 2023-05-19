package textAnalyzer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class TextAnalyzer {
    public static void main(String[] args) {
        String fileName = "theraven.txt";

        try {
            Map<String, Integer> wordFrequencies = analyzeFile(fileName);
            printWordFrequencies(wordFrequencies);
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file: " + e.getMessage());
        }
    }

    private static Map<String, Integer> analyzeFile(String fileName) throws IOException {
        Map<String, Integer> wordFrequencies = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] words = line.split("\\s+");

                for (String word : words) {
                    wordFrequencies.put(word, wordFrequencies.getOrDefault(word, 0) + 1);
                }
            }
        }

        return wordFrequencies;
    }

    private static void printWordFrequencies(Map<String, Integer> wordFrequencies) {
        List<Map.Entry<String, Integer>> entries = new ArrayList<>(wordFrequencies.entrySet());
        entries.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        for (Map.Entry<String, Integer> entry : entries) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
    }
}