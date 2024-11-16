package br.com.fiap.techchallange.core.entity.vo;

import java.util.InputMismatchException;

public class Name {
    private String nameValue;

    public Name(String name) {
        this.checkNameValue(name);
    }

    public void checkNameValue(String name) throws InputMismatchException {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nome é inválido!");
        }

        for (char c : name.toCharArray()) {
            if (Character.isDigit(c) || (!Character.isLetter(c) && c != ' ')) {
                throw new IllegalArgumentException("Nome é inválido!");
            }
        }

        this.nameValue = name.trim();
    }

    public String getNameValue() {
        return this.nameValue;
    }
}
