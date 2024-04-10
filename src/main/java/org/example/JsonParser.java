package org.example;

import com.google.gson.*;
import org.example.entity.Subject;
import org.example.entity.Teacher;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class JsonParser {
    public List<Teacher> parseJsonFiles(File[] files) {
        List<Teacher> teachers = new ArrayList<>();
        Gson gson = new Gson();

        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());

        for (File file : files) {
            executor.submit(() -> {
                try (FileReader fileReader = new FileReader(file)) {
                    JsonArray jsonArray = gson.fromJson(fileReader, JsonArray.class);
                    for (JsonElement element : jsonArray) {
                        JsonObject jsonObject = element.getAsJsonObject();
                        String name = jsonObject.get("Name").getAsString();
                        String surname = jsonObject.get("Surname").getAsString();
                        String subjectName = jsonObject.get("Subject").getAsString();
                        Subject subject = new Subject(subjectName);
                        String cities = jsonObject.get("Cities").getAsString();

                        Teacher teacher = new Teacher(name, surname, subject, cities);
                        synchronized (teachers) {
                            teachers.add(teacher);
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
        }

        executor.shutdown();
        try {
            executor.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return teachers;
    }
}
