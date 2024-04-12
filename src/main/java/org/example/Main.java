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
import java.util.concurrent.ExecutionException;

public class Main {
    public static final String FOLDER_PATH = "E:\\JavaProjects\\ProfITsoftTask1\\Jsons";
    public static final String RESULT_FILE_FOLDER = "src/main/resources/data/statistics_by_";
    public static final Integer AMOUNT_OF_THREADS = 1;

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String fieldName = "Cities";

        long startTimeParsing = System.currentTimeMillis();
        List<Teacher> teachers = parseJsonFiles();
        long endTimeParsing = System.currentTimeMillis();
        printDuration("Час парсингу JSON файлів", startTimeParsing, endTimeParsing);

        long startTimeCalculation = System.currentTimeMillis();
        Map<String, Integer> statistics = calculateStatistics(teachers, fieldName);
        long endTimeCalculation = System.currentTimeMillis();
        printDuration("Час обчислення статистики", startTimeCalculation, endTimeCalculation);

        long startTimeXmlGeneration = System.currentTimeMillis();
        generateXmlStatistics(statistics, fieldName);
        long endTimeXmlGeneration = System.currentTimeMillis();
        printDuration("Час створення XML файлу", startTimeXmlGeneration, endTimeXmlGeneration);
    }

    private static List<Teacher> parseJsonFiles() throws ExecutionException, InterruptedException {
        JsonParser jsonParser = new JsonParser();
        return jsonParser.parseJsonFiles(getJsonFiles(), AMOUNT_OF_THREADS);
    }

    private static File[] getJsonFiles() {
        File folder = new File(FOLDER_PATH);
        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("The specified folder does not exist or is not a directory.");
            return new File[0];
        }
        return folder.listFiles((dir, name) -> name.endsWith(".json"));
    }

    private static Map<String, Integer> calculateStatistics(List<Teacher> teachers, String fieldName) {
        StatisticsCalculator statisticsCalculator = new StatisticsCalculator();
        return statisticsCalculator.calculateStatistics(teachers, fieldName);
    }

    private static void generateXmlStatistics(Map<String, Integer> statistics, String fieldName) {
        LinkedHashMap<String, Integer> sortedStatistics = new LinkedHashMap<>();
        statistics.entrySet().stream()
                .sorted(Map.Entry.<String, Integer>comparingByValue().reversed())
                .forEachOrdered(entry -> sortedStatistics.put(entry.getKey(), entry.getValue()));

        String filePath = RESULT_FILE_FOLDER + fieldName.toLowerCase() + ".xml";

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

    private static void printDuration(String message, long startTime, long endTime) {
        long duration = endTime - startTime;
        System.out.println(message + ": " + duration + " ms");
    }
}
