package ru.itmo.alina.prog.models.forms;

import ru.itmo.alina.prog.exceptions.InvalidForm;

/**
 * Абстрактный класс для пользовательских форм ввода
 *
 * @param <T> класс формы
 */

public abstract class Forms<T> {
    public abstract T build() throws InvalidForm;
}
