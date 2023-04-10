package ru.itmo.alina.prog.commands;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.exceptions.*;
import ru.itmo.alina.prog.managers.*;

/**
 * Команда, которая очищает коллекцию
 */

public class ClearCommand extends Command {
    private Console console;
    private CollectionManager collectionManager;

    public ClearCommand(Console console, CollectionManager collectionManager) {
        super("clear", " : очистить коллекцию");
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
        collectionManager.clear();
        console.println("Коллекция очищена.");
    }
}
