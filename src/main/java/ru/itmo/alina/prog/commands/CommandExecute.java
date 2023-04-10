package ru.itmo.alina.prog.commands;

import ru.itmo.alina.prog.exceptions.*;

/**
 * Интерфейс, реализующий Command Pattern
 */

public interface CommandExecute {
    void execute(String args) throws IllegalArgument, CommandRuntime, MustExit;
}
