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

    private static JsonParser jsonParser;

    @BeforeAll
    public static void setUp() {
        jsonParser = new JsonParser();
        SUCCESSFUL_TEST_PATH = "src/main/resources/data/positive_test.json";
        UNSUCCESSFUL_TEST_PATH = "src/main/resources/data/empty.json";
    }

    @Test
    public void testParseNameField() {
        File[] testFiles = {new File(SUCCESSFUL_TEST_PATH)};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles);

        assertNotNull(teachers);
        assertFalse(teachers.isEmpty());

        Teacher firstTeacher = teachers.get(0);
        assertEquals("John", firstTeacher.getName());
    }

    @Test
    public void testParseSurnameField() {
        File[] testFiles = {new File(SUCCESSFUL_TEST_PATH)};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles);

        assertNotNull(teachers);
        assertFalse(teachers.isEmpty());

        Teacher firstTeacher = teachers.get(0);
        assertEquals("Doe", firstTeacher.getSurname());
    }

    @Test
    public void testParseSubjectField() {
        File[] testFiles = {new File(SUCCESSFUL_TEST_PATH)};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles);

        assertNotNull(teachers);
        assertFalse(teachers.isEmpty());

        Teacher firstTeacher = teachers.get(0);
        Subject subject = firstTeacher.getSubject();
        assertNotNull(subject);
        assertEquals("Math", subject.getName());
    }

    @Test
    public void testParseCitiesField() {
        File[] testFiles = {new File(SUCCESSFUL_TEST_PATH)};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles);

        assertNotNull(teachers);
        assertFalse(teachers.isEmpty());

        Teacher firstTeacher = teachers.get(0);
        assertEquals("Lviv, Kyiv", firstTeacher.getCities());
    }

    @Test
    public void testParseJsonFiles_Negative() {
        File[] testFiles = {new File(UNSUCCESSFUL_TEST_PATH)};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles);

        assertNotNull(teachers);
        assertTrue(teachers.isEmpty());
    }
}
