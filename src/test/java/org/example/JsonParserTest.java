package org.example;

import static org.junit.jupiter.api.Assertions.*;

import org.example.entity.Teacher;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.List;

public class JsonParserTest {

    @Test
    public void testParseJsonFiles_Positive() {
        JsonParser jsonParser = new JsonParser();
        File[] testFiles = {new File("positive_test.json")};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles);

        assertNotNull(teachers);
        assertFalse(teachers.isEmpty());
    }

    @Test
    public void testParseJsonFiles_Negative() {
        JsonParser jsonParser = new JsonParser();
        File[] testFiles = {new File("nonexistent_test.json")};

        List<Teacher> teachers = jsonParser.parseJsonFiles(testFiles);

        assertNotNull(teachers);
        assertTrue(teachers.isEmpty());
    }
}
