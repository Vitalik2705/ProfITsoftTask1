package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.entity.Subject;
import org.example.entity.Teacher;
import org.example.parser.JsonParser;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class JsonParserTest {

    private static String SUCCESSFUL_TEST_PATH;

    private static String UNSUCCESSFUL_TEST_PATH;

    public static Integer AMOUNT_OF_THREADS;

    private static JsonParser jsonParser;

    @BeforeAll
    public static void setUp() {
        jsonParser = new JsonParser();
        SUCCESSFUL_TEST_PATH = "src/main/resources/data/positive_test.json";
        UNSUCCESSFUL_TEST_PATH = "src/main/resources/data/empty.json";
        AMOUNT_OF_THREADS = 1;
    }

    @Test
    public void testParseNameField() throws InterruptedException {
        File[] testFiles = {new File(SUCCESSFUL_TEST_PATH)};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles, AMOUNT_OF_THREADS);

        assertNotNull(teachers);
        assertFalse(teachers.isEmpty());

        Teacher firstTeacher = teachers.get(0);
        assertEquals("John", firstTeacher.getName());
    }

    @Test
    public void testParseSurnameField() throws InterruptedException {
        File[] testFiles = {new File(SUCCESSFUL_TEST_PATH)};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles, AMOUNT_OF_THREADS);

        assertNotNull(teachers);
        assertFalse(teachers.isEmpty());

        Teacher firstTeacher = teachers.get(0);
        assertEquals("Doe", firstTeacher.getSurname());
    }

    @Test
    public void testParseSubjectField() throws InterruptedException {
        File[] testFiles = {new File(SUCCESSFUL_TEST_PATH)};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles, AMOUNT_OF_THREADS);

        assertNotNull(teachers);
        assertFalse(teachers.isEmpty());

        Teacher firstTeacher = teachers.get(0);
        Subject subject = firstTeacher.getSubject();
        assertNotNull(subject);
        assertEquals("Math", subject.getName());
    }

    @Test
    public void testParseCitiesField() throws InterruptedException {
        File[] testFiles = {new File(SUCCESSFUL_TEST_PATH)};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles, AMOUNT_OF_THREADS);

        assertNotNull(teachers);
        assertFalse(teachers.isEmpty());

        Teacher firstTeacher = teachers.get(0);
        assertEquals("Lviv, Kyiv", firstTeacher.getCities());
    }

    @Test
    public void testParseJsonFiles_Negative() throws InterruptedException {
        File[] testFiles = {new File(UNSUCCESSFUL_TEST_PATH)};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles, AMOUNT_OF_THREADS);

        assertNotNull(teachers);
        assertTrue(teachers.isEmpty());
    }
}
