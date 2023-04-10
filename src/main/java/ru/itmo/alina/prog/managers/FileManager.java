package ru.itmo.alina.prog.managers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.models.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Collection;
import java.util.NoSuchElementException;

/**
 * Файл менеджер - класс для работы с файлом
 */

public class FileManager {
    private final Print console;
    private final String myenv;
    File file;

    private final Gson gson = new GsonBuilder()
            .setPrettyPrinting()
            .serializeNulls()
            .registerTypeAdapter(LocalDateTime.class, new TypeAdapter())
            .create();

    public FileManager(Print console, String file_path) {
        this.console = console;
        this.myenv = file_path;
        try {
            this.file = new File(System.getenv(myenv));
        } catch (NullPointerException exception) {
            console.printError("Системная переменная с загрузочным файлом не найдена! Добавьте её и попробуйте вновь.");
            System.exit(0);
        } catch (Exception exception) {
            console.printError("Что-то пошло не так. Перезапустите программу.");
        }
    }

    /**
     * Обращение к переменным среды и чтение файла
     *
     * @return коллекция
     */
    public Collection<Worker> readCollection() {
        if (System.getenv(myenv) != null) {
            if (file.exists() && !file.canRead()) {
                console.printError("Недостаточно прав для чтения данных из файла. Добавьте права на чтение и запустите программу вновь.");
                System.exit(0);
            }
            try (var fileReader = new InputStreamReader(new FileInputStream(System.getenv(myenv)))) {
                var collectionType = new TypeToken<ArrayDeque<Worker>>() {}.getType();
                var reader = new BufferedReader(fileReader);
                var jsonStr = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    line = line.trim();
                    if (!line.equals("")) {
                        jsonStr.append(line);
                    }
                }

                if (jsonStr.length() == 0) {
                    jsonStr = new StringBuilder("[]");
                }

                ArrayDeque<Worker> collection = gson.fromJson(jsonStr.toString(), collectionType);
                console.println("Коллекция загружена из файла.");
                return collection;

            } catch (FileNotFoundException exception) {
                console.printError("Файл не найден.");
                System.exit(0);
            } catch (NoSuchElementException exception) {
                console.printError("Файл пустой.");
                System.exit(0);
            } catch (JsonParseException exception) {
                console.printError("В файле не найдена необходимая коллекция.");
                System.exit(0);
            } catch (IOException | IllegalStateException exception) {
                console.printError("Непредвиденная ошибка!");
                System.exit(0);
            }
        } else {
            System.out.println("Системная переменная с загрузочным файлом не найдена!");
            System.exit(0);
        }
        return new ArrayDeque<>();
    }
    // $ file_path=aboba java -jar sus.jar (aboba путь до файла)
    /*  set A=b
        echo %A% ---> b (выводит b)  */

    /**
     * Сохранение коллекции из менеджера в файл
     */
    public void saveCollection(Collection<Worker> collection) {
        if (System.getenv(myenv) != null) {
            if (file.exists() && !file.canRead()) {
                console.printError("Недостаточно прав для чтения данных из файла. Добавьте права на чтение и запустите программу вновь.");
            }
            try {
                BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(System.getenv(myenv)));
                bufferedOutputStream.write(gson.toJson(collection).getBytes(StandardCharsets.UTF_8));
                bufferedOutputStream.close();
            } catch (FileNotFoundException exception) {
                console.printError("Файл не существует.");
            } catch (IOException exception) {
                console.printError("Произошла непредвиденная ошибка. Коллекция не сохранена.");
            }
        } else {
            console.printError("Системная переменная с загрузочным файлом не найдена!");
        }
    }
}
