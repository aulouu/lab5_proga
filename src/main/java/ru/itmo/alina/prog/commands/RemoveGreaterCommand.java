package ru.itmo.alina.prog.commands;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.exceptions.*;
import ru.itmo.alina.prog.managers.*;
import ru.itmo.alina.prog.models.*;
import ru.itmo.alina.prog.models.forms.*;

import java.util.Collection;
import java.util.Objects;

/**
 * Команда, которая удаляет из коллекции все элементы, превышающие заданный
 */

public class RemoveGreaterCommand extends Command {
    private Console console;
    private CollectionManager collectionManager;

    public RemoveGreaterCommand(Console console, CollectionManager collectionManager) {
        super("RemoveGreater", " {element} : удалить из коллекции элементы, превышающие заданный");
        this.console = console;
        this.collectionManager = collectionManager;
    }

    /**
     * Исполнение команды
     *
     * @param args аргументы
     * @throws IllegalArgument неверные аргументы команды
     */
    @Override
    public void execute(String args) throws IllegalArgument {
        if (!args.isBlank()) throw new IllegalArgument();
        console.println("Создание нового объекта коллекции.");
        Worker element = new WorkerForm(console).build();
        Collection<Worker> remove = collectionManager.getCollection().stream()
                .filter(Objects::nonNull)
                .filter(worker -> worker.compareTo(element) >= 1)
                .toList();
        collectionManager.removeElements(remove);
        if(remove.isEmpty()) {
            console.println("Нет элементов, превышающие заданный.");
        } else console.println("Элементы, превышающие заданный, удалены.");
    }
}
