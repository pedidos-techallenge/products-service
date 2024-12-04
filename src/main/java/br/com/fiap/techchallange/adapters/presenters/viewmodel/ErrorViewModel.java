package br.com.fiap.techchallange.adapters.presenters.viewmodel;

public record ErrorViewModel(Integer code, String message) {
    public String getMessage() {
        return this.message;
    }

    public int getCode() {
        return this.code;
    }
}
