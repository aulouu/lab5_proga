package ru.itmo.alina.prog.commands;

import ru.itmo.alina.prog.exceptions.*;

/**
 * Команда, которая завершает программу без сохранения в файл
 */

public class ExitCommand extends Command {
    public ExitCommand() {
        super("exit", " : завершить программу без сохранения в файл");
    }

    /**
     * Исполнение команды
     *
     * @param args аргументы
     * @throws MustExit обязательный выход из прграммы
     */
    @Override
    public void execute(String args) throws MustExit {
        throw new MustExit();
    }
}
