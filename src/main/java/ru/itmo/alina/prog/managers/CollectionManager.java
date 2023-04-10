package ru.itmo.alina.prog.managers;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.exceptions.*;
import ru.itmo.alina.prog.models.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Менеджер коллекции - класс для работы с коллекцией
 */

public class CollectionManager {
    private ArrayDeque<Worker> collection = new ArrayDeque<>();
    private final Console console;
    private final FileManager fileManager;
    /**
     * Дата создания коллекции
     */
    private LocalDateTime lastInitTime;

    public CollectionManager(Console console, FileManager fileManager) {
        this.console = console;
        this.fileManager = fileManager;
        this.lastInitTime = null;

        loadCollection();
    }

    /**
     * @return коллекция
     */
    public ArrayDeque<Worker> getCollection() {
        return collection;
    }

    /**
     * @return время последней инициализации
     */
    public String getLastInitTime() {
        return lastInitTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    /**
     * @return имя типа коллекции
     */
    public String collectionType() {
        return collection.getClass().getName();
    }

    /**
     * @return размер коллекции
     */
    public int collectionSize() {
        return collection.size();
    }

    /**
     * Очищает коллекцию
     */
    public void clear() {
        this.collection.clear();
    }

    /**
     * Загружает коллекцию из файла
     */
    private void loadCollection() {
        collection = (ArrayDeque<Worker>) fileManager.readCollection();
        lastInitTime = LocalDateTime.now();
    }

    /**
     * Проверка на валидацию в исходном файле
     */
    public void validateAll(Console console) {
        (new ArrayList<>(this.getCollection())).forEach(worker -> {
            if (!worker.validate()) {
                console.printError("Worker с id=" + worker.getId() + " имеет невалидные поля.");
                System.exit(0);
            }
        });
    }

    /**
     * Проверяет, существует ли элемент с таким id
     *
     * @param id id
     */
    public boolean checkId(int id) {
        return collection.stream()
                .anyMatch((x) -> x.getId() == id);
    }

    /**
     * @param id id
     * @return элемент с таким id или null, если элемента с таким id нет
     */
    public Worker getById(int id) {
        for (Worker element : collection) {
            if (element.getId() == id) return element;
        }
        return null;
    }

    /**
     * Изменяет элемент по id
     *
     * @param id         id
     * @param newElement новый элемент
     * @throws InvalidForm несуществующий элемент с таким id
     */
    public void editById(int id, Worker newElement) throws InvalidForm {
        Worker pastElement = this.getById(id);
        this.removeElement(pastElement);
        newElement.setId(id);
        this.addElement(newElement);
        Worker.updateId(this.getCollection());
    }

    /**
     * Удаляет элемент из коллекции
     *
     * @param worker элемент
     */
    public void removeElement(Worker worker) {
        collection.remove(worker);
    }

    /**
     * Удаляет все элементы из коллекции
     *
     * @param collection коллекция
     */
    public void removeElements(Collection<Worker> collection) {
        this.collection.removeAll(collection);
    }

    /**
     * Добавляет элемент в коллекцию
     *
     * @param worker элемент
     * @throws InvalidForm неправильно заданные поля
     */
    public void addElement(Worker worker) throws InvalidForm {
        if (!worker.validate()) throw new InvalidForm();
        collection.add(worker);
    }

    /**
     * Метод для команды FilterByStatus
     *
     * @param status статус
     */
    public void filterByStatus(Status status) {
        boolean hasElement = false;
        for (Worker worker : collection) {
            if (worker.getStatus().equals(status)) {
                console.println(worker.toString());
                hasElement = true;
            }
        }
        if (!hasElement)
            console.println("Нет элемента с таким статусом.");
    }

    /**
     * Метод для команды FilterStartsWithName
     *
     * @param str подстрока
     */
    public void filterStartsWithName(String str) {
        boolean hasElement = false;
        for (Worker worker : collection) {
            if (worker.getName().startsWith(str)) {
                console.println(worker.toString());
                hasElement = true;
            }
        }
        if (!hasElement)
            console.println("Нет элемента, у которого имя начинается с заданной подстроки.");
    }

    /**
     * Метод для команды Head
     *
     * @return первый элемент
     */
    public Worker head() {
        console.println("Первый элемент коллекции: ");
        return collection.peekFirst();
    }
}
