package org.example;

import org.example.calculator.StatisticsCalculator;
import org.example.entity.Teacher;
import org.example.parser.JsonParser;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        String folderPath = "E:\\JavaProjects\\ProfITsoftTask1\\Jsons";
        String fieldName = "Cities";

        long startTimeParsing = System.currentTimeMillis();
        JsonParser jsonParser = new JsonParser();
        List<Teacher> teachers = jsonParser.parseJsonFiles(getJsonFiles(folderPath));
        long endTimeParsing = System.currentTimeMillis();
        long durationParsing = endTimeParsing - startTimeParsing;
        System.out.println("Час парсингу JSON файлів в " + getJsonFiles(folderPath).length + " потоках: " + durationParsing + " мс");

        long startTimeCalculation = System.currentTimeMillis();
        StatisticsCalculator statisticsCalculator = new StatisticsCalculator();
        Map<String, Integer> statistics = statisticsCalculator.calculateStatistics(teachers, fieldName);
        long endTimeCalculation = System.currentTimeMillis();
        long durationCalculation = endTimeCalculation - startTimeCalculation;
        System.out.println("Час обчислення статистики: " + durationCalculation + " мс");

        long startTimeXmlGeneration = System.currentTimeMillis();
        generateXmlStatistics(statistics, fieldName);
        long endTimeXmlGeneration = System.currentTimeMillis();
        long durationXmlGeneration = endTimeXmlGeneration - startTimeXmlGeneration;
        System.out.println("Час створення XML файлу: " + durationXmlGeneration + " мс");
    }

    private static File[] getJsonFiles(String folderPath) {
        File folder = new File(folderPath);
        if (folder.exists() && folder.isDirectory()) {
            return folder.listFiles((dir, name) -> name.endsWith(".json"));
        } else {
            System.out.println("The specified folder does not exist or is not a directory.");
            return new File[0];
        }
    }

    public static void generateXmlStatistics(Map<String, Integer> statistics, String fieldName) {
        LinkedHashMap<String, Integer> sortedStatistics = new LinkedHashMap<>();

        statistics.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sortedStatistics.put(entry.getKey(), entry.getValue()));

        String filePath = "src/main/resources/data/statistics_by_" + fieldName.toLowerCase() + ".xml";

        try (FileWriter fileWriter = new FileWriter(filePath)) {
            fileWriter.write("<statistics>\n");
            for (Map.Entry<String, Integer> entry : sortedStatistics.entrySet()) {
                fileWriter.write("  <item>\n");
                fileWriter.write("    <value>" + entry.getKey() + "</value>\n");
                fileWriter.write("    <count>" + entry.getValue() + "</count>\n");
                fileWriter.write("  </item>\n");
            }
            fileWriter.write("</statistics>");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
