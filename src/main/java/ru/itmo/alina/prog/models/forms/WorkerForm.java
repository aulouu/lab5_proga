package ru.itmo.alina.prog.models.forms;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.managers.ScannerManager;
import ru.itmo.alina.prog.models.*;

import java.time.LocalDateTime;
import java.util.Scanner;

public class WorkerForm extends Forms<Worker> {
    private final Print console;
    private final Scanner scanner = ScannerManager.getScanner();

    public WorkerForm(Print console) {
        this.console = (ScannerManager.isFileMode())
                ? new EmptyConsole()
                : console;
    }

    /**
     * Сконструирует объект класса {@link Worker}
     *
     * @return объект {@link Worker}
     */
    @Override
    public Worker build() {
        return new Worker(askName(), askCoordinates(), LocalDateTime.now(), askSalary(), askPosition(), askStatus(), askPerson());
    }

    public String askName() {
        String name;
        while (true) {
            console.println("Введите имя:");
            name = scanner.nextLine().trim();
            if (name.isBlank()) {
                console.printError("Имя не может быть пустым.");
                if (ScannerManager.isFileMode()) return "";
            } else {
                return name;
            }
        }
    }

    public Coordinates askCoordinates() {
        return new CoordinatesForm(console).build();
    }

    public long askSalary() {
        while (true) {
            console.println("Введите зарплату: ");
            String input = scanner.nextLine().trim();
            try {
                if (Long.parseLong(input) <= 0) {
                    console.printError("Зарплата должна быть больше 0.");
                    if (ScannerManager.isFileMode()) return -1;
                } else {
                    return Long.parseLong(input);
                }
            } catch (NumberFormatException exception) {
                console.printError("Зарплата должна быть числом типа long");
            } catch (Throwable throwable) {
                console.printError("Непредвиденная ошибка!");
            }
        }
    }

    public Position askPosition() {
        return new PositionForm(console).build();
    }

    public Status askStatus() {
        return new StatusForm(console).build();
    }

    public Person askPerson() {
        return new PersonForm(console).build();
    }
}
