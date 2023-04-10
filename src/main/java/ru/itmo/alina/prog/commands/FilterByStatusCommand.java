package ru.itmo.alina.prog.commands;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.exceptions.*;
import ru.itmo.alina.prog.managers.*;
import ru.itmo.alina.prog.models.*;

/**
 * Команда, которая выводит элементы, значение поля status которых равно заданному
 */

public class FilterByStatusCommand extends Command {
    private Console console;
    private CollectionManager collectionManager;

    public FilterByStatusCommand(Console console, CollectionManager collectionManager) {
        super("FilterByStatus", " status : вывести элементы, значение поля status которых равно заданному");
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
        if (args.isBlank()) throw new IllegalArgument();
        try {
            collectionManager.filterByStatus(Status.valueOf(args));
        } catch (IllegalArgumentException exception) {
            console.printError("Такой статус не доступен.");
        }
    }
}
