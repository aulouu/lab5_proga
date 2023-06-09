package ru.itmo.alina.prog.models.forms;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.managers.ScannerManager;
import ru.itmo.alina.prog.models.Location;

import java.util.Scanner;

public class LocationForm extends Forms<Location> {
    private final Print console;
    private final Scanner scanner = ScannerManager.getScanner();

    public LocationForm(Print console) {
        this.console = (ScannerManager.isFileMode())
                ? new EmptyConsole()
                : console;
    }

    @Override
    public Location build() {
        return new Location(askX(), askY(), askName());
    }

    /**
     * Запрашивает координату X
     *
     * @return координата X
     */
    public Integer askX() {
        while (true) {
            console.println("Введите координату X для локации: ");
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException exception) {
                console.printError("X должно быть числом типа integer.");
            } catch (Throwable throwable) {
                console.printError("Непредвиденная ошибка!");
            }
        }
    }

    /**
     * Запрашивает координату Y
     *
     * @return координата Y
     */
    public Float askY() {
        while (true) {
            console.println("Введите координату Y для локации: ");
            String input = scanner.nextLine().trim();
            try {
                if (Float.isNaN(Float.parseFloat(input)) || Float.isInfinite(Float.parseFloat(input))) {
                    console.printError("Координата Y для локации не может быть равна Nan или Infinite.");
                } else {
                    return Float.parseFloat(input);
                }
            } catch (NumberFormatException exception) {
                console.printError("Y должно быть числом типа float.");
            } catch (Throwable throwable) {
                console.printError("Непредвиденная ошибка!");
            }
        }
    }

    /**
     * Запрашивает название локации
     *
     * @return название локации
     */
    public String askName() {
        //while (true) {
        console.println("Введите название локации: ");
        return scanner.nextLine().trim();
        //}
    }
}
