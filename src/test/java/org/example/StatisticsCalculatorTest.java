package org.example;

import org.example.calculator.StatisticsCalculator;
import org.example.entity.Subject;
import org.example.entity.Teacher;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class StatisticsCalculatorTest {

    @Test
    public void testCalculateStatisticsForCities() {
        StatisticsCalculator calculator = new StatisticsCalculator();
        List<Teacher> teachers = createTestTeachers();

        Map<String, Integer> statistics = calculator.calculateStatistics(teachers, "Cities");

        assertNotNull(statistics);
        assertEquals(2, (int) statistics.get("Lviv"));
        assertEquals(2, (int) statistics.get("Kyiv"));
    }

    @Test
    public void testCalculateStatisticsForName() {
        StatisticsCalculator calculator = new StatisticsCalculator();
        List<Teacher> teachers = createTestTeachers();

        Map<String, Integer> statistics = calculator.calculateStatistics(teachers, "Name");

        assertNotNull(statistics);
        assertEquals(1, (int) statistics.get("John"));
        assertEquals(1, (int) statistics.get("Alice"));
        assertEquals(1, (int) statistics.get("Michael"));
    }

    @Test
    public void testCalculateStatisticsForSurname() {
        StatisticsCalculator calculator = new StatisticsCalculator();
        List<Teacher> teachers = createTestTeachers();

        Map<String, Integer> statistics = calculator.calculateStatistics(teachers, "Surname");

        assertNotNull(statistics);
        assertEquals(1, (int) statistics.get("Doe"));
        assertEquals(1, (int) statistics.get("Smith"));
        assertEquals(1, (int) statistics.get("Johnson"));
    }

    @Test
    public void testCalculateStatisticsForSubject() {
        StatisticsCalculator calculator = new StatisticsCalculator();
        List<Teacher> teachers = createTestTeachers();

        Map<String, Integer> statistics = calculator.calculateStatistics(teachers, "Subject");

        assertNotNull(statistics);
        assertEquals(1, (int) statistics.get("Math"));
        assertEquals(1, (int) statistics.get("Physics"));
        assertEquals(1, (int) statistics.get("Biology"));
    }

    @Test
    public void testCalculateStatistics_EmptyList() {
        StatisticsCalculator calculator = new StatisticsCalculator();
        List<Teacher> teachers = new ArrayList<>();

        Map<String, Integer> statistics = calculator.calculateStatistics(teachers, "Cities");

        assertNotNull(statistics);
        assertTrue(statistics.isEmpty());
    }

    private List<Teacher> createTestTeachers() {
        List<Teacher> teachers = new ArrayList<>();
        teachers.add(new Teacher("John", "Doe", new Subject("Math"), "Lviv, Kyiv"));
        teachers.add(new Teacher("Alice", "Smith", new Subject("Physics"), "Kyiv"));
        teachers.add(new Teacher("Michael", "Johnson", new Subject("Biology"), "Lviv"));
        return teachers;
    }
}
