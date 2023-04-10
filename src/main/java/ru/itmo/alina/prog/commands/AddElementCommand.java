package ru.itmo.alina.prog.commands;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.exceptions.*;
import ru.itmo.alina.prog.managers.*;
import ru.itmo.alina.prog.models.forms.*;

/**
 * Команда, которая добавляет новый элемент в коллекцию
 */

public class AddElementCommand extends Command {
    private Console console;
    private CollectionManager collectionManager;

    public AddElementCommand(Console console, CollectionManager collectionManager) {
        super("Add", " {element} : добавить новый элемент в коллекцию");
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
            collectionManager.addElement(new WorkerForm(console).build());
            console.println("Объект создан.");
        } catch (InvalidForm exception) {
            console.printError("Объект не создан, так как поля объекта невалидны.");
        }
    }
}
