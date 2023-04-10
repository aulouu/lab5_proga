package ru.itmo.alina.prog.managers;

import ru.itmo.alina.prog.commands.Command;
import ru.itmo.alina.prog.exceptions.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.stream.Collectors;

/**
 * Командный менеджер - класс для управления командами
 */

public class CommandManager {
    private final HashMap<String, Command> commands = new HashMap<>();

    /**
     * Добавляет команду
     *
     * @param commands комманда
     */
    public void addCommand(Collection<Command> commands) {
        this.commands.putAll(commands.stream()
                .collect(Collectors.toMap(Command::getName, с -> с)));
    }

    /**
     * @return коллекция команд
     */
    public Collection<Command> getCommands() {
        return commands.values();
    }

    /**
     * Выполняет команду
     *
     * @param name имя команды
     * @param args аргументы команды
     * @throws IllegalArgument неверные аргументы команды
     * @throws NoCommand       такой команды нет
     * @throws CommandRuntime  ошибка при исполнении команды
     */
    public void execute(String name, String args) throws IllegalArgument, NoCommand, CommandRuntime, MustExit {
        Command command = commands.get(name);
        if (command == null) throw new NoCommand();
        command.execute(args);
    }
}
