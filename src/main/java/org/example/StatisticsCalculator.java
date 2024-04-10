package org.example;

import org.example.entity.Teacher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticsCalculator {
    public Map<String, Integer> calculateStatistics(List<Teacher> teachers, String fieldName) {
        Map<String, Integer> statistics = new HashMap<>();

        for (Teacher teacher : teachers) {
            String fieldValue = getFieldByName(teacher, fieldName);
            String[] values = fieldValue.split(",\\s*");
            for (String value : values) {
                statistics.put(value, statistics.getOrDefault(value, 0) + 1);
            }
        }

        return statistics;
    }

    private String getFieldByName(Teacher teacher, String fieldName) {
        return switch (fieldName) {
            case "Name" -> teacher.getName();
            case "Surname" -> teacher.getSurname();
            case "Subject" -> teacher.getSubject().getName();
            case "Cities" -> teacher.getCities();
            default -> "";
        };
    }
}

