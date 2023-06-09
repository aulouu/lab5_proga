package ru.itmo.alina.prog.models.forms;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.managers.ScannerManager;
import ru.itmo.alina.prog.models.Status;

import java.util.Scanner;

public class StatusForm extends Forms<Status> {
    private final Print console;
    private final Scanner scanner = ScannerManager.getScanner();

    public StatusForm(Print console) {
        this.console = (ScannerManager.isFileMode())
                ? new EmptyConsole()
                : console;
    }

    @Override
    public Status build() {
        console.println("Варианты статуса: ");
        console.println(Status.names());
        while (true) {
            console.println("Введите статус: ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Status.valueOf(input);
            } catch (IllegalArgumentException exception) {
                console.printError("Такой статус не доступен.");
                if (ScannerManager.isFileMode()) return null;
            } catch (Throwable throwable) {
                console.printError("Непредвиденная ошибка!");
            }
        }
    }
}
