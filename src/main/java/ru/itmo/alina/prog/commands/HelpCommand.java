package ru.itmo.alina.prog.commands;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.exceptions.*;
import ru.itmo.alina.prog.managers.*;

/**
 * Команда, которая выводит информацию о всех остальных командах
 */

public class HelpCommand extends Command {
    private Console console;
    private CommandManager commandManager;

    public HelpCommand(Console console, CommandManager commandManager) {
        super("Help", " : вывести информацию о всех остальных командах");
        this.console = console;
        this.commandManager = commandManager;
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
        commandManager.getCommands()
                .forEach(command -> console.println(command.getName() + command.getDescription()));
    }
}
