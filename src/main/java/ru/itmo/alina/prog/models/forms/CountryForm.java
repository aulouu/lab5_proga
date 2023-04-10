package ru.itmo.alina.prog.models.forms;

import ru.itmo.alina.prog.console.*;
import ru.itmo.alina.prog.managers.ScannerManager;
import ru.itmo.alina.prog.models.Country;

import java.util.Scanner;

/**
 * Форма для национальности
 */

public class CountryForm extends Forms<Country> {
    private final Print console;
    private final Scanner scanner = ScannerManager.getScanner();

    public CountryForm(Print console) {
        this.console = (ScannerManager.isFileMode())
                ? new EmptyConsole()
                : console;
    }

    @Override
    public Country build() {
        console.println("Варианты национальности: ");
        console.println(Country.names());
        while (true) {
            console.println("Введите национальность: ");
            String input = scanner.nextLine().trim().toUpperCase();
            try {
                return Country.valueOf(input);
            } catch (IllegalArgumentException exception) {
                console.printError("Такая национальность не доступна.");
                if (ScannerManager.isFileMode()) return null;
            } catch (Throwable throwable) {
                console.printError("Непредвиденная ошибка!");
            }
        }
    }
}
