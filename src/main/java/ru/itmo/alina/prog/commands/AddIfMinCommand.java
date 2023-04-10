package ru.itmo.alina.prog.commands;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.exceptions.*;
import ru.itmo.alina.prog.managers.*;
import ru.itmo.alina.prog.models.*;
import ru.itmo.alina.prog.models.forms.*;

import java.util.Objects;

/**
 * Команда, которая добавляет новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекции
 */

public class AddIfMinCommand extends Command {
    private Console console;
    private CollectionManager collectionManager;

    public AddIfMinCommand(Console console, CollectionManager collectionManager) {
        super("add_if_min", " {element} : добавить новый элемент в коллекцию, если его значение меньше, чем у наименьшего элемента этой коллекци");
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
        try {
            console.println("Создание нового объекта коллекции.");
            Worker element = new WorkerForm(console).build();
            if (element.compareTo(collectionManager.getCollection().stream()
                    .filter(Objects::nonNull)
                    .min(Worker::compareTo)
                    .orElse(null)) >= 1) {
                collectionManager.addElement(element);
                console.println("Объект добавлен.");
            } else console.println("Элемент больше минимального");
        } catch (InvalidForm exception) {
            console.printError("Объект не создан, так как поля объекта невалидны.");
        }
    }
}
