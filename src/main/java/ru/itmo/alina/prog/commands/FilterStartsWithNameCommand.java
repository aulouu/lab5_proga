package ru.itmo.alina.prog.commands;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.exceptions.*;
import ru.itmo.alina.prog.managers.*;

/**
 * Команда, которая выводит элементы, значение поля name которых начинается с заданной подстроки
 */

public class FilterStartsWithNameCommand extends Command {
    private Console console;
    private CollectionManager collectionManager;

    public FilterStartsWithNameCommand(Console console, CollectionManager collectionManager) {
        super("FilterStartsWithName", " {name} : вывести элементы, значение поля name которых начинается с заданной подстроки");
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
        collectionManager.filterStartsWithName(args);
    }
}
