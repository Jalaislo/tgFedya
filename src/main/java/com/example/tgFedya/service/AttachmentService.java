package com.example.tgFedya.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
public class AttachmentService {

    private final String storagePath;

    public AttachmentService(@Value("${file.storage.path}") String storagePath) {
        this.storagePath = storagePath;
        createStorageDirectory(); // Создаём папку, если она отсутствует
    }

    /**
     * Создаёт папку для хранения вложений, если она отсутствует.
     */
    private void createStorageDirectory() {
        File directory = new File(storagePath);
        if (!directory.exists()) {
            boolean created = directory.mkdirs(); // Создаём папку и все родительские директории
            if (created) {
                System.out.println("Папка " + storagePath + " успешно создана.");
            } else {
                System.err.println("Не удалось создать папку " + storagePath);
            }
        }
    }

    /**
     * Сохраняет вложение в папку.
     *
     * @param fileData Данные файла.
     * @param fileName Имя файла.
     * @return Путь к сохранённому файлу.
     * @throws IOException Если произошла ошибка при сохранении файла.
     */
    public String saveAttachment(byte[] fileData, String fileName) throws IOException {
        Path path = Paths.get(storagePath, fileName);
        Files.write(path, fileData);
        return path.toString();
    }
}