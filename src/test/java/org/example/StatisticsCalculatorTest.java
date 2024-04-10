package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.entity.Subject;
import org.example.entity.Teacher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatisticsCalculatorTest {

    @Test
    public void testCalculateStatistics() {
        StatisticsCalculator calculator = new StatisticsCalculator();
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("John", "Doe", new Subject("Math"), "Lviv, Kyiv"));
        teachers.add(new Teacher("Alice", "Smith", new Subject("Physics"), "Kyiv"));
        teachers.add(new Teacher("Michael", "Johnson", new Subject("Biology"), "Lviv"));
        String fieldName = "Cities";

        Map<String, Integer> statistics = calculator.calculateStatistics(teachers, fieldName);

        assertNotNull(statistics);
        assertEquals(2, (int) statistics.get("Lviv"));
        assertEquals(2, (int) statistics.get("Kyiv"));
    }

    @Test
    public void testCalculateStatistics_EmptyList() {
        StatisticsCalculator calculator = new StatisticsCalculator();
        List<Teacher> teachers = new ArrayList<>();
        String fieldName = "Cities";

        Map<String, Integer> statistics = calculator.calculateStatistics(teachers, fieldName);

        assertNotNull(statistics);
        assertTrue(statistics.isEmpty());
    }
}
