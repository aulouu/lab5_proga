package ru.itmo.alina.prog.models;

/**
 * Варианты позиции
 */

public enum Position {
    HUMAN_RESOURCES,
    ENGINEER,
    HEAD_OF_DIVISION,
    DEVELOPER,
    MANAGER_OF_CLEANING;

    /**
     * @return перечисление в строке всех элементов
     */

    public static String names() {
        StringBuilder list = new StringBuilder();
        for (var forms : values()) {
            list.append(forms.name()).append("\n");
        }
        return list.substring(0, list.length() - 1);
    }
}
