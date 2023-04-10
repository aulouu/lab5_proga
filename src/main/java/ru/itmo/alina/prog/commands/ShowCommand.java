package ru.itmo.alina.prog.commands;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.exceptions.*;
import ru.itmo.alina.prog.managers.*;
import ru.itmo.alina.prog.models.*;

import java.util.Collection;

/**
 * Команда выводит все элементы коллекции в строковом представлении
 */

public class ShowCommand extends Command {
    private Console console;
    private CollectionManager collectionManager;

    public ShowCommand(Console console, CollectionManager collectionManager) {
        super("Show", " : вывести все элементы коллекции в строковом представлении");
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
        Collection<Worker> collection = collectionManager.getCollection();
        if (collection == null || collection.isEmpty()) {
            console.printError("Коллекция еще не инициализирована.");
            return;
        }
        console.println(collection.toString());
    }
}
