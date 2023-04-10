package ru.itmo.alina.prog.managers;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.exceptions.*;

import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Класс для работы с вводом пользователя
 */

public class RuntimeManager {
    private final Print console;
    private final CommandManager commandManager;

    public RuntimeManager(Console console, CommandManager commandManager) {
        this.console = console;
        this.commandManager = commandManager;
    }

    /**
     * Старт выполнения команд
     *
     * @param userCommand массив из двух элементов, первый - название команды, второй - аргументы команды
     * @throws IllegalArgument неверные аргументы команды
     * @throws NoCommand       такой команды не существует
     * @throws CommandRuntime  ошибка при исполнении команды
     * @throws MustExit        обязательный выход из программы
     */
    public void start(String[] userCommand) throws IllegalArgument, NoCommand, CommandRuntime, MustExit {
        if (userCommand[0].equals("")) return;
        if (userCommand.length < 2)
            commandManager.execute(userCommand[0], "");
        else
            commandManager.execute(userCommand[0], userCommand[1]);
    }

    /**
     * Работа с пользователем и выполнение команд
     */
    public void runTime() {
        Scanner scanner = ScannerManager.getScanner();
        while (true) {
            try {
                if (!scanner.hasNext()) throw new MustExit();
                String userCommand = scanner.nextLine().trim();
                this.start(userCommand.split(" ", 2));
            } catch (NoSuchElementException exception) {
                console.printError("Пользовательский ввод не обнаружен.");
            } catch (IllegalArgument exception) {
                console.printError("Введены неправильные аргументы команды.");
            } catch (NoCommand exception) {
                console.printError("Такой команды не существует.");
            } catch (CommandRuntime exception) {
                console.printError("Ошибка при исполнении команды.");
            } catch (MustExit exception) {
                console.printError("Выход из программы. Bye!");
                return;
            }
        }
    }
}
