package ru.itmo.alina.prog;

import ru.itmo.alina.prog.commands.*;
import ru.itmo.alina.prog.console.Console;
import ru.itmo.alina.prog.managers.*;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Console console = new Console();
        ScannerManager.setScanner(new Scanner(System.in));
        CommandManager commandManager = new CommandManager();
        RuntimeManager runtimeManager = new RuntimeManager(console, commandManager);
        ExecuteManager executeManager = new ExecuteManager(console, runtimeManager);
        final String myenv = "LABA";
        FileManager fileManager = new FileManager(console, myenv);
        CollectionManager collectionManager = new CollectionManager(console, fileManager);
        collectionManager.validateAll(console);

        commandManager.addCommand(List.of(
                new AddElementCommand(console, collectionManager),
                new AddIfMinCommand(console,collectionManager),
                new ClearCommand(console, collectionManager),
                new ExecuteScriptCommand(console, executeManager),
                new ExitCommand(),
                new FilterByStatusCommand(console, collectionManager),
                new FilterStartsWithNameCommand(console, collectionManager),
                new HeadCommand(console, collectionManager),
                new HelpCommand(console, commandManager),
                new InfoCommand(console, collectionManager),
                new RemoveByIDCommand(console, collectionManager),
                new RemoveGreaterCommand(console, collectionManager),
                new RemoveLowerCommand(console, collectionManager),
                new SaveCommand(console, collectionManager, fileManager),
                new ShowCommand(console, collectionManager),
                new UpdateCommand(console, collectionManager)
        ));
        new RuntimeManager(console, commandManager).runTime();
    }
}